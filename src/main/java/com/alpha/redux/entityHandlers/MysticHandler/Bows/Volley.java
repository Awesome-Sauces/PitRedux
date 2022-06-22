package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;

public class Volley {
    public Volley(EntityShootBowEvent event){
        BowEnchant volley = new BowEnchant(event, "volley") {
            @Override
            public void ThreeAction() {

                Player shooter = (Player) bowEvent.getEntity();


                Arrow arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);

                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);
            }
            @Override
            public void TwoAction() {

                Player shooter = (Player) bowEvent.getEntity();


                Arrow arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);

                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


            }
            @Override
            public void OneAction() {

                Player shooter = (Player) bowEvent.getEntity();


                Arrow arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);

                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation(), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


            }
        };

        volley.run();
    }
}
