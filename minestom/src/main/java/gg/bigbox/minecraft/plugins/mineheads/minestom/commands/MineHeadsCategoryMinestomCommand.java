package gg.bigbox.minecraft.plugins.mineheads.minestom.commands;

import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeads;
import gg.bigbox.minecraft.plugins.mineheads.minestom.Utils;
import net.kyori.adventure.text.Component;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.TransactionOption;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MineHeadsCategoryMinestomCommand extends Command {
    public MineHeadsCategoryMinestomCommand(MineHeads extension) {
        super("category", "ct");

        setCondition(this::isAllowed);
        setDefaultExecutor(this::defaultExecutor);

        var categoriesArgument = ArgumentType.Word("categories").from(
                extension.getCategoriesNames()
        );

        categoriesArgument.setCallback((sender, exception) -> sender.sendMessage(
                Utils.sendErrorMessage(Component.text("The category " + exception.getInput() + " is not valid."))
        ));

        addSyntax((sender, context) -> {
            Player p = (Player) sender;

            List<Head> heads = extension.findHeadsByCategoryName(context.get(categoriesArgument));

            if (heads.isEmpty()) {
                p.sendMessage(Utils.sendErrorMessage(Component.text("No heads found.")));

                return;
            }

            // Create the inventory for the heads
            Inventory inventory = new Inventory(InventoryType.CHEST_6_ROW, "Heads by " + context.get(categoriesArgument));

            inventory.addItemStacks(
                    heads.stream().limit(45).map(extension::getItemStack).toList(),
                    TransactionOption.ALL
            );

            p.openInventory(inventory);
        }, categoriesArgument);
    }

    private void defaultExecutor(@NotNull CommandSender sender, @NotNull CommandContext context) {
        sender.sendMessage(
                Component
                        .text("Usage:")
                        .append(Component.newline())
                        .append(Component.text("/mineheads category <category> - all heads of the specified category."))
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
