package net.vrs;

import me.clip.placeholderapi.PlaceholderAPI;
import net.vrs.Providers.MySQL;
import net.vrs.cmd.*;
import net.vrs.events.EventAll;
import net.vrs.events.SetMyHomeListener;
import net.vrs.events.StatisticEvent;
import net.vrs.msg.SendMsg;
import net.vrs.vk.OnlineCommand;
import net.vrs.vk.StatsCommand;
import net.vrs.vk.TopCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class VRS extends JavaPlugin implements Listener {

    private PlayerHPIndicator playerHPIndicator;

    public static VRS instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        MySQL.connect();

        new GamemodeCommand(this);
        new IpCommand(this);
        new AlertCommand(this);
        new PingCommand(this);
        new SendMsg(this);
        //new SetMyHomeCommand(this);

        Bukkit.getPluginManager().registerEvents(new EventAll(), this);
        Bukkit.getPluginManager().registerEvents(new OnlineCommand(), this);
        Bukkit.getPluginManager().registerEvents(new TopCommand(), this);
        Bukkit.getPluginManager().registerEvents(new StatsCommand(), this);
        Bukkit.getPluginManager().registerEvents(new StatisticEvent(), this);
        //Bukkit.getPluginManager().registerEvents(new SetMyHomeListener(), this);

        playerHPIndicator = new PlayerHPIndicator();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MySQL.disconnect();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerHPIndicator.setPlayerHP(player);
    }

//    @EventHandler
//    public void onPlayerDeath(PlayerDeathEvent event) {
//        Player player = event.getEntity();
//        String playerName = player.getName();
//        String deathDate = java.time.LocalDate.now().toString();
//
//        Date date = new Date();
//
//        // Задаем временную зону как "Europe/Moscow" (Московское время)
//        TimeZone timeZone = TimeZone.getTimeZone("Europe/Moscow");
//
//        // Создаем форматтер даты и времени
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//        sdf.setTimeZone(timeZone);
//
//        // Форматируем текущее время в строку с московским временем
//        String moscowTime = sdf.format(date);
//
//        // Получаем местоположение, где игрок умер
//        Location deathLocation = player.getLocation();
//
//        // Убираем высоту (чтобы табличка была на уровне земли)
//        deathLocation.setY(deathLocation.getY() - 0);
//
//        // Создаем табличку на месте смерти игрока
//        Block deathBlock = deathLocation.getBlock();
//        deathBlock.setType(Material.OAK_SIGN);
//
//        // Устанавливаем текст на табличке
//        Sign sign = (Sign) deathBlock.getState();
//        sign.setLine(0, "§c§lR.I.P");
//        sign.setLine(1, "§7" + playerName);
//        sign.setLine(2, "§7" + moscowTime);
//        sign.setLine(3, "§evanillareborn.net");
//        sign.update();
//
//        Location skullLocation = deathLocation.clone().add(0.5, 0, 0.5); // Смещаем голову по центру таблички
//        Block skullBlock = skullLocation.getBlock();
//        skullBlock.setType(Material.PLAYER_HEAD);
//
//        // Устанавливаем текстуру головы игрока
//        Skull skull = (Skull) skullBlock.getState();
//        skull.setRotation(BlockFace.WEST); // Можно изменить направление головы, если необходимо
//
//        // Устанавливаем текстуру головы игрока по его нику
//        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
//        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
//        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
//        skullItem.setItemMeta(skullMeta);
//
//        // Устанавливаем текстуру для блока головы
//        skull.setOwningPlayer(skullMeta.getOwningPlayer());
//
//        skull.update();
//    }

//    @EventHandler
//    public void onPlayerQuit(PlayerQuitEvent event) {
//        Player player = event.getPlayer();
//        playerHPIndicator.removePlayer(player);
//    }


}
