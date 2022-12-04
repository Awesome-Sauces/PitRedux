package com.alpha.redux.jewls;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.alpha.redux.apis.FancyText.compileListToString;
import static com.alpha.redux.apis.FancyText.hoverText;
import static com.alpha.redux.redux.*;
import static com.alpha.redux.well.enchanterrs.*;
import static com.alpha.redux.well.enchanterrs.Mirror;
import static com.alpha.redux.well.enchanters.FreshPants.convertEnchant;
import static com.alpha.redux.well.enchanters.FreshPants.enchantTier;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnPant;

public class pantJewl {

    private static List<String> renewEnchant(List<String> lore, List<String> enchant){

        List<String> enchants = CheckEnchantOnPant(lore);



        lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(enchantTier(convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

        lore.addAll(enchant);

        return lore;
    }

    public static ItemStack generateJewlPants(Player player){

        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.RED);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&cTier I Red Pants"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.spigot().setUnbreakable(true);


        Random rand = new Random(); //instance of random class
        int upperbound = 41;
        int int_random = rand.nextInt(upperbound);
        List<String> lore = new ArrayList<>();
        switch (int_random){
            case 0:
            case 1:
            case 2:
            case 3:
                lore = renewEnchant(lore, Arrays.asList(goldenHeartLore.lore(3).split("\n")));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                lore = renewEnchant(lore, Arrays.asList(moctezumaLore.lore(3).split("\n")));
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                lore = renewEnchant(lore, Arrays.asList(goldbumpLore.lore(3).split("\n")));
                break;
            case 13:
            case 14:
            case 15:
                lore = renewEnchant(lore, Arrays.asList(protectionLore.lore(3).split("\n")));
                 break;
            case 16:
                lore = renewEnchant(lore, Arrays.asList(criticallyFunkyLore.lore(3).split("\n")));
                 break;
            case 17:
            case 18:
                lore = renewEnchant(lore, Arrays.asList(peroxideLore.lore(3).split("\n")));
                 break;
            case 20:
            case 21:
            case 22:
                lore = renewEnchant(lore, Arrays.asList(sweatyLore.lore(3).split("\n")));
                 break;
            case 23:
            case 24:
            case 25:
            case 26:
                lore = renewEnchant(lore, Arrays.asList(xpboostLore.lore(3).split("\n")));
                break;
            case 27:
            case 28:
            case 29:
                lore = renewEnchant(lore, Arrays.asList(xpbumpLore.lore(3).split("\n")));
                break;
            case 30:
            case 31:
            case 32:
            case 33:
                lore = renewEnchant(lore, Arrays.asList(goldboostLore.lore(3).split("\n")));
                break;
            case 34:
            case 35:
            case 36:
                lore = renewEnchant(lore, Arrays.asList(notGladiatorLore.lore(3).split("\n")));
                break;
            case 37:
                lore = renewEnchant(lore, Arrays.asList(mirrorLore.lore(3).split("\n")));
                break;
            case 38:
                lore = renewEnchant(lore, Arrays.asList(escapePodLore.lore(3).split("\n")));
                hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7created &cTier I Pants!"), compileListToString(lore));
                Sounds.PRESTIGE.play(player);
                break;
            case 39:
                lore = renewEnchant(lore, Arrays.asList(retroGravityMicrocosmLore.lore(3).split("\n")));
                hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7created &cTier I Pants!"), compileListToString(lore));
                Sounds.PRESTIGE.play(player);
                break;
            case 40:
                lore = renewEnchant(lore, Arrays.asList(regularityLore.lore(3).split("\n")));
                hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7created &cTier I Pants!"), compileListToString(lore));
                Sounds.PRESTIGE.play(player);
                break;
            }

        hoverText(ChatColor.translateAlternateColorCodes('&', "&3&lJEWEL! " + rank.getNameColor(player) + player.getDisplayName() + ChatColor.GRAY + " obtained: " + ChatColor.RED + "Jewel Enchant!"), compileListToString(lore));

        lore.add(ChatColor.RED + "As strong as iron");

        for(String obj : lore) if(lore.contains(obj)) lore.set(lore.indexOf(obj), ChatColor.translateAlternateColorCodes('&', obj));

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
