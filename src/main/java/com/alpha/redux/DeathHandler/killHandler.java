package com.alpha.redux.DeathHandler;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.events.boards;
import com.alpha.redux.items.enchants;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.renownShop.MysticismChance;
import com.nametagedit.plugin.NametagEdit;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

import static com.alpha.redux.DeathHandler.ProccessHit.StrengthPerk;
import static com.alpha.redux.DeathHandler.jewls.PlayerFinishedJewl;
import static com.alpha.redux.apis.chatManager.rank.ChatEventApiGetLevelColor;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.apis.locations.getBotSpawnLocation;
import static com.alpha.redux.apis.locations.getSpawnLocation;
import static com.alpha.redux.commands.command.KillMessages;
import static com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap.deleteBlob;
import static com.alpha.redux.events.events.Strength;
import static com.alpha.redux.playerdata.bounties.BountyClaimed;
import static com.alpha.redux.playerdata.economy.*;
import static com.alpha.redux.events.nonPermItems.ClearAndCheck;
import static com.alpha.redux.playerdata.streaks.*;
import static com.alpha.redux.playerdata.streaks.setStreak;

public class killHandler {

    public static boolean isNPC(Player player){
        return CitizensAPI.getNPCRegistry().isNPC(player);
    }

    public static NPC getNPC(Player player){
        return CitizensAPI.getNPCRegistry().getNPC(player);
    }

    public static void tpNPC(Player player){

        NPC npc = getNPC(player);

        Location loc = getBotSpawnLocation();

        npc.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

    }

    public static boolean processKill(ReduxPlayer ReduxAttacker, ReduxPlayer ReduxDefender) {

        if(!isNPC(ReduxDefender.getPlayerObject())) {
            deleteBlob(ReduxDefender.getPlayerObject());
            ClearAndCheck(ReduxDefender.getPlayerObject());


            ReduxDefender.getPlayerObject().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

        }



        CraftPlayer craftAttacker = (CraftPlayer) ReduxAttacker.getPlayerObject(); //CraftBukkit
        EntityHuman entityAttacker = craftAttacker.getHandle(); //NMS

        //EntityHuman entityDefender = craftDefender.getHandle(); //NMS
        double abs = entityAttacker.getAbsorptionHearts();


        // Applying Gold and checking for extra enchants

        List<String> list = ReduxAttacker.getPantEnchants();
        if(list != null){
            for (String s : list) {
                switch (s) {

                    case "blobIII":
                    case "blobII":
                    case "blobI":
                        PitBlobMap.blobTick(ReduxAttacker.getPlayerObject());
                        break;


                    case "goldheartIII":
                        ReduxAttacker.getPlayerObject().removePotionEffect(PotionEffectType.ABSORPTION);
                        entityAttacker.setAbsorptionHearts((float) Math.min(abs + 4, 12.0));
                        break;
                    case "goldheartII":
                        ReduxAttacker.getPlayerObject().removePotionEffect(PotionEffectType.ABSORPTION);
                        entityAttacker.setAbsorptionHearts((float) Math.min(abs + 2, 10.0));
                        break;
                    case "goldheartI":
                        ReduxAttacker.getPlayerObject().removePotionEffect(PotionEffectType.ABSORPTION);
                        entityAttacker.setAbsorptionHearts((float) Math.min(abs + 1, 8.0));
                        break;
                }
            }
        }
        

        if (isNPC(ReduxAttacker.getPlayerObject())) {

            if(isNPC(ReduxDefender.getPlayerObject())){
                NPC npc = getNPC(ReduxDefender.getPlayerObject());
                npc.teleport(getBotSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                return true;
            }

            xp_amount_mega.put(ReduxDefender.getPlayerUUID(), 0);
            Strength.put(ReduxDefender.getPlayerUUID(), 0.0);
            mega_damage_amount.put(ReduxDefender.getPlayerUUID(), 0.0);
            true_damage_amount.put(ReduxDefender.getPlayerUUID(), 0.0);
            xp_amount_mega.put(ReduxDefender.getPlayerUUID(), 0);
            UberRewardClaimDeath(ReduxDefender.getPlayerObject());
            ReduxDefender.getPlayerObject().setMaxHealth(20);
            // Runs only when Entity is Player
            ReduxDefender.getPlayerObject().setHealth(Math.min(20, ReduxDefender.getPlayerObject().getMaxHealth()));
            //attacker.getWorld().playSound(attacker.getLocation(), Sound.LEVEL_UP, 1, 20);
            ReduxDefender.getPlayerObject().sendMessage(ChatColor.RED + colorCode("&lDEATH! ") + ChatColor.GRAY + "by " + ReduxDefender.getPlayerObject().getDisplayName());
            Location loc = getSpawnLocation();
            ReduxDefender.getPlayerObject().teleport(loc);
            hasStreak(ReduxDefender.getPlayerObject().getDisplayName());
            //setStreak(ReduxDefender.getPlayerUUID(), 0);
            if(!isNPC(ReduxDefender.getPlayerObject())) boards.CreateScore(ReduxDefender.getPlayerObject());
            ReduxDefender.getPlayerObject().removePotionEffect(PotionEffectType.SLOW);
            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                    IChatBaseComponent.ChatSerializer.a("{\"text\":\"YOU DIED\",\"color\":\"red\"}"), 100, 20, 20);
            ((CraftPlayer) ReduxDefender.getPlayerObject()).getHandle().playerConnection.sendPacket(title);
            hasStreak(ReduxDefender.getPlayerObject().getDisplayName());
            setStreak(ReduxDefender.getPlayerUUID(), 0);
            BountyClaimed(ReduxDefender.getPlayerObject(), ReduxAttacker.getPlayerObject());
            return true;
        }

        // Applying Gold and checking for extra enchants

        // Check if Entity Damaged is a Bot

        if (isNPC(ReduxDefender.getPlayerObject())) {

            double r = new Random().nextDouble();
            if (r < MysticismChance.getMysticismChance(ReduxAttacker.getPlayerUUID())) {
                ReduxAttacker.getPlayerObject().getInventory().addItem(enchants.fresh_reds);
                ReduxAttacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.RED + " Red Fresh!");
                ReduxAttacker.getPlayerObject().playSound(ReduxAttacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
            }

            double e = new Random().nextDouble();
            if(e < (MysticismChance.getMysticismChance(ReduxAttacker.getPlayerUUID()))){
                ReduxAttacker.getPlayerObject().getInventory().addItem(enchants.fresh_sword);
                ReduxAttacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.RED + " Mystic Sword!");
                ReduxAttacker.getPlayerObject().playSound(ReduxAttacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
            }

            StrengthPerk(ReduxAttacker.getPlayerObject());
            // Setting HP to MAX
            ReduxDefender.getPlayerObject().setHealth(Math.min(20, ReduxDefender.getPlayerObject().getMaxHealth()));
            //attacker.getWorld().playSound(attacker.getLocation(), Sound.LEVEL_UP, 1, 20);

            tpNPC(ReduxDefender.getPlayerObject());

            ReduxAttacker.addPlayerEXP(ReduxAttacker.calculateExperience());
            hasEconomy(ReduxAttacker.getPlayerUUID());
            addEconomy(ReduxAttacker.getPlayerUUID(), ReduxAttacker.calculateGold());
            if(!KillMessages.containsKey(ReduxAttacker.getPlayerUUID())){
                KillMessages.put(ReduxAttacker.getPlayerUUID(), true);
            }else if(KillMessages.get(ReduxAttacker.getPlayerUUID()).equals(true)){
                ReduxAttacker.getPlayerObject().sendMessage(ChatColor.GREEN + colorCode("&lKILL! ") + ChatColor.GRAY + "on " + ReduxDefender.getPlayerObject().getDisplayName() + ChatColor.RESET + ChatColor.AQUA + " +" + ReduxAttacker.calculateExperience() + "XP" + ChatColor.GOLD + " +" + ReduxAttacker.calculateGold() + "g");
            }
            hasStreak(ReduxAttacker.getPlayerUUID());
            addStreak(ReduxAttacker.getPlayerUUID(), 1);

            if(getStreak(ReduxAttacker.getPlayerUUID()) <= 49){
                NametagEdit.getApi().setNametag(ReduxAttacker.getPlayerObject(), ChatEventApiGetLevelColor(ReduxAttacker.getPlayerObject().getDisplayName(), ReduxAttacker.getPlayerUUID())+ rank.getNameColor(ReduxAttacker.getPlayerObject()), "");
            }

            multiKill(ReduxAttacker.getPlayerObject());
            //ReduxAttacker.getPlayerObject().playSound(ReduxAttacker.getPlayerObject().getLocation(), Sound.LEVEL_UP, 1.0F, 7.0F);
            ReduxDefender.getPlayerObject().removePotionEffect(PotionEffectType.SLOW);
            ReduxAttacker.getPlayerObject().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            if (!ReduxAttacker.getPlayerObject().hasPotionEffect(PotionEffectType.REGENERATION)) {
                ReduxAttacker.getPlayerObject().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, true, true));
            }
            ReduxAttacker.getPlayerObject().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80, 1, true, true));
            ReduxAttacker.refreshScoreBoard();
            PlayerFinishedJewl(ReduxAttacker.getPlayerObject());
        } else {
            UberRewardClaimDeath(ReduxDefender.getPlayerObject());

            BountyClaimed(ReduxDefender.getPlayerObject(), ReduxAttacker.getPlayerObject());
            Random rand = new Random(); //instance of random class
            int upperbound = 11;
            int int_random = rand.nextInt(upperbound);
            if (int_random == 1) {
                ReduxAttacker.getPlayerObject().getInventory().addItem(enchants.vile);
                ReduxAttacker.getPlayerObject().sendMessage(ChatColor.GREEN + "WOW!" + ChatColor.GRAY + " You got a" + ChatColor.DARK_PURPLE + " Vile!");
                ReduxAttacker.getPlayerObject().playSound(ReduxAttacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
            }
            xp_amount_mega.put(ReduxDefender.getPlayerUUID(), 0);

            mega_damage_amount.put(ReduxDefender.getPlayerUUID(), 0.0);
            true_damage_amount.put(ReduxDefender.getPlayerUUID(), 0.0);
            xp_amount_mega.put(ReduxDefender.getPlayerUUID(), 0);
            Strength.put(ReduxDefender.getPlayerUUID(), 0.0);
            ReduxDefender.getPlayerObject().setMaxHealth(20);
            StrengthPerk(ReduxAttacker.getPlayerObject());
            // Runs only when Entity is Player
            ReduxDefender.getPlayerObject().setHealth(Math.min(20, ReduxDefender.getPlayerObject().getMaxHealth()));
            //attacker.getWorld().playSound(attacker.getLocation(), Sound.LEVEL_UP, 1, 20);

            //ClearAndCheck(ReduxDefender.getPlayerObject());
            ReduxAttacker.addPlayerEXP(ReduxAttacker.calculateExperience());
            hasEconomy(ReduxAttacker.getPlayerUUID());
            addEconomy(ReduxAttacker.getPlayerUUID(), ReduxAttacker.calculateGold());
            ReduxAttacker.getPlayerObject().sendMessage(ChatColor.GREEN + colorCode("&lKILL! ") + ChatColor.GRAY + "on " + ReduxDefender.getPlayerObject().getDisplayName() + ChatColor.RESET + ChatColor.AQUA + " +" + ReduxAttacker.calculateExperience() + "XP" + ChatColor.GOLD + " +" + ReduxAttacker.calculateGold() + "g");
            ReduxDefender.getPlayerObject().sendMessage(ChatColor.RED + colorCode("&lDEATH! ") + ChatColor.GRAY + "by " + ReduxAttacker.getPlayerObject().getDisplayName());
            Location loc = getSpawnLocation();
            ReduxDefender.getPlayerObject().teleport(loc);
            hasStreak(ReduxAttacker.getPlayerUUID());
            hasStreak(ReduxDefender.getPlayerObject().getDisplayName());
            addStreak(ReduxAttacker.getPlayerUUID(), 1);
            setStreak(ReduxDefender.getPlayerUUID(), 0);

            if(getStreak(ReduxAttacker.getPlayerUUID()) <= 49){
                NametagEdit.getApi().setNametag(ReduxAttacker.getPlayerObject(), ChatEventApiGetLevelColor(ReduxAttacker.getPlayerObject().getDisplayName(), ReduxAttacker.getPlayerUUID())+ rank.getNameColor(ReduxAttacker.getPlayerObject()), "");
            }

            if(!isNPC(ReduxDefender.getPlayerObject())){
                NametagEdit.getApi().setNametag(ReduxDefender.getPlayerObject(), ChatEventApiGetLevelColor(ReduxDefender.getPlayerObject().getDisplayName(), ReduxDefender.getPlayerUUID())+ rank.getNameColor(ReduxDefender.getPlayerObject()), "");
            }

            PlayerFinishedJewl(ReduxAttacker.getPlayerObject());
            ReduxDefender.getPlayerObject().removePotionEffect(PotionEffectType.SLOW);
            ReduxAttacker.getPlayerObject().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            if(!ReduxAttacker.getPlayerObject().hasPotionEffect(PotionEffectType.REGENERATION)){
                ReduxAttacker.getPlayerObject().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, true, true));
            }
            ReduxAttacker.getPlayerObject().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80, 1, true, true));
            ReduxAttacker.refreshScoreBoard();
            ReduxDefender.refreshScoreBoard();
            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                    IChatBaseComponent.ChatSerializer.a("{\"text\":\"YOU DIED\",\"color\":\"red\"}"), 100, 20, 20);
            ((CraftPlayer) ReduxDefender.getPlayerObject()).getHandle().playerConnection.sendPacket(title);
        }
        return true;
    }

    public static void multiKill(Player player) {

        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {

                switch(count) {
                    case 0:
                        Sounds.MULTI_1.play(player);
                        break;
                    case 1:
                        Sounds.MULTI_2.play(player);
                        break;
                    case 2:
                        Sounds.MULTI_3.play(player);
                        break;
                    case 3:
                        Sounds.MULTI_4.play(player);
                        break;
                    case 4:
                        Sounds.MULTI_5.play(player);
                        break;
                }

                if(++count > 5) cancel();
            }
        }.runTaskTimer(economy.getPlugin(), 0L, 2L);
    }

}
