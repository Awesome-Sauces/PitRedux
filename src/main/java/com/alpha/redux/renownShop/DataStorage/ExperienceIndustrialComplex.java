package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class ExperienceIndustrialComplex extends DataStore{
    public ExperienceIndustrialComplex(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Streak &bXP &7bonus scales up to &c200 kills&7,\n" +
                "&b+50 max XP &7per kill.");
    }
}
