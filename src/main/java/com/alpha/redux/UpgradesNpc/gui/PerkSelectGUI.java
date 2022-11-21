package com.alpha.redux.UpgradesNpc.gui;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.advancedInventory;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.events.boards;
import com.alpha.redux.playerdata.Renown;
import com.alpha.redux.playerdata.prestiges;
import com.alpha.redux.playerdata.xpManager;
import com.alpha.redux.redux;
import com.alpha.redux.renownShop.gui.RenownShopGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static com.alpha.redux.apis.advancedInventory.HeadMaker;
import static com.alpha.redux.apis.advancedInventory.ItemMaker;
import static com.alpha.redux.apis.chatManager.rank.ChatEventApiGetLevelColor;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.playerdata.prestiges.getPrestige;
import static com.alpha.redux.playerdata.prestiges.hasPrestige;
import static com.alpha.redux.playerdata.xpManager.GetCurrentLevel;
import static com.alpha.redux.playerdata.xpManager.hasXp;

public class PerkSelectGUI implements Listener {

    public static boolean getPerkSlot(String uuid, String string){
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(string)) return true;
        else if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(string)) return true;
        else if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(string)) return true;
        else return String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(string);
    }

    public static ItemStack getGoBackItem(String uuid){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To permanent upgrades",1, true);
    }

    public static ItemStack getNoPerkItem(String uuid){
        return ItemMaker(Material.DIAMOND_BLOCK, ChatColor.RED + "No perk",
                ChatColor.GRAY+"Are you hardcore enough that you\n" +
                        ChatColor.GRAY + "don't need any perk for this\n" +
                        ChatColor.GRAY + "slot?\n\n" +
                ChatColor.YELLOW + "Click to remove perk!",1, true);
    }

    public static ItemStack getVampireItem(String uuid, Player player){
        hasPrestige(uuid);
        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = redux.theWay.hasValue(uuid) &&
                ((Integer) redux.theWay.getValue(uuid)) >= 1;

        if(level<25 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + redux.vampire.getName(),
                colorCode(redux.vampire.getLore() + "\n\n" +
                        "&cThis requires level " + rank.getBracketsWithLevel(uuid,25) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, redux.vampire.getRefID())){
            return ItemMaker(redux.vampire.getMaterial(), ChatColor.GREEN + redux.vampire.getName(),
                    colorCode(redux.vampire.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(redux.vampire.getMaterial(), ChatColor.YELLOW + redux.vampire.getName(),
                    colorCode(redux.vampire.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getDirtyItem(String uuid, Player player){
        hasPrestige(uuid);
        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = redux.theWay.hasValue(uuid) &&
                ((Integer) redux.theWay.getValue(uuid)) >= 1;

        if(level<30 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + redux.dirty.getName(),
                colorCode(redux.dirty.getLore() + "\n\n" +
                        "&cThis requires level " + rank.getBracketsWithLevel(uuid,30) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, redux.dirty.getRefID())){
            return ItemMaker(redux.dirty.getMaterial(), ChatColor.GREEN + redux.dirty.getName(),
                    colorCode(redux.dirty.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(redux.dirty.getMaterial(), ChatColor.YELLOW + redux.dirty.getName(),
                    colorCode(redux.dirty.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getStreakerItem(String uuid, Player player){
        hasPrestige(uuid);
        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = redux.theWay.hasValue(uuid) &&
                ((Integer) redux.theWay.getValue(uuid)) >= 1;

        if(level<30 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + redux.streaker.getName(),
                colorCode(redux.streaker.getLore() + "\n\n" +
                        "&cThis requires level " + rank.getBracketsWithLevel(uuid,30) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, redux.streaker.getRefID())){
            return ItemMaker(redux.streaker.getMaterial(), ChatColor.GREEN + redux.streaker.getName(),
                    colorCode(redux.streaker.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(redux.streaker.getMaterial(), ChatColor.YELLOW + redux.streaker.getName(),
                    colorCode(redux.streaker.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getAssistantStreakerItem(String uuid, Player player){
        hasPrestige(uuid);
        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = redux.theWay.hasValue(uuid) &&
                ((Integer) redux.theWay.getValue(uuid)) >= 1;

        if(level<50 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + redux.assistantStreaker.getName(),
                colorCode(redux.assistantStreaker.getLore() + "\n\n" +
                        "&cThis requires level " + rank.getBracketsWithLevel(uuid,50) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, redux.assistantStreaker.getRefID())){
            return ItemMaker(redux.assistantStreaker.getMaterial(), ChatColor.GREEN + redux.assistantStreaker.getName(),
                    colorCode(redux.assistantStreaker.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(redux.assistantStreaker.getMaterial(), ChatColor.YELLOW + redux.assistantStreaker.getName(),
                    colorCode(redux.assistantStreaker.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getGladiatorItem(String uuid, Player player){
        hasPrestige(uuid);
        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = redux.theWay.hasValue(uuid) &&
                ((Integer) redux.theWay.getValue(uuid)) >= 1;

        if(level<5 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + redux.gladiator.getName(),
                colorCode(redux.gladiator.getLore() + "\n\n" +
                        "&cThis requires level " + rank.getBracketsWithLevel(uuid,5) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, redux.gladiator.getRefID())){
            return ItemMaker(redux.gladiator.getMaterial(), ChatColor.GREEN + redux.gladiator.getName(),
                    colorCode(redux.gladiator.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(redux.gladiator.getMaterial(), ChatColor.YELLOW + redux.gladiator.getName(),
                    colorCode(redux.gladiator.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getStrengthItem(String uuid, Player player){
        hasPrestige(uuid);
        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = redux.theWay.hasValue(uuid) &&
                ((Integer) redux.theWay.getValue(uuid)) >= 1;

        if(level<10 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + redux.strengthChaining.getName(),
                colorCode(redux.strengthChaining.getLore() + "\n\n" +
                        "&cThis requires level " + rank.getBracketsWithLevel(uuid,10) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, redux.strengthChaining.getRefID())){
            return ItemMaker(redux.strengthChaining.getMaterial(), ChatColor.GREEN + redux.strengthChaining.getName(),
                    colorCode(redux.strengthChaining.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(redux.strengthChaining.getMaterial(), ChatColor.YELLOW + redux.strengthChaining.getName(),
                    colorCode(redux.strengthChaining.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }



    public static ItemStack getGoldenHeadItem(String uuid, Player player){
        hasPrestige(uuid);
        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = redux.theWay.hasValue(uuid) &&
                ((Integer) redux.theWay.getValue(uuid)) >= 1;
        
        if(level<1 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + redux.goldenHeads.getName(),
                colorCode(redux.goldenHeads.getLore() + "\n\n" +
                        "&cThis requires level " + rank.getBracketsWithLevel(uuid,1) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, redux.goldenHeads.getRefID())){
            return HeadMaker("ifishdupe", ChatColor.GREEN + redux.goldenHeads.getName(),
                    colorCode(redux.goldenHeads.getLore() + "\n\n" +
                            "&aAlready selected!"));
        }else{
            return HeadMaker("ifishdupe", ChatColor.YELLOW + redux.goldenHeads.getName(),
                    colorCode(redux.goldenHeads.getLore() + "\n\n"+
                            "&eClick to select!"));
        }
    }

    public static Inventory getPerkSelectMenu(Player player, int slot){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Choose a perk - Slot #" + slot);
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        advancedInventory.addInv(gui, getGoldenHeadItem(uuid, player), 2, 2, false);
        advancedInventory.addInv(gui, getVampireItem(uuid, player), 3, 2, false);
        advancedInventory.addInv(gui, getDirtyItem(uuid, player), 4, 2, false);
        advancedInventory.addInv(gui, getStreakerItem(uuid, player), 5, 2, false);
        advancedInventory.addInv(gui, getStrengthItem(uuid, player), 6, 2, false);
        advancedInventory.addInv(gui, getGladiatorItem(uuid, player), 7, 2, false);
        advancedInventory.addInv(gui, getAssistantStreakerItem(uuid, player), 8, 2, false);

        advancedInventory.addInv(gui, getGoBackItem(uuid), 5, 5, false);
        advancedInventory.addInv(gui, getNoPerkItem(uuid), 6, 5, false);


        //advancedInventory.addInv(gui, getOverDriveItem(uuid), 2, 2, false);

        return gui;
    }

    public boolean hasValue(String uuid, int slot){
        if(slot==1){
            return redux.perkSlotOne.hasValue(uuid);
        }else if(slot==2){
            return redux.perkSlotTwo.hasValue(uuid);
        }else if(slot==3){
            return redux.perkSlotThree.hasValue(uuid);
        }else if(slot==4){
            return redux.perkSlotFour.hasValue(uuid);
        }

        return false;
    }

    public String getValue(String uuid, int slot){
        if(slot==1){
            return String.valueOf(redux.perkSlotOne.getValue(uuid, ""));
        }else if(slot==2){
            return String.valueOf(redux.perkSlotTwo.getValue(uuid, ""));
        }else if(slot==3){
            return String.valueOf(redux.perkSlotThree.getValue(uuid, ""));
        }else if(slot==4){
            return String.valueOf(redux.perkSlotFour.getValue(uuid, ""));
        }

        return "";
    }

    public void setValue(String uuid, String data, int slot){
        if(slot==1){
            redux.perkSlotOne.setValue(uuid, data);
        }else if(slot==2){
            redux.perkSlotTwo.setValue(uuid, data);
        }else if(slot==3){
            redux.perkSlotThree.setValue(uuid, data);
        }else if(slot==4){
            redux.perkSlotFour.setValue(uuid, data);
        }
    }

    public boolean hasGoldenHead(String uuid){
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(redux.goldenHeads.getRefID())) return true;
        if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(redux.goldenHeads.getRefID())) return true;
        if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(redux.goldenHeads.getRefID())) return true;
        return String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(redux.goldenHeads.getRefID());
    }

    public boolean hasVampire(String uuid){
        if(String.valueOf(redux.perkSlotOne.getValue(uuid, "")).equals(redux.vampire.getRefID())) return true;
        if(String.valueOf(redux.perkSlotTwo.getValue(uuid, "")).equals(redux.vampire.getRefID())) return true;
        if(String.valueOf(redux.perkSlotThree.getValue(uuid, "")).equals(redux.vampire.getRefID())) return true;
        return String.valueOf(redux.perkSlotFour.getValue(uuid, "")).equals(redux.vampire.getRefID());
    }

    @EventHandler
    public void HandlePerkSelectEvent(InventoryClickEvent event) {
        if (event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().contains(ChatColor.GRAY + "Choose a perk")) return;

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        int[] playerData = GetCurrentLevel(uuid, xpManager.getXp(uuid), prestiges.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        
        boolean theWay = redux.theWay.hasValue(uuid) &&
                ((Integer) redux.theWay.getValue(uuid)) >= 1;

        event.setCancelled(true);

        int perkSlot = Integer.parseInt(ChatColor.stripColor(event.getClickedInventory().getName().replaceAll("Choose a perk - Slot #", "")));

        if (event.getCurrentItem().getType().equals(redux.assistantStreaker.getMaterial())) {
            Renown.hasRenown(uuid);

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), redux.assistantStreaker.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 50 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, redux.assistantStreaker.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(redux.vampire.getMaterial())) {
            Renown.hasRenown(uuid);

            if(hasGoldenHead(uuid)) {
                Sounds.NO.play(player);
            }else if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), redux.vampire.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 25 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, redux.vampire.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(redux.dirty.getMaterial())) {
            Renown.hasRenown(uuid);

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), redux.dirty.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 30 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, redux.dirty.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(redux.strengthChaining.getMaterial())) {
            Renown.hasRenown(uuid);

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), redux.strengthChaining.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 10 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, redux.strengthChaining.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(redux.streaker.getMaterial())) {
            Renown.hasRenown(uuid);

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), redux.streaker.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 30 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, redux.streaker.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(redux.gladiator.getMaterial())) {
            Renown.hasRenown(uuid);

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), redux.gladiator.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 5 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, redux.gladiator.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getItemMeta() != null &&
                event.getCurrentItem().getItemMeta().getDisplayName().contains("Golden Heads")) {
            Renown.hasRenown(uuid);

            if(hasVampire(uuid)) {
                Sounds.NO.play(player);
            }else if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), redux.goldenHeads.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 1 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, redux.goldenHeads.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(Material.ARROW)) {
            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
            Sounds.BUTTON.play(player);
        } else if (event.getCurrentItem().getType().equals(Material.DIAMOND_BLOCK)) {
            setValue(uuid, "", perkSlot);
            Sounds.GAMBLE_YES.play(player);
            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        }
    }
}
