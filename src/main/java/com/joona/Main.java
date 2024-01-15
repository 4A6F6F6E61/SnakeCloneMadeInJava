package com.joona;

import com.github.psnrigner.discordrpcjava.DiscordEventHandler;
import com.github.psnrigner.discordrpcjava.DiscordRpc;
import com.github.psnrigner.discordrpcjava.DiscordRichPresence;
import java.io.IOException;

@Author(
        name = "Joona Brueckner",
        github = "@Zockedidock"
)
class Main
{
    public static MainMenu menu;
    public static final boolean BUILD = false;

    public Main()
    {
        // Runtime.getRuntime().addShutdownHook(new Thread(DiscordRpc::shutdown));
    }
    public static void main(String[] args)
    throws IOException
    {
        initDiscord();
        menu = new MainMenu("Snake");
    }
    public static void initDiscord()
    {
        // DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
        //     System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
        //     DiscordRichPresence.Builder discordPresence = new DiscordRichPresence.Builder("Starting...");
        //     DiscordRPC.discordUpdatePresence(discordPresence.build());
        // }).build();
        // DiscordRPC.discordInitialize("841753821578526760", handlers, false);
        // DiscordRPC.discordRegister("841753821578526760", "");
    }
}
