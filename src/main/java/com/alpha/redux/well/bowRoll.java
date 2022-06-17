package com.alpha.redux.well;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class bowRoll {
    public static List<String> rollBowEnchants(Player player, boolean fresh, List<String> lore){
        Random rand = new Random(); //instance of random class

        double chanceII = 0.0001;
        double chanceIII = 0.0001;

        if(fresh) {
            lore = new ArrayList<>();
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
        }

        List<String> enchants = new ArrayList<>();

        enchants.add("sprint");

        enchants.add("sprint");
        enchants.add("sprint");
        enchants.add("ftts");
        enchants.add("ftts");
        enchants.add("ftts");
        enchants.add("mlb");
        enchants.add("mlb");
        enchants.add("volley");
        enchants.add("volley");
        enchants.add("pull");
        enchants.add("pull");
        enchants.add("tele");
        enchants.add("ex");
        enchants.add("ex");

        Collections.shuffle(enchants);

        switch (enchants.get(0)){
            case "ftts":
            case "sprint":
            case "volley":
                chanceII = .50;
                chanceIII = .25;
                break;
            case "pull":
            case "mlb":
            case "tele":
            case "ex":
                chanceII = .07;
                chanceIII = .05;
                break;
        }


        return new EnchantingMechanics(lore, enchants.get(0), chanceIII, chanceII, "BOW").getLore();
    }
}
