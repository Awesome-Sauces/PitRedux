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


public class RenownShopUpgradesGUI implements Listener {

    public static ItemStack getTheWayItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<7){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(7)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.theWay.hasValue(uuid) &&
                ((Integer)redux.theWay.getValue(uuid))>=1){
            return ItemMaker(Material.ACACIA_DOOR_ITEM, ChatColor.GREEN + "The Way",
                    colorCode(redux.theWay.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else{
            return ItemMaker(Material.ACACIA_DOOR_ITEM, ChatColor.YELLOW + "The Way",
                    colorCode(redux.theWay.getLore() + "\n\n" +
                            "&7Cost: &e50 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getPromotionItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<12){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(12)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.promotion.hasValue(uuid) &&
                ((Integer)redux.promotion.getValue(uuid))>=1){
            return ItemMaker(Material.FENCE, ChatColor.GREEN + "Promotion!!",
                    colorCode(redux.promotion.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else{
            return ItemMaker(Material.FENCE, ChatColor.YELLOW + "Promotion!!",
                    colorCode(redux.promotion.getLore() + "\n\n" +
                            "&7Cost: &e50 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getCelebrityItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<20){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(20)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.celebrity.hasValue(uuid) &&
                ((Integer)redux.celebrity.getValue(uuid))>=1){
            return FishMaker((short) 3, ChatColor.GREEN + "Celebrity",
                    colorCode(redux.celebrity.getLore() + "\n\n" +
                            "&aMaxed out!"));
        }else{
            return FishMaker((short) 3, ChatColor.YELLOW + "Celebrity",
                    colorCode(redux.celebrity.getLore() + "\n\n" +
                            "&7Cost: &e300 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"));
        }
    }

    public static ItemStack getFastPassItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<15){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(15)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.fastPass.hasValue(uuid) &&
                ((Integer)redux.fastPass.getValue(uuid))>=1){
            return ItemMaker(Material.ACTIVATOR_RAIL, ChatColor.GREEN + "Fast Pass",
                    colorCode(redux.fastPass.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else{
            return ItemMaker(Material.ACTIVATOR_RAIL, ChatColor.YELLOW + "Fast Pass",
                    colorCode(redux.fastPass.getLore() + "\n\n" +
                            "&7Cost: &e100 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getExperienceItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<14){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(14)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.experienceIndustrialComplex.hasValue(uuid) &&
                ((Integer)redux.experienceIndustrialComplex.getValue(uuid))>=1){
            return ItemMaker(Material.DIAMOND_BARDING, ChatColor.GREEN + "Experience-Industrial Complex",
                    colorCode(redux.experienceIndustrialComplex.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else{
            return ItemMaker(Material.DIAMOND_BARDING, ChatColor.YELLOW + "Experience-Industrial Complex",
                    colorCode(redux.experienceIndustrialComplex.getLore() + "\n\n" +
                            "&7Cost: &e80 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getHeresyItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<6){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(6)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.heresy.hasValue(uuid) &&
                ((Integer)redux.heresy.getValue(uuid))>=1){
            return ItemMaker(Material.COAL, ChatColor.GREEN + "Heresy",
                    colorCode(redux.heresy.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else{
            return ItemMaker(Material.COAL, ChatColor.YELLOW + "Heresy",
                    colorCode(redux.heresy.getLore() + "\n\n" +
                            "&7Cost: &e50 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getMysticismItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<1){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(1)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.mysticism.hasValue(uuid) &&
                ((Integer)redux.mysticism.getValue(uuid))>=20){
            return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.GREEN + "Mysticism",
                    colorCode("&7Current: &d+" + (((Integer)redux.mysticism.getValue(uuid))*5) +
                            "&d% chance\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.mysticism.getValue(uuid)) + "\n\n" +
                            redux.mysticism.getLore((Integer) redux.mysticism.getValue(uuid)+1) + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(redux.mysticism.hasValue(uuid)){
            return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.GREEN + "Mysticism",
                    colorCode("&7Current: &d+" + (((Integer)redux.mysticism.getValue(uuid))*5) +
                            "&d% chance\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.mysticism.getValue(uuid)) + "\n\n" +
                            redux.mysticism.getLore((Integer) redux.mysticism.getValue(uuid)+1) + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)redux.mysticism.getValue(uuid))*5)+" &eRenown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.YELLOW + "Mysticism",
                    colorCode(redux.mysticism.getLore(1) + "\n\n" +
                            "&7Cost: &e25 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getExtraHearts(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<5){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(5)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.extraHearts.hasValue(uuid) &&
                ((Integer)redux.extraHearts.getValue(uuid))>=2){
            return ItemMaker(Material.APPLE, ChatColor.GREEN + "Extra Hearts",
                    colorCode("&7Current: &c+" + redux.extraHearts.getValue(uuid) +
                            "&c❤\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.extraHearts.getValue(uuid)) + "\n\n" +
                            redux.extraHearts.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(redux.extraHearts.hasValue(uuid)){
            return ItemMaker(Material.APPLE, ChatColor.GREEN + "Extra Hearts",
                    colorCode("&7Current: &c+" + redux.extraHearts.getValue(uuid) +
                            "&c❤\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.extraHearts.getValue(uuid)) + "\n\n" +
                            redux.extraHearts.getLore() + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)redux.extraHearts.getValue(uuid))*20)+" &eRenown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.APPLE, ChatColor.YELLOW + "Extra Hearts",
                    colorCode(redux.extraHearts.getLore() + "\n\n" +
                            "&7Cost: &e20 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getTenacityItem(String uuid){
        hasPrestige(uuid);
        if(getPrestige(uuid)<1){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+boards.integerToRoman(1)),1, true);
        }

        Renown.hasRenown(uuid);
        if(redux.tenacity.hasValue(uuid) &&
                ((Integer)redux.tenacity.getValue(uuid))>=5){
            return ItemMaker(Material.MAGMA_CREAM, ChatColor.GREEN + "Tenacity",
                    colorCode("&7Current: Heal &c+0." + redux.tenacity.getValue(uuid) +
                            "&c❤ &7on kill.\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.tenacity.getValue(uuid)) + "\n\n" +
                            redux.tenacity.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(redux.tenacity.hasValue(uuid)){
            return ItemMaker(Material.MAGMA_CREAM, ChatColor.GREEN + "Tenacity",
                    colorCode("&7Current: Heal &c+0." + redux.tenacity.getValue(uuid) +
                            "&c❤ &7on kill.\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.tenacity.getValue(uuid)) + "\n\n" +
                            redux.tenacity.getLore() + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)redux.tenacity.getValue(uuid))*25)+" &eRenown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.MAGMA_CREAM, ChatColor.YELLOW + "Tenacity",
                    colorCode(redux.tenacity.getLore() + "\n\n" +
                            "&7Cost: &e25 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getXpBumpItem(String uuid){
        Renown.hasRenown(uuid);
        if(redux.renownXpBump.hasValue(uuid) &&
                ((Integer)redux.renownXpBump.getValue(uuid))>=10){
            return ItemMaker(Material.EXP_BOTTLE, ChatColor.GREEN + "Renown XP Bump",
                    colorCode("&7Current: &b+" + redux.renownXpBump.getValue(uuid) +
                            " &bkill XP\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.renownXpBump.getValue(uuid)) + "\n\n" +
                            redux.renownXpBump.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(redux.renownXpBump.hasValue(uuid)){
            return ItemMaker(Material.EXP_BOTTLE, ChatColor.GREEN + "Renown XP Bump",
                    colorCode("&7Current: &b+" + redux.renownXpBump.getValue(uuid) +
                            " &bkill XP\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.renownXpBump.getValue(uuid)) + "\n\n" +
                            redux.renownXpBump.getLore() + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)redux.renownXpBump.getValue(uuid))*5)+" &eRenown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.EXP_BOTTLE, ChatColor.YELLOW + "Renown XP Bump",
                    colorCode(redux.renownXpBump.getLore() + "\n\n" +
                            "&7Cost: &e10 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getGoldBumpItem(String uuid){
        Renown.hasRenown(uuid);
        if(redux.renownGoldBoost.hasValue(uuid) &&
                ((Integer)redux.renownGoldBoost.getValue(uuid))>=10){
            return ItemMaker(Material.GOLD_NUGGET, ChatColor.GREEN + "Renown Gold Boost",
                    colorCode("&7Current: &6+" + redux.renownGoldBoost.getValue(uuid) +
                            "&6.0% gold (g)\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.renownGoldBoost.getValue(uuid)) + "\n\n" +
                            redux.renownGoldBoost.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(redux.renownGoldBoost.hasValue(uuid)){
            return ItemMaker(Material.GOLD_NUGGET, ChatColor.GREEN + "Renown Gold Boost",
                    colorCode("&7Current: &6+" + redux.renownGoldBoost.getValue(uuid) +
                            "&6.0% gold (g)\n" +
                            "&7Tier: &a" + boards.integerToRoman((Integer) redux.renownGoldBoost.getValue(uuid)) + "\n\n" +
                            redux.renownGoldBoost.getLore() + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)redux.renownGoldBoost.getValue(uuid))*5)+" &eRenown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.GOLD_NUGGET, ChatColor.YELLOW + "Renown Gold Boost",
                    colorCode(redux.renownGoldBoost.getLore() + "\n\n" +
                            "&7Cost: &e10 Renown\n" +
                            "&7You have: &e"+Renown.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getGoBackItem(String uuid){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To Prestige & Renown",1, true);
    }

    public static Inventory getRenownShopUpgradesGUI(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Renown Shop - Upgrades");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        advancedInventory.addInv(gui, getTenacityItem(uuid), 2, 2, false);
        advancedInventory.addInv(gui, getXpBumpItem(uuid), 3, 2, false);
        advancedInventory.addInv(gui, getGoldBumpItem(uuid), 4, 2, false);
        advancedInventory.addInv(gui, getTheWayItem(uuid), 5, 2, false);
        advancedInventory.addInv(gui, getExperienceItem(uuid), 6, 2, false);
        advancedInventory.addInv(gui, getHeresyItem(uuid), 7, 2, false);
        advancedInventory.addInv(gui, getFastPassItem(uuid), 8, 2, false);

        advancedInventory.addInv(gui, getMysticismItem(uuid), 2, 3, false);
        advancedInventory.addInv(gui, getCelebrityItem(uuid), 3, 3, false);
        advancedInventory.addInv(gui, getExtraHearts(uuid), 4, 3, false);
        advancedInventory.addInv(gui, getPromotionItem(uuid), 5, 3, false);

        advancedInventory.addInv(gui, getGoBackItem(uuid), 5, 5, false);

        //advancedInventory.addInv(gui, getOverDriveItem(uuid), 2, 2, false);

        return gui;
    }

    @EventHandler
    public void HandleRenownShopUpgradesClick(InventoryClickEvent event){
        if(event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Renown Shop - Upgrades")) return;

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        event.setCancelled(true);

        if(event.getCurrentItem().getType().equals(Material.BEDROCK)){
            Sounds.ERROR.play(player);
            player.sendMessage(ChatColor.RED + "You are too low prestige to acquire this!");
        }

        if(event.getCurrentItem().getType().equals(Material.GOLD_NUGGET)){
            Renown.hasRenown(uuid);

            if(redux.renownGoldBoost.hasValue(uuid) &&
                    ((Integer)redux.renownGoldBoost.getValue(uuid))>=10){
                Sounds.NO.play(player);
            }else if(redux.renownGoldBoost.hasValue(uuid) && Renown.getRenown(uuid)>=(((Integer)redux.renownGoldBoost.getValue(uuid))*5)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-(((Integer)redux.renownGoldBoost.getValue(uuid))*5));
                redux.renownGoldBoost.addValue(uuid, (Integer) 1);
            }else if(Renown.getRenown(uuid)>=5 && !redux.renownGoldBoost.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-5);
                redux.renownGoldBoost.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.EXP_BOTTLE)){
            Renown.hasRenown(uuid);

            if(redux.renownXpBump.hasValue(uuid) &&
                    ((Integer)redux.renownXpBump.getValue(uuid))>=10){
                Sounds.NO.play(player);
            }else if(redux.renownXpBump.hasValue(uuid) &&
                    Renown.getRenown(uuid)>=(((Integer)redux.renownXpBump.getValue(uuid))*5)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-(((Integer)redux.renownXpBump.getValue(uuid))*5));
                redux.renownXpBump.addValue(uuid, (Integer) 1);
            }else if(Renown.getRenown(uuid)>=5 && !redux.renownXpBump.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-5);
                redux.renownXpBump.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.APPLE)){
            Renown.hasRenown(uuid);

            if(redux.extraHearts.hasValue(uuid) &&
                    ((Integer)redux.extraHearts.getValue(uuid))>=2){
                Sounds.NO.play(player);
            }else if(redux.extraHearts.hasValue(uuid) &&
                    Renown.getRenown(uuid)>=(((Integer)redux.extraHearts.getValue(uuid))*20)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-(((Integer)redux.extraHearts.getValue(uuid))*20));
                redux.extraHearts.addValue(uuid, (Integer) 1);

                player.setMaxHealth(20+((Integer)redux.extraHearts.getValue(uuid, 1)*2));
            }else if(Renown.getRenown(uuid)>=20 && !redux.extraHearts.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-20);
                redux.extraHearts.setValue(uuid, (Integer) 1);
                player.setMaxHealth(20+((Integer)redux.extraHearts.getValue(uuid, 1)*2));
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ENCHANTMENT_TABLE)){
            Renown.hasRenown(uuid);

            if(redux.mysticism.hasValue(uuid) &&
                    ((Integer)redux.mysticism.getValue(uuid))>=20){
                Sounds.NO.play(player);
            }else if(redux.mysticism.hasValue(uuid) &&
                    Renown.getRenown(uuid)>=(((Integer)redux.mysticism.getValue(uuid))*5)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-(((Integer)redux.mysticism.getValue(uuid))*5));
                redux.mysticism.addValue(uuid, (Integer) 1);
            }else if(Renown.getRenown(uuid)>=5 && !redux.mysticism.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-5);
                redux.mysticism.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.MAGMA_CREAM)){
            Renown.hasRenown(uuid);

            if(redux.tenacity.hasValue(uuid) &&
                    ((Integer)redux.tenacity.getValue(uuid))>=5){
                Sounds.NO.play(player);
            }else if(redux.tenacity.hasValue(uuid) && Renown.getRenown(uuid)>=(((Integer)redux.tenacity.getValue(uuid))*25)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-(((Integer)redux.tenacity.getValue(uuid))*25));
                redux.tenacity.addValue(uuid, (Integer) 1);
            }else if(Renown.getRenown(uuid)>=25 && !redux.tenacity.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-25);
                redux.tenacity.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ACACIA_DOOR_ITEM)){
            Renown.hasRenown(uuid);

            if(redux.theWay.hasValue(uuid) &&
                    ((Integer)redux.theWay.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=50 && !redux.theWay.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-50);
                redux.theWay.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ACTIVATOR_RAIL)){
            Renown.hasRenown(uuid);

            if(redux.fastPass.hasValue(uuid) &&
                    ((Integer)redux.fastPass.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=100 && !redux.fastPass.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-100);
                redux.fastPass.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.COAL)){
            Renown.hasRenown(uuid);

            if(redux.heresy.hasValue(uuid) &&
                    ((Integer)redux.heresy.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=50 && !redux.heresy.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-50);
                redux.heresy.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.DIAMOND_BARDING)){
            Renown.hasRenown(uuid);

            if(redux.experienceIndustrialComplex.hasValue(uuid) &&
                    ((Integer)redux.experienceIndustrialComplex.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=80 && !redux.experienceIndustrialComplex.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-80);
                redux.experienceIndustrialComplex.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.FENCE)){
            Renown.hasRenown(uuid);

            if(redux.promotion.hasValue(uuid) &&
                    ((Integer)redux.promotion.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=50 && !redux.promotion.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-50);
                redux.promotion.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getItemMeta()!=null&&
                event.getCurrentItem().getItemMeta().getDisplayName().contains("Celebrity")){
            Renown.hasRenown(uuid);

            if(redux.celebrity.hasValue(uuid) &&
                    ((Integer)redux.celebrity.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(Renown.getRenown(uuid)>=300 && !redux.celebrity.hasValue(uuid)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid)-300);
                redux.celebrity.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ARROW)){
            player.openInventory(RenownShopGUI.getRenownShopGUI(player));
            Sounds.BUTTON.play(player);
        }

    }
}
