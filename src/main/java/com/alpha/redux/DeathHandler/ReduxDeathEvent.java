package com.alpha.redux.DeathHandler;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.boosters.Booster;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.events.boards;
import com.alpha.redux.items.enchants;
import com.alpha.redux.playerdata.goldReq;
import com.alpha.redux.redux;
import com.alpha.redux.renownShop.MysticismChance;
import com.alpha.redux.well.enchants.global.*;
import com.nametagedit.plugin.NametagEdit;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;

import java.text.DecimalFormat;
import java.util.List;

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
import static com.alpha.redux.funEvents.event.twoTimesEvent;
import static com.alpha.redux.playerdata.bounties.BountyClaimed;
import static com.alpha.redux.playerdata.bounties.BountyManager;
import static com.alpha.redux.playerdata.economy.addEconomy;
import static com.alpha.redux.playerdata.economy.hasEconomy;
import static com.alpha.redux.playerdata.streaks.*;
import static com.alpha.redux.renownShop.GoldnBoosted.getGoldIncrease;
import static com.alpha.redux.renownShop.xpIncrease.getXpIncrease;

public class ReduxDeathEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private final ReduxPlayer attacker;
    private final ReduxPlayer defender;
    private double xp = 9;
    private int xp_cap = 200;
    private double gold = 13;
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
        
        if(!isNPC(defender.getPlayerObject())) {
            deleteBlob(defender.getPlayerObject());
            ClearAndCheck(defender.getPlayerObject());
        }
        
        if(!isNPC(attacker.getPlayerObject())) customDrops();
        
        if(!isNPC(attacker.getPlayerObject())) killEnchants();

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
            hasStreak(defender.getPlayerObject().getDisplayName());
            setStreak(defender.getPlayerUUID(), 0);
            
            // Refil Health
            defender.getPlayerObject().setHealth(defender.getPlayerObject().getMaxHealth());
            defender.getPlayerObject().setMaxHealth(20);

            // Refresh tab name
            NametagEdit.getApi().setNametag(defender.getPlayerObject(), ChatEventApiGetLevelColor(defender.getPlayerObject().getDisplayName(), defender.getPlayerUUID())+ rank.getNameColor(defender.getPlayerObject()), "");
        }

        // Mega Streak Calculations
        hasStreak(attacker.getPlayerUUID());
        hasMegaStreak(attacker.getPlayerUUID());
        String streak = getMegaStreak(attacker.getPlayerUUID());

        // Clearing Potions
        defender.removePotionEffect(PotionEffectType.SPEED);
        attacker.removePotionEffect(PotionEffectType.SPEED);


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

            double inc = (double) getGoldIncrease(attacker.getPlayerUUID())/100;

            new XpbumpLore().run(this);
            new XpboostLore().run(this);
            new SweatyLore().run(this);

            new MoctezumaLore().run(this);
            new GoldbumpLore().run(this);
            new GoldboostLore().run(this);

            addGold((int) Math.round(getGold()*inc));
        }

        if(streak.equals("highlander") && getStreak(attacker.getPlayerUUID()) >= 50){
            addGold((int) Math.round(getGold()*1.1));

            addGold(Math.min(100, Math.round((float)getStreak(getAttacker().getPlayerUUID())/3)));
        }else if(streak.equals("uber") && getStreak(attacker.getPlayerUUID()) >= 200 && attacker.getPlayerObject().getMaxHealth()/2 == 10){
            attacker.getPlayerObject().setMaxHealth(attacker.getPlayerObject().getMaxHealth()-4);
        }

        // Boosters
        int XP_BOOSTER = 1;
        int GOLD_BOOSTER = 1;
        if(Booster.xpActive) XP_BOOSTER+=1;
        if(Booster.goldActive) GOLD_BOOSTER+=1;

        xp_cap+=getXpIncrease(attacker.getPlayerUUID());

        gold = gold*GOLD_BOOSTER;
        xp = xp*XP_BOOSTER;

        gold = gold*twoTimesEvent;
        xp = xp*twoTimesEvent;

        if(XP_BOOSTER>1) xp_cap+=100;
        if(twoTimesEvent>1) xp_cap+=100;

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
                attacker.getPlayerObject().sendMessage(ChatColor.GREEN + colorCode("&lKILL! ") + ChatColor.GRAY + "on " + defender.getPlayerObject().getDisplayName() + ChatColor.RESET + ChatColor.AQUA + " +" + Math.min(this.xp, xp_cap) + "XP" + ChatColor.GOLD + " +" + Math.min(this.gold, this.gold_cap) + "g");
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
            defender.getPlayerObject().teleport(getSpawnLocation(defender.getPlayerObject().getWorld()));
        }

        // Standard Messages
        defender.getPlayerObject().sendMessage(ChatColor.RED + colorCode("&lDEATH! ") + ChatColor.GRAY + "by " + attacker.getPlayerObject().getDisplayName());
        killTitle(defender.getPlayerObject());
        BountyClaimed(defender.getPlayerObject(), attacker.getPlayerObject());
        if(!isNPC(attacker.getPlayerObject())) BountyManager(attacker.getPlayerObject());

        if(streak.equals("moon") && getStreak(attacker.getPlayerUUID()) >= 100){
            attacker.addMoonXP((int)Math.round( Math.min(xp_cap, xp)));
        }

        if(!isNPC(attacker.getPlayerObject())) goldReq.addGoldReq(attacker.getPlayerUUID(), Math.min((int) Math.round(gold), xp_cap));
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
                }
            }
        }
    }
    
    public void customDrops(){
        if(isNPC(defender.getPlayerObject()) && percentChance(0.001)){
            attacker.getPlayerObject().getInventory().addItem(enchants.vile);
            attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.DARK_PURPLE + " Vile!");
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }else if(!isNPC(defender.getPlayerObject()) && percentChance(0.10)){
            attacker.getPlayerObject().getInventory().addItem(enchants.vile);
            attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.DARK_PURPLE + " Vile!");
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }

        if(percentChance(MysticismChance.getMysticismChance(attacker.getPlayerUUID()))){
            attacker.getPlayerObject().getInventory().addItem(enchants.fresh_sword);
            attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.RED + " Mystic Sword!");
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }else if (percentChance(MysticismChance.getMysticismChance(attacker.getPlayerUUID()))) {
            attacker.getPlayerObject().getInventory().addItem(enchants.fresh_bow);
            attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.RED + " Mystic Bow!");
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }else if(percentChance(MysticismChance.getMysticismChance(attacker.getPlayerUUID()))){
            while (true){
                if(percentChance(.20)){
                    attacker.getPlayerObject().getInventory().addItem(enchants.fresh_greens);
                    attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.GREEN + " Green Fresh!");
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    attacker.getPlayerObject().getInventory().addItem(enchants.fresh_blues);
                    attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.BLUE + " Blue Fresh!");
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    attacker.getPlayerObject().getInventory().addItem(enchants.fresh_reds);
                    attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.RED + " Red Fresh!");
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    attacker.getPlayerObject().getInventory().addItem(enchants.fresh_oranges);
                    attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.GOLD + " Orange Fresh!");
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    attacker.getPlayerObject().getInventory().addItem(enchants.fresh_yellows);
                    attacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.YELLOW + " Yellow Fresh!");
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }
            }
        }
    }
}
