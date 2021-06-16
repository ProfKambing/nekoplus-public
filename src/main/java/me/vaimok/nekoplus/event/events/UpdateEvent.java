package me.vaimok.nekoplus.event.events;

import me.vaimok.nekoplus.event.EventStage;

public
class UpdateEvent extends EventStage {
    private final int stage;

    public
    UpdateEvent ( int stage ) {
        this.stage = stage;
    }

    public final
    int getStage ( ) {
        return this.stage;
    }
}
