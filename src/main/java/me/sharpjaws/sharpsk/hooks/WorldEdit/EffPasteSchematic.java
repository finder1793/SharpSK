package me.sharpjaws.sharpsk.hooks.WorldEdit;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.schematic.SchematicFormat;
import me.sharpjaws.sharpsk.SharpSK;
import me.sharpjaws.sharpsk.hooks.WorldEdit.enums.SchemFacingDirection;
import org.bukkit.Location;
import org.bukkit.event.Event;

import java.io.File;
import java.util.regex.Matcher;

public class EffPasteSchematic extends Effect {
	private Expression<?> name;
	private Expression<?> loc;
	private Expression<?> exair;
	private Expression<Number> angle;
	private int mark = 0;

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expression, int i, Kleenean kleenean, SkriptParser.ParseResult Result) {
		name = expression[0];
		loc = expression[1];
		exair = expression[2];
		angle = (Expression<Number>) expression[3];
		mark = Result.mark;
		return true;
	}

	public String toString(Event e, boolean debug) {
		return "Paste schematic \"" + this.name + "\" at " + this.loc;
	}

	protected void execute(Event event) {
		String name = (String) this.name.getSingle(event);
		Location loc = (Location) this.loc.getSingle(event);
		Boolean exair = false;
		if (this.exair != null) {
			exair = (Boolean) this.exair.getSingle(event);
		} else {
			exair = false;
		}

		try {
			switch (angle.getSingle(event).intValue()) {
			case 0:
			case 360:
				paste(name, loc, exair, SchemFacingDirection.NORTH);
				break;
			case 90:
				paste(name, loc, exair, SchemFacingDirection.EAST);
				break;
			case 180:
				paste(name, loc, exair, SchemFacingDirection.SOUTH);
				break;
			case 270:
				paste(name, loc, exair, SchemFacingDirection.WEST);
				break;
			default:
				paste(name, loc, exair, SchemFacingDirection.INVALID);
				break;
			}

		} catch (Exception e) {
			try {
				paste(name, loc, exair, SchemFacingDirection.NORTH);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	private static boolean paste(String f, Location loc, Boolean exair, SchemFacingDirection facing) throws Exception {
		File file;

		if (f.startsWith("/")) {
			file = new File((f + ".schematic").replaceAll("/", Matcher.quoteReplacement(File.separator)));
		} else {
			file = new File(

					("plugins/WorldEdit/schematics/" + (f.contains(".") ? f
							: new StringBuilder(f).append(".schematic").toString())).replaceAll("/",
									Matcher.quoteReplacement(File.separator)));
		}
		Vector v = new Vector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		if ((!file.exists()) && (file.isDirectory())) {
			return false;
		}
		EditSession es = WorldEdit.getInstance().getEditSessionFactory()
				.getEditSession((new BukkitWorld(loc.getWorld())), 800000);

		CuboidClipboard cc = SchematicFormat.getFormat(file).load(file);
		try {

			if (facing != null) {

				if (SchemFacingDirection.getDegree(facing) != -1) {
					cc.rotate2D(SchemFacingDirection.getDegree(facing));
				} else {
					SharpSK core = SharpSK.instance;
					core.getLogger().warning("Invalid rotation angle for schematic: " + "\"" + f + "\"");
					core.getLogger().warning("Valid angles are: 0, 90, 180, 270, 360");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!exair) {
			cc.paste(es, v, false);
		} else {
			cc.paste(es, v, true);
		}

		return true;

	}
}
