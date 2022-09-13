package com.alpha.redux.apis.leaderboardsplus;

import com.alpha.redux.playerdata.economy;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;


import java.util.*;

import static com.alpha.redux.apis.chatManager.rank.ChatEventApi;
import static com.alpha.redux.apis.locations.getBotSpawnLocation;
import static com.alpha.redux.apis.locations.getLeaderBoardLocation;
import static com.alpha.redux.playerdata.playerStats.mainGetTop;
import static org.bukkit.Bukkit.*;

public class leaderboards{

    public static HashMap<Integer, ArmorStand> LeaderBoard = new HashMap<>();

    private static Hologram hologram = HologramsAPI.createHologram(economy.getPlugin(), getLeaderBoardLocation());

    public static void TopPlayers(){
        try {
            List<Map.Entry<String, Integer>> topPlayers = mainGetTop();

            String top1 = "null";
            String top2 = "null";
            String top3 = "null";
            String top4 = "null";
            String top5 = "null";
            String top6 = "null";
            String top7 = "null";
            String top8 = "null";
            String top9 = "null";
            String top10 = "null";

            if (topPlayers.get(9).getKey() != null){
                top1 = getName(topPlayers.get(9).getKey());
            }

            if (topPlayers.get(8).getKey() != null){
                top2 = getName(topPlayers.get(8).getKey());
            }

            if (topPlayers.get(7).getKey() != null){
                top3 = getName(topPlayers.get(7).getKey());
            }

            if (topPlayers.get(6).getKey() != null){
                top4 = getName(topPlayers.get(6).getKey());
            }

            if (topPlayers.get(5).getKey() != null){
                top5 = getName(topPlayers.get(5).getKey());
            }

            if (topPlayers.get(4).getKey() != null){
                top6 = getName(topPlayers.get(4).getKey());
            }

            if (topPlayers.get(3).getKey() != null){
                top7 = getName(topPlayers.get(3).getKey());
            }

            if (topPlayers.get(2).getKey() != null){
                top8 = getName(topPlayers.get(2).getKey());
            }

            if (topPlayers.get(1).getKey() != null){
                top9 = getName(topPlayers.get(1).getKey());
            }

            if (topPlayers.get(0).getKey() != null){
                top10 = getName(topPlayers.get(0).getKey());
            }


            //For every player, add their name to gui
            hologram.appendTextLine(ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', "&lTOP ACTIVE PLAYERS"));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "1. " + ChatColor.RESET + ChatEventApi(top1, topPlayers.get(9).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "2. " + ChatEventApi(top2, topPlayers.get(8).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "3. " + ChatEventApi(top3, topPlayers.get(7).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "4. " + ChatEventApi(top4, topPlayers.get(6).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "5. " + ChatEventApi(top5, topPlayers.get(5).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "6. " + ChatEventApi(top6, topPlayers.get(4).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "7. " + ChatEventApi(top7, topPlayers.get(3).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "8. " + ChatEventApi(top8, topPlayers.get(2).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "9. " + ChatEventApi(top9, topPlayers.get(1).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "10. " + ChatEventApi(top10, topPlayers.get(0).getKey()));
            hologram.appendTextLine("");


        }catch (Exception ignored){}
    }

    public static void RefreshBoard(){
        hologram.delete();
        if(hologram.isDeleted()){
            hologram = HologramsAPI.createHologram(economy.getPlugin(), getLeaderBoardLocation());
            TopPlayers();
        }
    }

    public static void delBoard(){
        if(!hologram.isDeleted()){
            hologram.delete();
        }
    }

    public static String getName(String uuid) {
        String url = "https://api.mojang.com/user/profiles/"+uuid.replace("-", "")+"/names";
        try {
            @SuppressWarnings("deprecation")
            String nameJson = IOUtils.toString(new URL(url));
            JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
            String playerSlot = nameValue.get(nameValue.size()-1).toString();
            JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
            return nameObject.get("name").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
