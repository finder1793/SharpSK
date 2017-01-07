package me.sharpjaws.sharpSK.hooks.PermissionsEx;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ExprPexGroupOf extends SimpleExpression<String> {
	private Expression<OfflinePlayer> p;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		p = (Expression<OfflinePlayer>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "groups of (%player%|%offlineplayer%)";
	}

	@SuppressWarnings("deprecation")
	@Override
	@Nullable
	protected String[] get(Event e) {
		PermissionUser a = PermissionsEx.getUser("Notch");
		try {		
		
		if (!p.getSingle(e).isOnline()){
			for (OfflinePlayer pa : Bukkit.getServer().getOfflinePlayers()){
				if (pa.getName().contains(p.getSingle(e).getName())){
					a = PermissionsEx.getUser(pa.getName());
					break;
				}
			}
		
		}else{
			 a = PermissionsEx.getUser(p.getSingle(e).getPlayer());
		}
		}catch(Exception ex){
			return new String[] {};	
		}
		
			return a.getGroupNames();	
		}
	}


