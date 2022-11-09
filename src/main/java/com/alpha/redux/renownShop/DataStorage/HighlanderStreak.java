package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class HighlanderStreak extends DataStore{
    public HighlanderStreak(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Includes:\n" +
                "&8- &6Khanate\n"+
                "&8- &6Rush\n"+
                "&8- &6Gold Nano-factory\n\n"+
                "&7Megastreak: &6Highlander");
    }
}
