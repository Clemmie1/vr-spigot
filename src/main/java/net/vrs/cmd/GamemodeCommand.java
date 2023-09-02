package net.vrs.cmd;

import net.vrs.VRS;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GamemodeCommand implements CommandExecutor {

    public GamemodeCommand(VRS main){
        main.getCommand("gm").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args){

        if (commandSender instanceof CommandExecutor){
            return true;
        }

        Player player = (Player)commandSender;

        if (player.hasPermission("vrs.gm")){

            if (args.length == 0){
                player.sendMessage("§a======= §bGamemode §a=======");
                player.sendMessage("");
                player.sendMessage("§e/gm <0, 1, 2, 3> <можно ник> §7- Сменить режим");
            } else {

                if (Objects.equals(args[0], "0")){
                    if (player.hasPermission("vrs.gm.0")){

                        player.setGameMode(GameMode.SURVIVAL);

                    } else {
                        player.sendMessage("§cУ вас нет прав для выполнения этой команды");
                    }
                } else if (Objects.equals(args[0], "1")) {
                    if (player.hasPermission("vrs.gm.1")){

                        player.setGameMode(GameMode.CREATIVE);

                    } else {
                        player.sendMessage("§cУ вас нет прав для выполнения этой команды");
                    }
                } else if (Objects.equals(args[0], "2")){
                    if (player.hasPermission("vrs.gm.2")){

                        player.setGameMode(GameMode.ADVENTURE);

                    } else {
                        player.sendMessage("§cУ вас нет прав для выполнения этой команды");
                    }
                } else if (Objects.equals(args[0], "3")){
                    if (player.hasPermission("vrs.gm.3")){

                        player.setGameMode(GameMode.SPECTATOR);

                    } else {
                        player.sendMessage("§cУ вас нет прав для выполнения этой команды");
                    }
                }


            }

            if (args.length > 1){
                String recipient = args[1];
                Player target = Bukkit.getPlayerExact(recipient);

                if (player.hasPermission("vrs.gm.other")){
                    if (target != null){
                        player.sendMessage("§aИгровой режим был изменен для §e" + recipient);
                    } else {
                        player.sendMessage("§cИгрок не найден");
                    }
                }
            }

        } else {
            commandSender.sendMessage("§cУ вас нет прав для выполнения этой команды");
        }


        return false;

    }
}
