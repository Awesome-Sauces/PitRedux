package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class MoonStreak extends DataStore {
    public MoonStreak(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Includes:\n" +
                "&8- &bSuper Streaker\n"+
                "&8- &bGold Stack\n"+
                "&8- &bXP Stack\n\n"+
                "&7Megastreak: &bTo the Moon");
    }
}
