package com.shiedix;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
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
        Runtime.getRuntime().addShutdownHook(new Thread(DiscordRPC::discordShutdown));
    }
    public static void main(String[] args) throws IOException
    {
        initDiscord();
        menu = new MainMenu("Snake");
    }
    public static void initDiscord()
    {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
            DiscordRichPresence.Builder discordPresence = new DiscordRichPresence.Builder("Starting...");
            DiscordRPC.discordUpdatePresence(discordPresence.build());
        }).build();
        DiscordRPC.discordInitialize("841753821578526760", handlers, false);
        DiscordRPC.discordRegister("841753821578526760", "");
    }
}
