package gg.bigbox.minecraft.plugins.mineheads.api;

import net.minestom.server.item.ItemStack;
import net.minestom.server.item.metadata.PlayerHeadMeta;

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
    Optional<Head> findHead(String name);

    /**
     * Tries to find the number of heads that contain
     * the typed search term.
     *
     * @param searchTerm The head search term
     * @return Heads if found, otherwise empty list.
     */
    List<Head> findHeadByTerm(String searchTerm);

    /**
     * @param head
     * @return
     */
    PlayerHeadMeta getPlayerHead(Head head);

    ItemStack getItemStack(Head head);

    /**
     * Retrieves the configured data-store.
     *
     * @return Retrieves the configured data-store.
     */
    MineHeadsDatastore getDataStore();

}
