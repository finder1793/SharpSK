package me.sharpjaws.sharpsk.hooks.MythicMobs.old;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ExprEvtMMDropsOld extends SimpleExpression<ItemStack> {

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "event-mmdrops";
	}

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
		return ScriptLoader.isCurrentEvent(MythicMobDeathEvent.class);
	}

	@Override
	@Nullable
	protected ItemStack[] get(Event e) {
		if (e.getEventName().equals("MythicMobDeathEvent")) {
			List<ItemStack> a = ((MythicMobDeathEvent) e).getDrops();
			return a.toArray(new ItemStack[0]);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void change(Event e, Object[] deltas, Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.REMOVE_ALL) {
			final List<ItemStack> drops = ((MythicMobDeathEvent) e).getDrops();
			drops.clear();
			((MythicMobDeathEvent) e).setDrops(drops);

		}
		if (mode == Changer.ChangeMode.DELETE) {
			final List<ItemStack> drops = ((MythicMobDeathEvent) e).getDrops();
			drops.clear();
			((MythicMobDeathEvent) e).setDrops(drops);
		}
		if (mode == Changer.ChangeMode.SET) {

			List<ItemStack> drops = ((MythicMobDeathEvent) e).getDrops();

			drops.clear();
			((MythicMobDeathEvent) e).setDrops(drops);

			ItemStack[] items = (ItemStack[]) deltas;

			List<ItemStack> list = new ArrayList<>(Arrays.asList(items));
			((MythicMobDeathEvent) e).setDrops(list);
		}
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.REMOVE_ALL)
			return CollectionUtils.array(ItemStack.class);
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(ItemStack[].class, ItemStack.class);
		if (mode == Changer.ChangeMode.DELETE)
			return CollectionUtils.array(ItemStack.class);
		return null;
	}
}
