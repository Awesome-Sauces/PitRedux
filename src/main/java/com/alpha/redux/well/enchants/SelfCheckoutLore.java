package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.bounties;

import java.text.DecimalFormat;

import static com.alpha.redux.events.boards.integerToRoman;

public class SelfCheckoutLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        if(!bounties.BountiesMap.containsKey(event.getDefenders().getPlayerUUID())) return;
        double bounty = bounties.BountiesMap.get(event.getDefenders().getPlayerUUID());

        if(bounty < 5000) return;

        double amount = ((level-1)*1000L) + (level* 1000L);
        DecimalFormat formatter = new DecimalFormat("#,###");

        event.getDefenders().getPlayerObject().sendMessage(colorCode("&6&lSELF-CHECKOUT! &7you cashed in your bounty for &6" + formatter.format(amount) + "g"));

        event.getDefenders().addPlayerGold((int) amount);

        bounties.BountiesMap.put(event.getDefenders().getPlayerUUID(), 0);

    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Self-checkout" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}
        DecimalFormat formatter = new DecimalFormat("#,###");
        String multiplier = formatter.format(((level-1)*1000L) + (level* 1000L));

        String lore = "&9Self-checkout" + tier + "\n" +
                "&7Upon reaching a &65,000g\n" +
                "&7bounty, clear it and gain\n" +
                "&6+"+multiplier+"g&7. Consumes 1 life of\n" +
                "&7this item" + "\n&7";

        return colorCode(lore);
    }
}

