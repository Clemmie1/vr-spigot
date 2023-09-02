package net.vrs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class PlayerHPIndicator {

    private final ScoreboardManager scoreboardManager;
    private final Scoreboard scoreboard;

    public PlayerHPIndicator() {
        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        initObjective();
    }

    private void initObjective() {
        Objective objective = scoreboard.registerNewObjective("playerHP", "health", ChatColor.RED + "❤");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    public void setPlayerHP(Player player) {
        Team team = scoreboard.getTeam(player.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
        }

        if (player.hasPermission("vr.premium")){
            team.setPrefix("§a§lVRP" + ChatColor.RESET + " ");
        }


        team.addEntry(player.getName()); // Добавляем игрока в команду
        player.setScoreboard(scoreboard);
    }

    public void removePlayer(Player player) {
        player.getScoreboard().clearSlot(DisplaySlot.BELOW_NAME);
        Team team = scoreboard.getTeam(player.getName());
        if (team != null) {
            team.unregister();
        }
    }

}
