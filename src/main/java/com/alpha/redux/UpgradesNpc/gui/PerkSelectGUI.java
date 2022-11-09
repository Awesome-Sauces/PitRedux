package com.alpha.redux.UpgradesNpc.gui;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.advancedInventory;
import com.alpha.redux.playerdata.Renown;
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

import static com.alpha.redux.apis.advancedInventory.ItemMaker;

public class PerkSelectGUI implements Listener {
    public static ItemStack getGoBackItem(String uuid){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To permanent upgrades",1, true);
    }

    public static Inventory getPerkSelectMenu(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Choose a perk");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
            advancedInventory.addInv(gui, base_glass, i, 6, false);
        }

        advancedInventory.addInv(gui, getGoBackItem(uuid), 5, 6, false);

        //advancedInventory.addInv(gui, getOverDriveItem(uuid), 2, 2, false);

        return gui;
    }

    @EventHandler
    public void HandleRenownShopUpgradesClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Renown Shop - Upgrades")) return;

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        event.setCancelled(true);

        if (event.getCurrentItem().getType().equals(Material.FENCE)) {
            Renown.hasRenown(uuid);

            if (redux.promotion.hasValue(uuid) &&
                    ((Integer) redux.promotion.getValue(uuid)) >= 1) {
                Sounds.NO.play(player);
            } else if (Renown.getRenown(uuid) >= 50 && !redux.promotion.hasValue(uuid)) {
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid) - 50);
                redux.promotion.setValue(uuid, (Integer) 1);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getItemMeta() != null &&
                event.getCurrentItem().getItemMeta().getDisplayName().contains("Celebrity")) {
            Renown.hasRenown(uuid);

            if (redux.celebrity.hasValue(uuid) &&
                    ((Integer) redux.celebrity.getValue(uuid)) >= 1) {
                Sounds.NO.play(player);
            } else if (Renown.getRenown(uuid) >= 300 && !redux.celebrity.hasValue(uuid)) {
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                Renown.setRenown(uuid, Renown.getRenown(uuid) - 300);
                redux.celebrity.setValue(uuid, (Integer) 1);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(Material.ARROW)) {
            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
            Sounds.BUTTON.play(player);
        }
    }
}
