package me.sharpjaws.sharpSK.hooks.WorldEdit;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import me.sharpjaws.sharpSK.SharpSK;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffSaveSelectionToClipboard extends Effect {
	private Expression<Location> point1;
	private Expression<Location> point2;
	private Expression<Location> origin;
	private Expression<Player> pl;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		point1 = (Expression<Location>) expr[0];
		point2 = (Expression<Location>) expr[1];
		origin = (Expression<Location>) expr[2];
		pl = (Expression<Player>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] [worldedit] save [selection] p[oint][ ]1 %location% p[oint][ ]2 %location% [with origin %-location%] to clip[board] of [player] %player%";
	}

	@Override
	protected void execute(Event e) {
		WorldEditPlugin wep = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		LocalSession session = wep.getSession(pl.getSingle(e));

		Location point1loc = point1.getSingle(e);
		Location point2loc = point2.getSingle(e);

		Vector min = new Vector(point1loc.getBlockX(), point1loc.getBlockY(), point1loc.getZ());
		Vector max = new Vector(point2loc.getBlockX(), point2loc.getBlockY(), point2loc.getZ());
		CuboidRegion cr = new CuboidRegion(min, max);
		BlockArrayClipboard bc = new BlockArrayClipboard(cr);
		EditSession es = wep.createEditSession(pl.getSingle(e));
		try {
			if (origin == null) {
				bc.setOrigin(session.getPlacementPosition(wep.wrapPlayer(pl.getSingle(e))));
			} else {
				Location originloc = origin.getSingle(e);
				Vector originvec = new Vector(originloc.getBlockX(), originloc.getBlockY(), originloc.getBlockZ());
				bc.setOrigin(originvec);
			}
			session.setClipboard(new ClipboardHolder(bc, es.getWorld().getWorldData()));
			ForwardExtentCopy copy = new ForwardExtentCopy(es, cr, bc, cr.getMinimumPoint());
			Operations.complete(copy);

		} catch (WorldEditException e1) {
			SharpSK core = SharpSK.instance;
			core.getLogger().warning("Failed to save selection. Something went wrong");
			return;
		}

	}
}
