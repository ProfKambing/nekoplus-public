package me.vaimok.nekoplus.event.events;

import me.vaimok.nekoplus.event.EventStage;

public
class Render3DEvent extends EventStage {

    private final float partialTicks;

    public
    Render3DEvent ( float partialTicks ) {
        this.partialTicks = partialTicks;
    }

    public
    float getPartialTicks ( ) {
        return partialTicks;
    }
}
