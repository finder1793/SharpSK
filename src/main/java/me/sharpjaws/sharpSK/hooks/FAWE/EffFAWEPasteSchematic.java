package me.sharpjaws.sharpSK.hooks.FAWE;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.main;

public class EffFAWEPasteSchematic extends Effect {
	private Expression<String> name;
	private Expression<Location> loc;
	private Expression<Boolean> exair;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		name = (Expression<String>) expr[0];
		loc = (Expression<Location>) expr[1];
		exair = (Expression<Boolean>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] (fawe|fastasyncworldedit) paste schematic %string% at %location% [exclude air %-boolean%]";
	}

	@Override
	protected void execute(Event e) {
		File file;

		if (name.getSingle(e).startsWith("/")) {
			file = new File(
					(name.getSingle(e) + ".schematic").replaceAll("/", Matcher.quoteReplacement(File.separator)));
		} else {
			file = new File(

					("plugins/WorldEdit/schematics/" + (name.getSingle(e).contains(".") ? name.getSingle(e)
							: new StringBuilder(String.valueOf(name.getSingle(e))).append(".schematic").toString()))
									.replaceAll("/", Matcher.quoteReplacement(File.separator)));
		}

		Vector v = new Vector(loc.getSingle(e).getBlockX(), loc.getSingle(e).getBlockY(), loc.getSingle(e).getBlockZ());
		Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getPluginManager().getPlugin("FastAsyncWorldEdit"),
				new Runnable() {

					@Override
					public void run() {
						try {
							Boolean excludeair = false;
							if (exair != null) {
								excludeair = exair.getSingle(e);
							}
							Schematic schem = FaweAPI.load(file);
							EditSession ext = schem.paste(new BukkitWorld(loc.getSingle(e).getWorld()), v, false,
									excludeair, null);

						} catch (IOException e1) {
							main core = (main) Bukkit.getPluginManager().getPlugin("SharpSK");
							core.getLogger().warning("Failed to paste schematic: " + "\"" + name.getSingle(e) + "\""
									+ " An error occurred");
							return;
						}
					}

				});
	}

}
