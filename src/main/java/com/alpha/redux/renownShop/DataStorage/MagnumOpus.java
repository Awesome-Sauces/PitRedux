package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class MagnumOpus extends DataStore{
    public MagnumOpus(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Includes:\n" +
                "&8- &eLeech\n"+
                "&8- &eAssured Strike\n"+
                "&8- &eApostle to RNGesus\n\n"+
                "&7Megastreak: &eMagnum Opus");
    }
}
