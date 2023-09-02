package net.vrs.events;

import me.clip.placeholderapi.PlaceholderAPI;
import net.vrs.PlayerAPI;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StatisticEvent implements Listener {

    private static int total_nether_gold_ore = 0;
    private static int total_diamond_ore = 0;
    private static int total_redstone_ore = 0;
    private static int total_copper_ore = 0;
    private static int total_coal_ore = 0;
    private static int total_gold_ore = 0;

    private static int total_player_death = 0;
    private static int total_player_kills = 0;
    private static int total_player_kills_mob = 0;


    private static int total_changed_world = 0;
    private static int total_changed_world_nether = 0;
    private static int total_changed_world_the_end = 0;

    private static int time_played_world = 0;
    private static int time_played_world_the_end = 0;
    private static int time_played_world_nether = 0;


    private static int totalBlocks = 0;
    private static int total_exp = 0;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        totalBlocks ++;

        if (event.getBlock().getBlockData().getMaterial() == Material.NETHER_GOLD_ORE){
            total_nether_gold_ore++;
        }

        if (event.getBlock().getBlockData().getMaterial() == Material.DIAMOND_ORE){
            total_diamond_ore++;
        }

        if (event.getBlock().getBlockData().getMaterial() == Material.REDSTONE_ORE){
            total_redstone_ore++;
        }

        if (event.getBlock().getBlockData().getMaterial() == Material.COPPER_ORE){
            total_copper_ore++;
        }

        if (event.getBlock().getBlockData().getMaterial() == Material.COAL_ORE){
            total_coal_ore++;
        }

        if (event.getBlock().getBlockData().getMaterial() == Material.GOLD_ORE){
            total_gold_ore++;
        }


    }

    @EventHandler
    public void ExpChange(PlayerExpChangeEvent event){

        int exp = event.getAmount();
        total_exp += exp;

    }


    @EventHandler
    public void ChangedWorldEvent(PlayerChangedWorldEvent event) {

        if (event.getPlayer().getWorld().getName().equals("world")) {
            total_changed_world++;
        }

        if (event.getPlayer().getWorld().getName().equals("world_nether")) {
            total_changed_world_nether++;

        }

        if (event.getPlayer().getWorld().getName().equals("world_the_end")) {
            total_changed_world_the_end++;
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        total_player_death++;
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer != null) {
            total_player_kills++;
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity && !(entity instanceof Player)) {
            total_player_kills_mob++;
        }
    }



    @EventHandler
    public void quit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        String username = p.getName();

        int statistic_hours_played = Integer.parseInt(PlaceholderAPI.setPlaceholders(p, "%statistic_hours_played%"));
        int statistic_time_played = Integer.parseInt(PlaceholderAPI.setPlaceholders(p, "%statistic_time_played:minutes%"));


//        if (p.getWorld().getName().equals("world")) {
//            long time = p.getWorld().getFullTime() / 20;
//            String formattedTime = formatTime(time);
//            int t = Integer.parseInt(formattedTime);
//            time_played_world++;
//        } else if (p.getWorld().getName().equals("world_nether")) {
//            long time = p.getWorld().getFullTime() / 20;
//            String formattedTime = formatTime(time);
//            int t = Integer.parseInt(formattedTime);
//            time_played_world_nether++;
//        } else if (p.getWorld().getName().equals("world_the_end")) {
//            long time = p.getWorld().getFullTime() / 20;
//            String formattedTime = formatTime(time);
//            int t = Integer.parseInt(formattedTime);
//            time_played_world_the_end++;
//        }

        PlayerAPI.setUpdateHoursPlayed(username, statistic_hours_played);
        PlayerAPI.setUpdatePlayer(username, statistic_hours_played, total_exp, totalBlocks, total_nether_gold_ore, total_diamond_ore, total_redstone_ore, total_coal_ore, total_gold_ore, total_changed_world, total_changed_world_nether, total_changed_world_the_end, total_player_death, total_player_kills, total_player_kills_mob);

        SetNull();
    }

    private String formatTime(long time) {
        long minutes = (time % 3600) / 60;

        return String.format("%d", minutes);
    }

    public void SetNull(){
        total_nether_gold_ore = 0;
        total_diamond_ore = 0;
        total_redstone_ore = 0;
        total_copper_ore = 0;
        total_coal_ore = 0;

        total_player_death = 0;
        total_player_kills = 0;
        total_player_kills_mob = 0;


        total_changed_world = 0;
        total_changed_world_nether = 0;
        total_changed_world_the_end = 0;


        totalBlocks = 0;
        total_exp = 0;
    }

}
