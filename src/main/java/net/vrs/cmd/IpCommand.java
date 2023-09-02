package net.vrs.cmd;

import net.vrs.VRS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IpCommand implements CommandExecutor {
    public IpCommand(VRS main){
        main.getCommand("getip").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args){

        if (commandSender instanceof CommandExecutor){

        } else {
            Player player = (Player)commandSender;

            if (player.hasPermission("vrs.getip")){
                if(args.length > 0){

                    String username = args[0];
                    Player target = Bukkit.getPlayerExact(args[0]);

                    if (target != null){

                        String sIp1 = target.getAddress().getHostName();
                        player.sendMessage("§aВычисляем IP...");

                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        player.sendMessage("§7IP-адрес игрока §a" + username + "§7: " + sIp1);
                                    }
                                },
                                800
                        );

                    } else {
                        player.sendMessage("§cИгрок не найден");
                    }

                } else {
                    player.sendMessage("§cИспользуйте: /getip <ник>");
                }
            } else {
                player.sendMessage("§cУ вас нет прав для выполнения этой команды");
            }
        }

        return false;
    }
}
