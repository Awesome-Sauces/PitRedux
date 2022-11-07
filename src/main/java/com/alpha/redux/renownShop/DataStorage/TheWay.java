package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class TheWay extends DataStore{
    public TheWay(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7No perk level requirements.");
    }
}
