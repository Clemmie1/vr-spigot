package net.vrs.events;


import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.vrs.PlayerAPI;
import net.vrs.VRS;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class EventAll implements Listener {

    private double minX = -745.649;
    private double maxX = -584.752;
    private double minY = 47.00;
    private double maxY = 191.59;
    private double minZ = 779.301;
    private double maxZ = 912.686;

    @EventHandler
    public void Join(PlayerJoinEvent e){

        updateTabList();

        Player p = e.getPlayer();
        String username = p.getName();

        if (p.hasPermission("vr.premium")){
            e.setJoinMessage("§l§a+ §lVRP §r§a"+ username + " §7подключился ");
            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§l§a+ §lVRP §r§a"+ username));
            }
        } else {
            e.setJoinMessage("§l§a+ §r§a"+ username + " §7подключился ");
            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§l§a+ §r§a"+ username));
            }
        }

        if (!PlayerAPI.isUserExists(username)){
            PlayerAPI.createPlayer(username);
        }

        PlayerAPI.setPlayerOnlineStatus(username, true);

//        new BukkitRunnable() {
//            @Override
//            public void run() {
//
//                LocalDateTime expirationDate = LocalDateTime.of(2026, 1, 1, 0, 0);
//s
//                // Текущая дата и время
//                LocalDateTime currentDate = LocalDateTime.now();
//
//                // Вычисляем разницу между датами
//                long days = ChronoUnit.DAYS.between(currentDate, expirationDate);
//                long hours = ChronoUnit.HOURS.between(currentDate, expirationDate) % 24;
//                String result = String.format("\n\n§fВаша подписка §aПроходка на сервер§f истекает через §a%d§f д. §a%d§f ч.", days, hours);
//                String resultTitle = String.format("§aПроходка на сервер§f истекает через §a%d§f д. §a%d§f ч.", days, hours);
//
//                TextComponent message = new TextComponent("\n\n§fУправление аккаунтом, подпиской и тд.\n\n        §e§eКЛИКНИ СЮДА");
//                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/web"));
//
//                // Отправляем сообщение с кликабельным текстом игроку
//                p.spigot().sendMessage(message);
//                p.sendMessage(result);
//                p.sendTitle("§fВаша подписка", resultTitle);
//            }
//        }.runTaskLater(VRS.instance, 100);

    }

    @EventHandler
    public void Quit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        String username = p.getName();

        if (p.hasPermission("vr.premium")){
            e.setQuitMessage("§l§c- §a§lVRP §r§c" + username + " §7отключился");

            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§l§c- §a§lVRP §r§c" + username));
            }
        } else {
            e.setQuitMessage("§l§c- §r§c" + username + " §7отключился");

            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§l§c- §r§c" + username));
            }
        }

//        int player_total_exp = Integer.parseInt(PlaceholderAPI.setPlaceholders(p, "%player_total_exp%"));
//        int player_level = Integer.parseInt(PlaceholderAPI.setPlaceholders(p, "%player_level%"));
//        int statistic_hours_played = Integer.parseInt(PlaceholderAPI.setPlaceholders(p, "%statistic_hours_played%"));
//        int statistic_mine_block = Integer.parseInt(PlaceholderAPI.setPlaceholders(p, "%statistic_mine_block%"));
//        PlayerAPI.updatePlayer(username, statistic_hours_played, player_total_exp, player_level, statistic_mine_block);

        PlayerAPI.setPlayerOnlineStatus(username, false);

    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player p = (Player) event.getEntity();
            int health = (int) p.getHealth();

            if (health == 10) {
                p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§l§cВоу-Воу Палехчэ!"));
            } else if (health == 4) {
                p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§l§cЕщё немного, и я умру!"));
            }

        }

    }

    @EventHandler
    public void FoodLevelChangeEvent(FoodLevelChangeEvent event) {
        Player p = (Player) event.getEntity();
        int food = (int) p.getFoodLevel();

        if (food == 7) {
            p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§l§cЧто-то я проголодался!"));
        } else if (food == 4) {
            p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§l§cПора перекусить!"));
        }
    }

    @EventHandler
    public void PlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        Player p = (Player) event.getPlayer();
        if (event.getPlayer().getWorld().getName().equals("world")) {
            p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aРодной, ламповый, незаменимый, безопасный дом!"));
        } else if (event.getPlayer().getWorld().getName().equals("world_nether")) {
            p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cБудь осторожен, ведь на каждом уголке этого мира тебя поджидают опасности!"));
        } else if (event.getPlayer().getWorld().getName().equals("world_the_end")) {
            p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cОго, мы что в космосе?!"));
        }
    }

    @EventHandler
    public void GameModerChange(PlayerGameModeChangeEvent event) {
        event.getPlayer().sendMessage("§aВаш игровой режим был обновлен на: §e" + event.getNewGameMode().name());
    }

    @EventHandler
    public void Chat(AsyncPlayerChatEvent e) {
        Player sender = e.getPlayer();
        String senderName = sender.getDisplayName();


        e.setFormat("§f" + senderName + ": " + e.getMessage());

    }


    private void updateTabList() {
        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListHeaderFooter(
                    ChatColor.GREEN + "Vanilla Reborn\n",
                    new StringBuilder()
                            .append(ChatColor.YELLOW)
                            .append("Online: ")
                            .append(ChatColor.WHITE)
                            .append(onlinePlayers)
                            .append(" ")
                            .append(ChatColor.YELLOW)
                            .append("players")
                            .toString()
            );
            player.setPlayerListFooter("\n§evanillareborn.net");
        }
    }


    @EventHandler
    public void onCommandUse(PlayerCommandPreprocessEvent event) {
        List<String> commands = Arrays.asList("?", "pl", "about", "version", "ver", "plugins", "bukkit:?", "bukkit:pl", "bukkit:about", "bukkit:version", "bukkit:ver", "bukkit:plugins", "minecraft:pl", "minecraft:plugins", "minecraft:about", "minecraft:version", "minecraft:ver", "minecraft:ban", "minecraft:ban-ip", "minecraft:pardon", "minecraft:pardon-ip");
        commands.forEach(all -> {
            String[] arrCommand = event.getMessage().toLowerCase().split(" ", 2);
            if (arrCommand[0].equalsIgnoreCase("/" + all.toLowerCase())) {
                event.setCancelled(true);
            }
        });
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();

        if (x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ) {
            new ActionBarTask(player).runTaskTimer(VRS.instance, 0, 20); // Обновляем каждую секунду (20 тиков)
        }
    }

//    @EventHandler
//    public void onBlockBreak(BlockBreakEvent event) {
//        Player player = event.getPlayer();
//        double x = event.getBlock().getLocation().getX();
//        double y = event.getBlock().getLocation().getY();
//        double z = event.getBlock().getLocation().getZ();
//
//        if (x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ) {
//            event.setCancelled(true); // Отменяем ломание блока
//            player.sendTitle("", "§cВы не можете ломать блоки в этой области!");
//        }
//    }

    private static class ActionBarTask extends BukkitRunnable {

        private final Player player;
        private int ticksLeft = 5; // Отображаем сообщение в течение 5 секунд

        public ActionBarTask(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            if (ticksLeft > 0) {
                player.sendActionBar("§aПОЖАЛУЙСТА, НЕ ЛОМАЙТЕ СПАВН!");
                ticksLeft--;
            } else {
                this.cancel(); // Останавливаем задачу после 5 секунд
            }
        }
    }
}
