package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.apis.locations;
import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import org.bukkit.util.Vector;

public class PullBow {
    public PullBow(ReduxBowEvent event){
        BowEnchant pullBow = new BowEnchant(event, "pull") {
            @Override
            public void ThreeAction() {

                if(event.getAttacker().getPlayerObject().getLocation().getY() >= locations.getSpawnProtection()) return;

                Vector dirVector = event.getAttacker().getPlayerObject().getLocation().toVector().subtract(event.getDefender().getPlayerObject().getLocation().toVector()).setY(0);
                Vector pullVector = dirVector.clone().normalize().setY(0.5).multiply(2.5).add(dirVector.clone().multiply(0.03));
                event.getDefender().getPlayerObject().setVelocity(pullVector.multiply((3 * 0.02) + 1.15));

            }
            @Override
            public void TwoAction() {

                if(event.getAttacker().getPlayerObject().getLocation().getY() >= locations.getSpawnProtection()) return;

                Vector dirVector = event.getAttacker().getPlayerObject().getLocation().toVector().subtract(event.getDefender().getPlayerObject().getLocation().toVector()).setY(0);
                Vector pullVector = dirVector.clone().normalize().setY(0.5).multiply(2.5).add(dirVector.clone().multiply(0.03));
                event.getDefender().getPlayerObject().setVelocity(pullVector.multiply((2 * 0.02) + 1.15));

            }
            @Override
            public void OneAction() {

                if(event.getAttacker().getPlayerObject().getLocation().getY() >= locations.getSpawnProtection()) return;

                Vector dirVector = event.getAttacker().getPlayerObject().getLocation().toVector().subtract(event.getDefender().getPlayerObject().getLocation().toVector()).setY(0);
                Vector pullVector = dirVector.clone().normalize().setY(0.5).multiply(2.5).add(dirVector.clone().multiply(0.03));
                event.getDefender().getPlayerObject().setVelocity(pullVector.multiply((1 * 0.02) + 1.15));

            }
        };

        pullBow.run();
    }
}
