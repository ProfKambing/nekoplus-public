package me.vaimok.nekoplus.features.command.commands;

import me.vaimok.nekoplus.features.command.Command;
import me.vaimok.nekoplus.nekoplus;

public
class HelpCommand extends Command {

    public
    HelpCommand ( ) {
        super ( "commands" );
    }

    @Override
    public
    void execute ( String[] commands ) {
        sendMessage ( "You can use following commands: " );
        for (Command command : nekoplus.commandManager.getCommands ( )) {
            sendMessage ( nekoplus.commandManager.getPrefix ( ) + command.getName ( ) );
        }
    }
}
