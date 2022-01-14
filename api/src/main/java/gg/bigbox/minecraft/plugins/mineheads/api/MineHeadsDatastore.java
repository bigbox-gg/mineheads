package gg.bigbox.minecraft.plugins.mineheads.api;

import java.util.ArrayList;

public interface MineHeadsDatastore {

    /**
     * Retrieves the list of all known heads.
     * NOTE: If the list is empty, the data-store
     * will try to download the list again,
     * resulting in to an expensive computation.
     *
     * @return The list of heads in the data-store.
     */
    ArrayList<Head> getHeads();

    /**
     * Force the datastore to refresh.
     * NOTE: Definitely expensive.
     */
    void refresh();

}
