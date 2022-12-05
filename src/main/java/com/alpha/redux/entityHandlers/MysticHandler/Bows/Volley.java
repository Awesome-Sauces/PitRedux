package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.redux;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Volley {
    public Volley(EntityShootBowEvent event){
        BowEnchant volley = new BowEnchant(event, "v") {
            @Override
            public void ThreeAction() {

                Player shooter = (Player) bowEvent.getEntity();


                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);
            }
            @Override
            public void TwoAction() {

                Player shooter = (Player) bowEvent.getEntity();


                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);
            }
            @Override
            public void OneAction() {

                Player shooter = (Player) bowEvent.getEntity();




                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        final double arrowVelo = bowEvent.getProjectile().getVelocity().length();

                        Arrow volleyArrow = shooter.launchProjectile(Arrow.class);
                        volleyArrow.setVelocity(shooter.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

                        Sounds.VOLLEY.play(shooter);
                    }
                }.runTaskLater(redux.INSTANCE,  2L);
            }
        };

        volley.run();
    }
}
