package com.alpha.redux.playerdata;

import com.alpha.redux.redux;

import java.util.HashMap;

public class Renown {
    private static redux plugin;

    public Renown(redux instance){
        plugin = instance;
    }

    public static HashMap<String, Integer> Renown = new HashMap<>();

    public static void setRenown(String player, int amount){
        Renown.put(player, amount);
    }

    public static void addRenown(String player, int amount){
        Renown.put(player, Renown.get(player)+amount);
    }

    public static int getRenown(String player){
        hasRenown(player);
        return Renown.get(player);
    }

    public static HashMap<String, Integer> getRenownMap(){
        return Renown;
    }

    public static boolean hasRenown(String player){
        if(Renown.containsKey(player)){
            return true;
        }else{
            Renown.put(player, 0);
        }
        return true;
    }
}
