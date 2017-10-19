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
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import com.sk89q.worldedit.session.ClipboardHolder;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.main;

public class EffSaveClipToSchematic extends Effect{
	private Expression<Player> pl;
	private Expression<String> schem;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		// TODO Auto-generated method stub
		pl = (Expression<Player>) expr[0];
		schem = (Expression<String>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] [worldedit] save clipboard of %player% (to|as) [schem[atic]] %string%";
	}

	@Override
	protected void execute(Event e) {
		WorldEditPlugin wep = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		File file = new File(       
				("plugins/WorldEdit/schematics/" + (schem.getSingle(e).contains(".") ? schem.getSingle(e) : new StringBuilder(String.valueOf(schem.getSingle(e))).append(".schematic").toString())).replaceAll("/", 
						Matcher.quoteReplacement(File.separator)));
		try {
			LocalSession session = wep.getSession(pl.getSingle(e));
			ClipboardHolder selection = wep.getSession(pl.getSingle(e)).getClipboard();

			Vector min = selection.getClipboard().getMinimumPoint();
			Vector max = selection.getClipboard().getMaximumPoint();
			EditSession editSession = session.createEditSession(wep.wrapPlayer(pl.getSingle(e)));


			editSession.enableQueue();
			CuboidClipboard clipboard = new CuboidClipboard(max.subtract(min).add(new Vector(1, 1, 1)), min);
			clipboard.copy(editSession);
			SchematicFormat.MCEDIT.save(clipboard, file);
			editSession.flushQueue();


		} catch (DataException | IOException e1) {
			main core = (main)Bukkit.getPluginManager().getPlugin("SharpSK");
			core.getLogger().warning("Failed to save schematic: "+"\""+schem.getSingle(e)+"\""+" An error occurred");
			return;
		} catch (EmptyClipboardException e1) {
			main core = (main)Bukkit.getPluginManager().getPlugin("SharpSK");
			core.getLogger().warning("Failed to save schematic: "+"\""+schem.getSingle(e)+"\""+" Clipboard was empty");
			return;
		}

	}

}
