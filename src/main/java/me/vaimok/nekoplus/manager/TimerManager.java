package me.vaimok.nekoplus.manager;

import me.vaimok.nekoplus.features.Feature;
import me.vaimok.nekoplus.features.modules.misc.TimerMod;
import me.vaimok.nekoplus.nekoplus;

public
class TimerManager extends Feature {

    private float timer = 1.0f;
    private TimerMod module;

    public
    void init ( ) {
        module = nekoplus.moduleManager.getModuleByClass ( TimerMod.class );
    }

    public
    void unload ( ) {
        timer = 1.0f;
        mc.timer.tickLength = 50.0f;
    }

    public
    void update ( ) {
        if ( module != null && module.isEnabled ( ) ) {
            this.timer = module.speed;
        }
        mc.timer.tickLength = 50.0f / ( timer <= 0.0f ? 0.1f : timer );
    }

    public
    float getTimer ( ) {
        return this.timer;
    }

    public
    void setTimer ( float timer ) {
        if ( timer > 0.0f ) {
            this.timer = timer;
        }
    }

    public
    void reset ( ) {
        this.timer = 1.0f;
    }
}
