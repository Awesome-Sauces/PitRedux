package com.alpha.redux.events;

import com.alpha.redux.playerdata.prestiges;
import com.alpha.redux.playerdata.xpManager;
import com.alpha.redux.redux;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static com.alpha.redux.apis.HeadNames.headnames.changeName;
import static com.alpha.redux.apis.chatManager.levelcolor.getLevelColor;
import static com.alpha.redux.apis.chatManager.prestigebrackets.prestigebracket;
import static com.alpha.redux.apis.chatManager.rank.ChatEventApi;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.apis.leaderboardsplus.leaderboards.RefreshBoard;
import static com.alpha.redux.events.events.*;
import static com.alpha.redux.funEvents.event.handleTwoEvent;
import static com.alpha.redux.playerdata.economy.getEconomy;
import static com.alpha.redux.playerdata.economy.hasEconomy;
import static com.alpha.redux.playerdata.prestiges.getPrestige;
import static com.alpha.redux.playerdata.prestiges.hasPrestige;
import static com.alpha.redux.playerdata.streaks.*;
import static com.alpha.redux.playerdata.xpManager.GetCurrentLevel;
import static com.alpha.redux.playerdata.xpManager.hasXp;
import static java.lang.Math.abs;
import static java.lang.Math.round;

public class boards {



    private static HashMap<UUID, Integer> scoreboardRefresh = new HashMap<>();

    public static void PlayerJoinScore(PlayerJoinEvent event){

        int runnable = Bukkit.getScheduler().scheduleSyncRepeatingTask(redux.INSTANCE, new Runnable() {
            @Override
            public void run() {
                CreateScore(event.getPlayer());
            }
        }, 0L, 100L);

        scoreboardRefresh.put(event.getPlayer().getUniqueId(), runnable);
    }

    public static void PlayerLeaveScore(PlayerQuitEvent event){
        if(scoreboardRefresh.containsKey(event.getPlayer().getUniqueId())){
            Bukkit.getScheduler().cancelTask(scoreboardRefresh.get(event.getPlayer().getUniqueId()));
        }
    }

    public static void ClearAllScore(){
        for (UUID uuid : scoreboardRefresh.keySet()){
            Bukkit.getScheduler().cancelTask(scoreboardRefresh.get(uuid));
        }
    }

    private static void RefreshScoreBoard(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("Title", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW + colorCode("&lTHE BETTER PIT"));

        String uuid = String.valueOf(player.getUniqueId());

        hasStreak(uuid);
        hasEconomy(uuid);
        hasPrestige(uuid);
        hasXp(uuid);

        DecimalFormat formatter = new DecimalFormat("#,###");

        int[] playerData = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        String prestigeColor = prestigebracket(player);

        Score version = objective.getScore(ChatColor.GRAY + "v1.0.0"); // Pit Redux Version

        Score spacer1 = objective.getScore(" "); //blank space
        Score spacer2 = objective.getScore("  "); //blank space
        Score spacer3 = objective.getScore("   "); //blank space
        Score spacer4 = objective.getScore("    "); //blank space
        Score spacer5 = objective.getScore("     "); //blank space
        Score spacer6 = objective.getScore("      "); //blank space
        Score spacer7 = objective.getScore("       "); //blank space
        Score spacer8 = objective.getScore("        "); //blank space
        Score spacer9 = objective.getScore("         "); //blank space
        Score spacer10 = objective.getScore("         "); //blank space

        Score levelData = objective.getScore(ChatColor.WHITE + "Level: " + prestigeColor + "[" + ChatColor.AQUA + getLevelColor(level) + level + prestigeColor + "]");
        Score goldData = objective.getScore(ChatColor.WHITE + "Gold: " + ChatColor.GOLD + formatter.format(getEconomy(String.valueOf(player.getUniqueId()))) + "g");
        Score streakData = objective.getScore(ChatColor.WHITE + "Streak: " + ChatColor.GREEN + getStreak(uuid));
        Score ipData = objective.getScore(ChatColor.YELLOW + "mc.pitredux.net");

        Score strengthData;
        Score xpData;
        Score statusData;

        if (getStreak(String.valueOf(player.getUniqueId())) >= getMegaActiveData(uuid)){
            statusData = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.RESET + getMegaData(uuid));
        }else if (cooldowns.containsKey(String.valueOf(player.getUniqueId())) &&
                cooldowns.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
            long timeleft = (cooldowns.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
            statusData = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.RED + "Fighting " + ChatColor.GRAY + "(" + timeleft + ")");
        }else{statusData = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.GREEN + "Idling");}

        // If statement to determine needed xp position
        if (neededXP == 323232323){xpData = objective.getScore(ChatColor.WHITE + "Needed XP: " + ChatColor.GOLD + "MAX");
        }else{xpData = objective.getScore(ChatColor.WHITE + "Needed XP: " + ChatColor.AQUA + formatter.format(abs(neededXP)));}

        if (Strength.get(uuid) == null){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE");
        }else if(Strength.get(uuid) >= 0 && Strength.get(uuid) <= .10){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "I");
        }else if(Strength.get(uuid) >= .10 && Strength.get(uuid) <= .20){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "II");
        }else if(Strength.get(uuid) >= .20 && Strength.get(uuid) <= .30){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "III");
        }else if(Strength.get(uuid) >= .30 && Strength.get(uuid) <= .40){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "IV");
        }else if(Strength.get(uuid) >= .40 && Strength.get(uuid) <= .50){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "V");
        }else{strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE");}



        if(Strength.get(uuid) != null &&
            getStreak(uuid) > 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            strengthData.setScore(3);
            streakData.setScore(4);
            statusData.setScore(5);
            spacer2.setScore(6);
            goldData.setScore(7);
            spacer3.setScore(8);
            xpData.setScore(9);
            levelData.setScore(10);
            spacer4.setScore(11);
            version.setScore(12);
        }else if(Strength.get(uuid) == null &&
                getStreak(uuid) <= 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            statusData.setScore(3);
            spacer2.setScore(4);
            goldData.setScore(5);
            spacer3.setScore(6);
            xpData.setScore(7);
            levelData.setScore(8);
            spacer4.setScore(9);
            version.setScore(10);
        }else{
            ipData.setScore(1);
            spacer1.setScore(2);
            statusData.setScore(3);
            spacer2.setScore(4);
            goldData.setScore(5);
            spacer3.setScore(6);
            xpData.setScore(7);
            levelData.setScore(8);
            spacer4.setScore(9);
            version.setScore(10);
        }

    }

    private static String getMegaData(String uuid){
        String select_mega = "";
        String mega_color = "";
        hasMegaStreak(uuid);
        if (Objects.equals(getMegaStreak(uuid), "highlander")){
            select_mega = "Highlander";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&6");
        }else if (Objects.equals(getMegaStreak(uuid), "beastmode")){
            select_mega = "Beastmode";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&a");
        }else if (Objects.equals(getMegaStreak(uuid), "uber")){
            select_mega = "Uberstreak";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&d");
        }else if (Objects.equals(getMegaStreak(uuid), "moon")){
            select_mega = "To the Moon";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&b");
        }

        return mega_color + select_mega;
    }

    private static int getMegaActiveData(String uuid){
        hasMegaStreak(uuid);
        if (Objects.equals(getMegaStreak(uuid), "highlander") ||
                Objects.equals(getMegaStreak(uuid), "beastmode")){
            return 50;
        }else if (Objects.equals(getMegaStreak(uuid), "uber") ||
                Objects.equals(getMegaStreak(uuid), "moon")){
            return 100;
        }else {
            return 50;
        }
    }

    public static void CreateScore(Player player){
        /*String select_mega = "";
        String mega_color = "";
        int activate_amount = 50;
        hasMegaStreak(String.valueOf(player.getUniqueId()));
        if (Objects.equals(getMegaStreak(String.valueOf(player.getUniqueId())), "highlander")){
            select_mega = "Highlander";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&6");
        }else if (Objects.equals(getMegaStreak(String.valueOf(player.getUniqueId())), "beastmode")){
            select_mega = "Beastmode";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&a");
        }else if (Objects.equals(getMegaStreak(String.valueOf(player.getUniqueId())), "uber")){
            select_mega = "Uberstreak";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&d");
            activate_amount = 100;
        }else if (Objects.equals(getMegaStreak(String.valueOf(player.getUniqueId())), "moon")){
            select_mega = "To the Moon";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&b");
            activate_amount = 100;
        }

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("Title", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW + colorCode("&lTHE BETTER PIT"));

        hasStreak(String.valueOf(player.getUniqueId()));
        hasEconomy(String.valueOf(player.getUniqueId()));
        hasPrestige(String.valueOf(player.getUniqueId()));
        DecimalFormat formatter = new DecimalFormat("#,###");

        hasXp(String.valueOf(player.getUniqueId()));
        int[] randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);

        String prestige_bracket = prestigebracket(player);

        Score score = objective.getScore(ChatColor.GRAY + "v1.0.0"); //create a line for the board
        Score s2 = objective.getScore(" "); //blank space
        //Score s3 = objective.getScore(ChatColor.WHITE + "Prestige: " + ChatColor.YELLOW + integerToRoman(getPrestige(String.valueOf(player.getUniqueId()))));
        Score s3 = objective.getScore(ChatColor.WHITE + "Level: " + prestige_bracket + "[" + ChatColor.AQUA + getLevelColor(randomDUDE[1]) + randomDUDE[1] + prestige_bracket + "]");
        Score s4;

        if (randomDUDE[0] == 323232323){
            s4 = objective.getScore(ChatColor.WHITE + "Needed XP: " + ChatColor.GOLD + "MAX");
        }else{
            s4 = objective.getScore(ChatColor.WHITE + "Needed XP: " + ChatColor.AQUA + formatter.format(abs(randomDUDE[0])));
        }

        Score s5 = objective.getScore("  ");
        Score s6 = objective.getScore(ChatColor.WHITE + "Gold: " + ChatColor.GOLD + formatter.format(getEconomy(String.valueOf(player.getUniqueId()))) + "g");
        Score s7 = objective.getScore("   ");

        Score s9 = objective.getScore(ChatColor.WHITE + "Streak: " + ChatColor.GREEN + getStreak(String.valueOf(player.getUniqueId())));
        Score s8 = null;
        if (getStreak(String.valueOf(player.getUniqueId())) >= activate_amount){
            s8 = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.RESET + mega_color + select_mega);
        }else if (getStreak(String.valueOf(player.getUniqueId())) >= 1 && getStreak(String.valueOf(player.getUniqueId())) <= 49){
            s8 = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.RED + "Fighting");
        }else{
            s8 = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.GREEN + "Idling");
        }

        Score s10 = objective.getScore("    ");

        Score s11 = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "Not Selected");

        if (Strength.get(String.valueOf(player.getUniqueId())) == null){
            s11 = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE");
        }else if(Strength.get(String.valueOf(player.getUniqueId())) >= 0 && Strength.get(String.valueOf(player.getUniqueId())) <= .10){
            s11 = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "1");
        }else if(Strength.get(String.valueOf(player.getUniqueId())) >= .10 && Strength.get(String.valueOf(player.getUniqueId())) <= .20){
            s11 = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "2");
        }else if(Strength.get(String.valueOf(player.getUniqueId())) >= .20 && Strength.get(String.valueOf(player.getUniqueId())) <= .30){
            s11 = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "3");
        }else if(Strength.get(String.valueOf(player.getUniqueId())) >= .30 && Strength.get(String.valueOf(player.getUniqueId())) <= .40){
            s11 = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "4");
        }else if(Strength.get(String.valueOf(player.getUniqueId())) >= .40 && Strength.get(String.valueOf(player.getUniqueId())) <= .50){
            s11 = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "5");
        }else{
            s11 = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE");
        }

        Score s12 = objective.getScore("     ");

        Score s13;
        if (randomDUDE[0] == 323232323){
            s13 = objective.getScore(ChatColor.WHITE + "Needed XP: " + ChatColor.GOLD + "MAX");
        }else{
            s13 = objective.getScore(ChatColor.WHITE + "Needed XP: " + ChatColor.AQUA + formatter.format(abs(randomDUDE[0])));
        }


        score.setScore(13);
        s2.setScore(12);
        s3.setScore(11);
        s4.setScore(10);
        s5.setScore(9);
        s6.setScore(8);
        s7.setScore(7);
        s8.setScore(6);
        s9.setScore(5);
        s10.setScore(4);
        s11.setScore(3);
        s12.setScore(2);
        s13.setScore(1);
*/
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("Title", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW + colorCode("&lTHE BETTER PIT"));

        String uuid = String.valueOf(player.getUniqueId());

        hasStreak(uuid);
        hasEconomy(uuid);
        hasPrestige(uuid);
        hasXp(uuid);

        DecimalFormat formatter = new DecimalFormat("#,###");

        int[] playerData = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        String prestigeColor = prestigebracket(player);

        Score version = objective.getScore(ChatColor.GRAY + "v1.0.0"); // Pit Redux Version

        Score spacer1 = objective.getScore(" "); //blank space
        Score spacer2 = objective.getScore("  "); //blank space
        Score spacer3 = objective.getScore("   "); //blank space
        Score spacer4 = objective.getScore("    "); //blank space
        Score spacer5 = objective.getScore("     "); //blank space
        Score spacer6 = objective.getScore("      "); //blank space
        Score spacer7 = objective.getScore("       "); //blank space
        Score spacer8 = objective.getScore("        "); //blank space
        Score spacer9 = objective.getScore("         "); //blank space
        Score spacer10 = objective.getScore("         "); //blank space

        Score levelData = objective.getScore(ChatColor.WHITE + "Level: " + prestigeColor + "[" + ChatColor.AQUA + getLevelColor(level) + level + prestigeColor + "]");
        Score goldData = objective.getScore(ChatColor.WHITE + "Gold: " + ChatColor.GOLD + formatter.format(getEconomy(String.valueOf(player.getUniqueId()))) + "g");
        Score streakData = objective.getScore(ChatColor.WHITE + "Streak: " + ChatColor.GREEN + getStreak(uuid));
        Score ipData = objective.getScore(ChatColor.YELLOW + "mc.pitredux.net");
        Score prestigeData = objective.getScore(ChatColor.WHITE + "Prestige: " + ChatColor.YELLOW + integerToRoman(getPrestige(uuid)));

        Score strengthData;
        Score xpData;
        Score statusData;

        if (getStreak(String.valueOf(player.getUniqueId())) >= getMegaActiveData(uuid)){
            statusData = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.RESET + getMegaData(uuid));
        }else if (cooldowns.containsKey(String.valueOf(player.getUniqueId())) &&
                cooldowns.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
            long timeleft = (cooldowns.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
            statusData = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.RED + "Fighting " + ChatColor.GRAY + "(" + timeleft + ")");
        }else{statusData = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.GREEN + "Idling");}

        // If statement to determine needed xp position
        if (neededXP == 323232323){xpData = objective.getScore(ChatColor.WHITE + "XP: " + ChatColor.AQUA + "MAXED!");
        }else{xpData = objective.getScore(ChatColor.WHITE + "Needed XP: " + ChatColor.AQUA + formatter.format(abs(neededXP)));}

        if (Strength.get(uuid) == null){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE");
        }else if(Strength.get(uuid) >= 0 && Strength.get(uuid) <= .10){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "I");
        }else if(Strength.get(uuid) >= .10 && Strength.get(uuid) <= .20){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "II");
        }else if(Strength.get(uuid) >= .20 && Strength.get(uuid) <= .30){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "III");
        }else if(Strength.get(uuid) >= .30 && Strength.get(uuid) <= .40){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "IV");
        }else if(Strength.get(uuid) >= .40 && Strength.get(uuid) <= .50){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "V");
        }else{strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE");}



        if(Strength.get(uuid) != null &&
                getStreak(uuid) > 0 && getPrestige(uuid) > 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            strengthData.setScore(3);
            streakData.setScore(4);
            statusData.setScore(5);
            spacer2.setScore(6);
            goldData.setScore(7);
            spacer3.setScore(8);
            xpData.setScore(9);
            levelData.setScore(10);
            prestigeData.setScore(11);
            spacer4.setScore(12);
            version.setScore(13);
        }else if (Strength.get(uuid) != null &&
                getStreak(uuid) > 0 && getPrestige(uuid) == 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            strengthData.setScore(3);
            streakData.setScore(4);
            statusData.setScore(5);
            spacer2.setScore(6);
            goldData.setScore(7);
            spacer3.setScore(8);
            xpData.setScore(9);
            levelData.setScore(10);
            spacer4.setScore(11);
            version.setScore(12);
        }else if(Strength.get(uuid) == null &&
                getStreak(uuid) <= 0 && getPrestige(uuid) > 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            statusData.setScore(3);
            spacer2.setScore(4);
            goldData.setScore(5);
            spacer3.setScore(6);
            xpData.setScore(7);
            levelData.setScore(8);
            prestigeData.setScore(9);
            spacer4.setScore(10);
            version.setScore(11);
        }else if (getPrestige(uuid) >= 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            statusData.setScore(3);
            spacer2.setScore(4);
            goldData.setScore(5);
            spacer3.setScore(6);
            xpData.setScore(7);
            levelData.setScore(8);
            spacer4.setScore(9);
            version.setScore(10);
        }else{
            ipData.setScore(1);
            spacer1.setScore(2);
            statusData.setScore(3);
            spacer2.setScore(4);
            goldData.setScore(5);
            spacer3.setScore(6);
            xpData.setScore(7);
            levelData.setScore(8);
            prestigeData.setScore(9);
            spacer4.setScore(10);
            version.setScore(11);
        }

        player.setScoreboard(scoreboard);


    }

    public static StringBuilder integerToRoman(int num) {

        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman;
    }



}
