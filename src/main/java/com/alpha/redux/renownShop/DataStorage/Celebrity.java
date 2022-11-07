package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Celebrity extends DataStore {
    public Celebrity(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Literally earn &62x gold\n" +
                "&7from kills.");
    }
}
