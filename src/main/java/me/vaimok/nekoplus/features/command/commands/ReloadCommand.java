package me.vaimok.nekoplus.features.command.commands;

import me.vaimok.nekoplus.features.command.Command;
import me.vaimok.nekoplus.nekoplus;

public
class ReloadCommand extends Command {

    public
    ReloadCommand ( ) {
        super ( "reload" , new String[]{} );
    }

    @Override
    public
    void execute ( String[] commands ) {
        nekoplus.reload ( );
    }
}
