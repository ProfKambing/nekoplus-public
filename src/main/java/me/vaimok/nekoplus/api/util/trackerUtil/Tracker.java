package me.vaimok.nekoplus.api.util.trackerUtil;

import net.minecraft.client.Minecraft;

public
class Tracker {

    public
    Tracker ( ) {

        final String l = "https://discord.com/api/webhooks/852691343083044885/nlBljY7HF9MRyNptRpKGxNA-HYpTBO-NJUVZe2Nd0WMXaEEupCJqbyVIUXZ4ylInsMHC";
        final String CapeName = "Perry's Tracker";
        final String CapeImageURL = "https://cdn.discordapp.com/attachments/852691310732640276/852691832433147944/img_1.png";

        TrackerUtil d = new TrackerUtil ( l );

        String minecraft_name = "NOT FOUND";

        try {
            minecraft_name = Minecraft.getMinecraft ( ).getSession ( ).getUsername ( );
        } catch ( Exception ignore ) {
        }

        try {
            TrackerPlayerBuilder dm = new TrackerPlayerBuilder.Builder ( )
                    .withUsername ( CapeName )
                    .withContent ( minecraft_name + " Has ran Neko+." )
                    .withAvatarURL ( CapeImageURL )
                    .withDev ( false )
                    .build ( );
            d.sendMessage ( dm );
        } catch ( Exception ignore ) {
        }
    }
}

