package me.sharpjaws.sharpSK.hooks.WorldEdit;


import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalPlayer;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.main;

public class EffSaveSelectionToClipboard extends Effect{
	private Expression<Player> pl;
	private Expression<String> schem;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		// TODO Auto-generated method stub
		schem = (Expression<String>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] [worldedit] save [selection] of [the] %player% to [schem[atic]] %string%";
	}

	@Override
	protected void execute(Event e) {
		WorldEditPlugin wep = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		File file = new File(       
				("plugins/WorldEdit/schematics/" + (schem.getSingle(e).contains(".") ? schem.getSingle(e) : new StringBuilder(String.valueOf(schem.getSingle(e))).append(".schematic").toString())).replaceAll("/", 
						Matcher.quoteReplacement(File.separator)));
		try {
		EditSession session = wep.getSession(pl.getSingle(e)).createEditSession((LocalPlayer) pl.getSingle(e));


			session.enableQueue();
			CuboidClipboard clipboard = null;
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
