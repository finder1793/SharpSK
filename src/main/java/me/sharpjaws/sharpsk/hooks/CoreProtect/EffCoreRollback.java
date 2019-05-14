package me.sharpjaws.sharpsk.hooks.CoreProtect;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import net.coreprotect.CoreProtectAPI.ParseResult;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class EffCoreRollback extends Effect {
	private int mark;
	private Expression<Location> l;
	private Expression<Number> n;
	private Expression<Timespan> times;
	private Expression<ItemStack> exblocks;
	private Expression<OfflinePlayer> players;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult result) {
		players = (Expression<OfflinePlayer>) expr[0];
		l = (Expression<Location>) expr[1];
		n = (Expression<Number>) expr[2];
		times = (Expression<Timespan>) expr[3];
		exblocks = (Expression<ItemStack>) expr[4];
		mark = result.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[(coreprotect|cp)] (rollback|revert|undo) changes [made by %-offlineplayers%] at [the] %location% in radius %integer% [back] to %timespan% [ago] [and] [exclude %-itemstacks%]";
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute(final Event e) {
		final List<String> users = new ArrayList<>();
		if (users != null && players != null) {
			for (OfflinePlayer b : players.getAll(e)) {

				users.add(b.getName());
			}
		}
		final List<Object> exclude = new ArrayList<>();
		if (exblocks != null) {
			for (ItemStack b : exblocks.getAll(e)) {

				exclude.add(b.getType());
			}
		}

		Runnable RollbackRun = () -> {
            Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CoreProtect");
            CoreProtectAPI api = ((CoreProtect) plugin).getAPI();

            List<String[]> lookup = null;
            lookup = api.performRollback(times.getSingle(e).getTicks() / 20, users, null, null, exclude,
                    Collections.singletonList(mark), n.getSingle(e).intValue(), l.getSingle(e));
            if (lookup != null) {
                for (String[] value : lookup) {
                    ParseResult result = api.parseResult(value);
                    int x = result.getX();
                    int y = result.getY();
                    int z = result.getZ();
                }
            }
        };

		Thread RollbackThread = new Thread(RollbackRun);
		RollbackThread.start();

	}
}
