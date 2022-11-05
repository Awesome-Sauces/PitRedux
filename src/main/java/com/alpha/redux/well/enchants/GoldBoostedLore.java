package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import net.minecraft.server.v1_8_R3.EntityHuman;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import static com.alpha.redux.events.boards.integerToRoman;

public class GoldBoostedLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        CraftPlayer craftAttacker = (CraftPlayer) event.getAttacker().getPlayerObject(); //CraftBukkit
        EntityHuman entityAttacker = craftAttacker.getHandle(); //NMS

        //EntityHuman entityDefender = craftDefender.getHandle(); //NMS
        double abs = entityAttacker.getAbsorptionHearts();
        if(abs>0){
            double damage = (double) (level*5)/100;

            event.addReduxDamage(event.getReduxDamage() * (damage));
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Gold and Boosted" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*5);

        String lore = "&9Gold and Boosted" + tier + "\n" +
                "&7Deal &c+" + multiplier + "% &7damage when you have\n" +
                "&7absorption hearts" + "\n&7";

        return colorCode(lore);
    }
}

