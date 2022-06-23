package com.alpha.redux.entityHandlers.MysticHandler.Pants.data;

import com.alpha.redux.playerdata.economy;
import com.alpha.redux.playerdata.prestiges;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

import static com.alpha.redux.DeathHandler.ProccessHit.KillMan;
import static com.alpha.redux.DeathHandler.killHandler.getNPC;
import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.apis.leaderboardsplus.leaderboards.RefreshBoard;
import static com.alpha.redux.apis.locations.getBotSpawnLocation;
import static com.alpha.redux.funEvents.event.handleTwoEvent;
import static com.alpha.redux.playerdata.prestiges.getPrestigeMap;

public class PitBlobMap {

    public static HashMap<Player, Slime> blob = new HashMap<>();
    public static HashMap<Player, Integer> blobStreak = new HashMap<>();
    private static HashMap<Player, Integer> blobHealth = new HashMap<>();
    public static HashMap<Player, BukkitTask> runnable = new HashMap<>();


    public static void addBlob(Player player, Slime slime){
        blob.put(player, slime);

        if(!blob.containsKey(player) && !blobStreak.containsKey(player)) return;
    }

    public static Player getPlayerFromBlob(Slime slime){
        for(Player player : blob.keySet()) if(blob.get(player).equals(slime)) return player;

        return null;
    }

    public static void addBlobStreak(Player player){
        if(!blobStreak.containsKey(player)){
            blobStreak.put(player, 1);
        }else{
            blobStreak.put(player, blobStreak.get(player) + 1);
        }
    }

    public static int getBlobHealth(Player player){
        if(blobHealth.containsKey(player)) return blobHealth.get(player);
        return 0;
    }

    public static void addBlobHealth(Player player){
        if(!blobHealth.containsKey(player)){
            blobHealth.put(player, 20);
        }else{
            blobHealth.put(player, Math.min(20, blobHealth.get(player) + 1));
        }
    }

    public static void removeBlobHealth(Player player){
        if(blobHealth.containsKey(player)) blobHealth.put(player, blobHealth.get(player) - 1);
    }

    public static void resizeBlob(Player player){
        if(blob.containsKey(player)){
            int blobSize = blob.get(player).getSize();

            if(blobSize>=5) return;

            if(blobStreak.containsKey(player)){
                int streak = blobStreak.get(player);
                if((streak % 5) == 0) blob.get(player).setSize(blobSize+1);
            }

        }
    }

    public static void deleteBlob(Player player){
        if(!blob.containsKey(player) && !blobStreak.containsKey(player)) return;

        Slime slime = blob.get(player);

        runnable.get(player).cancel();
        runnable.remove(player);

        blob.remove(player);
        blobStreak.remove(player);

        slime.remove();



    }

    public static void blobTick(Player player){
        if(!blob.containsKey(player)) createBlob(player);

        addBlobStreak(player);
        addBlobHealth(player);

        resizeBlob(player);

    }

    private static void createBlob(Player player){
        Slime slime = (Slime) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
        slime.setRemoveWhenFarAway(false);
        slime.setSize(1);


        addBlob(player, slime);
        addBlobHealth(player);
        addBlobStreak(player);
        resizeBlob(player);

        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {

                for(Entity entity : slime.getNearbyEntities(7, 7, 7)){
                    LivingEntity livingEntity = (LivingEntity) entity;

                    if(livingEntity instanceof Player && isNPC((Player) livingEntity)){
                        Player living = (Player) livingEntity;

                        getNPC(living).teleport(getBotSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);

                        living.setHealth(living.getMaxHealth());

                        KillMan(player, living);

                    }

                }

            }
        }.runTaskTimer(economy.getPlugin(), 100, 100);

        runnable.put(player, task);


    }

}
