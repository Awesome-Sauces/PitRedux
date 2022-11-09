package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class ExtraHearts extends DataStore{
    public ExtraHearts(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Each tier:\n" +
                "&7Permanently gain an extra heart.");
    }
}
