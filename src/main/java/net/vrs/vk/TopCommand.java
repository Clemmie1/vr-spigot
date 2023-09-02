package net.vrs.vk;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import net.vrs.Providers.MySQL;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TopCommand implements Listener {

    private static final VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private static final GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void topPlayer(VKMessageEvent event){

        if (event.getMessage().getText().startsWith("!top")){

            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM `player_statistic` ORDER BY `player_hours_played` DESC, `player_total_exp` DESC, `mine_block_all` DESC");
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    try {
                        CLIENT.messages().send(ACTOR)
                                .randomId(RANDOM.nextInt())
                                .peerId(event.getPeer())
                                .message("Топ игрок в текущем сезоне" +
                                        "\n\n" +
                                        "\uD83D\uDD25 " + rs.getString("account_name") +
                                        "\n\n" +
                                        "⏲ Наиграно времени: " + rs.getInt("player_hours_played") + " ч." +
                                        "\n" +
                                        "⚡ Набрано опыта: " + rs.getInt("player_total_exp") +
                                        "\n" +
                                        "⛏ Добыто всех блоков: " + rs.getInt("mine_block_all"))
                                .execute();
                    } catch (ApiException | ClientException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        CLIENT.messages().send(ACTOR)
                                .randomId(RANDOM.nextInt())
                                .peerId(event.getPeer())
                                .message("Нет топ игрока в текущем сезоне.")
                                .execute();
                    } catch (ApiException | ClientException ex) {
                        ex.printStackTrace();
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

//            try {
//
//                CLIENT.messages().send(ACTOR)
//                        .randomId(RANDOM.nextInt())
//                        .peerId(event.getPeer())
//                        .message("Message cannot be forwarded")
//                        .execute();
//            } catch (ApiException | ClientException ex) {
//                ex.printStackTrace();
//            }

        }
    }

}
