package erebus.debug;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ErebusCommandDebug extends CommandBase{
	private static final IChatComponent text(String str){
		return new ChatComponentText(str);
	}
	
	@Override
	public String getCommandName(){
		return "erebus";
	}
	
	@Override
	public int getRequiredPermissionLevel(){
        return 3;
    }

	@Override
	public String getCommandUsage(ICommandSender sender){
		return "commands.erebus.debug.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] params){
		if (!(sender instanceof EntityPlayer)){
			sender.addChatMessage(text("Cannot use Erebus debug commands in console or command block."));
			return;
		}
		
		if (params.length == 0){
			sender.addChatMessage(text("Available commands:"));
			// TODO
		}
	}
}
