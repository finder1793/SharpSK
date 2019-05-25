package me.sharpjaws.sharpsk.hooks.WorldGuard;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.*;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.sharpjaws.sharpsk.SharpSK;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.Map.Entry;

public class ExprFlagValueOfFlagOfRegion extends SimpleExpression<String> {
    private Expression<String> flag;
    private Expression<String> region;
    private Expression<World> world;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expression, int arg1, Kleenean arg2, ParseResult arg3) {
        flag = (Expression<String>) expression[0];
        region = (Expression<String>) expression[1];
        world = (Expression<World>) expression[2];
        return true;

    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {

        WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        RegionManager set = wg.getRegionManager(world.getSingle(e));
        ProtectedRegion pr = null;

        String finalv = null;

        for (Entry<String, ProtectedRegion> a : set.getRegions().entrySet()) {
            if (a.getKey().equals(region.getSingle(e))) {
                pr = a.getValue();
            }
            try {
                for (Entry<Flag<?>, Object> b : pr.getFlags().entrySet()) {

                    if (b.getKey().getName().equalsIgnoreCase(flag.getSingle(e))) {
                        if (b.getKey() instanceof StateFlag) {
                            if (b.getValue() == StateFlag.State.ALLOW) {
                                finalv = "ALLOW";
                            } else if (b.getValue() == StateFlag.State.DENY) {
                                finalv = "DENY";
                            } else {
                                return new String[] {};
                            }
                        } else if (b.getKey() instanceof StringFlag) {
                            finalv = (String) b.getKey().getDefault();
                        } else if (b.getKey() instanceof IntegerFlag) {
                            finalv = (String) b.getKey().getDefault();
                        } else if (b.getKey() instanceof DoubleFlag) {
                            finalv = (String) b.getKey().getDefault();
                        }
                        break;
                    }
                }

            } catch (NullPointerException ex) {
                return new String[] {};
            }

        }
        return new String[] { finalv };
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        JavaPlugin mainp = SharpSK.plugin;
        Flag<?> fl = null;

        WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        RegionManager set = wg.getRegionManager(world.getSingle(e));

        for (Flag<?> f : DefaultFlag.getFlags()) {
            if (f.getName().equalsIgnoreCase(flag.getSingle(e))) {
                fl = f;
                break;
            }
        }
        try {
            try {
                if (delta[0] instanceof Boolean) {
                    if ((Boolean) delta[0]) {
                        set.getRegion(region.getSingle(e)).setFlag((StateFlag) fl, State.ALLOW);
                    } else {
                        set.getRegion(region.getSingle(e)).setFlag((StateFlag) fl, State.DENY);
                    }

                } else if (delta[0] instanceof String) {
                    set.getRegion(region.getSingle(e)).setFlag((StringFlag) fl, (String) delta[0]);
                } else if (delta[0] instanceof Integer) {
                    set.getRegion(region.getSingle(e)).setFlag((IntegerFlag) fl, (int) delta[0]);
                } else if (delta[0] instanceof Double) {
                    set.getRegion(region.getSingle(e)).setFlag((DoubleFlag) fl, (double) delta[0]);
                } else {
                    mainp.getLogger()
                            .warning("Region flag " + "\"" + fl.getName() + "\"" + " cannot be set to: " + delta[0]);
                }

            } catch (ClassCastException ex) {
                mainp.getLogger()
                        .warning("Region flag " + "\"" + fl.getName() + "\"" + " cannot be set to: " + delta[0]);
            }
        } catch (NullPointerException ex) {
            mainp.getLogger().warning("Region flag " + "\"" + flag.getSingle(e) + "\"" + " does not exist");
        }

    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET)
            return (new Class[] { Object.class });
        return null;
    }

}
