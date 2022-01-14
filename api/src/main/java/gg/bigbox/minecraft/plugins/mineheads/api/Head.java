package gg.bigbox.minecraft.plugins.mineheads.api;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public interface Head {

    /**
     * @return The unique identifier of this head.
     */
    @NotNull String getId();

    /**
     * @return The associated skull uuid (can be random)
     */
    @NotNull UUID getSkullUUID();

    /**
     * @return The head name
     */
    @NotNull String getName();

    /**
     * @return The head category name
     */
    @NotNull String getCategoryName();

    /**
     * Set the head category name.
     *
     * @param name The category name.
     * @return The head with the category name.
     */
    Head setCategoryName(@NotNull String name);

    /**
     * @return Returns a list of tags associated with the head.
     */
    @NotNull List<String> getSearchableBy();

    /**
     * @return Returns the base64 encoded skin data of the head.
     */
    @NotNull String getSkinData();

    /**
     * @return Gets the head provider name.
     */
    @NotNull String getProviderName();

}
