package com.alpha.redux.well.enchants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.events.boards.integerToRoman;

public class ExecutionerLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        double tier = level;

        if(isNPC(event.getDefenders().getPlayerObject())) tier+=7;

        double damage = event.getReduxTrueDamage() + event.getReduxTrueDamage();


        if(event.getDefenders().getPlayerObject().getHealth() - damage <= tier){
            Sounds.EXE.play(event.getAttacker().getPlayerObject());
            Bukkit.getWorld("world").playEffect(event.getDefenders().getPlayerObject().getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
            event.getDefenders().killPlayer(event.getAttacker().getPlayerObject());
            event.getBukkitEvent().setCancelled(true);
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Executioner" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level);

        String lore = "&dRARE! &9Executioner" + tier + "\n" +
                "&7Hitting an enemy to below &c" + multiplier + "\u2764" +
                "\n&7instantly kills them" + "\n&7";

        return colorCode(lore);
    }
}
