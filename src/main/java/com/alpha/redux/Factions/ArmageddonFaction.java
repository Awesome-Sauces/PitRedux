package com.alpha.redux.Factions;

import com.alpha.redux.Stash.StashCore;
import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.advancedInventory;
import com.alpha.redux.items.enchants;
import com.alpha.redux.redux;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.alpha.redux.apis.advancedInventory.*;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.playerdata.Renown.*;

public class ArmageddonFaction implements Listener {
    public static void editNPC(NPC npc){
        skin(npc);
    }

    public static boolean joinFaction(Player player){
        String uuid = player.getUniqueId().toString();

        if(redux.factionData.hasValue(uuid) &&
                redux.factionData.getValue(uuid).toString().equals("demon")) return true;

        if(redux.factionData.hasValue(uuid) &&
                redux.factionData.getValue(uuid).toString().equals("angel")) {
            hasRenown(uuid);

            if(getRenown(uuid)>=500){
                player.sendMessage(colorCode("&e&lFACTION! &7You switched factions and reset all your points!"));
                Sounds.EXE.play(player);
                redux.factionData.setValue(uuid, "demon");
                redux.botKills.setValue(uuid, 0);
                player.sendMessage(colorCode("&c&lPLEDGED! &7You joined the &cDemon &7Faction!"));
                setRenown(uuid, getRenown(uuid)-500);
                redux.factionReward.setValue(uuid, "NONE");
            }else{
                player.sendMessage(colorCode("&e&lFACTION! &7You can't afford this!"));
                Sounds.ERROR.play(player);
            }
        }

        if(redux.factionData.hasValue(uuid) &&
                redux.factionData.getValue(uuid).toString().equals("NONE")){
            redux.factionData.setValue(uuid, "demon");
            redux.botKills.setValue(uuid, 0);
            player.sendMessage(colorCode("&c&lPLEDGED! &7You joined the &cDemon &7Faction!"));
            return true;
        }

        return false;
    }

    public static ItemStack getTierI(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=300) return ClayMaker((short)14,ChatColor.RED+"Tier I Perk",colorCode("&8Demon\n\n" +
                "&7Deal &c+1 &7damage vs.\n" +
                "&bAngel &7players.\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker((short)15,ChatColor.RED+"Tier I Perk",colorCode("&8Demon\n\n" +
                "&7Deal &c+1 &7damage vs.\n" +
                "&bAngel &7players.\n\n" +
                "&cRequires 300 points"));
        else return ClayMaker((short)15,ChatColor.RED+"Tier I Perk",colorCode("&8Demon\n\n" +
                    "&7Deal &c+1 &7damage vs.\n" +
                    "&bAngel &7players.\n\n" +
                    "&cPledge to unlock!"));
    }

    public static ItemStack getTierII(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=1000) return ClayMaker((short)14,ChatColor.RED+"Tier II Perk", colorCode("&8Demon\n\n" +
                "&7Unlock the &cDemon &7spawn\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker((short)15,ChatColor.RED+"Tier II Perk", colorCode("&8Demon\n\n" +
                "&7Unlock the &cDemon &7spawn\n\n" +
                "&cRequires 1,000 points"));
        else return ClayMaker((short)15,ChatColor.RED+"Tier II Perk", colorCode("&8Demon\n\n" +
                    "&7Unlock the &cDemon &7spawn\n\n" +
                    "&cPledge to unlock!"));
    }

    public static ItemStack getTierIII(Player player, boolean isAngel, int botKills) {
        if(isAngel&&botKills>=2500) return ClayMaker((short)14,ChatColor.RED+"Tier III Perk", colorCode("&8Demon\n\n" +
                "&dMystic Well &7costs &c1/3\n" +
                "&7of the price.\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker((short)15,ChatColor.RED+"Tier III Perk", colorCode("&8Demon\n\n" +
                "&dMystic Well &7costs &c1/3\n" +
                "&7of the price.\n\n" +
                "&cRequires 2,500 points"));
        else return ClayMaker((short)15,ChatColor.RED+"Tier III Perk", colorCode("&8Demon\n\n" +
                    "&dMystic Well &7costs &c1/3\n" +
                    "&7of the price.\n\n" +
                    "&cPledge to unlock!"));
    }

    public static ItemStack getTierIV(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=7000) return ClayMaker((short)14,ChatColor.RED+"Tier IV Perk", colorCode("&8Demon\n\n" +
                "&7Deal &c+1 &7damage vs.\n" +
                "&7players wearing diamond\n&7armor.\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker((short)15,ChatColor.RED+"Tier IV Perk", colorCode("&8Demon\n\n" +
                "&7Deal &c+1 &7damage vs.\n" +
                "&7players wearing diamond\n&7armor.\n\n" +
                "&cRequires 7,000 points"));
        else return ClayMaker((short)15,ChatColor.RED+"Tier IV Perk", colorCode("&8Demon\n\n" +
                    "&7Deal &c+1 &7damage vs.\n" +
                    "&7players wearing diamond\n&7armor.\n\n" +
                    "&cPledge to unlock!"));
    }

    public static ItemStack getTierV(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=15000) return ClayMaker((short)14,ChatColor.RED+"Tier V Perk", colorCode("&8Demon\n\n" +
                "&7Accumulate &6+50% &7gold on\n" +
                "&7your bounties.\n\n" +
                "&7Also, earn &e+1 Renown\n" +
                "&7when earning Renown from\n" +
                "&7events.\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker((short)15,ChatColor.RED+"Tier V Perk", colorCode("&8Demon\n\n" +
                "&7Accumulate &6+50% &7gold on\n" +
                "&7your bounties.\n\n" +
                "&7Also, earn &e+1 Renown\n" +
                "&7when earning Renown from\n" +
                "&7events.\n\n" +
                "&cRequires 15,000 points"));
        else return ClayMaker((short)15,ChatColor.RED+"Tier V Perk", colorCode("&8Demon\n\n" +
                    "&7Accumulate &6+50% &7gold on\n" +
                    "&7your bounties.\n\n" +
                    "&7Also, earn &e+1 Renown\n" +
                    "&7when earning Renown from\n" +
                    "&7events.\n\n" +
                    "&cPledge to unlock!"));
    }

    public static ItemStack getTierVI(Player player, boolean isAngel, int botKills, String reward){
        if(reward.equals("unclaimed")) return ItemMaker(Material.GOLD_BLOCK,ChatColor.RED+"Tier VI Perk", colorCode("&8Demon\n\n" +
                "&7Earn &cArmageddon Boots\n&8Counters Dark Pants\n\n" +
                "&eClick to claim!"),1,true);

        if(isAngel&&botKills>=40000) return ClayMaker((short)14,ChatColor.RED+"Tier VI Perk", colorCode("&8Demon\n\n" +
                "&7Earn &cArmageddon Boots\n&8Counters Dark Pants\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker((short)15,ChatColor.RED+"Tier VI Perk", colorCode("&8Demon\n\n" +
                "&7Earn &cArmageddon Boots\n&8Counters Dark Pants\n\n" +
                "&cRequires 40,000 points"));
        else return ClayMaker((short)15,ChatColor.RED+"Tier VI Perk", colorCode("&8Demon\n\n" +
                    "&7Earn &cArmageddon Boots\n&8Counters Dark Pants\n\n" +
                    "&cPledge to unlock!"));
    }

    public static ItemStack getTierVII(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=70000) return ClayMaker((short) 14,ChatColor.RED+"Tier VII Perk", colorCode("&8Demon\n\n" +
                "&7Permanently gain &6+1g &7from kills\n" +
                "&8Can be claimed up to 15 times\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker((short)15,ChatColor.RED+"Tier VII Perk", colorCode("&8Demon\n\n" +
                "&7Permanently gain &6+1g &7from kills\n" +
                "&8Can be claimed up to 15 times\n\n" +
                "&cRequires 70,000 points"));
        else return ClayMaker((short)15,ChatColor.RED+"Tier VII Perk", colorCode("&8Demon\n\n" +
                    "&7Permanently gain &6+1g &7from kills\n" +
                    "&8Can be claimed up to 15 times\n\n" +
                    "&cPledge to unlock!"));
    }

    public static ItemStack getJoinButton(Player player, boolean isAngel){
        if(isAngel) return WoolMaker((short)14, ChatColor.RED+"Allegiance Pledged",
                colorCode("&8Demon\n\n" +
                        "&7You pleged allegiance to\n" +
                        "&7this faction.\n\n" +
                        "&7You have: &c"+redux.botKills.getValue(player.getUniqueId().toString(), 1).toString()+" points\n\n" +
                        "&c1 bot kill = 1 point"));
        else if(redux.factionData.getValue(player.getUniqueId().toString(), "NONE").equals("angel")) return WoolMaker((short)14, ChatColor.RED+"Allegiance Pledged",
                colorCode("&8Demon\n\n" +
                        "&7Join this faction, earn\n" +
                        "&7points from kills and earn\n" +
                        "&7exclusive perks.\n\n" +
                        "&7Cost: &e500 Renown\n" +
                        "&7You have: &e"+ getRenown(player.getUniqueId().toString())+" Renown\n\n" +
                        "&eClick to join!"));
        else return WoolMaker((short)14, ChatColor.RED+"Allegiance Pledged",
                colorCode("&8Demon\n\n" +
                        "&7Join this faction, earn\n" +
                        "&7points from kills and earn\n" +
                        "&7exclusive perks.\n\n" +
                        "&eClick to join!"));
    }

    public static ItemStack getRestartButton(Player player, boolean isAngel, int botKills){
        if(isAngel && botKills>=70000) return ItemMaker(Material.SEA_LANTERN, ChatColor.GREEN+"Restart Progress",
                colorCode("&7Click this button to\n" +
                        "&7restart all progress and\n" +
                        "&7gain all rewards again.\n" +
                        "&8Allows you to reclaim all rewards\n\n" +
                        "&7Cost: &e250 Renown\n" +
                        "&7You have: &e"+ getRenown(player.getUniqueId().toString())+" Renown\n\n" +
                        "&eClick to restart!"), 1, true);
        else return ItemMaker(Material.SEA_LANTERN, ChatColor.GREEN+"Restart Progress",
                colorCode("&7Click this button to\n" +
                        "&7gain all rewards again.\n" +
                        "&8Allows you to reclaim all rewards\n\n" +
                        "&cReach max tier first!"), 1, true);
    }
    @EventHandler
    public void clickInventory(InventoryClickEvent event) {
        if (event != null && event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Demon")) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            String uuid = player.getUniqueId().toString();

            ItemStack clicked = event.getCurrentItem();

            if(clicked==null || clicked.getItemMeta()==null && clicked.getItemMeta().getDisplayName()==null) return;

            String displayName = clicked.getItemMeta().getDisplayName();

            if(displayName.equals(ChatColor.RED+"Allegiance Pledged")) {
                joinFaction(player);
                openInventory(player);
                return;
            }

            if(clicked.getType().equals(Material.SEA_LANTERN)){
                if(((int)redux.botKills.getValue(uuid, 1))>=70000 &&
                        getRenown(uuid)>=250
                ){
                    redux.factionReward.setValue(uuid, "NONE");
                    redux.botKills.setValue(uuid, 0);
                    player.sendMessage(colorCode("&e&lFACTION! &7Successfully reset all faction points!"));
                    Sounds.EXE.play(player);
                    openInventory(player);
                }
            }

            if(clicked.getType().equals(Material.GOLD_BLOCK)){
                redux.factionReward.setValue(player.getUniqueId().toString(), "claimed");
                StashCore.safeGive(player, enchants.arma);
                openInventory(player);
            }

        }
    }

    public static Inventory openInventory(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Demon");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        boolean isAngel = redux.factionData.getValue(player.getUniqueId().toString(), "NONE").toString().equals("demon");
        int botKills = (int) redux.botKills.getValue(player.getUniqueId().toString(), 1);
        String reward = redux.factionReward.getValue(player.getUniqueId().toString(), "NONE").toString();

        advancedInventory.addInv(gui, getTierI(player, isAngel, botKills), 2,2,false);
        advancedInventory.addInv(gui, getTierII(player, isAngel, botKills), 3,2,false);
        advancedInventory.addInv(gui, getTierIII(player, isAngel, botKills), 4,2,false);
        advancedInventory.addInv(gui, getTierIV(player, isAngel, botKills), 5,2,false);
        advancedInventory.addInv(gui, getTierV(player, isAngel, botKills), 6,2,false);
        advancedInventory.addInv(gui, getTierVI(player, isAngel, botKills, reward), 7,2,false);
        advancedInventory.addInv(gui, getTierVII(player, isAngel, botKills), 8,2,false);

        advancedInventory.addInv(gui, getJoinButton(player, isAngel), 3,4,false);
        advancedInventory.addInv(gui, getRestartButton(player, isAngel, botKills), 7,4,false);

        player.openInventory(gui);

        return gui;
    }

    private static void skin(NPC npc) {
        SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
        skinTrait.setSkinName("APOC4LYPS3");

        LookClose lookClose = npc.getTrait(LookClose.class);
        lookClose.lookClose(true);
    }

}
