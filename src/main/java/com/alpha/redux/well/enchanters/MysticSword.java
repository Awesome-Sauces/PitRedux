package com.alpha.redux.well.enchanters;

import com.alpha.redux.entityHandlers.MysticHandler.Swords.Billionaire;
import com.alpha.redux.events.boards;
import com.alpha.redux.redux;
import com.alpha.redux.well.EnchantingMechanics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.events.boards.integerToRoman;
import static com.alpha.redux.playerdata.economy.getEconomy;
import static com.alpha.redux.playerdata.economy.removeEconomy;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnSword;
import static com.alpha.redux.well.mysticWell.*;
import static com.alpha.redux.well.swordLore.TierISword;

public class MysticSword {

    private static boolean removeGold(Player player, String uuid, int amount){
        if(getEconomy(uuid) >= amount){
            removeEconomy(uuid, amount);
            boards.CreateScore(player);
            return true;
        }else{
            player.sendMessage(colorCode("&l&cERROR! &7You need &6" + (amount-getEconomy(uuid)) + "g &7to afford this!"));
            return false;
        }
    }

    public static void clickSword(InventoryClickEvent event){
        event.setCancelled(true);

        String uuid = String.valueOf(event.getWhoClicked().getUniqueId());
        Player player = (Player) event.getWhoClicked();
        ItemStack items = event.getClickedInventory().getItem(20);

        if (items.getItemMeta().getDisplayName().contains("Tier III")){
            player.sendMessage(ChatColor.RED + "This sword is already max tier!");
            return;
        } else if (items.getItemMeta().getDisplayName().contains("Tier II") && removeGold(player, uuid, 8000)) {
            event.getClickedInventory().setItem(20, createSword(player,3, event.getClickedInventory().getItem(20)));
        }else if (items.getItemMeta().getDisplayName().contains("Tier I") && removeGold(player, uuid, 4000)) {
            event.getClickedInventory().setItem(20, createSword(player,2, event.getClickedInventory().getItem(20)));
        } else if (items.getItemMeta().getDisplayName().contains("Mystic Sword") && removeGold(player, uuid, 1000)) {
            event.getClickedInventory().setItem(20, createSword(player,1, null));
        }


    }

    public static ItemStack createSword(Player player, int tier, ItemStack sword){
        if (sword == null){
            ItemStack item = new ItemStack(Material.GOLD_SWORD, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + integerToRoman(tier) + " Sword"));
            item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            meta.spigot().setUnbreakable(true);
            meta.setLore(enchantSword(player, sword, tier));
            item.setItemMeta(meta);
            //Shaped Recipe

            return item;
        }else{
            ItemMeta meta = sword.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + integerToRoman(tier) + " Sword"));
            sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            meta.spigot().setUnbreakable(true);
            //enchantSword(player, sword, tier)
            meta.setLore(enchantSword(player, sword, tier));
            sword.setItemMeta(meta);
            //Shaped Recipe

            return sword;
        }
    }

    public static List<String> enchantSword(Player player, ItemStack sword, int tier) {

        double chanceII = 0.0001;
        double chanceIII = 0.0001;

        List<String> enchants = new ArrayList<>();

        try{

            if (sword != null){enchants = CheckEnchantOnSword(sword.getItemMeta().getLore());}

        }catch (Exception ignored){}

       // Bukkit.broadcastMessage(enchants.toString());

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        lore.addAll(enchants);

        String ench;
        int tokens = 0;
        boolean looping = true;

        while (looping){
            ench = getEnchant();

            Bukkit.broadcastMessage(ench);

            for (String str : enchants){
                tokens += str.length() - str.replaceAll("I", "").length();
            }

            double d = Math.random();

            float tier1 = ((float) ((tokens / 1) * 10) / 100);
            float tier2 = ((float) ((tokens / 2) * 10) / 100);
            float tier3 = ((float) ((tokens / 3) * 10) / 100);

            tokens = 0;

            if (d <= tier1){

                lore.addAll(Arrays.asList(enchantTier(ench, 1).split("\n")));

                looping = false;
            }else if (d <= tier2){

                lore.addAll(Arrays.asList(enchantTier(ench, 2).split("\n")));

                looping = false;
            }else if (d <= tier3){

                lore.addAll(Arrays.asList(enchantTier(ench, 3).split("\n")));

                looping = false;
            }else{

                lore.addAll(Arrays.asList(enchantTier(ench, 1).split("\n")));

                looping = false;
            }
        }

        /*
        T = sword tokens

                E = enchant tokens

                C = chance

        Equation:

        > c = ((T/E)*10) / 100

         */


        return lore;

       // return new EnchantingMechanics(lore, enchants.get(0), chanceIII, chanceII, "SWORD").getLore();

    }

    public static String enchantTier(String enchant, int tier){
        if (Objects.equals(enchant, "billionaire")) {
            return redux.billionaireLore.lore(tier);
        }else if (Objects.equals(enchant, "perun")) {
            return redux.perunLore.lore(tier);
        }else if (Objects.equals(enchant, "executioner")) {
            return redux.executionerLore.lore(tier);
        }else if (Objects.equals(enchant, "gamble")) {
            return redux.gambleLore.lore(tier);
        }else if (Objects.equals(enchant, "painfocus")) {
            return redux.painFocusLore.lore(tier);
        }else if (Objects.equals(enchant, "lifesteal")) {
            return redux.lifestealLore.lore(tier);
        }else if (Objects.equals(enchant, "sharp")) {
            return redux.sharpLore.lore(tier);
        }else if (Objects.equals(enchant, "shark")) {
            return redux.sharkLore.lore(tier);
        }else if (Objects.equals(enchant, "diamondstomp")) {
            return redux.diamondStompLore.lore(tier);
        }else if (Objects.equals(enchant, "kingbuster")) {
            return redux.kingBusterLore.lore(tier);
        }else{
            return "ERROR";
        }
    }

    public static String getEnchant(){
        while (true) {
            double d = Math.random();
            if (d <= 0.01){
                // Billionaire
                // 1% chance of being here
                return "billionaire";
            } else if (d <= 0.02){
                // Perun
                // 2% chance of being here
                return "perun";
            }else if (d <= 0.0250){
                // Executioner
                // 2.5% chance of being here
                return "executioner";
            }else if (d <= 0.0325){
                // Gamble
                // 3.25% chance of being here
                return "gamble";
            }else if (d <= 0.05){
                // Xp Boost
                // 5% chance of being here
                return "xpboost";
            }else if (d <= 0.0525){
                // Pain Focus
                // 5.25% chance of being here
                return "painfocus";
            }else if (d <= 0.0625){
                // Lifesteal
                // 6.25% chance of being here
                return "lifesteal";
            }else if (d <= 0.0650){
                // Gold Boost
                // 6.5% chance of being here
                return "goldboost";
            }else if (d <= 0.0675){
                // Sharp
                // 6.75% chance of being here
                return "sharp";
            }else if (d <= 0.0725){
                // Shark
                // 7.25% chance of being here
                return "shark";
            }else if (d <= 0.0750){
                // Xp Bump
                // 7.50% chance of being here
                return "xpbump";
            }else if (d <= 0.0775){
                // Gold Bump
                // 7.75% chance of being here
                return "goldbump";
            }else if (d <= 0.0825){
                // Diamond Stomp
                // 8.25% chance of being here
                return "diamondstomp";
            }else if (d <= 0.0925){
                // Sweaty
                // 9.25% chance of being here
                return "sweaty";
            }else if (d <= 0.1025){
                // Moctezuma
                // 10.25% chance of being here
                return "moctezuma";
            }else if (d <= 0.1125){
                // King Buster
                // 11.25% chance of being here
                return "kingbuster";
            }
        }
    }


}
