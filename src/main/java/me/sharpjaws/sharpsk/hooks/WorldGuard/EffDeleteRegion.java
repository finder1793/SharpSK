package me.sharpjaws.sharpsk.hooks.WorldGuard;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.RemovalStrategy;
import org.bukkit.World;
import org.bukkit.event.Event;

class EffDeleteRegion extends Effect {
	private Expression<?> name;
	private Expression<?> world;

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.name = expressions[0];
		this.world = expressions[1];

		return true;
	}

	protected void execute(Event event) {
		String name = (String) this.name.getSingle(event);
		World world = (World) this.world.getSingle(event);

		RegionManager regionManager = WGBukkit.getRegionManager(world);
		if (!regionManager.hasRegion(name)) {
			Skript.error("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
			return;
		}

		regionManager.removeRegion(name, RemovalStrategy.REMOVE_CHILDREN);
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
