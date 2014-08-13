package erebus.debug;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ErebusCommandDebug extends CommandBase
{
	private static final IChatComponent text(String str)
	{
		return new ChatComponentText(str);
	}

	@Override
	public String getCommandName()
	{
		return "erebus";
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 3;
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "commands.erebus.debug.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] params)
	{
		if (!(sender instanceof EntityPlayer))
		{
			sender.addChatMessage(text("Cannot use Erebus debug commands in console or command block."));
			return;
		}

		if (params.length == 0)
		{
			sender.addChatMessage(text("Available commands:"));
			sender.addChatMessage(text("/erebus gen <type> <feature>"));
		} else if (params[0].equals("gen") && params.length >= 3)
		{
			try
			{
				Class<?> cls = Class.forName("erebus.world.feature." + params[1] + ".WorldGen" + params[2]);
				WorldGenerator gen = (WorldGenerator) cls.newInstance();
				ChunkCoordinates coords = sender.getPlayerCoordinates();

				if (gen.generate(sender.getEntityWorld(), sender.getEntityWorld().rand, coords.posX, coords.posY, coords.posZ))
				{
					sender.addChatMessage(text("Generated."));
				} else
				{
					sender.addChatMessage(text("Failed."));
				}
			} catch (Throwable t)
			{
				t.printStackTrace();
				sender.addChatMessage(text("Something went wrong."));
			}
		} else
		{
			sender.addChatMessage(text("Wrong command u noob."));
		}
	}
}