package com.alpha.redux.eventManagers;

import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.items.enchants;
import com.alpha.redux.items.itemManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.ItemEvents.pants.VenomEvent;
import static com.alpha.redux.ItemEvents.pants.triggerChestplateMalding;
import static com.alpha.redux.apis.actionbarplus.sendHealthBar;
import static com.alpha.redux.apis.locations.getSpawnProtection;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnPant;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnSword;

public class ReduxDamageEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ReduxPlayer attacker;
    private final ReduxPlayer defender;
    private List<ItemStack> defenderArmor = new ArrayList<>();
    private List<ItemStack> attackerArmor = new ArrayList<>();
    private List<String> attackerSwordEnchants = new ArrayList<>();
    private List<String> attackerPantEnchants = new ArrayList<>();

    private ItemStack attackerSword;

    private List<String> defenderSwordEnchants = new ArrayList<>();
    private List<String> defenderPantEnchants = new ArrayList<>();

    private double damage = 1;
    private double trueDamage = 0;
    private final EntityDamageByEntityEvent event;
    private boolean isCancelled;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private void setDefenderArmor(){
        if(this.defender.getHelmet() != null) defenderArmor.add(this.defender.getChestplate());
        if(this.defender.getChestplate() != null) defenderArmor.add(this.defender.getChestplate());
        if(this.defender.getLeggings() != null) defenderArmor.add(this.defender.getChestplate());
        if(this.defender.getBoots() != null) defenderArmor.add(this.defender.getChestplate());
    }

    private void setAttackerArmor(){
        if(this.attacker.getHelmet() != null) attackerArmor.add(this.attacker.getChestplate());
        if(this.attacker.getChestplate() != null) attackerArmor.add(this.attacker.getChestplate());
        if(this.attacker.getLeggings() != null) attackerArmor.add(this.attacker.getChestplate());
        if(this.attacker.getBoots() != null) attackerArmor.add(this.attacker.getChestplate());
    }

    private void setAttackerEnchants(){

        ItemStack stack = attacker.getLeggings();

        if(stack != null && stack.getItemMeta() != null && stack.getItemMeta().getLore() != null && stack.getType().equals(Material.LEATHER_LEGGINGS)) {
            this.attackerPantEnchants = CheckEnchantOnPant(stack.getItemMeta().getLore());
        }
        if(this.attackerSword.getItemMeta() != null && this.attackerSword.getItemMeta().getLore() != null) this.attackerSwordEnchants = CheckEnchantOnSword(this.attackerSword.getItemMeta().getLore());
    }

    private void setDefenderEnchants(){

        ItemStack stack = defender.getLeggings();

        if(stack != null && stack.getItemMeta() != null && stack.getItemMeta().getLore() != null && stack.getType().equals(Material.LEATHER_LEGGINGS)) {
            this.defenderPantEnchants = CheckEnchantOnPant(stack.getItemMeta().getLore());
        }

    }

    public ReduxDamageEvent(ReduxPlayer attacker, ReduxPlayer defender, double damage, EntityDamageByEntityEvent event) {
        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
        this.event = event;
        this.isCancelled = false;
        if(this.attacker.getMainHand() != null) this.attackerSword = this.attacker.getMainHand();
        setDefenderArmor();
        setAttackerArmor();
        setAttackerEnchants();
        setDefenderEnchants();
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

    public EntityDamageByEntityEvent getBukkitEvent(){
        return this.event;
    }

    public ReduxPlayer getAttacker() {
        return this.attacker;
    }

    public ReduxPlayer getDefenders() {
        return this.defender;
    }

    public List<ItemStack> getDefenderArmor(){return this.defenderArmor;}

    public List<ItemStack> getAttackerArmor(){return this.attackerArmor;}

    public List<String> getAttackerPantEnchants(){return this.attackerPantEnchants;}
    public List<String> getAttackerSwordEnchants(){return this.attackerSwordEnchants;}

    public List<String> getDefenderPantEnchants(){return this.defenderPantEnchants;}
    public List<String> getDefenderSwordEnchants(){return this.defenderSwordEnchants;}

    public Double getReduxDamage() {
        return this.damage;
    }

    public Double getFinalDamage(){
        return this.event.getFinalDamage();
    }

    public void setReduxDamage(double damage) {
        this.event.setDamage(damage);
        this.damage = damage;
    }

    public void addReduxDamage(double damage) {
        this.damage += damage;
        this.event.setDamage(damage);
    }

    public void subtractReduxDamage(double damages){
        this.damage = Math.max(damage - damages, 1.0);
        this.event.setDamage(damage);
    }


    public Double getReduxTrueDamage() {
        return this.trueDamage;
    }

    public void setReduxTrueDamage(double damage) {
        this.trueDamage = damage;
    }

    public void addReduxTrueDamage(double damage) {
        this.trueDamage += damage;
    }

    public void subtractReduxTrueDamage(double damage){
        this.trueDamage -= damage;
    }

    public void run(){
        Player attacker = this.getAttacker().getPlayerObject();
        Player defender = this.getDefenders().getPlayerObject();

        ReduxPlayer ReduxAttacker = this.getAttacker();
        ReduxPlayer ReduxDefender = this.getDefenders();


        if(isNPC(attacker)){
            this.setReduxDamage(7);
        }

        if(isNPC(defender) && !isNPC(attacker)){
            this.subtractReduxDamage(event.getDamage()*.4);
        }

        /*
        if(true_damage_amount.containsKey(ReduxDefender.getPlayerUUID())){
            true_dmg += true_damage_amount.get(ReduxDefender.getPlayerUUID());
        }

         */

        try {
            if (attacker.getInventory().getItemInHand().getItemMeta().getDisplayName().contains("Tier III")) {
                ItemStack sword = attacker.getItemInHand();
                if(!attacker.getInventory().getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ALL)){
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
                }else if (attacker.getInventory().getItemInHand().getEnchantments().get(Enchantment.DAMAGE_ALL) == 1){
                    sword.removeEnchantment(Enchantment.DAMAGE_ALL);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
                }



            }

            if (attacker.getInventory().getItemInHand().equals(enchants.jewl_sword)) {
                ItemStack sword = attacker.getItemInHand();
                if(!attacker.getInventory().getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ALL)){
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
                }

            }
        } catch (Exception e){

        }

        try {

            if(attacker.getInventory().getItemInHand().getType().equals(Material.DIAMOND_SPADE)){

                try {
                    if(defender.getInventory().getChestplate().getType().equals(Material.DIAMOND_LEGGINGS)){
                        this.addReduxDamage(2);
                    }else if(defender.getInventory().getLeggings().getType().equals(Material.DIAMOND_CHESTPLATE)){
                        this.addReduxDamage(2);
                    }else if(defender.getInventory().getBoots().getType().equals(Material.DIAMOND_BOOTS)){
                        this.addReduxDamage(2);
                    }

                    this.addReduxDamage(3);
                }catch (Exception e){
                    this.addReduxDamage(4);
                }

            }

            //if (StrengthCheck(attacker) > 0) this.addReduxDamage(this.getReduxDamage() * StrengthCheck(attacker));

        } catch (Exception ignored){}

        // Pant enchants



        try{
            if(attacker.getInventory().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)){
                try{
                    VenomEvent(attacker, defender);
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {}



        if(defender.getInventory().getLeggings() != null){
            if(defender.getInventory().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)){
                this.subtractReduxDamage(this.getReduxDamage() * .10);

            }
        }


        /*
        try{
            if(attacker.getInventory().getItemInHand().getType().equals(Material.GOLD_SWORD)){
                ItemStack sword = attacker.getInventory().getItemInHand();
                if (!(attacker.getLocation().getY() >= getSpawnProtection())){damage += Billionaire(attacker, damage, sword.getItemMeta().getLore());}
                if (!(attacker.getLocation().getY() >= getSpawnProtection())){damage += GambleSword(attacker, damage, sword.getItemMeta().getLore());}
                damage += damage * criticalFunkyDamageCalculation(attacker, 0.0, true);
                damage += KingBuster(attacker, defender ,damage, sword.getItemMeta().getLore());
                damage += Sharker(attacker, defender ,damage, sword.getItemMeta().getLore());
                damage += Sharpness(attacker,damage, sword.getItemMeta().getLore());
                attacker.setHealth(Math.min(attacker.getHealth() + Lifesteals(attacker, damage, sword.getItemMeta().getLore()), attacker.getMaxHealth()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

         */

        //attacker.setHealth(Math.min(attacker.getHealth() +.5, attacker.getMaxHealth()));


        if(defender.getInventory().getHelmet() != null && defender.getInventory().getHelmet().equals(itemManager.goldHelm)){
            this.subtractReduxDamage(this.getReduxDamage() * .05);
        }

        if(defender.getInventory().getChestplate() != null && defender.getInventory().getChestplate().equals(itemManager.arch)){
            this.subtractReduxDamage(this.getReduxDamage() * .05);
        }


        if (attacker.getLocation().getY() >= getSpawnProtection()){
            this.setCancelled(true);
            return;
        }

        if (attacker.getInventory().getChestplate() != null && attacker.getInventory().getChestplate().equals(enchants.malding_chestplate)) {
            triggerChestplateMalding(attacker, defender, this.getReduxDamage());
        }

        try{
            this.addReduxDamage(this.getReduxDamage()*ReduxAttacker.getPlayerIncrease());
            ReduxAttacker.setPlayerIncrease(.0001);
            this.subtractReduxDamage(this.getReduxDamage()*ReduxDefender.getPlayerDecrease());
            ReduxDefender.setPlayerDecrease(.0001);
        }catch (Exception e){

        }

        try {
            if(defender.getInventory().getChestplate() != null){
                if(defender.getInventory().getChestplate().equals(enchants.malding_chestplate)){
                    this.subtractReduxDamage(this.getReduxDamage()*.10);
                }
            }
            if(defender.getInventory().getLeggings() != null){
                if(defender.getInventory().getLeggings().equals(enchants.malding_pants)){
                    this.subtractReduxDamage(this.getReduxDamage()*.10);
                }
            }
            if(defender.getInventory().getBoots() != null){
                if(defender.getInventory().getBoots().equals(enchants.malding_boots)){
                    this.subtractReduxDamage(this.getReduxDamage()*.10);
                }
            }
        } catch (Exception e) {

        }
/*
        if(isNPC(defender)){
            this.setReduxDamage(this.getReduxDamage() * .75);
        }


 */
        this.getBukkitEvent().setDamage(Math.max(this.getReduxDamage(),1));


        sendHealthBar(this.getBukkitEvent());

    }
}
