package com.alpha.redux.renownShop.CookieMonster;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.items.enchants;
import com.alpha.redux.redux;
import com.nametagedit.plugin.NametagEdit;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import static com.alpha.redux.DeathHandler.killHandler.getNPC;
import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.apis.chatManager.rank.ChatEventApiGetLevelColor;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.questMaster.bossBattles.maldingBoss.gearNearby;

public class MonsterHandler implements Listener {

    public static void percentageSpawn(Player player){/*
        if(Math.random() <= ((double)Monster.getMonsterChance(String.valueOf(player.getUniqueId()))/3000)){
            createMonsterBoss(player);
            player.sendMessage(colorCode("&c&lWOAH! &7a wild &bCookie Monster &7has appeared!"));
            Sounds.PRESTIGE.play(player);
        }
        */
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void MonsterDamageEvent(ReduxDamageEvent event){
        if(isNPC(event.getDefenders().getPlayerObject()) &&
                getNPC(event.getDefenders().getPlayerObject()).getName().equals(ChatColor.AQUA + "CookieMonster")){
            NPC npc = getNPC(event.getDefenders().getPlayerObject());

            event.setReduxDamage(event.getReduxDamage()/100);

            event.getDefenders().getPlayerObject().setHealth(Math.min(event.getDefenders().getPlayerObject().getMaxHealth(),
                    event.getDefenders().getPlayerObject().getHealth()+2));

            npc.getEntity().setVelocity(new Vector(0,0,0));

            npc.getEntity().getWorld().playEffect(event.getDefenders().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefenders().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefenders().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefenders().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefenders().getPlayerObject().getLocation(), Effect.HEART, 1);
        }else if(isNPC(event.getAttacker().getPlayerObject()) &&
        getNPC(event.getAttacker().getPlayerObject()).getName().equals(ChatColor.AQUA + "CookieMonster")){
            NPC npc = getNPC(event.getAttacker().getPlayerObject());

            npc.getEntity().getWorld().strikeLightningEffect(event.getDefenders().getPlayerObject().getLocation());
            event.getAttacker().addPotionEffect(PotionEffectType.POISON, 15, 1);
        }
    }

    private static void skin(NPC npc) {
        SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
        skinTrait.setSkinName("HawaiiFox");

        LookClose lookClose = npc.getTrait(LookClose.class);
        lookClose.lookClose(true);
    }

    public static void createMonsterBoss(Player player){
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.AQUA + "CookieMonster");
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, enchants.malding_sword);

        npc.setBukkitEntityType(EntityType.PLAYER);

        skin(npc);

        npc.spawn(player.getLocation());

        npc.teleport(player.getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);

        npc.setProtected(false);

        npc.getNavigator().getDefaultParameters()
                .speedModifier(1);

        npc.getNavigator().setTarget(player, false);

        MonsterData.addMonsterInstance(npc, player);

        long timeInTicks = 5L;

        BukkitTask runnable = new BukkitRunnable() {

            @Override
            public void run() {

                if(npc.isSpawned()){
                    npc.getNavigator().setTarget(player.getLocation());
                    npc.faceLocation(player.getLocation());

                    if (gearNearby(npc.getEntity(), 3.5, 3.5, 3.5).contains(player)) {
                        if(player.isOnGround() || player.isFlying()){
                            for (Player players : gearNearby(npc.getEntity(), 4, 4, 4)){
                                PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(((CraftEntity) npc.getEntity()).getHandle(), 0);
                                ((CraftPlayer) players).getHandle().playerConnection.sendPacket(animationPacket);
                            }

                            player.damage(MonsterData.getNPCDamage(), npc.getEntity()); // Use your event's data
                        }

                    }
                }else this.cancel();



            }
        }.runTaskTimer(redux.INSTANCE, timeInTicks, timeInTicks);

        Bukkit.getScheduler().scheduleSyncDelayedTask(redux.INSTANCE, new Runnable() {
            @Override
            public void run() {
                if(MonsterData.isOwner(npc, player)){

                    int renown = MonsterData.getRenownRollLoss(player.getUniqueId());
                    MonsterData.removeMonsterInstance(npc);
                    npc.despawn();
                    npc.destroy();
                    CitizensAPI.getNPCRegistry().deregister(npc);
                    player.sendMessage(colorCode("&c&lFAILURE! &7you failed to kill the cookie monster and lost &b" + renown + " &7renown."));
                    Sounds.DEATH_GHAST_SCREAM.play(player);
                }
            }
        }, 600L);
    }

    public static void handleMonsterDeath(Player player, NPC npc){

        Player attacker = player;



        if(MonsterData.isOwner(npc, attacker)){

            int renown = MonsterData.getRenownRoll(attacker.getUniqueId());
            MonsterData.removeMonsterInstance(npc);
            npc.despawn();
            npc.destroy();
            CitizensAPI.getNPCRegistry().deregister(npc);
            attacker.sendMessage(colorCode("&a&lCONGRATS! &7you killed the cookie monster and got &b" + renown + " &7renown."));
            Sounds.DEATH_GHAST_SCREAM.play(attacker);
        }

    }

}
