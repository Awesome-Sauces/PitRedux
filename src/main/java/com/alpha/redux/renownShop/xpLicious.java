package com.alpha.redux.renownShop;

import com.alpha.redux.redux;

import java.util.HashMap;

public class xpLicious {
        private static redux plugin;

        public xpLicious(redux instance){
            plugin = instance;
        }

        public static HashMap<String, Integer> xpliciousIncrease = new HashMap<>();

        public static void setXpliciousIncrease(String player, int amount){
            xpliciousIncrease.put(player, amount);
        }

        public static void addXpliciousIncrease(String player, int amount){
            xpliciousIncrease.put(player, xpliciousIncrease.get(player)+amount);
        }

        public static void removeXpliciousIncrease(String player, int amount){
            xpliciousIncrease.put(player, xpliciousIncrease.get(player)-amount);
        }

        public static int getXpliciousIncrease(String player){
            hasXpliciousIncrease(player);
            return xpliciousIncrease.get(player);
        }

        public static HashMap<String, Integer> getXpliciousIncreaseMap(){
            return xpliciousIncrease;
        }

        public static boolean hasXpliciousIncrease(String player){
            if(xpliciousIncrease.containsKey(player)){
                return true;
            }else{
                xpliciousIncrease.put(player, 0);
                return true;
            }
        }

        public static redux getPlugin(){
            return plugin;
        }
}

