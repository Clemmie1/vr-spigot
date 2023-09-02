package net.vrs.cmd;

import net.vrs.VRS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    public PingCommand(VRS main){
        main.getCommand("ping").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args){

        if(commandSender instanceof ConsoleCommandSender){
            return true;
        }

        Player player = (Player)commandSender;
        int getPing = player.getPing();

        if(args.length == 0){

            player.sendMessage("§aВычисляем ваш пинг...");
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            player.sendMessage("§7Ваш пинг: §a" + getPing + "мс");
                        }
                    },
                    600
            );

        } else if (args.length > 0){
            if(player.hasPermission("vrs.getping")){

                Player target = Bukkit.getPlayerExact(args[0]);
                String targetName = target.getName();
                int targetPing = target.getPing();

                if(target != null){
                    player.sendMessage("§aВычисляем пинг игрока "+targetName+"...");
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    player.sendMessage("§7Пинг игрока "+targetName+": §a" + targetPing + "мс");
                                }
                            },
                            600
                    );
                } else {
                    player.sendMessage("§cИгрок не найден");
                }

            } else {

                player.sendMessage("§cУ вас нет прав для выполнения этой команды");
            }
        }

        return false;

    }

}
