package com.alpha.redux.boosters;

import java.util.HashMap;

public class XpBoosterData {

    public static HashMap<String, Integer> xpBooster = new HashMap<>();

    public static void setXpBooster(String player, int amount){
        xpBooster.put(player, amount);
    }

    public static void addXpBooster(String player, int amount){
        xpBooster.put(player, xpBooster.get(player)+amount);
    }

    public static void removeXpBooster(String player, int amount){
        xpBooster.put(player, xpBooster.get(player)-amount);
    }

    public static int getXpBooster(String player){
        hasXpBooster(player);
        return xpBooster.get(player);
    }

    public static HashMap<String, Integer> getXpBoosterMap(){
        return xpBooster;
    }

    public static boolean hasXpBooster(String player){
        if(xpBooster.containsKey(player)){
            return true;
        }else{
            xpBooster.put(player, 0);
            return true;
        }
    }
    
}