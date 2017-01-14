package me.sharpjaws.sharpSK.hooks.mcMMO;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.gmail.nossr50.datatypes.skills.AbilityType;
import com.gmail.nossr50.util.player.UserManager;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;


 public class CondmcMMOAbilityEnabled extends Condition
 {
 private Expression<Player> p;
 private Expression<AbilityType> ability;



 @SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult arg3)
{
 p = (Expression<Player>) expr[0];	 
 ability = (Expression<AbilityType>) expr[1];
 return true;
 }


@Override
	public String toString(@Nullable Event e, boolean debug)
 {
 return "%abilitytype% is enabled";
}


@Override
	public boolean check(Event e)
 {
return UserManager.getPlayer(p.getSingle(e)).getAbilityMode(ability.getSingle(e));
}
 }
