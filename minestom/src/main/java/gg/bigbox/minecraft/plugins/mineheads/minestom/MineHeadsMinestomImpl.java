package gg.bigbox.minecraft.plugins.mineheads.minestom;

import gg.bigbox.minecraft.plugins.mineheads.api.MineHeads;
import gg.bigbox.minecraft.plugins.mineheads.api.MinecraftHeads.MinecraftHeadsProvider;
import gg.bigbox.minecraft.plugins.mineheads.api.Models.Head;
import gg.bigbox.minecraft.plugins.mineheads.minestom.Events.MineHeadsReadyMinestomEvent;
import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extensions.Extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class MineHeadsMinestomImpl extends Extension implements MineHeads {

    private ArrayList<Head> data;

    @Getter
    private final MineHeadMinestomConverter converter = new MineHeadMinestomConverter();

    private final MinecraftHeadsProvider mcHeadsProvider = new MinecraftHeadsProvider(
            getDataDirectory(),
            (Logger) getLogger()
    );

    @Override
    public void preInitialize() {
        data = mcHeadsProvider.refreshDatastore();

        // Check if we have data
        if (data.isEmpty()) {
            getLogger().warn("Unable to download all categories from MinecraftHeads. Disabling.");

            MinecraftServer.getExtensionManager().unloadExtension("MineHeads");
        }
    }

    @Override
    public void initialize() {
        // init
        getEventNode().call(new MineHeadsReadyMinestomEvent());
    }


    @Override
    public void terminate() {

    }

    @Override
    public Optional<Head> findHead(String name) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getName().equals(name)) {
                return Optional.of(data.get(i));
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Head> findHeadByTerm(String searchTerm) {
        ArrayList<Head> tempList = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getSearchableBy().contains(searchTerm)) {
                tempList.add(data.get(i));
            }
        }

        return tempList;
    }

    @Override
    public void refreshDatastore() {
        data = mcHeadsProvider.refreshDatastore();
    }
}
