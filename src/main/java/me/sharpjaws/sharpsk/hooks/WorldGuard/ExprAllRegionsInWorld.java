package me.sharpjaws.sharpsk.hooks.WorldGuard;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.World;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ExprAllRegionsInWorld extends SimpleExpression<String> {
	private Expression<?> world;

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.world = expressions[0];
		return true;
	}

	protected String[] get(Event event) {
		World world = (World) this.world.getSingle(event);

		RegionManager set = WGBukkit.getRegionManager(world);
		Map<String, ProtectedRegion> regions = set.getRegions();
		List<String> list = new ArrayList<>(regions.keySet());

		String[] s = new String[list.size()];
		return list.toArray(s);
	}

	public boolean isSingle() {
		return false;
	}

	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public String toString(Event event, boolean b) {
		return "[sharpsk] [all] [worldguard] regions in %world%";
	}

	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		return null;
	}
}
