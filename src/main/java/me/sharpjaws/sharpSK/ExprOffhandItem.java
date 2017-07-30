package me.sharpjaws.sharpSK;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprOffhandItem extends SimpleExpression<ItemStack> {
	private Expression<Player> p;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		p = (Expression<Player>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "%player%'s offhand";
	}

	@Override
	@Nullable
	protected ItemStack[] get(Event e) {
		return new ItemStack[] { this.p.getSingle(e).getInventory().getItemInOffHand() };
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Integer limit = Integer.valueOf(64);
		if (mode == Changer.ChangeMode.SET) {
			this.p.getSingle(e).getInventory().setItemInOffHand((ItemStack) delta[0]);
			Integer a = Integer.valueOf(this.p.getSingle(e).getInventory().getItemInOffHand().getAmount());
			if (limit.intValue() <= a.intValue()) {
				this.p.getSingle(e).getInventory().getItemInOffHand().setAmount(64);
			}
		}
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(new Class[] { ItemStack.class });
		return null;
	}
}
