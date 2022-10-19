package com.alpha.redux.boosters;

import java.util.HashMap;

public class BotBoosterData {

    public static HashMap<String, Integer> botBooster = new HashMap<>();

    public static void setBotBooster(String player, int amount){
        botBooster.put(player, amount);
    }

    public static void addBotBooster(String player, int amount){
        botBooster.put(player, botBooster.get(player)+amount);
    }

    public static void removeBotBooster(String player, int amount){
        botBooster.put(player, botBooster.get(player)-amount);
    }

    public static int getBotBooster(String player){
        hasBotBooster(player);
        return botBooster.get(player);
    }

    public static HashMap<String, Integer> getBotBoosterMap(){
        return botBooster;
    }

    public static boolean hasBotBooster(String player){
        if(botBooster.containsKey(player)){
            return true;
        }else{
            botBooster.put(player, 0);
            return true;
        }
    }

}

