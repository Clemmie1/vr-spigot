package net.vrs.cmd;

import net.vrs.VRS;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMyHomeCommand implements CommandExecutor {
    public SetMyHomeCommand(VRS main){
        main.getCommand("setmyhome").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args){
        Player player = (Player) commandSender;
        player.sendMessage(ChatColor.GREEN + "Наведите на блок и нажмите правой кнопкой мыши, чтобы установить свой дом.");

        return false;
    }
}
