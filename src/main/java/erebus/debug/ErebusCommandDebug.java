package erebus.debug;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ErebusCommandDebug extends CommandBase {

	private static final IChatComponent text(String str, Object... objects) {
		return new ChatComponentTranslation(str, objects);
	}

	@Override
	public String getCommandName() {
		return "erebus";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "commands.erebus.debug.usage";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void processCommand(ICommandSender sender, String[] params) {
		if (!(sender instanceof EntityPlayer)) {
			sender.addChatMessage(text("command.erebus.commanddenied"));
			return;
		}

		EntityPlayer player = (EntityPlayer) sender;

		if (params.length == 0) {
			sender.addChatMessage(text("command.erebus.available"));
			sender.addChatMessage(text("command.erebus.gen"));
			sender.addChatMessage(text("/erebus debug"));
		} else if (params[0].equals("gen") && params.length >= 3)
			try {
				Class<?> cls = Class.forName("erebus.world.feature." + params[1] + ".WorldGen" + params[2]);
				WorldGenerator gen = (WorldGenerator) cls.newInstance();
				ChunkCoordinates coords = sender.getPlayerCoordinates();

				if (gen.generate(sender.getEntityWorld(), sender.getEntityWorld().rand, coords.posX, coords.posY, coords.posZ))
					sender.addChatMessage(text("command.erebus.generated"));
				else
					sender.addChatMessage(text("command.erebus.failed"));
			} catch (Throwable t) {
				t.printStackTrace();
				sender.addChatMessage(text("command.erebus.somethingwentwrong"));
			}
		else if (params[0].equals("debug")) {
			/*
			 * Use this with hot code replace whenever there's something to debug and you want to print stuff out on command. Whoever comes here and needs this - if you think the current code will be useful another time, add a new command for it. If not, just delete it, no hard feelings.
			 */
			String response = "";

			// debug start

			int creature = player.worldObj.countEntities(EnumCreatureType.creature, true);
			int monster = player.worldObj.countEntities(EnumCreatureType.monster, true);
			int ambient = player.worldObj.countEntities(EnumCreatureType.ambient, true);

			response = "Mob count: creature " + creature + ", monster " + monster + ", ambient " + ambient;

			List<Entity> list = player.worldObj.loadedEntityList;
			Map<EnumCreatureType, Set<String>> mobs = new HashMap<EnumCreatureType, Set<String>>();

			for (Entity entity : list)
				for (EnumCreatureType type : EnumCreatureType.values())
					if (entity.isCreatureType(type, true)) {
						Set<String> set = mobs.get(type);
						if (set == null)
							mobs.put(type, set = new HashSet<String>());

						set.add(entity.getClass().getSimpleName());
					}

			for (Entry<EnumCreatureType, Set<String>> entry : mobs.entrySet()) {
				StringBuilder build = new StringBuilder();
				build.append("\n").append(entry.getKey().name()).append(" - ");

				for (String s : entry.getValue())
					build.append(s).append(", ");
				build.delete(build.length() - 2, build.length());

				response += build.toString();
			}

			// debug end

			for (String s : response.split("\n"))
				sender.addChatMessage(text(s));
		} else
			sender.addChatMessage(text("Wrong command u noob."));
	}
}