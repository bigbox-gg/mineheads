package gg.bigbox.minecraft.plugins.mineheads.minestom.commands;

import gg.bigbox.minecraft.plugins.mineheads.api.MineHeads;
import net.kyori.adventure.text.Component;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MineHeadsMinestomCommand extends Command {

    private final MineHeads extension;

    public MineHeadsMinestomCommand(MineHeads extension) {
        super("mineheads", "mh");

        this.extension = extension;

        setCondition(this::isAllowed);

        setDefaultExecutor(this::defaultExecutor);
    }

    private void defaultExecutor(@NotNull CommandSender sender, @NotNull CommandContext context) {
        sender.sendMessage(
                Component
                        .text("Usage:")
                        .append(Component.newline())
                        .append(Component.text("/mineheads search <search-term> - searches heads matching the term."))
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
        return player instanceof Player && player.hasPermission("mineheads.");
    }
}
