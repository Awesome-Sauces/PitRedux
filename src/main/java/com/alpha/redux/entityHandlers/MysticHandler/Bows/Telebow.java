package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.redux;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;

public class Telebow {
    public Telebow(ProjectileHitEvent event){
        BowEnchant telebow = new BowEnchant(event, "tele") {
            @Override
            public void ThreeAction() {
                Player player = (Player) hitEvent.getEntity().getShooter();

                if(!player.isSneaking()) return;

                if(!getCooldown(playerExists(player))){
                    Sounds.NO.play(player);
                    return;
                }

                Arrow arrow = (Arrow) hitEvent.getEntity();

                Location teleportLoc = arrow.getLocation().clone();
                teleportLoc.setYaw(-arrow.getLocation().getYaw());
                teleportLoc.setPitch(-arrow.getLocation().getPitch());

                player.teleport(teleportLoc);


            }
            @Override
            public void TwoAction() {
                Player player = (Player) hitEvent.getEntity().getShooter();

                if(!player.isSneaking()) return;

                if(!getCooldown(playerExists(player))){
                    Sounds.NO.play(player);
                    return;
                }

                Arrow arrow = (Arrow) hitEvent.getEntity();

                Location teleportLoc = arrow.getLocation().clone();
                teleportLoc.setYaw(-arrow.getLocation().getYaw());
                teleportLoc.setPitch(-arrow.getLocation().getPitch());

                player.teleport(teleportLoc);

            }
            @Override
            public void OneAction() {
                Player player = (Player) hitEvent.getEntity().getShooter();

                if(!player.isSneaking()) return;

                if(!getCooldown(playerExists(player))){
                    Sounds.NO.play(player);
                    return;
                }

                Arrow arrow = (Arrow) hitEvent.getEntity();

                Location teleportLoc = arrow.getLocation().clone();
                teleportLoc.setYaw(-arrow.getLocation().getYaw());
                teleportLoc.setPitch(-arrow.getLocation().getPitch());

                player.teleport(teleportLoc);

            }
        };

        telebow.run();
    }

    public Telebow(EntityShootBowEvent event){
        BowEnchant telebow = new BowEnchant(event, "tele") {
            @Override
            public void ThreeAction() {
                Player player = (Player) bowEvent.getEntity();
                Arrow arrow = (Arrow) bowEvent.getProjectile();

                if(player.isSneaking()) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if(arrow==null||arrow.isOnGround()||arrow.isDead()) this.cancel();
                            else for(int j = 0; j < 10; j++) arrow.getWorld().playEffect(arrow.getLocation(), Effect.POTION_SWIRL, 0, 30);
                        }
                    }.runTaskTimer(redux.INSTANCE, 0L, 1L);
                }

            }
            @Override
            public void TwoAction() {
                Player player = (Player) bowEvent.getEntity();
                Arrow arrow = (Arrow) bowEvent.getProjectile();

                if(player.isSneaking()) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if(arrow==null||arrow.isOnGround()||arrow.isDead()) this.cancel();
                            else for(int j = 0; j < 10; j++) arrow.getWorld().playEffect(arrow.getLocation(), Effect.POTION_SWIRL, 0, 30);
                        }
                    }.runTaskTimer(redux.INSTANCE, 0L, 1L);
                }

            }
            @Override
            public void OneAction() {
                Player player = (Player) bowEvent.getEntity();
                Arrow arrow = (Arrow) bowEvent.getProjectile();

                if(player.isSneaking()) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if(arrow==null||arrow.isOnGround()||arrow.isDead()) this.cancel();
                            else for(int j = 0; j < 10; j++) arrow.getWorld().playEffect(arrow.getLocation(), Effect.POTION_SWIRL, 0, 30);
                        }
                    }.runTaskTimer(redux.INSTANCE, 0L, 1L);
                }

            }
        };

        telebow.run();
    }

    private boolean getCooldown(ReduxPlayer owner){
        if (owner.getTelebowCD()){
            owner.setTelebowCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setTelebowCD();
                }
            }.runTaskLater(economy.getPlugin(), 600L);
            return true;
        }

        return false;
    }
}
