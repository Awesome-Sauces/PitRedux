package com.alpha.redux.MenuClicks;

import com.alpha.redux.Stash.StashCore;
import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.events.boards;
import com.alpha.redux.items.enchants;
import com.alpha.redux.playerdata.Renown;
import com.alpha.redux.playerdata.goldReq;
import com.alpha.redux.playerdata.prestiges;
import com.alpha.redux.playerdata.xpManager;
import com.alpha.redux.items.itemManager;
import com.alpha.redux.redux;
import com.alpha.redux.renownShop.gui.RenownShopGUI;
import com.alpha.redux.well.enchanters.FreshPants;
import com.alpha.redux.well.enchanters.MysticBow;
import com.alpha.redux.well.enchanters.MysticSword;
import com.nametagedit.plugin.NametagEdit;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;

import static com.alpha.redux.apis.HeadNames.headnames.changeName;
import static com.alpha.redux.apis.advancedInventory.addInv;
import static com.alpha.redux.apis.chatManager.rank.*;
import static com.alpha.redux.events.boards.integerToRoman;
import static com.alpha.redux.events.events.Strength;
import static com.alpha.redux.playerdata.economy.*;
import static com.alpha.redux.apis.advancedInventory.ItemMaker;
import static com.alpha.redux.events.events.Has_Strength;
import static com.alpha.redux.playerdata.goldReq.getGoldRequirement;
import static com.alpha.redux.playerdata.prestiges.*;
import static com.alpha.redux.playerdata.prestiges.getPrestige;
import static com.alpha.redux.playerdata.streaks.*;
import static com.alpha.redux.playerdata.xpManager.*;
import static com.alpha.redux.renownShop.renownAmount.GetByPrestige;
import static com.alpha.redux.renownShop.renownEvent.mainEvent;
import static com.alpha.redux.renownShop.shopGUI.mainMenu;
import static com.alpha.redux.well.gui.*;
import static com.alpha.redux.well.mysticWell.*;

public class InventoryEvent {

    public static void main(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        String uuid = String.valueOf(player.getUniqueId());

        if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Mystic Well")){
            event.setCancelled(true);
            if (event.getCurrentItem().getType() == Material.ENCHANTMENT_TABLE) {
                ItemStack items = event.getClickedInventory().getItem(20);
                hasEconomy(uuid);

                if (items.getType().equals(Material.LEATHER_LEGGINGS)) {
                    FreshPants.clickFresh(event);
                }

                if (items.getType().equals(Material.GOLD_SWORD)) {
                    MysticSword.clickSword(event);
                    return;
                }

                if (items.getType().equals(Material.BOW)) {
                    MysticBow.clickBow(event);
                }

            }else if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Mystic Well")){
                    if (event.getCurrentItem().getType().equals(Material.LEATHER_LEGGINGS) ||
                            event.getCurrentItem().getType().equals(Material.GOLD_SWORD) ||
                            event.getCurrentItem().getType().equals(Material.BOW)) {

                        StashCore.safeGive(player, event.getClickedInventory().getItem(20));
                        event.getClickedInventory().setItem(20, null);

                    }
            }

            }
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(player.getInventory().getTitle())){
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Fresh") ||
                    event.getCurrentItem().getItemMeta().getDisplayName().contains("Mystic Sword") ||
                    event.getCurrentItem().getItemMeta().getDisplayName().contains("Mystic Bow")){
                Inventory gui = enchanting(player);

                addInv(gui, event.getCurrentItem(), 3, 3, false);

                player.getInventory().removeItem(event.getCurrentItem());
                event.setCancelled(true);

            }else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Tier I")){
                enchanting_tierI(player, event.getCurrentItem());
                player.getInventory().removeItem(event.getCurrentItem());
                event.setCancelled(true);
            }else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Tier II")){
                enchanting_tierI(player, event.getClickedInventory().getItem(21));
                player.getInventory().removeItem(event.getCurrentItem());
                event.setCancelled(true);
            }
            }

        }
        /*else {
            hasFresh(uuid);
            if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Mystic Well")){
                if (event.getCurrentItem().getItemMeta().equals(enchants.fresh_reds.getItemMeta())){
                    base(player);
                    player.getInventory().addItem(enchants.fresh_reds);
                    setFresh(uuid, false);
                    event.setCancelled(true);
                }
            }

        }*/


    public static void NonPermItems(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String uuid = String.valueOf(player.getUniqueId());

        switch (event.getCurrentItem().getType()) {
            case DIAMOND_SWORD:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 150) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 150);

                        StashCore.safeGive(player, ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false));

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        boards.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

            }
            case DIAMOND_SPADE:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 750) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 750);

                        StashCore.safeGive(player, ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                                "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n"  +
                                ChatColor.BLUE + "+7 Attack Damage", 1, false));

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        boards.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case IRON_HELMET:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 200) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 200);

                        StashCore.safeGive(player, itemManager.IronHelmet);
                        StashCore.safeGive(player, itemManager.IronChestplate);
                        StashCore.safeGive(player, itemManager.IronLeggings);
                        StashCore.safeGive(player, itemManager.IronBoots);

                        }

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        boards.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

            case DIAMOND_CHESTPLATE:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 500) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 500);

                        if(player.getInventory().getChestplate() != null &&
                                player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE) || player.getInventory().getChestplate() == null){
                            Sounds.ARMOR_SWAP.play(player);
                            player.getInventory().setChestplate(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false));
                        }else{
                            StashCore.safeGive(player, ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false));
                        }

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        boards.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case DIAMOND_LEGGINGS:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 1500) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 1500);

                        if(player.getInventory().getLeggings() != null &&
                        player.getInventory().getLeggings().getType().equals(Material.IRON_LEGGINGS) || player.getInventory().getLeggings() == null){
                            Sounds.ARMOR_SWAP.play(player);
                            player.getInventory().setLeggings(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false));
                        }else{
                            StashCore.safeGive(player,ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false));
                        }

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        boards.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case OBSIDIAN:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 40) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 40);
                        StashCore.safeGive(player,ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 8, false));

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        boards.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case MONSTER_EGG:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 2000) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 2000);

                        StashCore.safeGive(player, enchants.firstaidfull);
                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        boards.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case MINECART:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 150 && event.getCurrentItem().getItemMeta().getDisplayName().contains("Pants Bundle")) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 150);
                        StashCore.safeGive(player,enchants.pantsPB);

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        boards.CreateScore(player);

                    }else if (getEconomy(String.valueOf(player.getUniqueId())) >= 150 && event.getCurrentItem().getItemMeta().getDisplayName().contains("Sword Bundle")) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 150);
                        StashCore.safeGive(player, enchants.swordPB);

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        boards.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }


                    event.setCancelled(true);
                    break;

                }
            case DIAMOND_BOOTS:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 300) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 300);

                        if(player.getInventory().getBoots() != null &&
                                player.getInventory().getBoots().getType().equals(Material.IRON_BOOTS) || player.getInventory().getBoots() == null){
                            Sounds.ARMOR_SWAP.play(player);
                            player.getInventory().setBoots(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false));
                        }else{
                            StashCore.safeGive(player, ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false));
                        }


                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);
                        boards.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
        }
    }

    public static void PrestigeItems(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem().getType() == Material.DIAMOND) {
            event.setCancelled(true);
            int[] randomDUDE;
            randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);

            if (randomDUDE[1] >= 120) {

                if(goldReq.getGoldReq(String.valueOf(player.getUniqueId())) <
                        getGoldRequirement(getPrestige(String.valueOf(player.getUniqueId())))){
                    player.sendMessage(ChatColor.RED + "You can't afford this!");
                    player.closeInventory();
                    return;
                }

                if (getPrestige(String.valueOf(player.getUniqueId())) >= 100) {
                    setPrestige(String.valueOf(player.getUniqueId()), 100);
                    player.sendMessage(ChatColor.RED + "You've reached the max Prestige! Congrats!");
                    return;
                }
                setMega(String.valueOf(player.getUniqueId()), "overdrive");
                setStreak(String.valueOf(player.getUniqueId()), 0);
                hasEconomy(String.valueOf(player.getUniqueId()));
                setEconomy(String.valueOf(player.getUniqueId()), 0);
                hasPrestige(String.valueOf(player.getUniqueId()));
                setXp(String.valueOf(player.getUniqueId()), 0);
                goldReq.setGoldReq(String.valueOf(player.getUniqueId()), 0);

                Renown.addRenown(String.valueOf(player.getUniqueId()),GetByPrestige(getPrestige(String.valueOf(player.getUniqueId()))));

                player.setExp(0);
                player.setLevel(0);
                NametagEdit.getApi().setNametag(player, ChatEventApiGetLevelColor(player.getDisplayName(), String.valueOf(player.getUniqueId()))+ rank.getNameColor(player), "");
                xp_amount_mega.put(String.valueOf(player.getUniqueId()), 0);
                Strength.put(String.valueOf(player.getUniqueId()), 0.0);
                mega_damage_amount.put(String.valueOf(player.getUniqueId()), 0.0);
                true_damage_amount.put(String.valueOf(player.getUniqueId()), 0.0);
                xp_amount_mega.put(String.valueOf(player.getUniqueId()), 0);
                addPrestige(String.valueOf(player.getUniqueId()), 1);
                addXp(String.valueOf(player.getUniqueId()), (int) (15 + (15* PrestigeXpAmount(getPrestige(String.valueOf(player.getUniqueId()))))));
                addXp(String.valueOf(player.getUniqueId()), (int) (15 + (15* PrestigeXpAmount(getPrestige(String.valueOf(player.getUniqueId()))))));
                addXp(String.valueOf(player.getUniqueId()), 1);
                randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);
                Bukkit.broadcastMessage(ChatColor.YELLOW + colorCode("&lPRESTIGE! ") + ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY + " unlocked prestige " + ChatColor.YELLOW + getPrestige(String.valueOf(player.getUniqueId())) + ChatColor.GRAY + " gg!");
                boards.CreateScore(player);
                player.closeInventory();
                if(redux.fastPass.hasValue(String.valueOf(player.getUniqueId()))){
                    setXp(player.getUniqueId().toString(),getLevelXP(player, 50, getPrestige(player.getUniqueId().toString())));
                }
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 0);
                PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"PRESTIGE!\",\"bold\":true,\"color\":\"yellow\"}"), 100, 20, 20);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
                PacketPlayOutTitle sub_title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("[\"\",{\"text\":\"\",\"color\":\"gray\"},{\"text\":\"" + "You unlocked prestige" + "\",\"color\":\"gray\"},{\"text\":\"\",\"color\":\"gray\"},{\"text\":\" \",\"color\":\"gray\"},{\"text\":\"\",\"color\":\"yellow\"},{\"text\":\"" + integerToRoman(getPrestige(String.valueOf(player.getUniqueId()))) + "\",\"color\":\"gray\"},{\"text\":\"\",\"color\":\"gray\"}]"), 100, 20, 20);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(sub_title);
            } else {
                player.sendMessage(ChatColor.RED + "You need Level 120 to prestige!");
            }
        }else if (event.getCurrentItem().getType() == Material.BEACON){
            player.openInventory(RenownShopGUI.getRenownShopGUI(player));
            event.setCancelled(true);
        }
        event.setCancelled(true);

    }

    public static void PerkItems(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        String uuid = String.valueOf(player.getUniqueId());
        hasPrestige(String.valueOf(player.getUniqueId()));
        int[] randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);
        switch (event.getCurrentItem().getType()) {
            case FERMENTED_SPIDER_EYE:
                player.sendMessage(ChatColor.GREEN + "You already have this selected you dummy!");
                event.setCancelled(true);
                break;
            case REDSTONE:
                event.setCancelled(true);
                if (!(randomDUDE[1] >= 10)){player.sendMessage(ChatColor.RED + "You aren't high enough level to select this!");return;}
                if (Has_Strength.containsKey(String.valueOf(player.getUniqueId()))){
                    player.sendMessage(ChatColor.RED + "Strength is already selected!");
                    event.setCancelled(true);
                    break;
                }else{
                    Has_Strength.put(String.valueOf(player.getUniqueId()), true);
                    player.sendMessage(ChatColor.RED + "Strength Chaining selected!");
                }

                break;
            case IRON_CHESTPLATE:
                if (!(prestiges.getPrestige(String.valueOf(player.getUniqueId())) >= 3)){player.sendMessage(ChatColor.RED + "You aren't high enough prestige to use select this!");return;}
                if (randomDUDE[1] >= 50){
                    player.sendMessage("Tank Selected!");
                }
                event.setCancelled(true);
                break;
              /*
            case WHEAT:
                int[] randomDUDE = GetCurrentLevel(xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())));
                if (randomDUDE[1] >= 10){
                    player.sendMessage("Strength Chaning Selected!");
                }
                event.setCancelled(true);
                break;
            case SLIME_BALL:
                int[] randomDUDE = GetCurrentLevel(xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())));
                if (randomDUDE[1] >= 10){
                    player.sendMessage("Strength Chaning Selected!");
                }
                event.setCancelled(true);
                break;
            case DIRT:
                int[] randomDUDE = GetCurrentLevel(xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())));
                if (randomDUDE[1] >= 10){
                    player.sendMessage("Strength Chaning Selected!");
                }
                event.setCancelled(true);
                break;
            case BONE:
                int[] randomDUDE = GetCurrentLevel(xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())));
                if (randomDUDE[1] >= 10){
                    player.sendMessage("Strength Chaning Selected!");
                }
                event.setCancelled(true);
                break;
                */

            default:
                event.setCancelled(true);
        }

    }

}
