package com.alpha.redux.PitEvents.MinorEvents;

import com.alpha.redux.PitEvents.EventExecute;
import com.alpha.redux.PitEvents.MinorEvent;
import com.alpha.redux.apis.locations;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.redux;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

import java.util.UUID;

import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.events.boards.boardMap;
import static com.alpha.redux.events.boards.updateBoard;

public class KingOfTheLadder extends MinorEvent{
    public KingOfTheLadder(){
        this.setRefID("kotl");
        this.setName(colorCode("&d&lMINOR EVENT! &a&lKOTL! &7Ending in &a01:05"));
        this.setTime(3600);
    }

    @Override
    public EventExecute getEventExecute() {
        return new EventExecute(){
            @Override
            public void runnable(){
                for(Player player : Bukkit.getOnlinePlayers()){

                    Bukkit.getScheduler().scheduleSyncDelayedTask(redux.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            deleteHolograms();
                        }
                    }, 20L);

                    BossBar bossBar = BossBarAPI.addBar(player,
                            new TextComponent(getName()),
                            BossBarAPI.Color.GREEN,
                            BossBarAPI.Style.NOTCHED_6,
                            1.0f,
                            20,
                            2);

                    Bukkit.getScheduler().scheduleSyncRepeatingTask(redux.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            deleteHolograms();
                        }
                    }, 20L, 20L);
                }

                loadHolograms();

                Bukkit.getScheduler().scheduleSyncDelayedTask(redux.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        deleteHolograms();
                    }
                }, 200L);
            }

            @Override
            public void run(ReduxDamageEvent event){

            }
        };

    }

    @Override
    public void loadHolograms() {
        this.setHologram1(HologramsAPI.createHologram(redux.INSTANCE,
                locations.getEventNotifyLocation(Bukkit.getWorld("world")).add(0,2,0)));
        this.getHologram1().appendTextLine(this.getName());

        this.setHologram2(HologramsAPI.createHologram(redux.INSTANCE,
                locations.getEventNotifyLocation(Bukkit.getWorld("lobby")).add(0,2,0)));
        this.getHologram2().appendTextLine(this.getName());

        this.setHologram3(HologramsAPI.createHologram(redux.INSTANCE,
                locations.getEventNotifyLocation(Bukkit.getWorld("lobby2")).add(0,2,0)));
        this.getHologram3().appendTextLine(this.getName());

    }

    @Override
    public void deleteHolograms() {
        if(this.getHologram1() != null &&
        !this.getHologram1().isDeleted()){
            this.getHologram1().delete();
            this.setHologram1(null);
        }

        if(this.getHologram2() != null &&
                !this.getHologram2().isDeleted()){
            this.getHologram2().delete();
            this.setHologram2(null);
        }

        if(this.getHologram3() != null &&
                !this.getHologram3().isDeleted()){
            this.getHologram3().delete();
            this.setHologram3(null);
        }
    }
}