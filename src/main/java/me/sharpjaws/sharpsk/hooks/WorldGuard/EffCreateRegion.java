package me.sharpjaws.sharpsk.hooks.WorldGuard;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

class EffCreateRegion extends Effect {
	private Expression<?> name;
	private Expression<?> loc1;
	private Expression<?> loc2;
	private Expression<?> world;

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.name = expressions[0];
		this.loc1 = expressions[1];
		this.loc2 = expressions[2];
		this.world = expressions[3];

		return true;
	}

	protected void execute(Event event) {
		String name = (String) this.name.getSingle(event);
		Location loc1 = (Location) this.loc1.getSingle(event);
		Location loc2 = (Location) this.loc2.getSingle(event);
		World world = (World) this.world.getSingle(event);

		int x1 = loc1.getBlockX();
		int y1 = loc1.getBlockY();
		int z1 = loc1.getBlockZ();

		int x2 = loc2.getBlockX();
		int y2 = loc2.getBlockY();
		int z2 = loc2.getBlockZ();

		Vector p1 = new Vector(x1, y1, z1);
		Vector p2 = new Vector(x2, y2, z2);
		RegionManager regionManager = WGBukkit.getRegionManager(world);
		ProtectedCuboidRegion region = new ProtectedCuboidRegion(name, p1.toBlockVector(), p2.toBlockVector());
		regionManager.addRegion(region);
		try {
			regionManager.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString(Event e, boolean debug) {
		return getClass().getName();
	}
}
