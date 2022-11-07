package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class RenownXpBump extends DataStore{
    public RenownXpBump(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Each tier:\n" +
                "&7Earn &b+1 kill XP&7.");
    }
}
