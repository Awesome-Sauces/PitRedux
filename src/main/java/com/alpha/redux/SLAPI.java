package com.alpha.redux;
import com.alpha.redux.boosters.BotBoosterData;
import com.alpha.redux.boosters.GoldBoosterData;
import com.alpha.redux.boosters.XpBoosterData;
import com.alpha.redux.playerdata.*;
import com.alpha.redux.renownShop.CookieMonster.Monster;
import com.alpha.redux.renownShop.MysticismChance;

import static com.alpha.redux.playerdata.Renown.getRenownMap;
import static com.alpha.redux.playerdata.prestiges.*;
import static com.alpha.redux.playerdata.xpManager.getXpMap;
import static com.alpha.redux.renownShop.MysticismChance.getMysticismChanceMap;

public class SLAPI {

    private static final redux plugin = prestiges.getPlugin();

    public static void savePrestige(){
        for(String p : getPrestigeMap().keySet()){
            plugin.getConfig().set("prestige."+p, prestiges.getPrestigeMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadPrestige(){
        if (!plugin.getConfig().contains("prestige")) return;
        for (String s : plugin.getConfig().getConfigurationSection("prestige").getKeys(false))
        {
            prestiges.setPrestige(s, plugin.getConfig().getInt("prestige."+s));
        }
    }

    public static void saveGoldReq(){
        for(String p : goldReq.getGoldReqMap().keySet()){
            plugin.getConfig().set("goldreq."+p, goldReq.getGoldReqMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadGoldReq(){
        if (!plugin.getConfig().contains("goldreq")) return;
        for (String s : plugin.getConfig().getConfigurationSection("goldreq").getKeys(false))
        {
            goldReq.setGoldReq(s, plugin.getConfig().getInt("goldreq."+s));
        }
    }

    public static void saveMonster(){
        for(String p : Monster.getMonsterChanceMap().keySet()){
            plugin.getConfig().set("monster."+p, Monster.getMonsterChanceMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadMonster(){
        if (!plugin.getConfig().contains("monster")) return;
        for (String s : plugin.getConfig().getConfigurationSection("monster").getKeys(false))
        {
            Monster.setMonsterChance(s, plugin.getConfig().getInt("monster." + s));
        }
    }

    public static void saveXpBooster(){
        for(String p : XpBoosterData.getXpBoosterMap().keySet()){
            plugin.getConfig().set("xpbooster."+p, XpBoosterData.getXpBoosterMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadXpBooster(){
        if (!plugin.getConfig().contains("xpbooster")) return;
        for (String s : plugin.getConfig().getConfigurationSection("xpbooster").getKeys(false))
        {
            XpBoosterData.setXpBooster(s, plugin.getConfig().getInt("xpbooster." + s));
        }
    }

    public static void saveGoldBooster(){
        for(String p : GoldBoosterData.getGoldBoosterMap().keySet()){
            plugin.getConfig().set("goldbooster."+p, GoldBoosterData.getGoldBoosterMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadGoldBooster(){
        if (!plugin.getConfig().contains("goldbooster")) return;
        for (String s : plugin.getConfig().getConfigurationSection("goldbooster").getKeys(false))
        {
            GoldBoosterData.setGoldBooster(s, plugin.getConfig().getInt("goldbooster." + s));
        }
    }

    public static void saveBotBooster(){
        for(String p : BotBoosterData.getBotBoosterMap().keySet()){
            plugin.getConfig().set("botbooster."+p, BotBoosterData.getBotBoosterMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadBotBooster(){
        if (!plugin.getConfig().contains("botbooster")) return;
        for (String s : plugin.getConfig().getConfigurationSection("botbooster").getKeys(false))
        {
            BotBoosterData.setBotBooster(s, plugin.getConfig().getInt("botbooster." + s));
        }
    }

    public static void saveRenown(){
        for(String p : getRenownMap().keySet()){
            plugin.getConfig().set("renown."+p, getRenownMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadRenown(){
        if (!plugin.getConfig().contains("renown")) return;
        for (String s : plugin.getConfig().getConfigurationSection("renown").getKeys(false))
        {
            Renown.setRenown(s, plugin.getConfig().getInt("renown."+s));
        }
    }

    public static void saveMystic(){
        for(String p : getMysticismChanceMap().keySet()){
            plugin.getConfig().set("mystic."+p, MysticismChance.getMysticismChanceMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadMystic(){
        if (!plugin.getConfig().contains("mystic")) return;
        for (String s : plugin.getConfig().getConfigurationSection("mystic").getKeys(false))
        {
            MysticismChance.setMysticismChance(s, plugin.getConfig().getDouble("mystic."+s));
        }
    }

    private static final redux plugin_experience = xpManager.getPlugin();

    public static void saveXp(){
        for(String p : getXpMap().keySet()){
            plugin_experience.getConfig().set("exp."+p, getXpMap().get(p));
        }
        plugin_experience.saveConfig();
    }

    public static void loadXp(){
        if (!plugin_experience.getConfig().contains("exp")) return;
        for (String s : plugin_experience.getConfig().getConfigurationSection("exp").getKeys(false))
        {
            xpManager.setXp(s, plugin_experience.getConfig().getInt("exp."+s));
        }
    }

}