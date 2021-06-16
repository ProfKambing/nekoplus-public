package me.vaimok.nekoplus.event.events;

import me.vaimok.nekoplus.event.EventStage;
import net.minecraft.entity.player.EntityPlayer;

public
class TotemPopEvent extends EventStage {

    private final EntityPlayer entity;

    public
    TotemPopEvent ( EntityPlayer entity ) {
        super ( );
        this.entity = entity;
    }

    public
    EntityPlayer getEntity ( ) {
        return entity;
    }

}
