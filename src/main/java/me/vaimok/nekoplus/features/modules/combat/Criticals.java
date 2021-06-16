package me.vaimok.nekoplus.features.modules.combat;

import me.vaimok.nekoplus.event.events.PacketEvent;
import me.vaimok.nekoplus.features.modules.Module;
import me.vaimok.nekoplus.features.setting.Setting;
import me.vaimok.nekoplus.util.Timer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

public
class Criticals extends Module {

    private final Setting < Mode > mode = register ( new Setting ( "Mode" , Mode.PACKET ) );
    private final Setting < Integer > packets = register ( new Setting ( "Packets" , 2 , 1 , 4 , v -> mode.getValue ( ) == Mode.PACKET , "Amount of packets you want to send." ) );
    private final Setting < Integer > desyncDelay = register ( new Setting ( "DesyncDelay" , 10 , 0 , 500 , v -> mode.getValue ( ) == Mode.PACKET , "Amount of packets you want to send." ) );
    private final Timer timer = new Timer ( );
    private final Timer timer32k = new Timer ( );
    public Setting < Boolean > noDesync = register ( new Setting ( "NoDesync" , true ) );
    public Setting < Boolean > cancelFirst = register ( new Setting ( "CancelFirst32k" , true ) );
    public Setting < Integer > delay32k = register ( new Setting ( "32kDelay" , 25 , 0 , 500 , v -> cancelFirst.getValue ( ) ) );
    private boolean firstCanceled = false;
    private boolean resetTimer = false;

    public
    Criticals ( ) {
        super ( "Criticals" , "Scores criticals for you" , Category.COMBAT , true , false , false );
    }

    @SubscribeEvent
    public
    void onPacketSend ( PacketEvent.Send event ) {
        /*if(Auto32k.getInstance().isOn() && timer.passedMs(500) && cancelFirst.getValue()) {
            firstCanceled = true;
        } else*/
        if (/*Auto32k.getInstance().isOff() ||*/ ! cancelFirst.getValue ( ) ) {
            firstCanceled = false;
        }

        if ( event.getPacket ( ) instanceof CPacketUseEntity ) {
            CPacketUseEntity packet = event.getPacket ( );
            if ( packet.getAction ( ) == CPacketUseEntity.Action.ATTACK ) {

                if ( firstCanceled ) {
                    timer32k.reset ( );
                    resetTimer = true;
                    timer.setMs ( desyncDelay.getValue ( ) + 1 );
                    firstCanceled = false;
                    return;
                }

                if ( resetTimer && ! timer32k.passedMs ( delay32k.getValue ( ) ) ) {
                    return;
                } else if ( resetTimer && timer32k.passedMs ( delay32k.getValue ( ) ) ) {
                    resetTimer = false;
                }

                if ( ! timer.passedMs ( desyncDelay.getValue ( ) ) ) {
                    return;
                }

                if ( mc.player.onGround && ! mc.gameSettings.keyBindJump.isKeyDown ( ) && ( packet.getEntityFromWorld ( mc.world ) instanceof EntityLivingBase || ! noDesync.getValue ( ) ) && ! ( mc.player.isInWater ( ) || mc.player.isInLava ( ) ) ) {
                    if ( mode.getValue ( ) == Mode.PACKET ) {
                        switch (packets.getValue ( )) {
                            case 1:
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY + 0.1f , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY , mc.player.posZ , false ) );
                                break;
                            case 2:
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY + 0.0625101D , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY + 1.1E-5 , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY , mc.player.posZ , false ) );
                                break;
                            case 3:
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY + 0.0625101D , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY + 0.0125D , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY , mc.player.posZ , false ) );
                                break;
                            case 4:
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY + 0.1625 , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY + 4.0E-6 , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY + 1.0E-6 , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer.Position ( mc.player.posX , mc.player.posY , mc.player.posZ , false ) );
                                mc.player.connection.sendPacket ( new CPacketPlayer ( ) );
                                mc.player.onCriticalHit ( Objects.requireNonNull ( packet.getEntityFromWorld ( mc.world ) ) );
                                break;
                            default:
                        }
                    } else {
                        mc.player.jump ( );
                    }
                    timer.reset ( );
                }
            }
        }
    }

    @Override
    public
    String getDisplayInfo ( ) {
        return mode.currentEnumName ( );
    }

    public
    enum Mode {
        JUMP,
        PACKET
    }
}
