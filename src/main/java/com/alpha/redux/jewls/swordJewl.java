package com.alpha.redux.jewls;

import com.alpha.redux.apis.chatManager.rank;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import static com.alpha.redux.apis.FancyText.compileListToString;
import static com.alpha.redux.apis.FancyText.hoverText;
import static com.alpha.redux.redux.*;
import static com.alpha.redux.well.enchanters.MysticSword.convertEnchant;
import static com.alpha.redux.well.enchanters.MysticSword.enchantTier;
import static com.alpha.redux.well.swordEnchanter.*;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnSword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class swordJewl {

    private static List<String> renewEnchant(List<String> lore, List<String> enchant){

        List<String> enchants = CheckEnchantOnSword(lore);

        // Bukkit.broadcastMessage(enchants.toString());

        lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(enchantTier(convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

        //hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7 created &cTier I Sword!"), compileListToString(lore));
        //player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);

        lore.addAll(enchant);

        return lore;
    }
    public static ItemStack generateJewelSword(Player player){

        ItemStack item = new ItemStack(Material.GOLD_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&cTier I Sword"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        meta.spigot().setUnbreakable(true);


        Random rand = new Random(); //instance of random class
        int upperbound = 40;
        int int_random = rand.nextInt(upperbound);
        List<String> lore = new ArrayList<>();
        switch (int_random){
            case 0:
            case 1:
            case 2:
            case 3:
                lore = renewEnchant(lore, Arrays.asList(billionaireLore.lore(3).split("\n")));
                hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7 created &cTier I Sword!"), compileListToString(lore));
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
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
                lore = renewEnchant(lore, Arrays.asList(lifestealLore.lore(3).split("\n")));
                break;
            case 16:
                lore = renewEnchant(lore, Arrays.asList(kingBusterLore.lore(3).split("\n")));
                break;
            case 17:
            case 18:
                lore = renewEnchant(lore, Arrays.asList(executionerLore.lore(3).split("\n")));
                hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7 created &cTier I Sword!"), compileListToString(lore));
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
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
                lore = renewEnchant(lore, Arrays.asList(sharkLore.lore(3).split("\n")));
                break;
            case 37:
                lore = renewEnchant(lore, Arrays.asList(sharpLore.lore(3).split("\n")));
                break;
            case 38:
                lore = renewEnchant(lore, Arrays.asList(perunLore.lore(3).split("\n")));
                hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7 created &cTier I Sword!"), compileListToString(lore));
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                break;
            case 39:
                lore = renewEnchant(lore, Arrays.asList(gambleLore.lore(3).split("\n")));
                hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7 created &cTier I Sword!"), compileListToString(lore));
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                break;
        }

        lore.add(ChatColor.BLUE + "+6.5 Attack Damage");

        for(String obj : lore) if(lore.contains(obj)) lore.set(lore.indexOf(obj), ChatColor.translateAlternateColorCodes('&', obj));
        meta.setLore(lore);
        item.setItemMeta(meta);

        hoverText(ChatColor.translateAlternateColorCodes('&', "&3&lJEWEL! " + rank.getNameColor(player) + player.getDisplayName() + ChatColor.GRAY + " obtained: " + ChatColor.RED + "Jewel Enchant!"), compileListToString(lore));
        return item;
    }
}
