package gg.bigbox.minecraft.plugins.mineheads.minestom;

import fr.minuskube.inv.InventoryManager;
import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import gg.bigbox.minecraft.plugins.mineheads.api.HeadCategory;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeads;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeadsDatastore;
import gg.bigbox.minecraft.plugins.mineheads.api.events.MineHeadsHeadClickEvent;
import gg.bigbox.minecraft.plugins.mineheads.api.events.MineHeadsReadyEvent;
import gg.bigbox.minecraft.plugins.mineheads.minestom.commands.MineHeadsMinestomCommand;
import gg.bigbox.minecraft.plugins.mineheads.minestom.datastores.MineHeadsDatastoreMinestomImpl;
import lombok.Getter;
import lombok.SneakyThrows;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerUseItemEvent;
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
    private MineHeadsDatastore dataStore;

    @Getter
    private static InventoryManager inventoryManager;

    @SneakyThrows
    @Override
    public void preInitialize() {
        // Create extension directory if non-existent
        if (!getDataDirectory().toFile().exists()) {
            if (!getDataDirectory().toFile().mkdir()) {
                getLogger().warn("Unable to create extension data directory.");
            }
        }

        // Init the datastore
        dataStore = new MineHeadsDatastoreMinestomImpl(
                getEventNode(),
                getDataDirectory()
        );

        // Refresh the datastore by default.
        dataStore.refresh();
    }

    @Override
    public void initialize() {
        MinecraftServer.getCommandManager().register(new MineHeadsMinestomCommand(this));

        EventNode<Event> simpleInventoriesEvents = EventNode.all("smartinvs-listener");

        getEventNode().addChild(simpleInventoriesEvents);

        inventoryManager = new InventoryManager(this);
        inventoryManager.init(simpleInventoriesEvents);

        // Advise everyone that MineHeads is ready
        getEventNode().call(new MineHeadsReadyEvent());

        // Listen for block use
        getEventNode().addListener(PlayerUseItemEvent.class, event -> {
            if (event.isCancelled()) {
                return;
            }

            // Call MineHeadsHeadClickEvent in case this itemstack
            // is a head
            if (isHead(event.getItemStack())) {
                getEventNode().call(new MineHeadsHeadClickEvent(
                        event.getPlayer(),
                        event.getItemStack(),
                        getHeadFrom(event.getItemStack()).get()
                ));
            }
        });
    }


    @Override
    public void terminate() {

    }

    @Override
    public boolean isHead(ItemStack itemStack) {
        return converter.isHead(itemStack);
    }

    @Override
    public @NotNull Optional<Head> findHeadById(String headId, String providerName) {
        return dataStore.getHeads()
                .stream()
                .filter(
                        head -> Utils.stringMatch(head.getId(), headId)
                                && Utils.stringMatch(head.getProviderName(), providerName)
                )
                .findFirst();
    }

    @Override
    public @NotNull Optional<Head> findHeadByName(String name, String providerName) {
        return dataStore.getHeads()
                .stream()
                .filter(
                        head -> Utils.stringMatch(head.getName(), name)
                                && Utils.stringMatch(head.getProviderName(), providerName)
                )
                .findFirst();
    }

    @Override
    public @NotNull List<Head> findHeadsByTerm(String searchTerm) {
        return dataStore.getHeads()
                .stream()
                .filter(head -> Utils.stringMatch(head.getName(), searchTerm)
                        || head.getSearchableBy().stream().anyMatch(s1 -> Utils.stringMatch(s1, searchTerm)))
                .toList();
    }

    @Override
    public @NotNull List<Head> findHeadsByCategory(HeadCategory category) {
        return dataStore.getHeads()
                .stream()
                .filter(head -> Utils.stringMatch(head.getCategoryName(), category.getName()))
                .toList();
    }

    @Override
    public @NotNull List<Head> findHeadsByCategoryName(String name) {
        return dataStore.getHeads()
                .stream()
                .filter(head -> Utils.stringMatch(head.getCategoryName(), name))
                .toList();
    }

    @Override
    public @NotNull List<HeadCategory> getCategories() {
        return dataStore.getCategories();
    }

    @Override
    public @NotNull String[] getCategoriesNames() {
        String[] categories = new String[getCategories().size()];

        for (int i = 0; i < getCategories().size(); i++) {
            categories[i] = getCategories().get(i).getName();
        }

        return categories;
    }

    @Override
    public @NotNull PlayerHeadMeta getPlayerHead(Head head) {
        return converter.playerHead(head);
    }

    @Override
    public @NotNull ItemStack getItemStack(Head head) {
        return converter.playerItemStack(head);
    }

    @Override
    public @NotNull Optional<Head> getHeadFrom(ItemStack itemStack) {
        return converter.headFromItemStack(dataStore.getHeads(), itemStack);
    }

    @Override
    public void refresh() {
        // Do it as an async task
        MinecraftServer.getSchedulerManager()
                .buildTask(dataStore::refresh)
                .schedule();
    }
}
