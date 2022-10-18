package com.alpha.redux.renownShop.CookieMonster;

import com.alpha.redux.redux;

import java.util.HashMap;

public class Monster {
    private static redux plugin;

    public Monster(redux instance){
        plugin = instance;
    }

    public static HashMap<String, Integer> MonsterChance = new HashMap<>();

    public static void setMonsterChance(String player, int amount){
        MonsterChance.put(player, amount);
    }

    public static void addMonsterChance(String player, int amount){
        MonsterChance.put(player, MonsterChance.get(player)+amount);
    }

    public static void removeMonsterChance(String player, int amount){
        MonsterChance.put(player, MonsterChance.get(player)-amount);
    }

    public static int getMonsterChance(String player){
        hasMonsterChance(player);
        return MonsterChance.get(player);
    }

    public static HashMap<String, Integer> getMonsterChanceMap(){
        return MonsterChance;
    }

    public static boolean hasMonsterChance(String player){
        if(MonsterChance.containsKey(player)){
            return true;
        }else{
            MonsterChance.put(player, 0);
            return true;
        }
    }

    public static redux getPlugin(){
        return plugin;
    }
}
