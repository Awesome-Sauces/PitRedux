package com.alpha.redux.playerdata;

import java.util.HashMap;

public class goldReq {
    public static HashMap<String, Integer> GoldReq = new HashMap<>();



    public static void setGoldReq(String player, int amount){
        GoldReq.put(player, amount);
    }

    public static void addGoldReq(String player, int amount){
        hasGoldReq(player);
        GoldReq.put(player, GoldReq.get(player)+amount);
    }

    public static int getGoldReq(String player){
        return GoldReq.get(player);
    }

    public static HashMap<String, Integer> getGoldReqMap(){
        return GoldReq;
    }

    public static boolean hasGoldReq(String player){
        if(GoldReq.containsKey(player)){
            return true;
        }else{
            GoldReq.put(player, 0);
        }
        return true;
    }
    
    public static int getGoldRequirement(int prestige){

        if(prestige <= 10) return prestige*5000;

        if(prestige <= 20) return prestige*10000;

        if (prestige <= 25) return prestige*15000;

        if (prestige <= 30) return prestige*100000;

        if(prestige <= 35) return prestige*150000;

        if(prestige <= 40) return prestige*200000;

        if(prestige <= 45) return prestige*250000;

        if(prestige <= 50) return prestige*500000;

        if(prestige <= 55) return prestige*600000;

        if(prestige <= 60) return prestige*700000;

        if(prestige <= 65) return prestige*800000;

        if(prestige <= 80) return prestige*850000;

        if(prestige <= 85) return prestige*900000;

        if(prestige <= 94) return prestige*950000;

        if(prestige <= 100) return prestige*1000000;


        return 1;
    }
}
