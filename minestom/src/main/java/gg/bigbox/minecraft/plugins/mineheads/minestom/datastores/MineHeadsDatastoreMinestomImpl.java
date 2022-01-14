package gg.bigbox.minecraft.plugins.mineheads.minestom.datastores;

import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import gg.bigbox.minecraft.plugins.mineheads.api.HeadCategory;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeadsDatastore;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeadsHeadProvider;
import gg.bigbox.minecraft.plugins.mineheads.api.events.MineHeadsDatabaseRefreshedEvent;
import gg.bigbox.minecraft.plugins.mineheads.minestom.providers.minecraftheads.MinecraftHeadsProvider;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MineHeadsDatastoreMinestomImpl implements MineHeadsDatastore {

    private final ArrayList<MineHeadsHeadProvider> headProviders = new ArrayList<>(2);

    private final ArrayList<Head> heads = new ArrayList<>(100000);

    private final List<HeadCategory> categories = new ArrayList<>(100);

    private final EventNode<Event> eventNode;

    public MineHeadsDatastoreMinestomImpl(EventNode<Event> eventNode, Path basePath) {
        this.eventNode = eventNode;

        // Add all the supported providers
        headProviders.add(new MinecraftHeadsProvider(basePath));

        // Add all categories from each provider
        headProviders.forEach(mineHeadsHeadProvider -> categories.addAll(mineHeadsHeadProvider.getCategories()));
    }

    @Override
    public @NotNull ArrayList<Head> getHeads() {
        return heads;
    }

    @Override
    public @NotNull List<HeadCategory> getCategories() {
        return categories;
    }

    @Override
    public void refresh() {
        // Refresh all the data-stores
        headProviders.forEach(MineHeadsHeadProvider::refresh);

        // After all data-stores have been refresh, we can now clear our base cache and get all the heads.
        heads.clear();
        headProviders.forEach(mineHeadsHeadProvider -> heads.addAll(mineHeadsHeadProvider.getHeads()));

        // Advise every listener that the database has been refreshed.
        eventNode.call(new MineHeadsDatabaseRefreshedEvent());
    }
}
