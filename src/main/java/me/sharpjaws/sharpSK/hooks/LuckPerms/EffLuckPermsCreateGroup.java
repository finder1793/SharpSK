package me.sharpjaws.sharpSK.hooks.LuckPerms;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.exceptions.ObjectAlreadyHasException;


public class EffLuckPermsCreateGroup extends Effect{
private Expression<String> group;
private Expression<String> perms;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		group = (Expression<String>) expr[0];
		perms = (Expression<String>)expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "[sharpsk] luckperms create group %string% [with permissions %-strings%]";
	}

	@Override
	protected void execute(Event e) {
		
	LuckPermsApi api = LuckPerms.getApi();
		api.getStorage().createAndLoadGroup(group.getSingle(e)).thenAcceptAsync(success -> {
		    if (!success) {
		        return;
		    }

		    Group Lgroup = api.getGroup(group.getSingle(e));
		    if (Lgroup == null) {
		        return;
		    }

		    if (perms != null){
		   for (String s: perms.getAll(e)){
			     try {
			    	Node permission = api.buildNode(s).build();
					Lgroup.setPermission(permission);
				} catch (ObjectAlreadyHasException | IllegalArgumentException ex) {
					return;
				}	
		    }
		    }

		    // Now save the group back to storage
		    api.getStorage().saveGroup(Lgroup);
		}, api.getStorage().getAsyncExecutor());
	}
}
