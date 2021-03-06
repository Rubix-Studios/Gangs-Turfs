package nl.rubixstudios.gangsturfs.gang.commands.admin;

import nl.rubixstudios.gangsturfs.commands.manager.SubCommand;
import nl.rubixstudios.gangsturfs.data.Language;
import nl.rubixstudios.gangsturfs.gang.GangController;
import nl.rubixstudios.gangsturfs.gang.GangManager;
import nl.rubixstudios.gangsturfs.gang.type.PlayerGang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForceJoinCommand extends SubCommand {

    public ForceJoinCommand() {
        super("forcejoin", "gangturfs.gang.forcejoin", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Language.GANG_PREFIX + Language.GANGS_FORCE_JOIN_USAGE);
            return;
        }

        final Player player = (Player) sender;

        if(GangManager.getInstance().getPlayerGang(player) != null) {
            player.sendMessage(Language.GANG_PREFIX + Language.GANGS_ALREADY_IN_GANG_SELF);
            return;
        }

        final PlayerGang gang = GangManager.getInstance().searchForGang(args[0]);

        if(gang == null) {
            sender.sendMessage(Language.GANG_PREFIX + Language.GANGS_GANG_DOESNT_EXIST.replace("<argument>", args[0]));
            return;
        }

        if(!GangController.getInstance().joinGang(player, gang)) return;

        gang.sendMessage(Language.GANG_PREFIX + Language.GANGS_FORCE_JOINED.replace("<player>", player.getName()));
    }
}
