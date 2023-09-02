package net.vrs.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SetMyHomeListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                if (block.getType() == Material.OAK_SIGN) {
                    Sign sign = (Sign) block.getState();
                    if (sign.getLine(0).equals(ChatColor.GREEN + "Дом игрока") && sign.getLine(1).equals(player.getName())) {
                        player.sendMessage(ChatColor.RED + "Вы уже установили свой дом здесь.");
                    }
                } else {
                    Location location = block.getLocation();
                    player.sendMessage(ChatColor.GREEN + "Вы установили свой дом!");

                    // Создаем табличку
                    block.setType(Material.OAK_SIGN);
                    Sign sign = (Sign) block.getState();
                    sign.setLine(0, ChatColor.GREEN + "Дом игрока");
                    sign.setLine(1, player.getName());
                    sign.update();
                }
            }
        }
    }
}
