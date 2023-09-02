package net.vrs.msg;

import net.kyori.adventure.sound.Sound;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.vrs.VRS;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SendMsg implements CommandExecutor {

    public SendMsg(VRS main){
        main.getCommand("m").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args){

        if(commandSender instanceof ConsoleCommandSender){
            return true;
        }

        Player player = (Player)commandSender;

        if(args.length == 0){
            player.sendMessage("§cИспользуйте: /m <ник> <сообщение>");
            return true;
        }

        if (args.length >= 2){

            String sender = player.getName();

            Player target = Bukkit.getPlayerExact(args[0]);
            String targetName = target.getName();

            if(target != null){

                StringBuilder builder = new StringBuilder();

                for (int i = 1; i < args.length; ++i) {
                    builder.append(args[i]).append(' ');
                }

                player.sendMessage(translateColorCodes("&e" + targetName + "&7 > &f" + builder.toString()));

                target.sendMessage(translateColorCodes("&e" + sender + "&7 > &f" + builder.toString()));
                target.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§fНОВОЕ СООБЩЕНИЕ"));


                // XYZ A: -745.649 / 47.00 / 779.301
                // XYZ B: -584.752 / 191.59 / 912.686

            } else {
                player.sendMessage("§cИгрок не найден");
            }

        } else {
            player.sendMessage("§cИспользуйте: /m <ник> <сообщение>");
        }

        return false;
    }

    public String translateColorCodes(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }



}
