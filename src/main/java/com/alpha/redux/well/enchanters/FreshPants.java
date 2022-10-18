package com.alpha.redux.well.enchanters;

import com.alpha.redux.events.boards;
import com.alpha.redux.redux;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.events.boards.integerToRoman;
import static com.alpha.redux.playerdata.economy.getEconomy;
import static com.alpha.redux.playerdata.economy.removeEconomy;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnPant;
import static com.alpha.redux.well.mysticWell.*;

public class FreshPants {

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

    public static void clickFresh(InventoryClickEvent event){
        event.setCancelled(true);

        String uuid = String.valueOf(event.getWhoClicked().getUniqueId());
        Player player = (Player) event.getWhoClicked();
        ItemStack items = event.getClickedInventory().getItem(20);

        if (items.getItemMeta().getDisplayName().contains("Tier III")){
            player.sendMessage(ChatColor.RED + "This pant is already max tier!");
            return;
        } else if (items.getItemMeta().getDisplayName().contains("Tier II") && removeGold(player, uuid, 8000)) {
            event.getClickedInventory().setItem(20, createPant(player,3, event.getClickedInventory().getItem(20)));
        }else if (!items.getItemMeta().getDisplayName().contains("Tier II") &&
                items.getItemMeta().getDisplayName().contains("Tier I") && removeGold(player, uuid, 4000)) {
            event.getClickedInventory().setItem(20, createPant(player,2, event.getClickedInventory().getItem(20)));
        } else if (items.getItemMeta().getDisplayName().contains("Fresh") && removeGold(player, uuid, 1000)) {
            event.getClickedInventory().setItem(20, createPant(player,1, null));
        }


    }

    public static ItemStack createPant(Player player, int tier, ItemStack pant){
        if (pant == null){
            ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setColor(Color.fromRGB(255,85,85));
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + integerToRoman(tier) + " Pants"));
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            meta.spigot().setUnbreakable(true);
            meta.setLore(enchantPant(player, pant, tier));
            item.setItemMeta(meta);
            //Shaped Recipe

            return item;
        }else{
            ItemMeta meta = pant.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + integerToRoman(tier) + " Pants"));
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            meta.spigot().setUnbreakable(true);
            //enchantpant(player, pant, tier)
            meta.setLore(enchantPant(player, pant, tier));
            pant.setItemMeta(meta);
            //Shaped Recipe

            return pant;
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

    public static List<String> enchantPant(Player player, ItemStack pant, int tier) {

        double chanceII = 0.0001;
        double chanceIII = 0.0001;

        List<String> enchants = new ArrayList<>();

        try{

            if (pant != null){enchants = CheckEnchantOnPant(pant.getItemMeta().getLore());}

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
        T = pant tokens

                E = enchant tokens

                C = chance

        Equation:

        > c = ((T/E)*10) / 100

         */

        lore.add(ChatColor.RED + "As strong as iron");



        return lore;

        // return new EnchantingMechanics(lore, enchants.get(0), chanceIII, chanceII, "pant").getLore();

    }

    public static List<String> renewEnchant(List<String> lore, List<String> enchant){

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

    public static String getEnchantTitle(String enchant, int tier){
        if (Objects.equals(enchant, "retro-gravitymicrocosm")) {
            return colorCode(redux.retroGravityMicrocosmLore.title(tier));
        }else if (Objects.equals(enchant, "criticallyfunky")) {
            return colorCode(redux.criticallyFunkyLore.title(tier));
        }else if (Objects.equals(enchant, "goldenheart")) {
            return colorCode(redux.goldenHeartLore.title(tier));
        }else if (Objects.equals(enchant, "regularity")) {
            return colorCode(redux.regularityLore.title(tier));
        }else if (Objects.equals(enchant, "protection")) {
            return colorCode(redux.protectionLore.title(tier));
        }else if (Objects.equals(enchant, "solitude")) {
            return colorCode(redux.solitudeLore.title(tier));
        }else if (Objects.equals(enchant, "notgladiator")) {
            return colorCode(redux.notGladiatorLore.title(tier));
        }else if (Objects.equals(enchant, "mirror")) {
            return colorCode(redux.mirrorLore.title(tier));
        }else if (Objects.equals(enchant, "escapepod")) {
            return colorCode(redux.escapePodLore.title(tier));
        }else if (Objects.equals(enchant, "peroxide")) {
            return colorCode(redux.peroxideLore.title(tier));
        }else if (Objects.equals(enchant, "fractionalreserve")) {
            return colorCode(redux.fractionalReserveLore.title(tier));
        }else if (Objects.equals(enchant, "moctezuma")){
            return colorCode(redux.moctezumaLore.title(tier));
        }else if (Objects.equals(enchant, "goldbump")){
            return colorCode(redux.goldbumpLore.title(tier));
        }else if (Objects.equals(enchant, "goldboost")){
            return colorCode(redux.goldboostLore.title(tier));
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
        if (Objects.equals(enchant, "escape")){
            return "escapepod";
        }else if (Objects.equals(enchant, "glad")){
            return "notgladiator";
        }else if (Objects.equals(enchant, "mirror")){
            return "mirror";
        }else if (Objects.equals(enchant, "crit")){
            return "criticallyfunky";
        }else if (Objects.equals(enchant, "rgm")){
            return "retro-gravitymicrocosm";
        }else if (Objects.equals(enchant, "prot")){
            return "protection";
        }else if (Objects.equals(enchant, "soli")){
            return "solitude";
        }else if (Objects.equals(enchant, "frac")){
            return "fractionalreserve";
        }else if (Objects.equals(enchant, "peroxide")){
            return "peroxide";
        }else if (Objects.equals(enchant, "reg")){
            return "regularity";
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
        }else if (Objects.equals(enchant, "goldheart")){
            return "goldenheart";
        }else{
            return enchant;
        }
    }

    public static String enchantTier(String enchant, int tier){
        if (Objects.equals(enchant, "retro-gravitymicrocosm")) {
            return colorCode(redux.retroGravityMicrocosmLore.lore(tier));
        }else if (Objects.equals(enchant, "criticallyfunky")) {
            return colorCode(redux.criticallyFunkyLore.lore(tier));
        }else if (Objects.equals(enchant, "goldenheart")) {
            return colorCode(redux.goldenHeartLore.lore(tier));
        }else if (Objects.equals(enchant, "regularity")) {
            return colorCode(redux.regularityLore.lore(tier));
        }else if (Objects.equals(enchant, "protection")) {
            return colorCode(redux.protectionLore.lore(tier));
        }else if (Objects.equals(enchant, "solitude")) {
            return colorCode(redux.solitudeLore.lore(tier));
        }else if (Objects.equals(enchant, "notgladiator")) {
            return colorCode(redux.notGladiatorLore.lore(tier));
        }else if (Objects.equals(enchant, "mirror")) {
            return colorCode(redux.mirrorLore.lore(tier));
        }else if (Objects.equals(enchant, "escapepod")) {
            return colorCode(redux.escapePodLore.lore(tier));
        }else if (Objects.equals(enchant, "peroxide")) {
            return colorCode(redux.peroxideLore.lore(tier));
        }else if (Objects.equals(enchant, "fractionalreserve")) {
            return colorCode(redux.fractionalReserveLore.lore(tier));
        }else if (Objects.equals(enchant, "moctezuma")){
            return colorCode(redux.moctezumaLore.lore(tier));
        }else if (Objects.equals(enchant, "goldbump")){
            return colorCode(redux.goldbumpLore.lore(tier));
        }else if (Objects.equals(enchant, "goldboost")){
            return colorCode(redux.goldboostLore.lore(tier));
        }else if (Objects.equals(enchant, "sweaty")){
            return colorCode(redux.sweatyLore.lore(tier));
        }else if (Objects.equals(enchant, "xpbump")){
            return colorCode(redux.xpbumpLore.lore(tier));
        }else if (Objects.equals(enchant, "xpboost")){
            return colorCode(redux.xpboostLore.lore(tier));
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

        double retroGravityMicrocosm = .01 * calcEnchant(lore, "retro-gravitymicrocosm");
        double criticallyFunky = .02 * calcEnchant(lore, "criticallyfunky");
        double regularity = .0250 * calcEnchant(lore, "regularity");
        double protection = .0325 * calcEnchant(lore, "protection");
        double xpboost = .05 * calcEnchant(lore, "xpboost");
        double solitude = .0525 * calcEnchant(lore, "solitude");
        double notGladiator = .0625 * calcEnchant(lore, "notgladiator");
        double goldboost = .0650 * calcEnchant(lore, "goldboost");
        double mirror = .0675 * calcEnchant(lore, "mirror");
        double escapePod = .0725 * calcEnchant(lore, "escapepod");
        double xpbump = .0750 * calcEnchant(lore, "xpbump");
        double goldbump = .0775 * calcEnchant(lore, "goldbump");
        double peroxide = .0825 * calcEnchant(lore, "peroxide");
        double sweaty = .0925 * calcEnchant(lore, "sweaty");
        double moctezuma = .1025 * calcEnchant(lore, "moctezuma");
        double fractionalReserve = .1125 * calcEnchant(lore, "fractionalreserve");
        double goldenHeart = .0735 * calcEnchant(lore, "goldenheart");

        while (true) {
            if (percentChance(retroGravityMicrocosm)){
                // Billionaire
                // 1% chance of being here
                return "retro-gravitymicrocosm";
            } else if (percentChance(criticallyFunky)){
                // Perun
                // 2% chance of being here
                return "criticallyfunky";
            }else if (percentChance(regularity)){
                // Executioner
                // 2.5% chance of being here
                return "regularity";
            }else if (percentChance(protection)){
                // Gamble
                // 3.25% chance of being here
                return "protection";
            }else if (percentChance(goldenHeart)){
                // Golden Heart
                // 7.35% chance of being here
                return "protection";
            }else if (percentChance(xpboost)){
                // Xp Boost
                // 5% chance of being here
                return "xpboost";
            }else if (percentChance(solitude)){
                // Pain Focus
                // 5.25% chance of being here
                return "solitude";
            }else if (percentChance(notGladiator)){
                // Lifesteal
                // 6.25% chance of being here
                return "notgladiator";
            }else if (percentChance(goldboost)){
                // Gold Boost
                // 6.5% chance of being here
                return "goldboost";
            }else if (percentChance(escapePod)){
                // Sharp
                // 6.75% chance of being here
                return "escapepod";
            }else if (percentChance(peroxide)){
                // Shark
                // 7.25% chance of being here
                return "peroxide";
            }else if (percentChance(xpbump)){
                // Xp Bump
                // 7.50% chance of being here
                return "xpbump";
            }else if (percentChance(goldbump)){
                // Gold Bump
                // 7.75% chance of being here
                return "goldbump";
            }else if (percentChance(mirror)){
                // Diamond Stomp
                // 8.25% chance of being here
                return "mirror";
            }else if (percentChance(sweaty)){
                // Sweaty
                // 9.25% chance of being here
                return "sweaty";
            }else if (percentChance(moctezuma)){
                // Moctezuma
                // 10.25% chance of being here
                return "moctezuma";
            }else if (percentChance(fractionalReserve)){
                // King Buster
                // 11.25% chance of being here
                return "fractionalreserve";
            }
        }
    }

}
