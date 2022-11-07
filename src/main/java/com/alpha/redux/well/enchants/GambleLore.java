package com.alpha.redux.well.enchants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.entityHandlers.TrueDamage.TrueDamageHandler;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;
import static com.alpha.redux.events.boards.integerToRoman;

public class GambleLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {


        Random rand = new Random(); //instance of random class
        int upperbound = 2;
        int int_random = rand.nextInt(upperbound);
        switch (int_random){
            case 0:
                if(!gambleCooldown(event.getAttacker())) break;
                Sounds.GAMBLE_YES.play(event.getAttacker().getPlayerObject());
                event.addReduxTrueDamage(level*2);
                break;
            case 1:
                if(!gambleCooldown(event.getAttacker())) break;
                Sounds.GAMBLE_NO.play(event.getAttacker().getPlayerObject());
                gambleCalc(event.getAttacker().getPlayerObject(), level*2);
                break;
        }
    }

    private boolean gambleCooldown(ReduxPlayer owner){
        if (owner.getGambleCD()){
            owner.setGambleCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setGambleCD();
                }
            }.runTaskLater(economy.getPlugin(), 5L);
            return true;
        }

        return false;
    }

    private void gambleCalc(Player player, double multiplier){
        new TrueDamageHandler(playerExists(player), playerExists(player), multiplier, 0).run();
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Gamble!" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level);

        String lore = "&dRARE! &9Gamble!" + tier + "\n" +
                "&d50% chance &7to deal &c"+multiplier+"❤&7 true\n" +
                "&7damage to whoever you hit, or to\n" + "&7yourself" + "\n&7";

        return colorCode(lore);
    }
}
