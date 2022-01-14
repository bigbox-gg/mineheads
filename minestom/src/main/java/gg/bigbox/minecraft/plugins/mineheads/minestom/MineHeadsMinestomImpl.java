package gg.bigbox.minecraft.plugins.mineheads.minestom;

import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import gg.bigbox.minecraft.plugins.mineheads.api.HeadCategory;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeads;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeadsDatastore;
import gg.bigbox.minecraft.plugins.mineheads.api.events.MineHeadsReadyEvent;
import gg.bigbox.minecraft.plugins.mineheads.minestom.datastores.minecraftheads.MinecraftHeadsProvider;
import lombok.Getter;
import lombok.SneakyThrows;
import net.minestom.server.extensions.Extension;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.metadata.PlayerHeadMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class MineHeadsMinestomImpl extends Extension implements MineHeads {

    @Getter
    private final MineHeadsMinestomConverter converter = new MineHeadsMinestomConverter();

    @Getter
    private final MineHeadsDatastore dataStore = new MinecraftHeadsProvider(
            getEventNode(),
            getDataDirectory()
    );

    @SneakyThrows
    @Override
    public void preInitialize() {
        // Refresh the datastore by default.
        dataStore.refresh();
    }

    @Override
    public void initialize() {
        // Advise everyone that MineHeads is ready
        getEventNode().call(new MineHeadsReadyEvent());
    }


    @Override
    public void terminate() {

    }

    @Override
    public boolean isHead(ItemStack itemStack) {
        return converter.isHead(itemStack);
    }

    @Override
    public @NotNull Optional<Head> findHead(String name) {
        for (int i = 0; i < dataStore.getHeads().size(); i++) {
            if (dataStore.getHeads().get(i).getName().equals(name)) {
                return Optional.of(dataStore.getHeads().get(i));
            }
        }

        return Optional.empty();
    }

    @Override
    public @NotNull List<Head> findHeadByTerm(String searchTerm) {
        return dataStore.getHeads()
                .stream()
                .filter(head -> head.getSearchableBy().contains(searchTerm))
                .toList();
    }

    @Override
    public @NotNull List<Head> findHeadByCategory(HeadCategory category) {
        return dataStore.getHeads()
                .stream()
                .filter(head -> head.getCategoryName().equals(category.getName()))
                .toList();
    }

    @Override
    public @NotNull List<Head> findHeadByCategoryName(String name) {
        return dataStore.getHeads()
                .stream()
                .filter(head -> head.getCategoryName().equals(name))
                .toList();
    }

    @Override
    public @NotNull PlayerHeadMeta getPlayerHead(Head head) {
        return converter.playerHead(head);
    }

    @Override
    public @NotNull ItemStack getItemStack(Head head) {
        return converter.playerItemStack(head);
    }
}
