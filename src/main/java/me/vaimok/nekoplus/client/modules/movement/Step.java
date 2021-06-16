package me.vaimok.nekoplus.client.modules.movement;

import me.vaimok.nekoplus.api.event.events.UpdateWalkingPlayerEvent;
import me.vaimok.nekoplus.client.modules.Module;
import me.vaimok.nekoplus.client.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public
class Step extends Module {
    public Setting < Integer > height = register ( new Setting ( "Height" , 2 , 0 , 5 ) );
    public Setting < Boolean > reverse = register ( new Setting ("reverse", false));
    private final Setting < Integer > reverseSpeed = this.register ( new Setting < Integer > ( "reverseSpeed" , 10 , 1 , 20 ) );

    public
    Step ( ) {
        super ( "Step" , "Allows you to step up blocks" , Module.Category.MOVEMENT , true , false , false );

    }

    @Override
    public
    void onUpdate ( ) {
        if ( fullNullCheck ( ) ) return;
        mc.player.stepHeight = 2.0f;
    }

    @Override
    public
    void onDisable ( ) {
        super.onDisable();
        mc.player.stepHeight = 0.6f;
    }
    @SubscribeEvent
    public
    void onUpdateWalkingPlayer ( UpdateWalkingPlayerEvent event ) {
        if ( Step.fullNullCheck ( ) || this.mc.player.isInWater ( ) || this.mc.player.isInLava ( ) || this.mc.player.isOnLadder ( ) ) {
            return;
        }
        if ( this.mc.player.onGround && reverse.getValue( ) ) {
            this.mc.player.motionY -= (float) this.reverseSpeed.getValue ( ).intValue ( ) / 10.0f;
        }
    }

}
