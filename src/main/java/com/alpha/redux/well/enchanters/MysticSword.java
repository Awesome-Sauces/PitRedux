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
        if(getEconomy(uuid) > amount-1){
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
        }else if (!items.getItemMeta().getDisplayName().contains("Tier II") &&
                items.getItemMeta().getDisplayName().contains("Tier I") && removeGold(player, uuid, 4000)) {
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

    public static List<String> translateList(List<String> lore){
        for(String text : lore){
            lore.set(lore.indexOf(text), colorCode(text));
        }

        return lore;
    }

    public static Boolean percentChance(double chance) {
        return Math.random() <= chance;
    }

    public static List<String> enchantSword(Player player, ItemStack sword, int tier) {

        double chanceII = 0.0001;
        double chanceIII = 0.0001;

        List<String> enchants = new ArrayList<>();

        try{

            if (sword != null){enchants = CheckEnchantOnSword(sword.getItemMeta().getLore());}

        }catch (Exception ignored){}



        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(enchantTier(convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

     //   lore.addAll(enchants);

        String ench;
        int tokens = 0;
        boolean looping = true;

        int loopnum = 0;

        while (looping){
            loopnum++;
            ench = getEnchant(enchants);


            for (String str : enchants){
                tokens += str.length() - str.replaceAll("I", "").length();
            }

            double d = Math.random();

            if (tokens <= 0){
                tokens = 1;
            }

            float tier1 = ((float) ((tokens) * 35) / 100);
            float tier2 = ((float) ((tokens) * 34) / 100);
            float tier3 = ((float) ((tokens) * 33) / 100);

            tokens = 0;


            if (percentChance(tier1)){

                if (lore.contains(colorCode(getEnchantTitle(ench, 3)))){
                    continue;
                }else if (lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    //lore.remove(" ");
                    lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 1).split("\n"))));
                    lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 2).split("\n"))));
                    looping = false;
                }else if (lore.contains(colorCode(getEnchantTitle(ench, 2)))){
                    //lore.remove(" ");
                    lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 2).split("\n"))));
                    lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    looping = false;
                }else if (!lore.contains(colorCode(getEnchantTitle(ench, 3))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 2))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    lore.addAll(Arrays.asList(enchantTier(ench, 1).split("\n")));
                    looping = false;
                }

            }else if (percentChance(tier2)){

                if (lore.contains(colorCode(getEnchantTitle(ench, 3)))){
                    continue;
                }else if (lore.contains(colorCode(getEnchantTitle(ench, 2)))){
                    //lore.remove(" ");
                    lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 2).split("\n"))));
                    lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    looping = false;
                }else if (lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    //lore.remove(" ");
                    lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 1).split("\n"))));
                    lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    looping = false;
                }else if(!lore.contains(colorCode(getEnchantTitle(ench, 3))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 2))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    lore.addAll(Arrays.asList(enchantTier(ench, 2).split("\n")));
                    looping = false;
                }

            }else if (percentChance(tier3)){

                if (!lore.contains(colorCode(getEnchantTitle(ench, 3))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 2))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    lore.addAll(Arrays.asList(enchantTier(ench, 3).split("\n")));
                    looping = false;
                }else if(lore.contains(colorCode(getEnchantTitle(ench, 2))) || lore.contains(colorCode(getEnchantTitle(ench, 1)))){

                    if (lore.contains(colorCode(getEnchantTitle(ench, 2)))){
                        lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 2).split("\n"))));
                        lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    }else if (lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                        lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 1).split("\n"))));
                        lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    }

                    looping = false;
                }


            }/*else{

                lore.addAll(Arrays.asList(enchantTier(ench, 1).split("\n")));

                looping = false;
            }
            */
        }

        /*
        T = sword tokens

                E = enchant tokens

                C = chance

        Equation:

        > c = ((T/E)*10) / 100

         */

        lore.add(ChatColor.BLUE + "+6.5 Attack Damage");



        return lore;

       // return new EnchantingMechanics(lore, enchants.get(0), chanceIII, chanceII, "SWORD").getLore();

    }

    public static List<String> renewEnchant(List<String> lore, List<String> enchant){

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

    public static String getEnchantTitle(String enchant, int tier){
        if (Objects.equals(enchant, "billionaire")) {
            return colorCode(redux.billionaireLore.title(tier));
        }else if (Objects.equals(enchant, "perun")) {
            return colorCode(redux.perunLore.title(tier));
        }else if (Objects.equals(enchant, "executioner")) {
            return colorCode(redux.executionerLore.title(tier));
        }else if (Objects.equals(enchant, "gamble")) {
            return colorCode(redux.gambleLore.title(tier));
        }else if (Objects.equals(enchant, "painfocus")) {
            return colorCode(redux.painFocusLore.title(tier));
        }else if (Objects.equals(enchant, "lifesteal")) {
            return colorCode(redux.lifestealLore.title(tier));
        }else if (Objects.equals(enchant, "sharp")) {
            return colorCode(redux.sharpLore.title(tier));
        }else if (Objects.equals(enchant, "shark")) {
            return colorCode(redux.sharkLore.title(tier));
        }else if (Objects.equals(enchant, "diamondstomp")) {
            return colorCode(redux.diamondStompLore.title(tier));
        }else if (Objects.equals(enchant, "combodamage")) {
            return colorCode(redux.combodamageLore.title(tier));
        }else if (Objects.equals(enchant, "kingbuster")) {
            return colorCode(redux.kingBusterLore.title(tier));
        }else if (Objects.equals(enchant, "moctezuma")){
            return colorCode(redux.moctezumaLore.title(tier));
        }else if (Objects.equals(enchant, "goldbump")){
            return colorCode(redux.goldbumpLore.title(tier));
        }else if (Objects.equals(enchant, "goldboost")){
            return colorCode(redux.goldboostLore.title(tier));
        }else if (Objects.equals(enchant, "pantsradar")){
            return colorCode(redux.pantsRadarLore.title(tier));
        }else if (Objects.equals(enchant, "sweaty")){
            return colorCode(redux.sweatyLore.title(tier));
        }else if (Objects.equals(enchant, "xpbump")){
            return colorCode(redux.xpbumpLore.title(tier));
        }else if (Objects.equals(enchant, "xpboost")){
            return colorCode(redux.xpboostLore.title(tier));
        }else{
            return "ERROR";
        }
    }

    public static String convertEnchant(String enchant) {
        if (Objects.equals(enchant, "bill")){
            return "billionaire";
        }else if (Objects.equals(enchant, "pf")){
            return "painfocus";
        }else if (Objects.equals(enchant, "ls")){
            return "lifesteal";
        }else if (Objects.equals(enchant, "diamond")){
            return "diamondstomp";
        }else if(Objects.equals(enchant, "pantsradar")){
            return "pantsradar";
        }else if(Objects.equals(enchant, "combodamage")){
            return "combodamage";
        }else if (Objects.equals(enchant, "gamb")){
            return "gamble";
        }else if (Objects.equals(enchant, "king")){
            return "kingbuster";
        }else if (Objects.equals(enchant, "exe")){
            return "executioner";
        }else if (Objects.equals(enchant, "moct")){
            return "moctezuma";
        }else if (Objects.equals(enchant, "goldBump")){
            return "goldbump";
        }else if (Objects.equals(enchant, "xp")){
            return "xpboost";
        }else if (Objects.equals(enchant, "xpb")){
            return "xpbump";
        }else if (Objects.equals(enchant, "gb")){
            return "goldboost";
        }else{
            return enchant;
        }
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
        }else if (Objects.equals(enchant, "moctezuma")){
            return redux.moctezumaLore.lore(tier);
        }else if (Objects.equals(enchant, "goldbump")){
            return redux.goldbumpLore.lore(tier);
        }else if (Objects.equals(enchant, "pantsradar")){
            return colorCode(redux.pantsRadarLore.lore(tier));
        }else if (Objects.equals(enchant, "goldboost")){
            return redux.goldboostLore.lore(tier);
        }else if (Objects.equals(enchant, "combodamage")){
            return redux.combodamageLore.lore(tier);
        }else if (Objects.equals(enchant, "sweaty")){
            return redux.sweatyLore.lore(tier);
        }else if (Objects.equals(enchant, "xpbump")){
            return redux.xpbumpLore.lore(tier);
        }else if (Objects.equals(enchant, "xpboost")){
            return redux.xpboostLore.lore(tier);
        }else{
            return "ERROR";
        }
    }

    private static double calcEnchant(List<String> lore, String name){
        if (lore.contains(name)) return 3;
        return 1;
    }

    public static String getEnchant(List<String> lore){

        for (String ench : lore){
            lore.set(lore.indexOf(ench), convertEnchant(ench.replaceAll("I", "")));
        }

        // Rare
        double billionaire = .0125 * calcEnchant(lore, "billionaire");
        double perun = .0125 * calcEnchant(lore, "perun");
        double executioner = .0125 * calcEnchant(lore, "executioner");
        double gamble = .0125 * calcEnchant(lore, "gamble");

        // Common Normal
        double combodamage = .0535 * calcEnchant(lore, "combodamage");
        double sharp = .0535 * calcEnchant(lore, "sharp");
        double kingbuster = .0535 * calcEnchant(lore, "kingbuster");

        // Uncommon Normal
        double painfocus = .0425 * calcEnchant(lore, "painfocus");
        double lifesteal = .0425 * calcEnchant(lore, "lifesteal");
        double diamondstomp = .0425 * calcEnchant(lore, "diamondstomp");
        double shark = .0425 * calcEnchant(lore, "shark");

        // Resource - Gold
        double goldbump = .0475 * calcEnchant(lore, "goldbump");
        double goldboost = .0450 * calcEnchant(lore, "goldboost");
        double moctezuma = .0425 * calcEnchant(lore, "moctezuma");

        // Resource - Sweaty
        double sweaty = .0425 * calcEnchant(lore, "sweaty");
        double xpbump = .0475 * calcEnchant(lore, "xpbump");
        double xpboost = .0475 * calcEnchant(lore, "xpboost");

        // Resource - Misc
        double pantsRadar = .0425 * calcEnchant(lore, "pantsradar");

        while (true) {
            if(percentChance(pantsRadar)){
                // Pants radar
                // 5.25% of being here
                return "pantsradar";
            }else if (percentChance(billionaire)){
                // Billionaire
                // 1% chance of being here
                return "billionaire";
            } else if (percentChance(perun)){
                // Perun
                // 2% chance of being here
                return "perun";
            }else if (percentChance(executioner)){
                // Executioner
                // 2.5% chance of being here
                return "executioner";
            }else if (percentChance(gamble)){
                // Gamble
                // 3.25% chance of being here
                return "gamble";
            }else if (percentChance(xpboost)){
                // Xp Boost
                // 5% chance of being here
                return "xpboost";
            }else if (percentChance(painfocus)){
                // Pain Focus
                // 5.25% chance of being here
                return "painfocus";
            }else if (percentChance(lifesteal)){
                // Lifesteal
                // 6.25% chance of being here
                return "lifesteal";
            }else if (percentChance(goldboost)){
                // Gold Boost
                // 6.5% chance of being here
                return "goldboost";
            }else if (percentChance(combodamage)){
                // Combo Damage
                // Huys is the best Pit Pvper!!!!
                // 4.35% chance of being here
                return "combodamage";
            }else if (percentChance(sharp)){
                // Sharp
                // 6.75% chance of being here
                return "sharp";
            }else if (percentChance(shark)){
                // Shark
                // 7.25% chance of being here
                return "shark";
            }else if (percentChance(xpbump)){
                // Xp Bump
                // 7.50% chance of being here
                return "xpbump";
            }else if (percentChance(goldbump)){
                // Gold Bump
                // 7.75% chance of being here
                return "goldbump";
            }else if (percentChance(diamondstomp)){
                // Diamond Stomp
                // 8.25% chance of being here
                return "diamondstomp";
            }else if (percentChance(sweaty)){
                // Sweaty
                // 9.25% chance of being here
                return "sweaty";
            }else if (percentChance(moctezuma)){
                // Moctezuma
                // 10.25% chance of being here
                return "moctezuma";
            }else if (percentChance(kingbuster)){
                // King Buster
                // 11.25% chance of being here
                return "kingbuster";
            }
        }
    }


}
