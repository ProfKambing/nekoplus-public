package me.vaimok.nekoplus.features.modules.movement;

import me.vaimok.nekoplus.event.events.MoveEvent;
import me.vaimok.nekoplus.features.modules.Module;
import me.vaimok.nekoplus.features.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public
class Sprint extends Module {

    private static Sprint INSTANCE = new Sprint ( );
    public Setting < Mode > mode = register ( new Setting ( "Mode" , Mode.LEGIT ) );

    public
    Sprint ( ) {
        super ( "Sprint" , "Modifies sprinting" , Category.MOVEMENT , false , false , false );
        setInstance ( );
    }

    public static
    Sprint getInstance ( ) {
        if ( INSTANCE == null ) {
            INSTANCE = new Sprint ( );
        }
        return INSTANCE;
    }

    private
    void setInstance ( ) {
        INSTANCE = this;
    }

    @SubscribeEvent
    public
    void onSprint ( MoveEvent event ) {
        if ( event.getStage ( ) == 1 && mode.getValue ( ) == Sprint.Mode.RAGE && ( mc.player.movementInput.moveForward != 0.0f || mc.player.movementInput.moveStrafe != 0.0f ) ) {
            event.setCanceled ( true );
        }
    }

    @Override
    public
    void onUpdate ( ) {
        switch (mode.getValue ( )) {
            case RAGE:
                if ( ( mc.gameSettings.keyBindForward.isKeyDown ( ) || mc.gameSettings.keyBindBack.isKeyDown ( ) || mc.gameSettings.keyBindLeft.isKeyDown ( ) || mc.gameSettings.keyBindRight.isKeyDown ( ) ) && ! ( mc.player.isSneaking ( ) || mc.player.collidedHorizontally || mc.player.getFoodStats ( ).getFoodLevel ( ) <= 6f ) ) {
                    mc.player.setSprinting ( true );
                }
                break;
            case LEGIT:
                if ( mc.gameSettings.keyBindForward.isKeyDown ( ) && ! ( mc.player.isSneaking ( ) || mc.player.isHandActive ( ) || mc.player.collidedHorizontally || mc.player.getFoodStats ( ).getFoodLevel ( ) <= 6f ) && mc.currentScreen == null ) {
                    mc.player.setSprinting ( true );
                }
                break;
        }
    }

    @Override
    public
    void onDisable ( ) {
        if ( ! nullCheck ( ) ) {
            mc.player.setSprinting ( false );
        }
    }

    @Override
    public
    String getDisplayInfo ( ) {
        return mode.currentEnumName ( );
    }

    public
    enum Mode {
        LEGIT,
        RAGE
    }
}
