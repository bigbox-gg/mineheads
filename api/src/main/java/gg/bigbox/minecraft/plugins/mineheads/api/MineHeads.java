package gg.bigbox.minecraft.plugins.mineheads.api;

import net.minestom.server.item.ItemStack;
import net.minestom.server.item.metadata.PlayerHeadMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface MineHeads {

    /**
     * Checks if an itemstack is part of MineHeads.
     *
     * @param itemStack The itemstack to check
     * @return True or false.
     */
    boolean isHead(ItemStack itemStack);

    /**
     * Tries to find a head with the provided name.
     * It will search for the exact name.
     *
     * @param name The name to find.
     * @return A head if found, empty optional otherwise.
     */
    @NotNull Optional<Head> findHead(String name);

    /**
     * Tries to find the number of heads that contain
     * the typed search term.
     *
     * @param searchTerm The head search term
     * @return Heads if found, otherwise empty list.
     */
    @NotNull List<Head> findHeadByTerm(String searchTerm);

    /**
     * Retrieves the base PlayerHeadMeta of the
     * specified head.
     *
     * @param head The heat to get the PlayerHeadMeta for.
     * @return The generated PlayerHeadMeta.
     */
    @NotNull PlayerHeadMeta getPlayerHead(Head head);

    /**
     * Retrieves an itemstack with quantity 1
     * of the specified head.
     *
     * @param head The head to get the itemstack for.
     * @return The generated itemstack.
     */
    @NotNull ItemStack getItemStack(Head head);

    /**
     * Retrieves the configured data-store.
     *
     * @return Retrieves the configured data-store.
     */
    @NotNull MineHeadsDatastore getDataStore();

}
