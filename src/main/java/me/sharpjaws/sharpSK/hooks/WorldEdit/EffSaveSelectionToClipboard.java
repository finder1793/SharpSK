package me.sharpjaws.sharpSK.hooks.WorldEdit;


import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.main;

public class EffSaveSelectionToClipboard extends Effect{
	private Expression<Location> loc1;
	private Expression<Location> loc2;
	private Expression<World> world;
	private Expression<String> schem;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		// TODO Auto-generated method stub
		loc1 = (Expression<Location>) expr[0];
		loc2 = (Expression<Location>) expr[1];
		world = (Expression<World>) expr[2];
		schem = (Expression<String>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] [worldedit] save [selection] point 1 %location% point 2 %location% in [world] %world% to [schem[atic]] %string%";
	}

	@Override
	protected void execute(Event e) {
		WorldEditPlugin wep = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		File file = new File(       
				("plugins/WorldEdit/schematics/" + (schem.getSingle(e).contains(".") ? schem.getSingle(e) : new StringBuilder(String.valueOf(schem.getSingle(e))).append(".schematic").toString())).replaceAll("/", 
						Matcher.quoteReplacement(File.separator)));
		try {
			EditSession session = wep.getWorldEdit().getEditSessionFactory().getEditSession((new BukkitWorld(world.getSingle(e))), 400000);
			Vector min = new Vector(loc1.getSingle(e).getBlockX(),loc1.getSingle(e).getBlockY(),loc1.getSingle(e).getBlockZ());
			Vector max = new Vector(loc2.getSingle(e).getBlockX(),loc2.getSingle(e).getBlockY(),loc2.getSingle(e).getBlockZ());


			session.enableQueue();
			CuboidClipboard clipboard = new CuboidClipboard(max.subtract(min).add(new Vector(1, 1, 1)), min);
			clipboard.copy(session);
			SchematicFormat.MCEDIT.save(clipboard, file);
			session.flushQueue();


		} catch (DataException | IOException e1) {
			main core = (main)Bukkit.getPluginManager().getPlugin("SharpSK");
			core.getLogger().warning("Failed to save schematic: "+"\""+schem.getSingle(e)+"\""+" An error occurred");
			return;
		}

	}

}
