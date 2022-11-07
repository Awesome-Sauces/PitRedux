package com.alpha.redux.entityHandlers;

import com.alpha.redux.DeathHandler.ProccessHit;
import com.alpha.redux.commands.commandUtils;
import com.alpha.redux.events.boards;

import com.alpha.redux.playerdata.xpManager;
import com.alpha.redux.redux;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.redux.funEvents.event.twoTimesEvent;
import static com.alpha.redux.playerdata.economy.*;
import static com.alpha.redux.playerdata.prestiges.*;
import static com.alpha.redux.playerdata.streaks.getStreak;
import static com.alpha.redux.playerdata.streaks.hasStreak;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnPant;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnSword;

public class ReduxPlayer {

    Player player;
    String uuid;
    int task;
    boolean regCD = true;
    boolean vampireCD = true;
    boolean booCD = true;
    boolean GoldenCD = true;
    boolean PitPocketCD = true;
    boolean perunCD = true;
    boolean prickCD = true;
    boolean ComboDamageCD = true;
    boolean gambleCD = true;
    boolean escape = true;
    double damageIncrease;
    int STRENGTH_TIMER = 0;
    boolean STRENGTH_REPEATABLE = false;
    double damageDecrease;
    double xpBooster = 1;
    int moonXP = 0;
    double goldBooster = 1;
    int obbyTime = 2400*5;
    double strength = 0.0;
    long strengthTimer;

    List<String> perks = new ArrayList<>();

    public ReduxPlayer(Player player) {
        this.player = player;
        this.damageIncrease = 0.01;
        this.damageDecrease = 0.01;
        this.uuid = String.valueOf(this.player.getUniqueId());
    }

    public void spawnPlayer(){
        commandUtils.spawnPlayer(this.player);
    }

    public void killPlayer(Player killer){
        ProccessHit.KillMan(killer, this.player);
    }

    public void addStreak(Player victim){
        ProccessHit.KillMan(this.player, victim);
    }

    public int getPlayerPrestige(){
        hasPrestige(this.uuid);
        return getPrestige(this.uuid);
    }

    public void setPlayerPrestige(int amount){
        hasPrestige(this.uuid);
        setPrestige(this.uuid, amount);
    }

    public void addPlayerPrestige(){
        hasPrestige(this.uuid);
        addPrestige(this.uuid, 1);
    }

    public void addPlayerPrestige(int amount){
        hasPrestige(this.uuid);
        addPrestige(this.uuid, amount);
    }

    public double getPlayerGold(){
        hasEconomy(this.uuid);
        return getEconomy(this.uuid);
    }

    public void setPlayerGold(int amount){
        hasEconomy(this.uuid);
        setEconomy(this.uuid, amount);
    }

    public void addPlayerGold(int amount){
        hasEconomy(this.uuid);
        addEconomy(this.uuid, amount);
    }

    public boolean getEscape(){
        if (this.escape){
            if(this.player.getHealth() / 2 == 10){
                this.escape = false;
                return true;
            }else return false;
        }
        return false;
    }

    public void setEscape(boolean set){this.escape = set;}

    public void resetEscape(){this.escape = true;}

    public void setVampireCD(){this.vampireCD = !this.vampireCD;}

    public boolean getVampireCD(){return this.vampireCD;}

    public void setPrickCD(){this.prickCD = !this.prickCD;}

    public boolean getPrickCD(){return this.prickCD;}

    public void setPitPocketCD(){this.PitPocketCD = !this.PitPocketCD;}

    public boolean getPitPocketCD(){return this.PitPocketCD;}

    public boolean getRegCD(){return this.regCD;}

    public void setRegCD(){this.regCD = !this.regCD;}

    public boolean getBooCD(){return this.booCD;}

    public void setBooCD(){this.booCD = !this.booCD;}

    public boolean getGoldenCD(){return this.GoldenCD;}

    public void setGoldenCD(){this.GoldenCD = !this.GoldenCD;}

    public boolean getPerunCD(){return this.perunCD;}
    public boolean getComboDamageCD(){return this.ComboDamageCD;}

    public void setPerunCD(){this.perunCD = !this.perunCD;}
    public void setComboDamageCD(){this.ComboDamageCD = !this.ComboDamageCD;}

    public boolean getGambleCD(){return this.gambleCD;}

    public void setGambleCD(){this.gambleCD = !this.gambleCD;}

    public double getPlayerGoldBooster(){
        return this.goldBooster;
    }

    public void setPlayerGoldBooster(int amount){
        this.goldBooster += amount;
    }

    public double getPlayerXpBooster(){
        return this.xpBooster;
    }

    public void setPlayerXpBooster(double amount){
        this.xpBooster += amount;
    }

    public void setPlayerIncrease(double amount){
        this.damageIncrease = amount;
    }

    public void addPlayerIncrease(double amount){
        this.damageIncrease += amount;
    }

    public void setPlayerEXP(int amount){
        xpManager.setXp(this.uuid, amount);
    }

    public void addPlayerEXP(int amount){
        xpManager.addXp(this.uuid, amount);
    }

    public void setPlayerDecrease(double amount){
        this.damageIncrease = amount;
    }

    public void addPlayerDecrease(double amount){
        this.damageIncrease += amount;
    }

    public double getPlayerIncrease(){
        return this.damageIncrease;
    }

    public double getPlayerDecrease(){
        return this.damageDecrease;
    }

    public String getPlayerUUID(){
        return this.uuid;
    }

    public Player getPlayerObject(){
        return this.player;
    }

    public void refreshScoreBoard(){
        boards.CreateScore(this.player);
    }

    public int getObsidianTime(){return this.obbyTime;}

    public ItemStack getHelmet(){
        if (this.player.getInventory().getHelmet() != null) return this.player.getInventory().getHelmet();
        else return null;
    }

    public ItemStack getChestplate(){
        if (this.player.getInventory().getChestplate() != null) return this.player.getInventory().getChestplate();
        else return null;
    }

    public ItemStack getLeggings(){
        if (this.player.getInventory().getLeggings() != null) return this.player.getInventory().getLeggings();
        else return null;
    }

    public ItemStack getBoots(){
        if (this.player.getInventory().getBoots() != null) return this.player.getInventory().getBoots();
        else return null;
    }

    public ItemStack getMainHand(){
        if (this.player.getInventory().getItemInHand() != null) return this.player.getInventory().getItemInHand();
        else return null;
    }

    public List<String> getPantEnchants(){
        if(getLeggings() != null && getLeggings().getItemMeta().getLore() != null) return CheckEnchantOnPant(getLeggings().getItemMeta().getLore());
        else return null;
    }

    public List<String> getSwordEnchants(){
        if(getMainHand() != null && getMainHand().getItemMeta() != null && getMainHand().getItemMeta().getLore() != null) return CheckEnchantOnSword(getMainHand().getItemMeta().getLore());
        else return null;
    }

    public int calculateExperience(){
        return ReduxPlayerUtils.calculateExp(this);
    }

    public int calculateGold(){
        return ReduxPlayerUtils.calculateGoldAmount(this);
    }

    public List<String> getPerks() {
        return perks;
    }

    public int getPerksAmount(){
        return perks.size();
    }

    public void addPerks(String perk){
        this.perks.add(perk);
    }

    public void setPerks(List<String> perks) {
        this.perks = perks;
    }

    public void strengthTick(){

        strengthTimer = 7;
        strength = Math.min(.40, strength+.08);

        if (!this.STRENGTH_REPEATABLE) {
            this.STRENGTH_REPEATABLE = true;
            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(redux.INSTANCE, new Runnable() {
                @Override
                public void run() {

                    if (strengthTimer > 0) {
                        strengthTimer -= 1;
                    }

                    if (strengthTimer <= 0) {
                        strength = 0.0;
                    }
                }
            }, 20, 20);
        }
    }

    public void cancelSTRENGTH_TIMER(){
        this.STRENGTH_REPEATABLE=false;
        Bukkit.getScheduler().cancelTask(this.task);
    }

    public int getSTRENGTH_TIMER() {
        return STRENGTH_TIMER;
    }

    public void setSTRENGTH_TIMER(int STRENGTH_TIMER) {
        this.STRENGTH_TIMER = STRENGTH_TIMER;
    }

    public void setStrength(double strength){
        this.strength = strength;
    }

    public double getStrength(){
        return strength;
    }

    public int getStrengthTier(){
        return (int) (strength/.08);
    }

    public int getMoonXP() {
        return moonXP;
    }

    public void setMoonXP(int moonXP) {
        this.moonXP = moonXP;
    }

    public void addMoonXP(int moonXP) {
        this.moonXP += moonXP;
    }

    public double getMoonMultiplier(){
        hasStreak(this.uuid);

        if(getStreak(this.uuid)>100){
            return Math.min((double)(getStreak(this.uuid)-100)*.005, 1.5);
        }else return 0.0;
    }

    public void addPotionEffect(PotionEffectType type, int time, int power){
        if(!player.hasPotionEffect(type)){
            player.addPotionEffect(new PotionEffect(type, time*20, Math.max(power-1, 0), true, true));
        }
    }

    public void removePotionEffect(PotionEffectType type){
        if(player.hasPotionEffect(type)){
            player.removePotionEffect(type);
        }
    }

    public static float getWalkSpeed(float enchantLvl) {
        if(enchantLvl == 0) return 0.2F;
        else return (0.2F+(0.2F*enchantLvl));
    }

    public void setSpeed(float speed){
        player.setWalkSpeed(getWalkSpeed(speed));
    }

    public float getSpeed(){
        return getWalkSpeed(0);
    }
}
