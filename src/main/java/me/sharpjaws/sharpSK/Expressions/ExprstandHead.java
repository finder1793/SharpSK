package me.sharpjaws.sharpSK.Expressions;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.util.EulerAngle;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprstandHead extends SimpleExpression<Location> {
	private Expression<Entity> en;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
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
		return "rank of [the] group %string%";
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		ArmorStand arstand1 = null;
		
		if (en.getSingle(e) instanceof ArmorStand){
			arstand1 = (ArmorStand)en.getSingle(e);
		}
		
		return new Location[] {(Location)arstand1.getEyeLocation()};
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) {
			try {
				if (en.getSingle(e) instanceof ArmorStand){
					 ArmorStand arstand = (ArmorStand)en.getSingle(e);
					double x=0,y=0,z=0;
					Location loc2 = (Location)delta[0];
					
		                x = Math.toRadians(loc2.getPitch());
		                y = Math.toRadians(loc2.getYaw());
		                EulerAngle a = new EulerAngle(x,y,z);
		                arstand.setHeadPose(a);
		                
				}
			} catch (NullPointerException ex) {

			}

		}
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(new Class[] { Location.class });
		if (mode == Changer.ChangeMode.ADD)
			return CollectionUtils.array(new Class[] { Location.class });
		return null;
	}
}
