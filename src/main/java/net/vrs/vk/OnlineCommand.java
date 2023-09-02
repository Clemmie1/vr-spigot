package net.vrs.vk;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Random;


public class OnlineCommand implements Listener {
    private static final VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private static final GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) {

        if (e.getMessage().getText().startsWith("!online")){

            StringBuilder str = new StringBuilder();
            int online = Bukkit.getServer().getOnlinePlayers().size();

            if (online == 0) {

                try {

                    CLIENT.messages().send(ACTOR)
                            .randomId(RANDOM.nextInt())
                            .peerId(e.getPeer())
                            .message("Сейчас нет игроков на сервере.")
                            .execute();
                } catch (ApiException | ClientException ex) {
                    ex.printStackTrace();
                }

            } else {
                for (Player ps : Bukkit.getOnlinePlayers()){
                    String nameList = ps.getName();

                    if (str.length() > 0) {
                        str.append(", ");
                    }

                    str.append(nameList);

                }

                try {
                    CLIENT.messages().send(ACTOR)
                            .randomId(RANDOM.nextInt())
                            .peerId(e.getPeer())
                            .message("Список игроков ("+online+"): " + str.toString())
                            .execute();

                } catch (ApiException | ClientException ex) {
                    ex.printStackTrace();
                }


            }

//            try {
//
//                CLIENT.messages().send(ACTOR)
//                        .randomId(RANDOM.nextInt())
//                        .peerId(e.getPeer())
//                        .message("Message cannot be forwarded")
//                        .execute();
//            } catch (ApiException | ClientException ex) {
//                ex.printStackTrace();
//            }

        }

    }
}
