package gg.bigbox.minecraft.plugins.mineheads.minestom.commands;

import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeads;
import gg.bigbox.minecraft.plugins.mineheads.minestom.Utils;
import gg.bigbox.minecraft.plugins.mineheads.minestom.inventories.MineHeadsDisplayHeadsInventory;
import net.kyori.adventure.text.Component;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
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
            MineHeadsDisplayHeadsInventory.INVENTORY(extension, heads).open(p);
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
