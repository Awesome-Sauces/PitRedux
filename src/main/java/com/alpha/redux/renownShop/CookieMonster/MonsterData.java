package com.alpha.redux.renownShop.CookieMonster;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

import static com.alpha.redux.playerdata.Renown.*;

public class MonsterData {

    public static HashMap<NPC, String> MonsterInstance = new HashMap<>();

    public static void addMonsterInstance(NPC npc, Player player){
        MonsterInstance.put(npc, String.valueOf(player.getUniqueId()));
    }

    public static void removeMonsterInstance(NPC npc){
        MonsterInstance.remove(npc);
    }

    public static boolean isOwner(NPC npc, Player player){
        if (MonsterInstance.containsKey(npc)) return MonsterInstance.get(npc).equals(String.valueOf(player.getUniqueId()));
        else return false;
    }

    public static Player getFromNPC(NPC npc){
        if (MonsterInstance.containsKey(npc)) return Bukkit.getPlayer(MonsterInstance.get(npc));
        return null;
    }

    public static double getNPCDamage(){
        return 8.5;
    }

    public static int getRenownRoll(UUID uuid){
        List<Integer> renown = new ArrayList<>();

        renown.add(5);
        renown.add(10);
        renown.add(15);
        renown.add(20);
        renown.add(25);
        renown.add(30);
        renown.add(35);
        renown.add(40);
        renown.add(45);
        renown.add(50);

        Collections.shuffle(renown);

        hasRenown(String.valueOf(uuid));
        addRenown(String.valueOf(uuid), renown.get(0));
        return renown.get(0);
    }

    public static int getRenownRollLoss(UUID uuid){
        List<Integer> renown = new ArrayList<>();

        renown.add(5);
        renown.add(10);
        renown.add(15);

        Collections.shuffle(renown);

        hasRenown(String.valueOf(uuid));
        if(getRenown(String.valueOf(uuid)) >= renown.get(0)){
            setRenown(String.valueOf(uuid), getRenown(String.valueOf(uuid))-renown.get(0));
            return renown.get(0);
        }

        return 0;
    }

}
