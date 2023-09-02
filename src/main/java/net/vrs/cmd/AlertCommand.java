package net.vrs.cmd;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.vrs.VRS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertCommand implements CommandExecutor {

    public AlertCommand(VRS main){
        main.getCommand("alert").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args){

        if (commandSender instanceof CommandExecutor){

        } else {
            Player player = (Player)commandSender;

            if (player.hasPermission("vrs.alert")) {

                if (args.length > 0) {

                    String alertMsg = args[0];

                    StringBuilder builder = new StringBuilder();

                    for (int i = 0; i < args.length; ++i) {
                        builder.append(args[i]).append(' ');
                    }

                    for (Player players : Bukkit.getServer().getOnlinePlayers()) {

                        players.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c§lВНИМАНИЕ! ОБЪЯВЛЕНИЕ В ЧАТЕ"));


                        players.sendMessage("§c§l[ОБЪЯВЛЕНИЕ]§r§f " + builder.toString());


                    }

                } else {

                    player.sendMessage("§cИспользуйте: /alert <сообщение>");
                }
            }



        }

        return false;

    }

    private String formatTime(long time) {
        long minutes = (time % 3600) / 60;
        long seconds = time % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

}
