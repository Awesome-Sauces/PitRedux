package com.alpha.redux.DeathHandler;

import com.alpha.redux.Stash.StashCore;
import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.boosters.Booster;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.events.boards;
import com.alpha.redux.items.enchants;
import com.alpha.redux.playerdata.Renown;
import com.alpha.redux.playerdata.goldReq;
import com.alpha.redux.redux;
import com.alpha.redux.renownShop.DataStorage.BotKills;
import com.alpha.redux.renownShop.MysticismChance;
import com.alpha.redux.well.enchants.global.*;
import com.nametagedit.plugin.NametagEdit;
import me.alpha.hunter.api.HunterAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;

import java.text.DecimalFormat;
import java.util.List;

import static com.alpha.redux.DeathHandler.ProccessHit.KillMan;
import static com.alpha.redux.DeathHandler.jewls.PlayerFinishedJewl;
import static com.alpha.redux.DeathHandler.killHandler.*;
import static com.alpha.redux.apis.chatManager.rank.ChatEventApiGetLevelColor;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.apis.locations.getBotSpawnLocation;
import static com.alpha.redux.apis.locations.getSpawnLocation;
import static com.alpha.redux.commands.command.KillMessages;
import static com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap.deleteBlob;
import static com.alpha.redux.events.events.Strength;
import static com.alpha.redux.events.nonPermItems.ClearAndCheck;
import static com.alpha.redux.events.nonPermItems.ClearRegular;
import static com.alpha.redux.funEvents.event.twoTimesEvent;
import static com.alpha.redux.playerdata.bounties.BountyClaimed;
import static com.alpha.redux.playerdata.bounties.BountyManager;
import static com.alpha.redux.playerdata.economy.addEconomy;
import static com.alpha.redux.playerdata.economy.hasEconomy;
import static com.alpha.redux.playerdata.streaks.*;

public class ReduxDeathEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private final ReduxPlayer attacker;
    private final ReduxPlayer defender;
    private double xp = 14;
    private int xp_cap = 200;
    private int mystic_chance=0;
    private double gold = 18;
    private double gold_cap = 2500;
    private boolean isCancelled;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public ReduxDeathEvent(ReduxPlayer attacker, ReduxPlayer defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.isCancelled = false;
    }

    public void run(){

        if(!isNPC(defender.getPlayerObject())){
            hasMegaStreak(defender.getPlayerUUID());
            hasStreak(defender.getPlayerUUID());
            if(getMegaStreak(defender.getPlayerUUID()).equals("overdrive") &&
                    getStreak(defender.getPlayerUUID())>=50){
                defender.addPlayerEXP(4000);
                Sounds.SHOCKWAVE.play(defender.getPlayerObject());
                defender.getPlayerObject().sendMessage(colorCode("&a&lCONGRATS! &7you earned &b4,000 XP &7from &cOverdrive&7!"));
            }
        }


        if(isNPC(attacker.getPlayerObject()) && isNPC(defender.getPlayerObject())){
            NPC npc = getNPC(defender.getPlayerObject());
            npc.teleport(getBotSpawnLocation(npc.getEntity().getWorld()), PlayerTeleportEvent.TeleportCause.PLUGIN);
            return;
        }
        
        if(!isNPC(defender.getPlayerObject())) deleteBlob(defender.getPlayerObject());
        if(!isNPC(defender.getPlayerObject())){
            if(defender.getPerks().contains(redux.assistantStreaker.getRefID()) && redux.promotion.hasValue(defender.getPlayerUUID()) &&
                    (((Integer)redux.promotion.getValue(defender.getPlayerUUID()))>=1) && getStreak(defender.getPlayerUUID())>=100){
                if(!isNPC(defender.getPlayerObject())) ClearRegular(defender.getPlayerObject());
                defender.getPlayerObject().sendMessage(colorCode("&e&lPROMOTION! &7you managed to reach a &c100 killstreak &7and kept your mystic lives!"));
            }else if(!isNPC(defender.getPlayerObject())) {
                ClearAndCheck(defender.getPlayerObject());
            }
        }

        // Defender Streak tick
        if (!isNPC(defender.getPlayerObject())) {

            if(!isNPC(defender.getPlayerObject()) &&
                    getMegaStreak(getDefender().getPlayerUUID()).equals("moon") && getStreak(defender.getPlayerUUID()) >= 100){
                DecimalFormat formatter = new DecimalFormat("#,###");
                defender.getPlayerObject().sendMessage(colorCode("&b&lTO THE MOON! &7Earned &b+"+formatter.format(defender.getMoonXP()*defender.getMoonMultiplier())+" XP &7from megastreak (&b"+defender.getMoonMultiplier()+"x &7multiplier)"));
                defender.addPlayerEXP((int) Math.round(defender.getMoonXP()*defender.getMoonMultiplier()));
                Sounds.DEATH_GHAST_SCREAM.play(defender.getPlayerObject());
            }

            defender.setMoonXP(0);
            // Check if user has pending uber rewards
            UberRewardClaimDeath(defender.getPlayerObject());
            
            // Resets streaks
            hasStreak(defender.getPlayerUUID());
            setStreak(defender.getPlayerUUID(), 0);
            
            // Refil Health
            defender.getPlayerObject().setHealth(defender.getPlayerObject().getMaxHealth());
            if(!isNPC(defender.getPlayerObject())&&
            redux.extraHearts.hasValue(defender.getPlayerUUID())){
                defender.getPlayerObject().setMaxHealth(20+((Integer)redux.extraHearts.getValue(defender.getPlayerUUID(), 1)*2));
            }else{
                defender.getPlayerObject().setMaxHealth(20);
            }

            // Refresh tab name
            if(!isNPC(defender.getPlayerObject())) NametagEdit.getApi().setNametag(defender.getPlayerObject(), ChatEventApiGetLevelColor(defender.getPlayerObject().getDisplayName(), defender.getPlayerUUID())+ rank.getNameColor(defender.getPlayerObject()), "");
        }

        // Mega Streak Calculations
        hasStreak(attacker.getPlayerUUID());
        hasMegaStreak(attacker.getPlayerUUID());
        String streak = getMegaStreak(attacker.getPlayerUUID());

        // Clearing Potions
        defender.removePotionEffect(PotionEffectType.SPEED);
        attacker.removePotionEffect(PotionEffectType.SPEED);


        // Sound effects for mega
        if(streak.equals("beastmode") ||
                streak.equals("overdrive") ||
                streak.equals("highlander")){
            if(getStreak(attacker.getPlayerUUID())==50){

                String megastreakMessage = "&c&lOVERDRIVE";

                if(streak.equals("beastmode")){
                    megastreakMessage="&a&lBEASTMODE";
                }else if(streak.equals("highlander")){
                    megastreakMessage="&6&lHIGHLANDER";
                }

                Bukkit.broadcastMessage(colorCode("&c&lMEGASTREAK! " +
                        ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID()) + rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName()
                        + " &7activated " + megastreakMessage));

                Sounds.MEGA_GENERAL.play(attacker.getPlayerObject());
            }
        }else if(streak.equals("moon") ||
                    streak.equals("magnum")||
                streak.equals("uber")){
                if(getStreak(attacker.getPlayerUUID())==100){
                    String megastreakMessage = "&b&lTO THE MOON";

                    if(streak.equals("uber")){
                        megastreakMessage="&d&lUBERSTREAK";
                        Bukkit.broadcastMessage(colorCode("&c&lMEGASTREAK! " +
                                ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID()) + rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName()
                                + " &7activated " + megastreakMessage));
                    }else if(streak.equals("moon")){
                        Bukkit.broadcastMessage(colorCode("&c&lMEGASTREAK! " +
                                ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID()) + rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName()
                                +" &7activated " + megastreakMessage));
                    }
                    Sounds.MEGA_GENERAL.play(attacker.getPlayerObject());
                }
            }

        // Megastreak calcs
        if(streak.equals("beastmode") && getStreak(attacker.getPlayerUUID()) >= 50){
            addXp(xp*.5);
            addGold((int) Math.round(gold*.75));
            setXp_cap(getXp_cap()+200);

            addXp(100);
            addXp(Math.min(Math.round((float)getStreak(getAttacker().getPlayerUUID())/3), 200));
        }else if(streak.equals("overdrive") && getStreak(attacker.getPlayerUUID()) >= 50){
            addXp(xp);
            addGold((int) Math.round(getGold()*.5));
            setXp_cap(getXp_cap()+100);

            addXp(50);
            addXp(Math.min(Math.round((float)getStreak(getAttacker().getPlayerUUID())/3), 100));
        }else if(streak.equals("moon") && getStreak(attacker.getPlayerUUID()) >= 100){
            addXp(100);
            addXp(xp*.2);
            setXp_cap(getXp_cap()+550);
            addXp(Math.min(Math.round((float)getStreak(getAttacker().getPlayerUUID())/2), 350));
        }

        // Gold/XP calculations
        if(!isNPC(attacker.getPlayerObject())){


            new XpbumpLore().run(this);
            new XpboostLore().run(this);
            new SweatyLore().run(this);

            new MoctezumaLore().run(this);
            new GoldbumpLore().run(this);
            new GoldboostLore().run(this);

        }

        if(streak.equals("highlander") && getStreak(attacker.getPlayerUUID()) >= 50){
            addGold((int) Math.round(getGold()*1.1));

            addGold(Math.min(100, Math.round((float)getStreak(getAttacker().getPlayerUUID())/3)));
        }else if(streak.equals("uber") && getStreak(attacker.getPlayerUUID()) >= 100){
            mystic_chance=5;
        }else if(streak.equals("magnum") && getStreak(attacker.getPlayerUUID()) >= 100){
            Bukkit.broadcastMessage(colorCode("&c&lMEGASTREAK! " +ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID()) + rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName() +" &7activated &e&lMAGNUM OPUS &7and exploded! So smart!"));
            attacker.getPlayerObject().getWorld().playEffect(attacker.getPlayerObject().getLocation(), Effect.EXPLOSION_LARGE, 10);
            Sounds.JUGGERNAUT_EXPLOSION.play(attacker.getPlayerObject());
            Renown.addRenown(attacker.getPlayerUUID(), 7);
            KillMan(defender.getPlayerObject(), attacker.getPlayerObject());
        }else if(streak.equals("uber") && getStreak(attacker.getPlayerUUID()) >= 200 && attacker.getPlayerObject().getMaxHealth()/2 == 10){
            attacker.getPlayerObject().setMaxHealth(attacker.getPlayerObject().getMaxHealth()-4);
        }

        // Boosters
        int XP_BOOSTER = 1;
        int GOLD_BOOSTER = 1;
        if(Booster.xpActive) XP_BOOSTER+=1;
        if(Booster.goldActive) GOLD_BOOSTER+=1;

        if(redux.experienceIndustrialComplex.hasValue(attacker.getPlayerUUID())){
            this.xp += xp*.2;
        }

        gold = gold*GOLD_BOOSTER;
        xp = xp*XP_BOOSTER;

        gold = gold*twoTimesEvent;
        xp = xp*twoTimesEvent;

        if(XP_BOOSTER>1) xp_cap+=300;
        if(twoTimesEvent>1) xp_cap+=300;

        // Celebrity
        if(!isNPC(attacker.getPlayerObject())){
            if(redux.celebrity.hasValue(attacker.getPlayerUUID())) {
                gold += gold;
            }
        }

        if(!isNPC(attacker.getPlayerObject())){
            if(redux.renownXpBump.hasValue(attacker.getPlayerUUID())){
                this.xp += ((Integer)redux.renownXpBump.getValue(attacker.getPlayerUUID()));
            }

            if(redux.experienceIndustrialComplex.hasValue(attacker.getPlayerUUID())){
                this.xp_cap += 50;
            }

            if(redux.renownGoldBoost.hasValue(attacker.getPlayerUUID())){
                this.gold += gold*(((double)((Integer)redux.renownGoldBoost.getValue(attacker.getPlayerUUID())))/100);
            }

            if(redux.tenacity.hasValue(attacker.getPlayerUUID())){
                attacker.getPlayerObject().setHealth(Math.min(attacker.getPlayerObject().getMaxHealth(),
                        attacker.getPlayerObject().getHealth()+
                                (((double)((Integer)redux.tenacity.getValue(attacker.getPlayerUUID())))/10)));
            }
        }

        if(isNPC(defender.getPlayerObject())&&
        !isNPC(attacker.getPlayerObject())){
            if(redux.botKills.hasValue(attacker.getPlayerObject().getUniqueId().toString())){
                redux.botKills.addValue(attacker.getPlayerObject().getUniqueId().toString(),1);
            }else{
                redux.botKills.setValue(attacker.getPlayerObject().getUniqueId().toString(),1);
            }

            if(((int)redux.botKills.getValue(attacker.getPlayerObject().getUniqueId().toString()))==40000){
                redux.factionReward.setValue(attacker.getPlayerObject().getUniqueId().toString(), "unclaimed");
            }
        }

        xp = (int) Math.round(xp);
        gold = (int) Math.round(gold);

        // Attacker Streak tick
        if(!isNPC(attacker.getPlayerObject())){
            hasStreak(attacker.getPlayerUUID());
            addStreak(attacker.getPlayerUUID(), 1);
            multiKill(attacker.getPlayerObject());
            PlayerFinishedJewl(attacker.getPlayerObject());

            if(getStreak(attacker.getPlayerUUID()) <= 49){
                NametagEdit.getApi().setNametag(attacker.getPlayerObject(), ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID())+ rank.getNameColor(attacker.getPlayerObject()), "");
            }

            if(!KillMessages.containsKey(attacker.getPlayerUUID())){
                KillMessages.put(attacker.getPlayerUUID(), true);
            }else if(KillMessages.get(attacker.getPlayerUUID()).equals(true)){
                if(isNPC(defender.getPlayerObject())){
                    attacker.getPlayerObject().sendMessage(ChatColor.GREEN + colorCode("&lKILL! ") + ChatColor.GRAY + "on " + getNPC(defender.getPlayerObject()).getName() + ChatColor.RESET + ChatColor.AQUA + " +" + String.valueOf((int)Math.min(this.xp, xp_cap)) + "XP" + ChatColor.GOLD + " +" + String.valueOf((int) Math.min(this.gold, this.gold_cap)) + "g");
                }else{
                    attacker.getPlayerObject().sendMessage(ChatColor.GREEN + colorCode("&lKILL! ") + ChatColor.GRAY + "on " + defender.getPlayerObject().getDisplayName() + ChatColor.RESET + ChatColor.AQUA + " +" + String.valueOf((int)Math.min(this.xp, xp_cap)) + "XP" + ChatColor.GOLD + " +" + String.valueOf((int) Math.min(this.gold, this.gold_cap)) + "g");
                }
            }

        }

        // Streak Messages
        if(!isNPC(attacker.getPlayerObject())){
            // &c&lSTREAK! &7of &c5 &7kills by <level_username>
            hasStreak(attacker.getPlayerUUID());

            if(getStreak(attacker.getPlayerUUID())%5==0){
                Bukkit.broadcastMessage(colorCode("&c&lSTREAK! &7of &c"+getStreak(attacker.getPlayerUUID())+" &7kills by <level_username>", attacker));
            }

        }

        // Bounty Handling
        if(!isNPC(defender.getPlayerObject())) BountyClaimed(defender.getPlayerObject(), attacker.getPlayerObject());

        // Kill rewards
        if(!isNPC(attacker.getPlayerObject())){
            attacker.addPlayerEXP((int) Math.round(Math.min(this.xp_cap, this.xp)));
            hasEconomy(attacker.getPlayerUUID());
            addEconomy(attacker.getPlayerUUID(), Math.min((int) Math.round(this.gold), xp_cap));
        }

        // Teleporting
        if(isNPC(defender.getPlayerObject())){
            NPC npc = getNPC(defender.getPlayerObject());
            if(npc.getEntity()!=null) npc.teleport(getBotSpawnLocation(npc.getEntity().getWorld()), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }else if (!isNPC(defender.getPlayerObject())){
            defender.getPlayerObject().teleport(getSpawnLocation(defender.getPlayerObject().getWorld()), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }

        // Standard Messages
        if(!isNPC(defender.getPlayerObject())) {
            if(isNPC(attacker.getPlayerObject())){
                defender.getPlayerObject().sendMessage(ChatColor.RED + colorCode("&lDEATH! ") + ChatColor.GRAY + "by " + HunterAPI.getRandomName());
            }else{
                defender.getPlayerObject().sendMessage(ChatColor.RED + colorCode("&lDEATH! ") + ChatColor.GRAY + "by " + attacker.getPlayerObject().getDisplayName());
            }
        }
        killTitle(defender.getPlayerObject());
        BountyClaimed(defender.getPlayerObject(), attacker.getPlayerObject());
        if(!isNPC(attacker.getPlayerObject())) BountyManager(attacker.getPlayerObject());

        if(streak.equals("moon") && getStreak(attacker.getPlayerUUID()) >= 100){
            attacker.addMoonXP((int)Math.round( Math.min(xp_cap, xp)));
        }

        if(!isNPC(attacker.getPlayerObject())) goldReq.addGoldReq(attacker.getPlayerUUID(), Math.min((int) Math.round(gold), xp_cap));

        if(!isNPC(attacker.getPlayerObject())) killEnchants();

        if(!isNPC(attacker.getPlayerObject())) customDrops();

        // Final TICK Scoreboard refresh
        /*
        if(!isNPC(defender.getPlayerObject()) &&
            !isNPC(attacker.getPlayerObject())){
            attacker.refreshScoreBoard();
            defender.refreshScoreBoard();
        }

         */
    }
    
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public ReduxPlayer getAttacker() {
        return this.attacker;
    }

    public ReduxPlayer getDefender() {
        return this.defender;
    }

    public int getXp() {
        return (int) xp;
    }

    public void addXp(double xps) {
        this.xp += xps;
    }

    public int getGold() {
        return (int) gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public int getXp_cap() {
        return xp_cap;
    }

    public void setXp_cap(int xp_cap) {
        this.xp_cap = xp_cap;
    }

    private static Boolean percentChance(double chance) {
        return Math.random() <= chance;
    }

    private void killTitle(Player player){
        if(!isNPC(player)) boards.CreateScore(player);
        player.removePotionEffect(PotionEffectType.SLOW);
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"YOU DIED\",\"color\":\"red\"}"), 100, 20, 20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }

    private void killEnchants(){
        CraftPlayer craftAttacker = (CraftPlayer) attacker.getPlayerObject(); //CraftBukkit
        EntityHuman entityAttacker = craftAttacker.getHandle(); //NMS

        //EntityHuman entityDefender = craftDefender.getHandle(); //NMS
        double abs = entityAttacker.getAbsorptionHearts();


        // Applying Gold and checking for extra enchants

        List<String> list = attacker.getPantEnchants();
        if(list != null){
            for (String s : list) {
                switch (s) {

                    case "blobIII":
                    case "blobII":
                    case "blobI":
                        PitBlobMap.blobTick(attacker.getPlayerObject());
                        break;


                    case "goldheartIII":
                        attacker.getPlayerObject().removePotionEffect(PotionEffectType.ABSORPTION);
                        entityAttacker.setAbsorptionHearts((float) Math.min(abs + 4, 12.0));
                        break;
                    case "goldheartII":
                        attacker.getPlayerObject().removePotionEffect(PotionEffectType.ABSORPTION);
                        entityAttacker.setAbsorptionHearts((float) Math.min(abs + 2, 10.0));
                        break;
                    case "goldheartI":
                        attacker.getPlayerObject().removePotionEffect(PotionEffectType.ABSORPTION);
                        entityAttacker.setAbsorptionHearts((float) Math.min(abs + 1, 8.0));
                        break;

                    case "pantsradarIII":
                        mystic_chance+=9;
                        break;
                    case "pantsradarII":
                        mystic_chance+=5;
                        break;
                    case "pantsradarI":
                        mystic_chance+=2;
                        break;
                }
            }
        }
    }
    
    public void customDrops(){
        if(isNPC(defender.getPlayerObject()) && percentChance(0.005) &&
        redux.heresy.hasValue(attacker.getPlayerUUID())){
            StashCore.safeGive(attacker.getPlayerObject(), enchants.vile);
            attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.DARK_PURPLE + " Vile!");
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }else if(!isNPC(defender.getPlayerObject()) && percentChance(0.50) &&
                redux.heresy.hasValue(attacker.getPlayerUUID())){
            StashCore.safeGive(attacker.getPlayerObject(), enchants.vile);
            attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.DARK_PURPLE + " Vile!");
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }

        if(redux.mysticism.hasValue(attacker.getPlayerUUID())&&
                percentChance(((double)((Integer) redux.mysticism.getValue(attacker.getPlayerUUID()))+mystic_chance)/1500)){
            while (true){
                if(percentChance(.20)){
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_greens);
                    if(isNPC(defender.getPlayerObject())){
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    }else{
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID())+ rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName() + attacker.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_blues);
                    if(isNPC(defender.getPlayerObject())){
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    }else{
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID())+ rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName() + attacker.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_reds);
                    if(isNPC(defender.getPlayerObject())){
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    }else{
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID())+ rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName() + attacker.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_oranges);
                    if(isNPC(defender.getPlayerObject())){
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    }else{
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID())+ rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName() + attacker.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_yellows);
                    if(isNPC(defender.getPlayerObject())){
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    }else{
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID())+ rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName() + attacker.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }
            }
        }else if (redux.mysticism.hasValue(attacker.getPlayerUUID())&&
                percentChance(((double)((Integer) redux.mysticism.getValue(attacker.getPlayerUUID()))+mystic_chance)/1500)) {
            StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_bow);
            if(isNPC(defender.getPlayerObject())){
                attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                        " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
            }else{
                attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                        " &7dropped from killing " +
                        ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID())+ rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName() + "&7!"));
            }
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }else if(redux.mysticism.hasValue(attacker.getPlayerUUID())&&
                percentChance(((double)((Integer) redux.mysticism.getValue(attacker.getPlayerUUID()))+mystic_chance)/1500)){
            StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_sword);
            if(isNPC(defender.getPlayerObject())){
                attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                        " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
            }else{
                attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                        " &7dropped from killing " +
                        ChatEventApiGetLevelColor(attacker.getPlayerObject().getDisplayName(), attacker.getPlayerUUID())+ rank.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName() + "&7!"));
            }
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }
    }
}
