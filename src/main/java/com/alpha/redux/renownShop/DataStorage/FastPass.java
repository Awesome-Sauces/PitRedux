package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class FastPass extends DataStore{
    public FastPass(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Start at level 50 after\n" +
                "&7prestige.");
    }
}
