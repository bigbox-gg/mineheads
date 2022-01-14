package gg.bigbox.minecraft.plugins.mineheads.minestom.commands;

import gg.bigbox.minecraft.plugins.mineheads.api.MineHeads;
import net.kyori.adventure.text.Component;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.TransactionOption;
import org.jetbrains.annotations.NotNull;

public class MineHeadsSearchMinestomCommand extends Command {

    public MineHeadsSearchMinestomCommand(MineHeads extension) {
        super("search", "s");

        setCondition(this::isAllowed);
        setDefaultExecutor(this::defaultExecutor);

        ArgumentString searchTermArgument = ArgumentType.String("search-term");

        searchTermArgument.setCallback((sender, exception) -> sender.sendMessage(
                Component.text("The search term " + exception.getInput() + " is not valid.")
        ));

        addSyntax((sender, context) -> {
            var heads = extension.findHeadByTerm(context.get(searchTermArgument));

            // Create the inventory for the heads
            Inventory inventory = new Inventory(InventoryType.CHEST_6_ROW, "Heads by " + context.get(searchTermArgument));

            inventory.addItemStacks(
                    heads.stream().limit(45).map(extension::getItemStack).toList(),
                    TransactionOption.ALL
            );

            Player p = (Player) sender;

            p.openInventory(inventory);

        }, searchTermArgument);
    }

    private void defaultExecutor(@NotNull CommandSender sender, @NotNull CommandContext context) {
        sender.sendMessage(
                Component
                        .text("Usage:")
                        .append(Component.newline())
                        .append(Component.text("/mineheads search <search-term> - searches heads matching the term."))
        );
    }

    /**
     * Disable console execution.
     * Only execute if player has permission "mineheads.".
     *
     * @param player      The player that is trying to execute the command.
     * @param commandName The command name
     */
    private boolean isAllowed(CommandSender player, String commandName) {
        //return player instanceof Player && player.hasPermission("mineheads.");

        return player instanceof Player;
    }
}
