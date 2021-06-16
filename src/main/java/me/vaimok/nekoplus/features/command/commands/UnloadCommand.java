package me.vaimok.nekoplus.features.command.commands;

import me.vaimok.nekoplus.features.command.Command;
import me.vaimok.nekoplus.nekoplus;

public
class UnloadCommand extends Command {

    public
    UnloadCommand ( ) {
        super ( "unload" , new String[]{} );
    }

    @Override
    public
    void execute ( String[] commands ) {
        nekoplus.unload ( true );
    }
}
