package me.sharpjaws.sharpSK.hooks.MythicMobs.old;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.elseland.xikage.MythicMobs.MythicMobs;


 public class CondNotMythicMobOld extends Condition
 {
 private Expression<Entity> mythicmob;
@SuppressWarnings("unused")
	private Expression<Location> loc;


 @SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult arg3)
{
mythicmob = (Expression<Entity>) expr[0];
 return true;
 }


@Override
	public String toString(@Nullable Event e, boolean debug)
 {
 return "%entity% is a mythicmob";
}


@Override
	public boolean check(Event e)
 {
 Boolean result = Boolean.valueOf(false);
 Boolean result2 = false;
 try
{
 result = Boolean.valueOf(MythicMobs.inst().getAPI().getMobAPI().isMythicMob(mythicmob.getSingle(e).getUniqueId()));
 if (result.equals(false)){
	 result2 = true;
 }
} catch (NullPointerException ex) {

	return false;
}
 return result2;
}
}
