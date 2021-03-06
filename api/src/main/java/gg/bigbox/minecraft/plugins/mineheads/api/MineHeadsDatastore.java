package gg.bigbox.minecraft.plugins.mineheads.api;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface MineHeadsDatastore {

    /**
     * Retrieves a list of all the available heads
     * in the data-store.
     *
     * @return The list of heads in the data-store.
     */
    @NotNull ArrayList<Head> getHeads();

    /**
     * Retrieves a list of all the available categories
     * in the datastore.
     *
     * @return Retrieves a list of all the available categories
     */
    @NotNull List<HeadCategory> getCategories();

    /**
     * Force the datastore to refresh.
     * NOTE: Definitely expensive.
     */
    void refresh();

}
