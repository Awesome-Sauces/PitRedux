package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Heresy extends DataStore{
    public Heresy(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7You have trained in the profane\n" +
                "&7arts of counter-mysticism.");
    }
}
