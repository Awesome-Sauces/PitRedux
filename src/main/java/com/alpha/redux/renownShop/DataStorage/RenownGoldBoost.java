package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class RenownGoldBoost extends DataStore{
    public RenownGoldBoost(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Each tier:\n" +
                "&7Earn &6+1% gold (g) &7from\n" +
                "&7kills and coin pickups.");
    }
}
