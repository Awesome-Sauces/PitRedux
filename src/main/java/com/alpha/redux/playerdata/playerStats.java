package com.alpha.redux.playerdata;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.*;

import static com.alpha.redux.playerdata.prestiges.getPrestigeMap;
import static com.alpha.redux.playerdata.streaks.*;

public class playerStats {

    public static List<Entry<String, Integer>> mainGetTop() {return findGreatest(getPrestigeMap(), 10);}

    private static <K, V extends Comparable<? super V>> List<Entry<K, V>>
    findGreatest(Map<K, V> map, int n)
    {
        Comparator<? super Entry<K, V>> comparator =
                (Comparator<Entry<K, V>>) (e0, e1) -> {
                    V v0 = e0.getValue();
                    V v1 = e1.getValue();
                    return v0.compareTo(v1);
                };
        PriorityQueue<Entry<K, V>> highest =
                new PriorityQueue<Entry<K,V>>(n, comparator);
        for (Entry<K, V> entry : map.entrySet())
        {
            highest.offer(entry);
            while (highest.size() > n)
            {
                highest.poll();
            }
        }

        List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
        while (highest.size() > 0)
        {
            result.add(highest.poll());
        }
        return result;
    }

}
