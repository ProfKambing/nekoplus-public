package me.vaimok.nekoplus.features.command.commands;

import me.vaimok.nekoplus.features.command.Command;
import me.vaimok.nekoplus.features.modules.client.ClickGui;
import me.vaimok.nekoplus.nekoplus;
import me.vaimok.nekoplus.util.TextUtil;

public
class PrefixCommand extends Command {

    public
    PrefixCommand ( ) {
        super ( "prefix" , new String[]{"<char>"} );
    }

    @Override
    public
    void execute ( String[] commands ) {
        if ( commands.length == 1 ) {
            Command.sendMessage ( TextUtil.RED + "Specify a new prefix." );
            return;
        }

        ( nekoplus.moduleManager.getModuleByClass ( ClickGui.class ) ).prefix.setValue ( commands[0] );
        Command.sendMessage ( "Prefix set to " + TextUtil.GREEN + nekoplus.commandManager.getPrefix ( ) );
    }
}
