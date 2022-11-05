package com.alpha.redux.well.enchants;

import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ArmorEvents.ArmorEquipEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.awt.datatransfer.FlavorEvent;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;
import static com.alpha.redux.events.boards.integerToRoman;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnPant;

public class GottaGoFastLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event, int level) {

    }

    public void run(ArmorEquipEvent event){
        if(isNPC(event.getPlayer())) return;
        if(event.getNewArmorPiece() == null) {
            playerExists(event.getPlayer()).setSpeed(0);
            return;
        }
        if(event.getOldArmorPiece() != null &&
                event.getOldArmorPiece().getType().equals(Material.LEATHER_LEGGINGS) &&
                !isNPC(event.getPlayer())){
            ItemStack leggings = event.getOldArmorPiece();

            boolean CRICKET = false;
            String ENCHANT = "";

            if(leggings.getItemMeta() != null &&
                    leggings.getItemMeta().getLore() != null){
                for(String enchant : CheckEnchantOnPant(leggings.getItemMeta().getLore())) {
                    if (enchant.contains("gottagofast")) {
                        ENCHANT = enchant;
                        CRICKET = true;
                        break;
                    }
                }
            }

            if(CRICKET){
                playerExists(event.getPlayer()).setSpeed(0);
            }

            return;
        }
        if(!event.getNewArmorPiece().getType().equals(Material.LEATHER_LEGGINGS)) return;


        ReduxPlayer player = playerExists(event.getPlayer());
        ItemStack leggings = event.getNewArmorPiece();

        boolean CRICKET = false;
        String ENCHANT = "";

        if(leggings != null &&
                leggings.getItemMeta() != null &&
                leggings.getItemMeta().getLore() != null){
            for(String enchant : CheckEnchantOnPant(leggings.getItemMeta().getLore())) {
                if (enchant.contains("gottagofast")) {
                    CRICKET = true;
                    ENCHANT = enchant;
                    break;
                }
            }
        }

        if(CRICKET){
            int level = ENCHANT.length() - ENCHANT.replaceAll("I", "").length();
            float speed = 5+(level*7);
            player.setSpeed((speed/100));
        }

    }

    @Override
    public void init() {
        EnchantRarity rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Gotta go fast" + tier;
    }

    // T1 = 5 T2 = 7 T3 = 15

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(5+(level*5));

        String lore = "&9Gotta go fast" + tier + "\n" +
                "&7Move &e" + multiplier  + "% faster &7at all times" + "\n&7";

        return colorCode(lore);
    }
}

