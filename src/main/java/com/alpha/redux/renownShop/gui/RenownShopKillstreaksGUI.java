package com.alpha.redux.renownShop.gui;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.advancedInventory;
import com.alpha.redux.events.boards;
import com.alpha.redux.playerdata.Renown;
import com.alpha.redux.redux;
import com.alpha.redux.well.gui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.alpha.redux.apis.advancedInventory.FishMaker;
import static com.alpha.redux.apis.advancedInventory.ItemMaker;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.playerdata.prestiges.getPrestige;
import static com.alpha.redux.playerdata.prestiges.hasPrestige;


public class RenownShopKillstreaksGUI implements Listener {

    public static ItemStack getMoonItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<14){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(14)),1, true);
        }
        
        Renown.hasRenown(uuid);
        if(redux.moonStreak.hasValue(uuid) &&
                ((Integer)redux.moonStreak.getValue(uuid))>=1){
            return ItemMaker(Material.ENDER_STONE, ChatColor.GREEN + "Killstreaks: To the Moon",
                    colorCode(redux.moonStreak.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.ENDER_STONE, ChatColor.YELLOW + "Killstreaks: To the Moon",
                    colorCode(redux.moonStreak.getLore() + "\n\n" +
                            "&7Cost: &e70 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getBeastItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<3){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(3)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.beastmodeStreak.hasValue(uuid) &&
                ((Integer)redux.beastmodeStreak.getValue(uuid))>=1){
            return ItemMaker(Material.DIAMOND_HELMET, ChatColor.GREEN + "Killstreaks: Beastmode",
                    colorCode(redux.beastmodeStreak.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.DIAMOND_HELMET, ChatColor.YELLOW + "Killstreaks: Beastmode",
                    colorCode(redux.beastmodeStreak.getLore() + "\n\n" +
                            "&7Cost: &e20 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getHighlanderItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<7){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(7)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.highlanderStreak.hasValue(uuid) &&
                ((Integer)redux.highlanderStreak.getValue(uuid))>=1){
            return ItemMaker(Material.GOLD_BOOTS, ChatColor.GREEN + "Killstreaks: Highlander",
                    colorCode(redux.highlanderStreak.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.GOLD_BOOTS, ChatColor.YELLOW + "Killstreaks: Highlander",
                    colorCode(redux.highlanderStreak.getLore() + "\n\n" +
                            "&7Cost: &e50 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getMagnumOpusItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<10){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(10)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.magnumOpus.hasValue(uuid) &&
                ((Integer)redux.magnumOpus.getValue(uuid))>=1){
            return ItemMaker(Material.NETHER_STAR, ChatColor.GREEN + "Killstreaks: Magnum Opus",
                    colorCode(redux.magnumOpus.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.NETHER_STAR, ChatColor.YELLOW + "Killstreaks: Magnum Opus",
                    colorCode(redux.magnumOpus.getLore() + "\n\n" +
                            "&7Cost: &e5 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getUberItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<20){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(20)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.uberStreak.hasValue(uuid) &&
                ((Integer)redux.uberStreak.getValue(uuid))>=1){
            return ItemMaker(Material.GOLD_SWORD, ChatColor.GREEN + "Killstreaks: Uberstreak",
                    colorCode(redux.uberStreak.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.GOLD_SWORD, ChatColor.YELLOW + "Killstreaks: Uberstreak",
                    colorCode(redux.uberStreak.getLore() + "\n\n" +
                            "&7Cost: &e100 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }
    
    public static ItemStack getGoBackItem(String uuid){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To Prestige & Renown",1, true);
    }

    public static Inventory getRenownShopKillstreaksGUI(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Renown Shop - Killstreaks");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        advancedInventory.addInv(gui, getBeastItem(uuid), 2, 2, false);

        advancedInventory.addInv(gui, getHighlanderItem(uuid), 3, 2, false);

        advancedInventory.addInv(gui, getMagnumOpusItem(uuid), 4, 2, false);

        advancedInventory.addInv(gui, getMoonItem(uuid), 5, 2, false);

        advancedInventory.addInv(gui, getUberItem(uuid), 6, 2, false);
        
        advancedInventory.addInv(gui, getGoBackItem(uuid), 5,5, false);

        //advancedInventory.addInv(gui, getOverDriveItem(uuid), 2, 2, false);

        return gui;
    }

    @EventHandler
    public void HandleRenownShopUpgradesClick(InventoryClickEvent event){
        if(event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Renown Shop - Killstreaks")) return;

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        event.setCancelled(true);

        if(event.getCurrentItem().getType().equals(Material.GOLD_BOOTS)){
            Renown.hasRenown(uuid);

            if(redux.highlanderStreak.hasValue(uuid) &&
                    ((Integer)redux.highlanderStreak.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=50 && !redux.highlanderStreak.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-50);
                redux.highlanderStreak.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.DIAMOND_HELMET)){
            Renown.hasRenown(uuid);

            if(redux.beastmodeStreak.hasValue(uuid) &&
                    ((Integer)redux.beastmodeStreak.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=20 && !redux.beastmodeStreak.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-20);
                redux.beastmodeStreak.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ENDER_STONE)){
            Renown.hasRenown(uuid);

            if(redux.moonStreak.hasValue(uuid) &&
                    ((Integer)redux.moonStreak.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=70 && !redux.moonStreak.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-70);
                redux.moonStreak.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.NETHER_STAR)){
            Renown.hasRenown(uuid);

            if(redux.magnumOpus.hasValue(uuid) &&
                    ((Integer)redux.magnumOpus.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=5 && !redux.magnumOpus.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-5);
                redux.magnumOpus.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.GOLD_SWORD)){
            Renown.hasRenown(uuid);

            if(redux.uberStreak.hasValue(uuid) &&
                    ((Integer)redux.uberStreak.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=100 && !redux.uberStreak.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-100);
                redux.uberStreak.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.BEDROCK)){
            player.sendMessage(ChatColor.RED + "You are too low prestige to acquire this!");
            Sounds.NO.play(player);
        }else if(event.getCurrentItem().getType().equals(Material.ARROW)){
            player.openInventory(RenownShopGUI.getRenownShopGUI(player));
            Sounds.BUTTON.play(player);
        }

    }
}
