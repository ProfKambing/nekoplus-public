package me.vaimok.nekoplus.event.events;

import me.vaimok.nekoplus.event.EventStage;
import net.minecraft.entity.player.EntityPlayer;

public
class DeathEvent extends EventStage {

    public EntityPlayer player;

    public
    DeathEvent ( EntityPlayer player ) {
        super ( );
        this.player = player;
    }

}
