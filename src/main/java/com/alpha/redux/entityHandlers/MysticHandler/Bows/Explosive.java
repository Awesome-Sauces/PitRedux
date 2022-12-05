package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class Explosive {
    public Explosive(ProjectileHitEvent event){
        BowEnchant explosive = new BowEnchant(event, "ex") {
            @Override
            public void ThreeAction() {
                Player player = (Player) hitEvent.getEntity().getShooter();

                Arrow arrow = (Arrow) hitEvent.getEntity();

                Location teleportLoc = arrow.getLocation().clone();
                teleportLoc.setYaw(-arrow.getLocation().getYaw());
                teleportLoc.setPitch(-arrow.getLocation().getPitch());


                teleportLoc.getWorld().playEffect(teleportLoc, Effect.EXPLOSION_LARGE, 5);

            }
            @Override
            public void TwoAction() {

                Player player = (Player) hitEvent.getEntity().getShooter();

                Arrow arrow = (Arrow) hitEvent.getEntity();

                Location teleportLoc = arrow.getLocation().clone();
                teleportLoc.setYaw(-arrow.getLocation().getYaw());
                teleportLoc.setPitch(-arrow.getLocation().getPitch());


                teleportLoc.getWorld().playEffect(teleportLoc, Effect.EXPLOSION_HUGE, 5);

            }
            @Override
            public void OneAction() {

                Player player = (Player) hitEvent.getEntity().getShooter();

                Arrow arrow = (Arrow) hitEvent.getEntity();

                Location teleportLoc = arrow.getLocation().clone();
                teleportLoc.setYaw(-arrow.getLocation().getYaw());
                teleportLoc.setPitch(-arrow.getLocation().getPitch());


                teleportLoc.getWorld().playEffect(teleportLoc, Effect.EXPLOSION, 5);

            }
        };

        explosive.run();
    }
}
