package me.sharpjaws.sharpsk.hooks.GlowAPI;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.inventivetalent.glow.GlowAPI;

import javax.annotation.Nullable;
import java.util.Random;

public class ExprGlowingColorOf extends SimpleExpression<GlowAPI.Color> {
	private Expression<Entity> en;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends GlowAPI.Color> getReturnType() {
		return GlowAPI.Color.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		en = (Expression<Entity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "Glowing color of %entity%";
	}

	@Override
	@Nullable
	protected GlowAPI.Color[] get(Event e) {
		Player p = null;
		GlowAPI.Color c = null;
		try {
			en.getSingle(e).getType();
			if (en.getSingle(e).getType() == EntityType.PLAYER) {
				p = (Player) en.getSingle(e);
				c = GlowAPI.getGlowColor(en.getSingle(e), p);
			} else if (en.getSingle(e).getType() != EntityType.PLAYER) {

				Random rand = new Random();
				int random = rand.nextInt(Bukkit.getServer().getOnlinePlayers().size());
				Player p2 = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];
				try {
					c = GlowAPI.getGlowColor(en.getSingle(e), p2);
				} catch (NullPointerException ex) {

				}
			} else if (en.getSingle(e).getType() == null) {
			}
		} catch (NullPointerException ex) {
			c = GlowAPI.Color.NONE;
		}

		return new GlowAPI.Color[] { c };

	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) {
			GlowAPI.Color c = GlowAPI.Color.valueOf(delta[0].toString());
			GlowAPI.setGlowing(this.en.getSingle(e), c, Bukkit.getOnlinePlayers());
		}
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(new Class[] { GlowAPI.Color.class });
		return null;
	}
}
