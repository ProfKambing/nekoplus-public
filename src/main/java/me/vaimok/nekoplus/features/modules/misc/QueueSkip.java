package me.vaimok.nekoplus.features.modules.misc;

import me.vaimok.nekoplus.features.command.Command;
import me.vaimok.nekoplus.features.modules.Module;
import me.vaimok.nekoplus.util.TextUtil;

public
class QueueSkip extends Module {

    public
    QueueSkip ( ) {
        super ( "Queue Skip" , "skips 2b queue" , Category.MISC , true , false , false );
    }

    public
    void onEnable ( ) {
        Command.sendMessage ( TextUtil.LIGHT_PURPLE + "Skipping Queue..." );
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Command.sendMessage ( TextUtil.LIGHT_PURPLE + "Queue skipped succesfully!" );
        toggle ( );
    }
}
