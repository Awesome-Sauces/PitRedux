package com.alpha.redux.well.enchants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PantMaps;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class RetroGravityMicrocosmLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        if(criticalHit(event)){
            runIt(event.getAttacker().getPlayerObject(), event.getDefenders().getPlayerObject(), .5+((level-1)*.25), .5+((level-1)*.5));
        }

    }

    public boolean criticalHit(ReduxDamageEvent event){
        Player player = event.getDefenders().getPlayerObject();

        if(!event.getAttacker().getPlayerObject().isOnGround()){
            if(PantMaps.hitCounter.containsKey(String.valueOf(player.getUniqueId()))){
                PantMaps.hitCounter.put(String.valueOf(player.getUniqueId()), PantMaps.hitCounter.get(String.valueOf(player.getUniqueId())) + 1);
            }else{
                PantMaps.hitCounter.put(String.valueOf(player.getUniqueId()), 1);
            }

            return PantMaps.hitCounter.get(String.valueOf(player.getUniqueId())) >= 3;
        }
        return !event.getAttacker().getPlayerObject().isOnGround();
    }

    public void runIt(Player attacker, Player defender, double health, double trueDmg){
        PantMaps.hitCounter.put(String.valueOf(defender.getUniqueId()), 0);
        attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRGM! &7Proc'd against " + rank.getNameColor(defender) + defender.getDisplayName() + "&7!"));
        Sounds.RGM.play(attacker.getLocation());
        Sounds.RGM.play(defender.getLocation());
        defender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRGM! &7Procced against " + rank.getNameColor(attacker) + attacker.getDisplayName() + "&7!"));
        defender.setHealth(Math.min(defender.getHealth() + health, defender.getMaxHealth()));

        EntityDamageByEntityEvent events = new EntityDamageByEntityEvent(defender, attacker,
                EntityDamageEvent.DamageCause.MAGIC, trueDmg);
        Bukkit.getServer().getPluginManager().callEvent(events);
        if(!events.isCancelled()) {
            attacker.setHealth(Math.max(attacker.getHealth() - trueDmg, 0));
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Retro-Gravity Microcosm" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(.5+((level-1)*.25));

        String take = String.valueOf(.5+((level-1)*.5));

        String lore = "&dRARE! &9Retro-Gravity Microcosm" + tier + "\n" +
                "&7When a player hits you from\n" +
                "&7above ground &e3 times &fin a row:\n" +
                "&7You heal &c"+multiplier+"\u2764\n" +
                "&7They take &c"+take+"\u2764 &ftrue damage" + "\n&7";

        return colorCode(lore);
    }
}