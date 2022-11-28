package com.alpha.redux.Factions;

import com.alpha.redux.Stash.StashCore;
import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.advancedInventory;
import com.alpha.redux.items.enchants;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.playerdata.goldReq;
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

import static com.alpha.redux.apis.advancedInventory.ItemMaker;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.playerdata.Renown.*;
import static com.alpha.redux.playerdata.xpManager.addXp;

public class KingFaction implements Listener {

    public static void editNPC(NPC npc){
        skin(npc);
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent event){
        if(event != null && event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "King's Quest")){
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            String uuid = player.getUniqueId().toString();

            hasRenown(uuid);

            if(event.getCurrentItem()!=null&&event.getCurrentItem().getType().equals(Material.GOLD_HELMET)){
                if(!redux.factionData.getValue(uuid, "NONE").equals("NONE") ||
                        !redux.factionData.getValue(uuid, "NONE").equals("")){
                    if(((int)redux.botKills.getValue(uuid, 1))>=50000 &&
                            getRenown(uuid)>=5000 &&
                            player.getInventory().containsAtLeast(enchants.playerSoul, 64)
                    ){
                        setRenown(uuid, getRenown(uuid)-5000);
                        for (int i = 0; i < 64; i++) player.getInventory().removeItem(enchants.playerSoul);

                        StashCore.safeGive(player, enchants.kingsHelmet);
                        economy.addEconomy(uuid, 500000);
                        goldReq.addGoldReq(uuid,500000);
                        addXp(uuid, 1000000);
                        player.sendMessage(colorCode("&6&lKING! &7You finished the &6King's Quest &7maybe come back later and help me again!"));
                        Sounds.SUCCESS.play(player);
                        player.closeInventory();
                    }else{
                        player.sendMessage(colorCode("&6&lKING! &7Maybe come back when you can afford this!"));
                        Sounds.NO.play(player);
                        player.closeInventory();
                    }
                }
            }
        }
    }

    public static ItemStack getKingsItem(){
        return ItemMaker(Material.GOLD_HELMET, ChatColor.YELLOW+"The King's Quest",
                colorCode("&8Special Mission\n\n" +
                        "&7The Task:\n" +
                        " &7- Bring 64x &3Player Soul's\n" +
                        " &7- Bring &e5,000 &7renown\n" +
                        " &7- Have &e50,000 &7faction points\n\n" +
                        "&7The Rewards:\n" +
                        " &e+ &6Golden Helmet\n" +
                        " &e+ &b1,000,000 XP\n" +
                        " &e+ &6500,000g\n\n" +
                        "&eClick to accept!"), 1, true);
    }

    public static Inventory openInventory(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "King's Quest");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
        }

        advancedInventory.addInv(gui, getKingsItem(), 5, 2, false);

        player.openInventory(gui);

        return gui;
    }

    private static void skin(NPC npc) {
        SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
        skinTrait.setSkinName("Destinybro56");

        LookClose lookClose = npc.getTrait(LookClose.class);
        lookClose.lookClose(true);
    }
}
