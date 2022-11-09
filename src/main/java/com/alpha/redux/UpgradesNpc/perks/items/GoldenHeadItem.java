package com.alpha.redux.UpgradesNpc.perks.items;

import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.items.enchants;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.redux;
import net.minecraft.server.v1_8_R3.EntityHuman;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;

public class GoldenHeadItem implements Listener {

    public void addEffects(Player player) {
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5*20, 1, true, true));

        player.removePotionEffect(PotionEffectType.SPEED);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 8*20, 0, true, true));
    }

    public void addHearts(Player player){

        CraftPlayer CraftPlayer = (CraftPlayer) player; //CraftBukkit
        EntityHuman HumanPlayer = CraftPlayer.getHandle(); //NMS

        double abs = HumanPlayer.getAbsorptionHearts();

        HumanPlayer.setAbsorptionHearts(Math.min((float) abs+6, 12));
    }

    @EventHandler
    public void onConsume(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.LEFT_CLICK_AIR) ||
                event.getAction().equals(Action.LEFT_CLICK_BLOCK) ||
                event.getAction().equals(Action.RIGHT_CLICK_AIR) ||
                event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player player = event.getPlayer();
            ReduxPlayer reduxPlayer = playerExists(player);



            if (player.getItemInHand() != null &&
            player.getItemInHand().getType().equals(enchants.goldenhead.getType())){
                if (reduxPlayer.getGoldenCD()){
                    reduxPlayer.setGoldenCD();

                    int amount = player.getItemInHand().getAmount();

                    player.setItemInHand(null);

                    if(amount > 2) {
                        player.getInventory().setItemInHand(null);
                    }else if(amount > 1) {
                        player.getInventory().setItemInHand(enchants.goldenhead);
                    }

                    addEffects(player);
                    addHearts(player);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            reduxPlayer.setGoldenCD();
                        }
                    }.runTaskLater(redux.INSTANCE, 20L);
                }

            }

        }
    }

}