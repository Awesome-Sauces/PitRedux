package com.alpha.redux.UpgradesNpc.gui;

import com.alpha.redux.UpgradesNpc.perks.Streaker;
import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.advancedInventory;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.playerdata.prestiges;
import com.alpha.redux.playerdata.xpManager;
import com.alpha.redux.redux;
import com.alpha.redux.renownShop.CookieMonster.Monster;
import com.alpha.redux.renownShop.RenownItems;
import com.alpha.redux.well.gui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.alpha.redux.UpgradesNpc.gui.PerkSelectGUI.getPerkSelectMenu;
import static com.alpha.redux.apis.advancedInventory.*;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.playerdata.prestiges.getPrestige;
import static com.alpha.redux.playerdata.prestiges.hasPrestige;
import static com.alpha.redux.playerdata.streaks.*;
import static com.alpha.redux.playerdata.xpManager.GetCurrentLevel;

public class PermanentUpgrades implements Listener {
    public static ItemStack VampirePerkItem(String uuid, int slot){
        return ItemMaker(Material.FERMENTED_SPIDER_EYE, ChatColor.YELLOW + "Perk Slot #" + String.valueOf(slot),
                ChatColor.GRAY + "Selected: " + redux.vampire.getName() + "\n\n" + redux.vampire.getLore() + "\n\n" +
                        ChatColor.YELLOW + "Click to choose perk!", 1, true);
    }

    public static ItemStack GladiatorPerkItem(String uuid, int slot){
        return ItemMaker(Material.BONE, ChatColor.YELLOW + "Perk Slot #" + String.valueOf(slot),
                ChatColor.GRAY + "Selected: " + redux.gladiator.getName() + "\n\n" + redux.gladiator.getLore() + "\n\n" +
                        ChatColor.YELLOW + "Click to choose perk!", 1, true);
    }

    public static ItemStack DirtyPerkItem(String uuid, int slot){
        return DirtMaker((short) 2, ChatColor.YELLOW + "Perk Slot #" + String.valueOf(slot),
                ChatColor.GRAY + "Selected: " + redux.dirty.getName() + "\n\n" + redux.dirty.getLore() + "\n\n" +
                        ChatColor.YELLOW + "Click to choose perk!");
    }

    public static ItemStack GoldenHeadPerkItem(String uuid, int slot){
        return HeadMaker("ifishdupe", ChatColor.YELLOW + "Perk Slot #" + String.valueOf(slot),
                ChatColor.GRAY + "Selected: " + redux.goldenHeads.getName() + "\n\n" + redux.goldenHeads.getLore() + "\n\n" +
                        ChatColor.YELLOW + "Click to choose perk!");
    }

    public static ItemStack StrengthPerkItem(String uuid, int slot){
        return ItemMaker(Material.REDSTONE, ChatColor.YELLOW + "Perk Slot #" + String.valueOf(slot),
                ChatColor.GRAY + "Selected: " + redux.strengthChaining.getName() + "\n\n" +
                        redux.strengthChaining.getLore() + "\n\n" +
                        ChatColor.YELLOW + "Click to choose perk!", 1, true);
    }

    public static ItemStack EmptySlotItem(String uuid, int slot){
        return ItemMaker(Material.DIAMOND_BLOCK, ChatColor.GREEN + "Perk Slot #" + String.valueOf(slot),
                ChatColor.GRAY + "Select a perk to fill this slot\n\n" +
                        ChatColor.YELLOW + "Click to choose perk!", 1, true);
    }

    public static ItemStack EmptyKillstreakSlotItem(String uuid, int slot){
        return ItemMaker(Material.GOLD_BLOCK, ChatColor.GREEN + "Perk Slot #" + String.valueOf(slot),
                ChatColor.GRAY + "Select a killstreak for this\n"+ChatColor.GRAY+"slot.\n\n" +
                        ChatColor.YELLOW + "Click to choose perk!", 1, true);
    }

    public static ItemStack KillStreakItem(String uuid, Material material){
        return ItemMaker(material, ChatColor.GREEN + "Killstreaks",
                ChatColor.GRAY + "Choose killstreak perks which\n"+
                ChatColor.GRAY + "trigger every time you get X\n" + ChatColor.GRAY + "kills.\n\n" +
                ChatColor.GRAY + "Megastreak: " + ChatColor.GREEN + getMegaStreak(uuid) +"\n\n" +
                        ChatColor.YELLOW + "Click to edit killstreaks!", 1, true);
    }

    public static ItemStack XPBoostSlotItem(String uuid){
        return DyeMaker((short) 12, ChatColor.RED + "XP Boost",
                ChatColor.GRAY + "Each tier:\n" + ChatColor.GRAY + "Earn " + ChatColor.AQUA +
                        "+10% XP " + ChatColor.GRAY + "from all\n"+ChatColor.GRAY+"sources.\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "500g\n" +
                ChatColor.RED + "Not enough gold!");
    }

    public static ItemStack GoldBoostSlotItem(String uuid){
        return DyeMaker((short) 14, ChatColor.RED + "Gold Boost",
                ChatColor.GRAY + "Each tier:\n" + ChatColor.GRAY + "Earn " + ChatColor.GOLD +
                        "+10% gold (g) " + ChatColor.GRAY + "from\n" + ChatColor.GRAY +
                        "kills and coin pickups.\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "1000g\n" +
                        ChatColor.RED + "Not enough gold!");
    }

    public static ItemStack MeleeSlotItem(String uuid){
        return DyeMaker((short) 1, ChatColor.RED + "Melee Damage",
                ChatColor.GRAY + "Each tier:\n" + ChatColor.GRAY + "Deal " + ChatColor.RED +
                        "+1% " + ChatColor.GRAY + "melee damage.\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "2500g\n" +
                        ChatColor.RED + "Not enough gold!");
    }

    public static ItemStack BowSlotItem(String uuid){
        return DyeMaker((short) 11, ChatColor.RED + "Bow Damage",
                ChatColor.GRAY + "Each tier:\n" + ChatColor.GRAY + "Deal " + ChatColor.RED +
                        "+3% " + ChatColor.GRAY + "bow damage.\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "500g\n" +
                        ChatColor.RED + "Not enough gold!");
    }

    public static ItemStack ReductionSlotItem(String uuid){
        return DyeMaker((short) 6, ChatColor.RED + "Damage Reduction",
                ChatColor.GRAY + "Each tier:\n" + ChatColor.GRAY + "Receive " + ChatColor.BLUE +
                        "-1% " + ChatColor.GRAY + "damage.\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "1500g\n" +
                        ChatColor.RED + "Not enough gold!");
    }

    public static ItemStack BuildBattlerSlotItem(String uuid){
        return DyeMaker((short) 15, ChatColor.RED + "Build Battler",
                ChatColor.GRAY + "Each tier:\n" + ChatColor.GRAY + "Your blocks stay " + ChatColor.GREEN +
                        "+60%\n" + ChatColor.GRAY + "longer.\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "1000g\n" +
                        ChatColor.RED + "Not enough gold!");
    }

    public static ItemStack ElGatoSlotItem(String uuid){
        return ItemMaker(Material.CAKE, ChatColor.RED + "El Gato",
                ChatColor.GRAY + "Each tier:\n" + ChatColor.LIGHT_PURPLE + "First kill " + ChatColor.GRAY +
                        "each life rewards\n" + ChatColor.GOLD + "+5g " + ChatColor.AQUA + "+5 XP" + ChatColor.GRAY + ".\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "1000g\n" +
                        ChatColor.RED + "Not enough gold!", 1, true);
    }

    public static String getOverDriveLore(){
        return colorCode("&7Triggers on: &c50 kills\n\n" +
                "&7On trigger:\n" +
                "&a&l- &7Perma &eSpeed I&7.\n" +
                "&a&l- &7Earn &b+100% XP &7from kills.\n" +
                "&a&l- &7Earn &6+50% gold &7from kills.\n\n" +
                "&7BUT:\n" +
                "&c&l- &7Receive &c+0.1❤ &7&overy\n" +
                "&7true damage per 5 kills over 50.\n\n" +
                "&7On death:\n" +
                "&e&l- &7Gain &b4,000 XP&7.");
    }

    public static String getUberLore(){
        return colorCode("&7Triggers on: &c100 kills\n\n" +
                "&7On trigger:\n" +
                "&a&l- &7Gain &d+50% &7chance to find mystic items.\n\n" +
                "&7BUT:\n" +
                "&c&l- &7Receive &c+20% &7damage per 100 kills.\n\n" +
                "&7During the streak:\n" +
                "&d&l- &7100 kills: &7Deal &c-50% damage &7vs bots.\n" +
                "&d&l- &7200 kills: &c-2 max ❤\n" +
                "&d&l- &7300 kills: &7Potion Effects last &c-50% &7as long\n" +
                "&d&l- &7400 kills: &cNo longer gain health\n\n" +
                "&7On death:\n" +
                "&d&l- &7Earn an &dUberdrop &7if you have at least a 400 streak.");
    }

    public static String getBeastModeLore(){
        return colorCode("&7Triggers on: &c50 kills\n\n" +
                "&7On trigger:\n" +
                "&a&l- &7Gain a &bDiamond Helmet&7.\n" +
                "&a&l- &7Deal &c+25% &7damage.\n" +
                "&a&l- &7Earn &b+50% XP &7from kills.\n" +
                "&a&l- &7Earn &6+75% gold &7from kills.\n\n" +
                "&7BUT:\n" +
                "&c&l- &7Receive &c+0.1❤ " +
                "&7damage per 5 kills over 50.\n\n" +
                "&7On death:\n" +
                "&e&l- &7Keep the &bDiamond Helmet&7.");
    }

    public static String getMagnum(){
        return colorCode("&7Triggers on: &c100 kills\n\n" +
                "&7On trigger:\n" +
                "&a&l- &7EXPLODE!!!\n\n" +
                "&7BUT:\n" +
                "&c&l- &7Die.\n\n" +
                "&7On death:\n" +
                "&e&l- &7Earn &e7 Renown&7.");
    }


    public static String getToTheMoonLore(){
        return colorCode("&7Triggers on: &c100 kills\n\n" +
                "&7On trigger:\n" +
                "&a&l- &7Earn &b+20% XP &7from kills.\n" +
                "&a&l- &7Get &b+100 max XP &7from kills.\n\n" +
                "&7BUT:\n" +
                "&c&l- &7Receive &c+10% &7damage per 20 kills.\n&7(starting from 100)\n" +
                "&c&l- &7Receive &c+0.1❤ &7true damage per 20 kills.\n&7(starting from 200)\n\n" +
                "&7During the streak:\n" +
                "&b&l- &7Copy and store your kills &bXP&7.\n"+
                "&7On death:\n" +
                "&b&l- &7Earn the stored &bXP&7, but\n&7multiply it by &b0.005x &7per kill\n&7above 100, up to &b1.5x&7.");
    }

    public static String getHighLanderLore(){
        return colorCode("&7Triggers on: &c50 kills\n\n" +
                "&7On trigger:\n" +
                "&a&l- &7Perma &eSpeed I&7.\n" +
                "&a&l- &7Earn &6+110% gold &7from kills.\n" +
                "&a&l- &7Deal &c+33% damage &7vs bountied players.\n\n" +
                "&7BUT:\n" +
                "&c&l- &6+5000g &7max bounty.\n" +
                "&c&l- &7Receive &c+0.3% &7damage\n&7from &6Bounty Hunter &7wearers per\n&7kill over 50.\n\n" +
                "&7On death:\n" +
                "&e&l- &7Earn your own bounty aswell.");
    }

    public static ItemStack getHigherLanderItem(String uuid, Player player){
        hasPrestige(uuid);
        hasMegaStreak(uuid);

        if(getMegaStreak(uuid).equals("highlander")){
            return ItemMaker(Material.GOLD_BOOTS, ChatColor.GREEN + "Highlander",
                    getHighLanderLore() + "\n\n" + ChatColor.GREEN + "Already selected!", 1, true);
        }

        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        if(redux.highlanderStreak.hasValue(uuid) && level>=50){
            return ItemMaker(Material.GOLD_BOOTS, ChatColor.YELLOW + "Highlander",
                    getHighLanderLore() + "\n\n" + ChatColor.YELLOW + "Click to select!", 1, true);
        }else if(redux.highlanderStreak.hasValue(uuid) && level<50){
            return ItemMaker(Material.GOLD_BOOTS, ChatColor.YELLOW + "Highlander",
                    getHighLanderLore() + "\n\n" + colorCode("&cThis requires level " + rank.getBracketsWithLevel(uuid,50) + "\n" +
                            "&cor higher."), 1, true);
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.YELLOW + "Highlander",
                    getHighLanderLore() + "\n\n" + ChatColor.RED + "Unlocked in Renown Shop!\n" + ChatColor.RED +
                            "Bundle: " + ChatColor.GOLD + "Highlander", 1, true);
        }
    }

    public static ItemStack getToTheMoonItem(String uuid, Player player){
        hasPrestige(uuid);
        hasMegaStreak(uuid);

        if(getMegaStreak(uuid).equals("moon")){
            return ItemMaker(Material.ENDER_STONE, ChatColor.GREEN + "To the Moon",
                    getToTheMoonLore() + "\n\n" + ChatColor.GREEN + "Already selected!", 1, true);
        }

        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        if(redux.moonStreak.hasValue(uuid) && level>= 50){
            return ItemMaker(Material.ENDER_STONE, ChatColor.YELLOW + "To the Moon",
                    getToTheMoonLore() + "\n\n" + ChatColor.YELLOW + "Click to select!", 1, true);
        }else if(redux.moonStreak.hasValue(uuid) && level<50){
            return ItemMaker(Material.ENDER_STONE, ChatColor.YELLOW + "To the Moon",
                    getToTheMoonLore() + "\n\n" + colorCode("&cThis requires level " + rank.getBracketsWithLevel(uuid,50) + "\n" +
                            "&cor higher."), 1, true);
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.YELLOW + "To the Moon",
                    getToTheMoonLore() + "\n\n" + ChatColor.RED + "Unlocked in Renown Shop!\n" + ChatColor.RED +
                            "Bundle: " + ChatColor.AQUA + "To the Moon", 1, true);
        }
    }

    public static ItemStack getUberItem(String uuid, Player player){
        hasPrestige(uuid);
        hasMegaStreak(uuid);

        if(getMegaStreak(uuid).equals("uber")){
            return ItemMaker(Material.GOLD_SWORD, ChatColor.GREEN + "Uberstreak",
                    getUberLore() + "\n\n" + ChatColor.GREEN + "Already selected!", 1, true);
        }

        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        if(redux.uberStreak.hasValue(uuid) && level>=100){
            return ItemMaker(Material.GOLD_SWORD, ChatColor.YELLOW + "Uberstreak",
                    getUberLore() + "\n\n" + ChatColor.YELLOW + "Click to select!", 1, true);
        }else if(redux.uberStreak.hasValue(uuid) && level<100){
            return ItemMaker(Material.GOLD_SWORD, ChatColor.YELLOW + "Uberstreak",
                    getUberLore() + "\n\n" + ChatColor.RED + colorCode("&cThis requires level " + rank.getBracketsWithLevel(uuid,100) + "\n" +
                            "&cor higher."), 1, true);
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.YELLOW + "Uberstreak",
                    getUberLore() + "\n\n" + ChatColor.RED + "Unlocked in Renown Shop!\n" + ChatColor.RED +
                            "Bundle: " + ChatColor.LIGHT_PURPLE + "Uberstreak", 1, true);
        }
    }

    public static ItemStack getMagnumItem(String uuid, Player player){
        hasPrestige(uuid);
        hasMegaStreak(uuid);

        if(getMegaStreak(uuid).equals("magnum")){
            return ItemMaker(Material.NETHER_STAR, ChatColor.GREEN + "Magnum Opus",
                    getMagnum() + "\n\n" + ChatColor.GREEN + "Already selected!", 1, true);
        }

        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        if(redux.magnumOpus.hasValue(uuid) && level>=70){
            return ItemMaker(Material.NETHER_STAR, ChatColor.YELLOW + "Magnum Opus",
                    getMagnum() + "\n\n" + ChatColor.YELLOW + "Click to select!", 1, true);
        }else if(redux.magnumOpus.hasValue(uuid) && level<70){
            return ItemMaker(Material.NETHER_STAR, ChatColor.RED + "Magnum Opus",
                    getMagnum() + "\n\n" + ChatColor.RED + colorCode("&cThis requires level " + rank.getBracketsWithLevel(uuid,70) + "\n" +
                            "&cor higher."), 1, true);
        }else{
            return ItemMaker(Material.NETHER_STAR, ChatColor.RED + "Magnum Opus",
                    getMagnum() + "\n\n" + ChatColor.RED + "Unlocked in Renown Shop!\n" + ChatColor.RED +
                            "Bundle: " + ChatColor.GREEN + "Magnum Opus", 1, true);
        }
    }

    public static ItemStack getBeastItem(String uuid, Player player){
        hasPrestige(uuid);
        hasMegaStreak(uuid);

        if(getMegaStreak(uuid).equals("beastmode")){
            return ItemMaker(Material.DIAMOND_HELMET, ChatColor.GREEN + "Beastmode",
                    getBeastModeLore() + "\n\n" + ChatColor.GREEN + "Already selected!", 1, true);
        }

        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        if(redux.beastmodeStreak.hasValue(uuid) && level>=25){
            return ItemMaker(Material.DIAMOND_HELMET, ChatColor.YELLOW + "Beastmode",
                    getBeastModeLore() + "\n\n" + ChatColor.YELLOW + "Click to select!", 1, true);
        }else if(redux.beastmodeStreak.hasValue(uuid) && level<25){
            return ItemMaker(Material.DIAMOND_HELMET, ChatColor.RED + "Beastmode",
                    getBeastModeLore() + "\n\n" + ChatColor.RED + colorCode("&cThis requires level " + rank.getBracketsWithLevel(uuid,25) + "\n" +
                            "&cor higher."), 1, true);
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Beastmode",
                    getBeastModeLore() + "\n\n" + ChatColor.RED + "Unlocked in Renown Shop!\n" + ChatColor.RED +
                    "Bundle: " + ChatColor.GREEN + "Beastmode", 1, true);
        }
    }

    public static ItemStack getOverDriveItem(String uuid){
        hasMegaStreak(uuid);

        if(getMegaStreak(uuid).equals("overdrive")){
            return ItemMaker(Material.BLAZE_POWDER, ChatColor.GREEN + "Overdrive",
                    getOverDriveLore() + "\n\n" + ChatColor.GREEN + "Already selected!", 1, true);
        }

        return ItemMaker(Material.BLAZE_POWDER, ChatColor.YELLOW + "Overdrive",
                getOverDriveLore() + "\n\n" + ChatColor.YELLOW + "Click to select!", 1, true);
    }

    public static ItemStack getPerkOneItem(String uuid, int slot){
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(redux.vampire.getRefID())) return ItemMaker(redux.vampire.getMaterial(), ChatColor.YELLOW + redux.vampire.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.vampire.getName() + "\n\n" +
                colorCode(redux.vampire.getLore() + "\n\n" +
                        "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(redux.streaker.getRefID())) return ItemMaker(redux.streaker.getMaterial(), ChatColor.YELLOW + redux.streaker.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.streaker.getName() + "\n\n" +
                        colorCode(redux.streaker.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(redux.assistantStreaker.getRefID())) return ItemMaker(redux.assistantStreaker.getMaterial(), ChatColor.YELLOW + redux.assistantStreaker.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.assistantStreaker.getName() + "\n\n" +
                        colorCode(redux.assistantStreaker.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(redux.dirty.getRefID())) return ItemMaker(redux.dirty.getMaterial(), ChatColor.YELLOW + redux.dirty.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.dirty.getName() + "\n\n" +
                        colorCode(redux.dirty.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(redux.gladiator.getRefID())) return ItemMaker(redux.gladiator.getMaterial(), ChatColor.YELLOW + redux.gladiator.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.gladiator.getName() + "\n\n" +
                        colorCode(redux.gladiator.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(redux.goldenHeads.getRefID())) return HeadMaker("ifishdupe", ChatColor.YELLOW + redux.goldenHeads.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.goldenHeads.getName() + "\n\n" +
                        colorCode(redux.goldenHeads.getLore() + "\n\n" +
                                "&eAlready selected!"));
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(redux.strengthChaining.getRefID())) return ItemMaker(redux.strengthChaining.getMaterial(), ChatColor.YELLOW + redux.strengthChaining.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.strengthChaining.getName() + "\n\n" +
                        colorCode(redux.strengthChaining.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);

        return EmptySlotItem(uuid, 1);
    }

    public static ItemStack getPerkTwoItem(String uuid, int slot){
        if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(redux.vampire.getRefID())) return ItemMaker(redux.vampire.getMaterial(), ChatColor.YELLOW + redux.vampire.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.vampire.getName() + "\n\n" +
                        colorCode(redux.vampire.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(redux.streaker.getRefID())) return ItemMaker(redux.streaker.getMaterial(), ChatColor.YELLOW + redux.streaker.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.streaker.getName() + "\n\n" +
                        colorCode(redux.streaker.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(redux.assistantStreaker.getRefID())) return ItemMaker(redux.assistantStreaker.getMaterial(), ChatColor.YELLOW + redux.assistantStreaker.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.assistantStreaker.getName() + "\n\n" +
                        colorCode(redux.assistantStreaker.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(redux.dirty.getRefID())) return ItemMaker(redux.dirty.getMaterial(), ChatColor.YELLOW + redux.dirty.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.dirty.getName() + "\n\n" +
                        colorCode(redux.dirty.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(redux.gladiator.getRefID())) return ItemMaker(redux.gladiator.getMaterial(), ChatColor.YELLOW + redux.gladiator.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.gladiator.getName() + "\n\n" +
                        colorCode(redux.gladiator.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(redux.goldenHeads.getRefID())) return HeadMaker("ifishdupe", ChatColor.YELLOW + redux.goldenHeads.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.goldenHeads.getName() + "\n\n" +
                        colorCode(redux.goldenHeads.getLore() + "\n\n" +
                                "&eAlready selected!"));
        if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(redux.strengthChaining.getRefID())) return ItemMaker(redux.strengthChaining.getMaterial(), ChatColor.YELLOW + redux.strengthChaining.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.strengthChaining.getName() + "\n\n" +
                        colorCode(redux.strengthChaining.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);

        return EmptySlotItem(uuid, 2);
    }

    public static ItemStack getPerkThreeItem(String uuid, int slot){
        if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(redux.vampire.getRefID())) return ItemMaker(redux.vampire.getMaterial(), ChatColor.YELLOW + redux.vampire.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.vampire.getName() + "\n\n" +
                        colorCode(redux.vampire.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(redux.streaker.getRefID())) return ItemMaker(redux.streaker.getMaterial(), ChatColor.YELLOW + redux.streaker.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.streaker.getName() + "\n\n" +
                        colorCode(redux.streaker.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(redux.assistantStreaker.getRefID())) return ItemMaker(redux.assistantStreaker.getMaterial(), ChatColor.YELLOW + redux.assistantStreaker.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.assistantStreaker.getName() + "\n\n" +
                        colorCode(redux.assistantStreaker.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(redux.dirty.getRefID())) return ItemMaker(redux.dirty.getMaterial(), ChatColor.YELLOW + redux.dirty.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.dirty.getName() + "\n\n" +
                        colorCode(redux.dirty.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(redux.gladiator.getRefID())) return ItemMaker(redux.gladiator.getMaterial(), ChatColor.YELLOW + redux.gladiator.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.gladiator.getName() + "\n\n" +
                        colorCode(redux.gladiator.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(redux.goldenHeads.getRefID())) return HeadMaker("ifishdupe", ChatColor.YELLOW + redux.goldenHeads.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.goldenHeads.getName() + "\n\n" +
                        colorCode(redux.goldenHeads.getLore() + "\n\n" +
                                "&eAlready selected!"));
        if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(redux.strengthChaining.getRefID())) return ItemMaker(redux.strengthChaining.getMaterial(), ChatColor.YELLOW + redux.strengthChaining.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.strengthChaining.getName() + "\n\n" +
                        colorCode(redux.strengthChaining.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);

        return EmptySlotItem(uuid, 3);
    }

    public static ItemStack getPerkFourItem(String uuid, int slot){
        if(String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(redux.vampire.getRefID())) return ItemMaker(redux.vampire.getMaterial(), ChatColor.YELLOW + redux.vampire.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.vampire.getName() + "\n\n" +
                        colorCode(redux.vampire.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(redux.streaker.getRefID())) return ItemMaker(redux.streaker.getMaterial(), ChatColor.YELLOW + redux.streaker.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.streaker.getName() + "\n\n" +
                        colorCode(redux.streaker.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(redux.assistantStreaker.getRefID())) return ItemMaker(redux.assistantStreaker.getMaterial(), ChatColor.YELLOW + redux.assistantStreaker.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.assistantStreaker.getName() + "\n\n" +
                        colorCode(redux.assistantStreaker.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(redux.dirty.getRefID())) return ItemMaker(redux.dirty.getMaterial(), ChatColor.YELLOW + redux.dirty.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.dirty.getName() + "\n\n" +
                        colorCode(redux.dirty.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(redux.gladiator.getRefID())) return ItemMaker(redux.gladiator.getMaterial(), ChatColor.YELLOW + redux.gladiator.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.gladiator.getName() + "\n\n" +
                        colorCode(redux.gladiator.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);
        if(String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(redux.goldenHeads.getRefID())) return HeadMaker("ifishdupe", ChatColor.YELLOW + redux.goldenHeads.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.goldenHeads.getName() + "\n\n" +
                        colorCode(redux.goldenHeads.getLore() + "\n\n" +
                                "&eAlready selected!"));
        if(String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(redux.strengthChaining.getRefID())) return ItemMaker(redux.strengthChaining.getMaterial(), ChatColor.YELLOW + redux.strengthChaining.getName(),
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + redux.strengthChaining.getName() + "\n\n" +
                        colorCode(redux.strengthChaining.getLore() + "\n\n" +
                                "&eAlready selected!"),1, true);

        return EmptySlotItem(uuid, 4);
    }

    public static Inventory getPermanentUpgrades(Player player){

        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Permanent upgrades");

        Material megastreak = Material.BLAZE_POWDER;

        if(getMegaStreak(uuid).equals("uber")){
            megastreak = Material.GOLD_SWORD;
        }else if(getMegaStreak(uuid).equals("highlander")){
            megastreak = Material.GOLD_BOOTS;
        }else if(getMegaStreak(uuid).equals("beastmode")){
            megastreak = Material.DIAMOND_HELMET;
        }else if(getMegaStreak(uuid).equals("moon")){
            megastreak = Material.ENDER_STONE;
        }else if(getMegaStreak(uuid).equals("magnum")){
            megastreak = Material.NETHER_STAR;
        }

        ItemStack base_glass = advancedInventory.cGlass();

        ItemStack killstreak = KillStreakItem(uuid, megastreak);

        ItemStack xpBoost = XPBoostSlotItem(uuid);
        ItemStack goldBoost = GoldBoostSlotItem(uuid);
        ItemStack meleeBoost = MeleeSlotItem(uuid);
        ItemStack bowBoost = BowSlotItem(uuid);
        ItemStack reduction = ReductionSlotItem(uuid);
        ItemStack builder = BuildBattlerSlotItem(uuid);
        ItemStack elgato = ElGatoSlotItem(uuid);

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        // First Row
        //String.valueOf(redux.perkSlotOne.getValue(uuid, "")

        advancedInventory.addInv(gui, getPerkOneItem(uuid, 1), 4, 2, false);

        advancedInventory.addInv(gui, getPerkTwoItem(uuid, 2), 5, 2, false);

        advancedInventory.addInv(gui, getPerkThreeItem(uuid, 3), 6, 2, false);

        advancedInventory.addInv(gui, getPerkFourItem(uuid, 4), 7, 2, false);

        advancedInventory.addInv(gui, killstreak, 3, 2, false);

        advancedInventory.addInv(gui, xpBoost, 2, 4, false);
        advancedInventory.addInv(gui, goldBoost, 3, 4, false);
        advancedInventory.addInv(gui, meleeBoost, 4, 4, false);
        advancedInventory.addInv(gui, bowBoost, 5, 4, false);
        advancedInventory.addInv(gui, reduction, 6, 4, false);
        advancedInventory.addInv(gui, builder, 7, 4, false);
        advancedInventory.addInv(gui, elgato, 8, 4, false);

        return gui;

    }

    public static Inventory getSelectKillstreak(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Choose a killstreak");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
        }

        advancedInventory.addInv(gui, getOverDriveItem(uuid), 2, 2, false);

        advancedInventory.addInv(gui, getBeastItem(uuid, player), 3, 2, false);

        advancedInventory.addInv(gui, getHigherLanderItem(uuid, player), 4, 2, false);

        advancedInventory.addInv(gui, getMagnumItem(uuid, player), 5, 2, false);

        advancedInventory.addInv(gui, getToTheMoonItem(uuid,player), 6, 2, false);

        advancedInventory.addInv(gui, getUberItem(uuid, player), 7, 2, false);

        return gui;
    }

    public static Inventory getKillstreakUpgrades(Player player){

        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Killstreaks");

        ItemStack base_glass = advancedInventory.cGlass();

        Material megastreak = Material.BLAZE_POWDER;
        String lore = getOverDriveLore();

        if(getMegaStreak(uuid).equals("uber")){
                megastreak = Material.GOLD_SWORD;
                lore = getUberLore();
        }else if(getMegaStreak(uuid).equals("highlander")){
                megastreak = Material.GOLD_BOOTS;
                lore = getHighLanderLore();
        }else if(getMegaStreak(uuid).equals("beastmode")){
                megastreak = Material.DIAMOND_HELMET;
                lore = getBeastModeLore();
        }else if(getMegaStreak(uuid).equals("moon")){
            megastreak = Material.ENDER_STONE;
            lore = getToTheMoonLore();
        }else if(getMegaStreak(uuid).equals("magnum")){
            megastreak = Material.NETHER_STAR;
            lore = getMagnum();
        }

        ItemStack empty = EmptySlotItem(uuid, 4);

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
        }

        // First Row
        advancedInventory.addInv(gui, EmptyKillstreakSlotItem(uuid, 1), 3, 2, false);

        advancedInventory.addInv(gui, EmptyKillstreakSlotItem(uuid, 2), 5, 2, false);

        advancedInventory.addInv(gui, ItemMaker(megastreak, ChatColor.YELLOW + "MegaStreak",
                ChatColor.GRAY + "Selected: " + ChatColor.GREEN + getMegaStreak(uuid)  + "\n\n"+ lore + "\n\n" + ChatColor.YELLOW + "Click to switch megastreak!", 1, true), 7, 2, false);

        return gui;

    }

    @EventHandler
    public void PermanentUpgradesClickEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
        !event.getClickedInventory().getTitle().equals(ChatColor.GRAY+"Permanent upgrades")) return;

        event.setCancelled(true);

        String uuid = String.valueOf(player.getUniqueId());hasMegaStreak(uuid);
        Material megastreak = Material.BLAZE_POWDER;

        hasMegaStreak(uuid);

        if(getMegaStreak(uuid).equals("uber")){
            megastreak = Material.GOLD_SWORD;
        }else if(getMegaStreak(uuid).equals("highlander")){
            megastreak = Material.GOLD_BOOTS;
        }else if(getMegaStreak(uuid).equals("beastmode")){
            megastreak = Material.DIAMOND_HELMET;
        }else if(getMegaStreak(uuid).equals("moon")){
            megastreak = Material.ENDER_STONE;
        }else if(getMegaStreak(uuid).equals("magnum")){
            megastreak = Material.NETHER_STAR;
        }

        if(event.getCurrentItem().getType().equals(megastreak)){
            player.openInventory(getKillstreakUpgrades(player));
        }

        if(event.getSlot()==12||
        event.getSlot()==13||
        event.getSlot()==14||
        event.getSlot()==15){
            player.openInventory(getPerkSelectMenu(player, event.getSlot()-11));
        }

    }

    @EventHandler
    public void KillstreakClickEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        String uuid = String.valueOf(player.getUniqueId());

        if(event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
        !event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Killstreaks")) return;

        event.setCancelled(true);

        Material megastreak = Material.BLAZE_POWDER;

        if(getMegaStreak(uuid).equals("uber")){
                megastreak = Material.GOLD_SWORD;
        }else if(getMegaStreak(uuid).equals("highlander")){
                megastreak = Material.GOLD_BOOTS;
        }else if(getMegaStreak(uuid).equals("beastmode")){
                megastreak = Material.DIAMOND_HELMET;
        }else if(getMegaStreak(uuid).equals("moon")){
            megastreak = Material.ENDER_STONE;
        }else if(getMegaStreak(uuid).equals("magnum")){
            megastreak = Material.NETHER_STAR;
        }

        if(event.getCurrentItem().getType().equals(megastreak)){
                player.openInventory(getSelectKillstreak(player));
        }

    }

    @EventHandler
    public void SelectKillstreakClickEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        String uuid = String.valueOf(player.getUniqueId());

        if(event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Choose a killstreak")) return;

        event.setCancelled(true);

        Material uber = Material.GOLD_SWORD;
        Material highlander = Material.GOLD_BOOTS;
        Material moon = Material.ENDER_STONE;
        Material beastmode = Material.DIAMOND_HELMET;
        Material overdrive = Material.BLAZE_POWDER;
        Material magnum = Material.NETHER_STAR;

        Material clicked = event.getCurrentItem().getType();

        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        hasMegaStreak(uuid);
        hasPrestige(uuid);

        if(clicked.equals(Material.BEDROCK)){
            Sounds.ERROR.play(player);
        }

        if(clicked.equals(uber) &&
            !getMegaStreak(uuid).equals("uber") &&
            redux.uberStreak.hasValue(uuid) &&
            level >= 100){
            Sounds.SUCCESS.play(player);
            setMega(uuid,"uber");
            setStreak(uuid, 0);
            player.openInventory(getKillstreakUpgrades(player));
        }if(clicked.equals(magnum) &&
                !getMegaStreak(uuid).equals("magnum") &&
                redux.magnumOpus.hasValue(uuid) &&
                level >= 70){
            Sounds.SUCCESS.play(player);
            setMega(uuid,"magnum");
            setStreak(uuid, 0);
            player.openInventory(getKillstreakUpgrades(player));
        }else if(clicked.equals(moon) &&
                !getMegaStreak(uuid).equals("moon") &&
                redux.moonStreak.hasValue(uuid) &&
                level >= 50){
            Sounds.SUCCESS.play(player);
            setMega(uuid,"moon");
            setStreak(uuid, 0);
            player.openInventory(getKillstreakUpgrades(player));
        }else if(clicked.equals(highlander) &&
                !getMegaStreak(uuid).equals("highlander") &&
                redux.highlanderStreak.hasValue(uuid) &&
                level >= 50){
            Sounds.SUCCESS.play(player);
            setMega(uuid,"highlander");
            setStreak(uuid, 0);
            player.openInventory(getKillstreakUpgrades(player));
        }if(clicked.equals(beastmode) &&
                !getMegaStreak(uuid).equals("beastmode") &&
                redux.beastmodeStreak.hasValue(uuid) &&
                level >= 25){
            Sounds.SUCCESS.play(player);
            setMega(uuid,"beastmode");
            setStreak(uuid, 0);
            player.openInventory(getKillstreakUpgrades(player));
        }if(clicked.equals(overdrive) &&
                !getMegaStreak(uuid).equals("overdrive") &&
                getPrestige(uuid) >= 0 &&
                level >= 0){
            Sounds.SUCCESS.play(player);
            setMega(uuid,"overdrive");
            setStreak(uuid, 0);
            player.openInventory(getKillstreakUpgrades(player));
        }

    }
}
