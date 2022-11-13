package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Volley {
    public Volley(EntityShootBowEvent event){
        BowEnchant volley = new BowEnchant(event, "v") {
            @Override
            public void ThreeAction() {

                Player shooter = (Player) bowEvent.getEntity();

                if(!shooter.isOp()){
                    shooter.sendMessage(colorCode("&c&lTemporarily Disabled!"));
                    Sounds.NO.play(shooter);
                    return;
                }

                Arrow arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);

                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);

                Sounds.VOLLEY.play(shooter);
            }
            @Override
            public void TwoAction() {

                Player shooter = (Player) bowEvent.getEntity();

                if(!shooter.isOp()){
                    shooter.sendMessage(colorCode("&c&lTemporarily Disabled!"));
                    Sounds.NO.play(shooter);
                    return;
                }

                Arrow arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);

                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);

                Sounds.VOLLEY.play(shooter);
            }
            @Override
            public void OneAction() {

                Player shooter = (Player) bowEvent.getEntity();

                if(!shooter.isOp()){
                    shooter.sendMessage(colorCode("&9Volley &c&lTemporarily Disabled!"));
                    Sounds.NO.play(shooter);
                    return;
                }

                Arrow arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);

                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);


                arrow = (Arrow) bowEvent.getProjectile().getWorld().spawnEntity(bowEvent.getProjectile().getLocation().add(0,-1,0), EntityType.ARROW);
                arrow.setCritical(true);
                arrow.setVelocity(bowEvent.getProjectile().getVelocity());
                arrow.setShooter(shooter);

                Sounds.VOLLEY.play(shooter);
            }
        };

        volley.run();
    }
}
