package me.sharpjaws.sharpSK.hooks.PermissionsEx;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ExprPexGroupOf extends SimpleExpression<String> {
	private Expression<OfflinePlayer> p;
	
	
	private int mark = 0;

	

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		p = (Expression<OfflinePlayer>) expr[0];	
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "groups of %player%/%offlineplayer%";
	}


	@Override
	@Nullable
	protected String[] get(Event e) {
		
		Object o = p.getSingle(e);
		PermissionUser a = null;
		
		if (o instanceof Player) {
			a = PermissionsEx.getUser((Player)o);
		}
		if(o instanceof OfflinePlayer) {
			a = PermissionsEx.getUser(((OfflinePlayer)o).getName());
		}else {
			a = PermissionsEx.getUser(o.toString());
		}
		
		ArrayList<String> farr = new ArrayList<String>(); 
		 for (PermissionGroup a1 : a.getParents()) {
		
			 farr.add(a1.getName());
		 }
		
		 
		 return farr.toArray(new String[farr.size()]);
		
			}

	@Override
	public boolean isSingle() {
		return false;
	}

	

}


