package com.alpha.redux.questMaster.bossBattles;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import net.minecraft.server.v1_8_R3.*;

public class citizensUtils {
    public static void skin(NPC npc, String name) {
        npc.data().setPersistent(name, npc);

        // send skin change to online players by removing and adding this fake player
        if (npc.isSpawned()) {
            Location loc = npc.getStoredLocation();
            npc.despawn();
            npc.spawn(loc);
        }
    }
}
