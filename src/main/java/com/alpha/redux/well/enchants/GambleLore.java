package com.alpha.redux.well.enchants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.TrueDamage.TrueDamageHandler;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Player;

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
                Sounds.GAMBLE_YES.play(event.getAttacker().getPlayerObject());
                event.addReduxTrueDamage(level*2);
                break;
            case 1:
                Sounds.GAMBLE_NO.play(event.getAttacker().getPlayerObject());
                gambleCalc(event.getAttacker().getPlayerObject(), level*2);
                break;
        }
    }

    private void gambleCalc(Player player, double trueAmount){
        new TrueDamageHandler(playerExists(player), playerExists(player), trueAmount, 0).run();
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
                "&d50% chance &7to deal &c"+multiplier+"\u2764&7 true\n" +
                "&7damage to whoever you hit, or to\n" + "&7yourself" + "\n&7";

        return colorCode(lore);
    }
}
