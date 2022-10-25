package com.alpha.redux.renownShop;

import java.util.HashMap;

public class GoldnBoosted {

    public static HashMap<String, Integer> goldIncrease = new HashMap<>();

    public static void setGoldIncrease(String player, int amount){
        goldIncrease.put(player, amount);
    }

    public static void addGoldIncrease(String player, int amount){
        goldIncrease.put(player, goldIncrease.get(player)+amount);
    }

    public static void removeGoldIncrease(String player, int amount){
        goldIncrease.put(player, goldIncrease.get(player)-amount);
    }

    public static int getGoldIncrease(String player){
        hasGoldIncrease(player);
        return goldIncrease.get(player);
    }

    public static HashMap<String, Integer> getGoldIncreaseMap(){
        return goldIncrease;
    }

    public static boolean hasGoldIncrease(String player){
        if(goldIncrease.containsKey(player)){
            return true;
        }else{
            goldIncrease.put(player, 0);
            return true;
        }
    }
    
}

