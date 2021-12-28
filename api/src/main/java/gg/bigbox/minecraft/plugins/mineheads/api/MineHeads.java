package gg.bigbox.minecraft.plugins.mineheads.api;

import gg.bigbox.minecraft.plugins.mineheads.api.Models.HeadImpl;

import java.util.List;
import java.util.Optional;

public interface MineHeads {

    /**
     * Tries to find a head with the provided name.
     * It will search for the exact name.
     *
     * @param name The name to find.
     * @return A head if found, empty optional otherwise.
     */
    Optional<HeadImpl> findHead(String name);

    /**
     * Tries to find the number of heads that contain
     * the typed search term.
     *
     * @param searchTerm The head search term
     * @return Heads if found, otherwise empty list.
     */
    List<HeadImpl> findHeadByTerm(String searchTerm);

    /**
     * Force the datastore to refresh.
     * NOTE: Definitely expensive.
     */
    void refreshDatastore();

    MineHeadsConverter<?> getConverter();

}
