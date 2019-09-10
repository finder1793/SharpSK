package me.sharpjaws.sharpsk.hooks.WorldGuard;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map.Entry;

public class ExprRegionsOfPlayer extends SimpleExpression<String> {
    private Expression<OfflinePlayer> player;
    private Expression<World> wo;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<OfflinePlayer>) expr[0];
        wo = (Expression<World>) expr[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "[sharpsk] [all] regions of [player] %offlineplayer% [in %-world%]";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        RegionManager regionManager = null;
        if (wo != null) {
            regionManager = wg.getRegionManager(wo.getSingle(e));
        } else {
            regionManager = wg.getRegionManager(Bukkit.getServer().getWorlds().get(0));
        }
        if (player.getSingle(e) == null) {
            return new String[]{};
        }
        LocalPlayer wpl;
        if (player.getSingle(e).isOnline()) {
            wpl = wg.wrapPlayer(player.getSingle(e).getPlayer());
        } else {
            wpl = wg.wrapOfflinePlayer(player.getSingle(e));
        }
        ArrayList<String> pregions = new ArrayList<>();
        for (Entry<String, ProtectedRegion> reg : regionManager.getRegions().entrySet()) {
            if (reg.getValue().isMember(wpl)) {
                pregions.add(reg.getValue().getId());
            }
        }
        return pregions.toArray(new String[0]);

    }

}
