package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class UberStreak extends DataStore{
    public UberStreak(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Includes:\n\n" +
                "&7Megastreak: &dUberstreak");
    }
}
