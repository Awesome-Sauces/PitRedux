package com.alpha.redux.well.enchants;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ArmorEvents.ArmorEquipEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;
import static com.alpha.redux.events.boards.integerToRoman;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnPant;

public class CricketLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event, int level) {
        if(event.getAttacker().getPlayerObject().getWorld().getBlockAt(event.getAttacker().getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS) ||
                event.getDefenders().getPlayerObject().getWorld().getBlockAt(event.getDefenders().getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS)){
            double damage = (level*5);
            event.subtractReduxDamage(event.getReduxDamage()*(damage/100));


            event.getDefenders().addPotionEffect(PotionEffectType.REGENERATION, 32000, 1);
        }else{
            event.getDefenders().removePotionEffect(PotionEffectType.REGENERATION);
        }
    }

    public void run(ArmorEquipEvent event){
        if(isNPC(event.getPlayer())) return;
        if(event.getNewArmorPiece() == null) {
            playerExists(event.getPlayer()).removePotionEffect(PotionEffectType.REGENERATION);
            return;
        }
        if(event.getOldArmorPiece() != null &&
                event.getOldArmorPiece().getType().equals(Material.LEATHER_LEGGINGS) &&
        !isNPC(event.getPlayer())){
            ItemStack leggings = event.getOldArmorPiece();

            boolean CRICKET = false;

            if(leggings.getItemMeta() != null &&
            leggings.getItemMeta().getLore() != null){
                for(String enchant : CheckEnchantOnPant(leggings.getItemMeta().getLore())) {
                    if (enchant.contains("cricket")) {
                        CRICKET = true;
                        break;
                    }
                }
            }

            if(CRICKET){
                playerExists(event.getPlayer()).removePotionEffect(PotionEffectType.REGENERATION);
            }

            return;
        }
        if(!event.getNewArmorPiece().getType().equals(Material.LEATHER_LEGGINGS)) return;

        ReduxPlayer player = playerExists(event.getPlayer());
        ItemStack leggings = event.getNewArmorPiece();

        boolean CRICKET = false;

        if(leggings != null &&
                leggings.getItemMeta() != null &&
                leggings.getItemMeta().getLore() != null){
            for(String enchant : CheckEnchantOnPant(leggings.getItemMeta().getLore())) {
                if (enchant.contains("cricket")) {
                    CRICKET = true;
                    break;
                }
            }
        }

        if(CRICKET &&
                player.getPlayerObject().getWorld().getBlockAt(player.getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS)){
            player.addPotionEffect(PotionEffectType.REGENERATION, 32000, 1);
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

        return "&9Cricket" + tier;
    }

    // T1 = 5 T2 = 7 T3 = 15

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*5);

        String lore = "&9Cricket" + tier + "\n" +
                "&7Receive &9-"+multiplier+"% &7damage when you or\n" +
                "&7your victims are standing on grass\n" +
                "&aPerma &cRegen I &aon grass!" + "\n&7";

        return colorCode(lore);
    }
}
