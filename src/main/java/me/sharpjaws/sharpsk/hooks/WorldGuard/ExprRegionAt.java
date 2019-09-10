package me.sharpjaws.sharpsk.hooks.WorldGuard;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;

public class ExprRegionAt extends SimpleExpression<String> {
    private Expression<Location> loc;

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.loc = (Expression<Location>) expressions[0];
        return true;
    }

    protected String[] get(Event event) {
        Location loc = this.loc.getSingle(event);
        String a = null;
        WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        RegionManager regionManager = wg.getRegionContainer().get(loc.getWorld());

        for (ProtectedRegion reg : regionManager.getApplicableRegions(loc)) {

            if (reg.contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                a = reg.getId();
            }
        }
        return new String[]{a};

    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends String> getReturnType() {
        return String.class;
    }

    public String toString(Event event, boolean b) {
        return "wg region at" + this.loc.getSingle(event).toString();
    }

}
