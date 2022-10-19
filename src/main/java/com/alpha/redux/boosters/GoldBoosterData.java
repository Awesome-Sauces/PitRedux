package com.alpha.redux.boosters;

import java.util.HashMap;

public class GoldBoosterData {

    public static HashMap<String, Integer> goldBooster = new HashMap<>();

    public static void setGoldBooster(String player, int amount){
        goldBooster.put(player, amount);
    }

    public static void addGoldBooster(String player, int amount){
        goldBooster.put(player, goldBooster.get(player)+amount);
    }

    public static void removeGoldBooster(String player, int amount){
        goldBooster.put(player, goldBooster.get(player)-amount);
    }

    public static int getGoldBooster(String player){
        hasGoldBooster(player);
        return goldBooster.get(player);
    }

    public static HashMap<String, Integer> getGoldBoosterMap(){
        return goldBooster;
    }

    public static boolean hasGoldBooster(String player){
        if(goldBooster.containsKey(player)){
            return true;
        }else{
            goldBooster.put(player, 0);
            return true;
        }
    }

}
