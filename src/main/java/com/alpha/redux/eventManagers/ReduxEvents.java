package com.alpha.redux.eventManagers;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ArmorEvents.ArmorEquipEvent;
import com.alpha.redux.items.enchants;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.redux;
import com.alpha.redux.renownShop.CookieMonster.MonsterHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Random;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.apis.locations.getSpawnProtection;
import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;

public class ReduxEvents implements Listener {
    @EventHandler
    public static void InterceptEntityDamage(ReduxDamageEvent event){

    }

    @EventHandler
    public void onEquips(ArmorEquipEvent event){
        redux.cricketLore.run(event);
        redux.gottaGoFastLore.run(event);
    }

    @EventHandler
    public static void DeathEventHandler(ReduxDeathEvent event){

        MonsterHandler.percentageSpawn(event.getAttacker().getPlayerObject());

        ReduxPlayer ReduxAttacker = event.getAttacker();

        if(!isNPC(ReduxAttacker.getPlayerObject())){
            double r = new Random().nextDouble();
            if(ReduxAttacker.getPlayerObject().getInventory().getItemInHand().equals(enchants.reaper_scythe)){
                if (r < 0.5) {
                    ReduxAttacker.getPlayerObject().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lWOW! &7you found a &3Player Soul&7!"));
                    ReduxAttacker.getPlayerObject().getInventory().addItem(enchants.playerSoul);
                }
            }else if (r < 0.005) {
                ReduxAttacker.getPlayerObject().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lWOW! &7you found a &3Player Soul&7!"));
                ReduxAttacker.getPlayerObject().getInventory().addItem(enchants.playerSoul);
            }
        }


    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        if(!event.getBlock().getType().equals(Material.OBSIDIAN) && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(true);
            return;
        }

        if(event.getBlock().getType().equals(Material.CAKE_BLOCK) || event.getBlock().getType().equals(Material.CAKE)){
            event.setCancelled(true);
            return;
        }

        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;

        if(event.getPlayer().getLocation().getY() >= getSpawnProtection()-13){
            event.setCancelled(true);
            return;
        }

        Location playerloc = event.getBlock().getLocation();

        ReduxPlayer reduxPlayer = playerExists(event.getPlayer());


        Block replaced = event.getBlockReplacedState().getBlock();
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        if (event.getBlockReplacedState().getType() != null && event.getBlockReplacedState().getType() == Material.AIR && event.getBlockReplacedState().getType() != Material.GRASS){
            if (event.getBlock().getType() == Material.OBSIDIAN) {

                Bukkit.getScheduler().scheduleSyncDelayedTask(economy.getPlugin(), new Runnable() {

                    @Override
                    public void run() {

                        //Bukkit.broadcastMessage(String.valueOf(replaced));

                        //if(replaced != null) event.getBlock().setType(replaced.getType());
                        //else
                        event.getBlock().setType(Material.AIR);

                    }
                }, /**/reduxPlayer.getObsidianTime());


            }
        }else{
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockBreakEvent event) {if(event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);}



}
