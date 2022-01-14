package gg.bigbox.minecraft.plugins.mineheads.api;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface MineHeadsHeadProvider {

    /**
     * Retrieves the list of all known heads.
     * NOTE: If the list is empty, the data-store
     * will try to download the list again,
     * resulting in to an expensive computation.
     *
     * @return The list of heads in the data-store.
     */
    @NotNull ArrayList<Head> getHeads();

    /**
     * Retrieves a list of the available categories
     * from the head provider.
     *
     * @return Retrieves a list of the available categories
     */
    @NotNull List<HeadCategory> getCategories();

    /**
     * Force the datastore to refresh.
     * NOTE: Definitely expensive.
     */
    void refresh();

}
