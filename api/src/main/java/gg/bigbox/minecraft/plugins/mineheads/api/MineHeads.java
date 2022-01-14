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
     * Tries to find a head with the provided id
     * on the specified provider.
     *
     * @param headId The id to find.
     * @return A head if found, empty optional otherwise.
     */
    @NotNull Optional<Head> findHeadById(String headId, String providerName);

    /**
     * Tries to find a head with the provided name
     * on the specified provider.
     *
     * @param name The name to find.
     * @return A head if found, empty optional otherwise.
     */
    @NotNull Optional<Head> findHeadByName(String name, String providerName);

    /**
     * Tries to find heads that contain
     * the specified search term.
     *
     * @param searchTerm The head search term
     * @return Heads if found, otherwise empty list.
     */
    @NotNull List<Head> findHeadsByTerm(String searchTerm);

    /**
     * Tries to find heads that are of the specified
     * category.
     *
     * @param category The category to find heads for.
     * @return A list of heads of that category.
     */
    @NotNull List<Head> findHeadsByCategory(HeadCategory category);

    /**
     * Tries to find heads that are of the specified
     * category name.
     *
     * @param name The category name to find heads for.
     * @return A list of heads of that category.
     */
    @NotNull List<Head> findHeadsByCategoryName(String name);

    /**
     * Retrieves a list of all the available head categories.
     *
     * @return Retrieves a list of all the available head categories.
     */
    @NotNull List<HeadCategory> getCategories();

    /**
     * Retrieves a list of all the available head categories names.
     *
     * @return Retrieves a list of all the available head categories names.
     */
    @NotNull String[] getCategoriesNames();

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

    @NotNull Optional<Head> getHeadFrom(ItemStack itemStack);

    /**
     * Triggers a data store refresh.
     * NOTE: Definitely expensive and not intended
     * to be abused or called frequently.
     */
    void refresh();

}
