package net.vrs;

import jdk.jfr.Timestamp;
import net.vrs.Providers.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerAPI {

    public static boolean isUserExists(String player) {


        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT `account_name` FROM `player_statistic` WHERE `account_name` = ?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public static void createPlayer(String player) {
        if(!isUserExists(player)) {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO `player_statistic` (`account_name`, `first_join`, `last_join`) " +
                        "VALUES ('"+player+"', '"+ServerTime.getTimeData()+"', '"+ServerTime.getTimeData()+"')");
                ps.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void setPlayerOnlineStatus(String player, boolean isServerOnline){
        if (isServerOnline) {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE `player_statistic` SET `player_status` = 1 WHERE `account_name` = '"+player+"'");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE `player_statistic` SET `last_join` = '"+ServerTime.getTimeData()+"' WHERE `account_name` = '"+player+"'");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE `player_statistic` SET `player_status` = 0 WHERE `account_name` = '"+player+"'");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void setUpdateHoursPlayed(String username, int count){
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE `player_statistic` SET `player_hours_played` = ? WHERE `account_name` = ?");
            ps.setInt(1, count);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setUpdatePlayer(String username, int player_hours_played, int player_total_exp, int mine_block_all, int mine_block_nether_gold_ore, int mine_block_diamond_ore, int mine_block_redstone_ore, int mine_block_coal_ore, int mine_block_gold_ore, int total_changed_world, int total_changed_world_nether, int total_changed_world_the_end, int total_player_death, int total_player_kills, int total_player_kills_mob){
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE `player_statistic` SET `player_hours_played`=?, `player_total_exp`=`player_total_exp`+?, `mine_block_all`=`mine_block_all`+?, `mine_block_nether_gold_ore`=`mine_block_nether_gold_ore`+?, `mine_block_diamond_ore`=`mine_block_diamond_ore`+?, `mine_block_redstone_ore`=`mine_block_redstone_ore`+?, `mine_block_coal_ore`=`mine_block_coal_ore`+?, `mine_block_gold_ore`=`mine_block_gold_ore`+?, `total_changed_world`=`total_changed_world`+?, `total_changed_world_nether`=`total_changed_world_nether`+?, `total_changed_world_the_end`=`total_changed_world_the_end`+?, `total_player_death`=`total_player_death`+?, `total_player_kills`=`total_player_kills`+?, `total_player_kills_mob`=`total_player_kills_mob`+? WHERE `account_name` = ?");
            ps.setInt(1, player_hours_played);
            ps.setInt(2, player_total_exp);
            ps.setInt(3, mine_block_all);
            ps.setInt(4, mine_block_nether_gold_ore);
            ps.setInt(5, mine_block_diamond_ore);
            ps.setInt(6, mine_block_redstone_ore);
            ps.setInt(7, mine_block_coal_ore);
            ps.setInt(8, mine_block_gold_ore);
            ps.setInt(9, total_changed_world);
            ps.setInt(10, total_changed_world_nether);
            ps.setInt(11, total_changed_world_the_end);
            ps.setInt(12, total_player_death);
            ps.setInt(13, total_player_kills);
            ps.setInt(14, total_player_kills_mob);
            ps.setString(15, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static boolean setUpdateTimeRecordPlayer(String username, int count) {
//        try {
//            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT `time_played` FROM `player_statistic` WHERE `account_name` = ?");
//            ps.setString(1, username);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                int timePlayed = rs.getInt("time_played");
//                if (timePlayed > count) {
//                    PreparedStatement updatePs = MySQL.getConnection().prepareStatement("UPDATE `player_statistic` SET `time_played` = ? WHERE `account_name` = ?");
//                    updatePs.setInt(1, count);
//                    updatePs.setString(2, username);
//                    updatePs.executeUpdate();
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
}
