package me.sharpjaws.sharpSK.Effects;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffBrewerInv extends Effect {
	private Expression<Player> p;
	private Expression<String> s;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		p = (Expression<Player>) expr[0];
		s = (Expression<String>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "open brewer inventory";
	}

	@Override
	protected void execute(Event e) {
		try {
			String invname = s.getSingle(e);
			Inventory brewing = Bukkit.createInventory(p.getSingle(e), InventoryType.BREWING, invname);
			p.getSingle(e).openInventory(brewing);
		} catch (NullPointerException ex) {
			Inventory brewing2 = Bukkit.createInventory(p.getSingle(e), InventoryType.BREWING, "Brewing Stand");
			p.getSingle(e).openInventory(brewing2);
		}

	}
}
