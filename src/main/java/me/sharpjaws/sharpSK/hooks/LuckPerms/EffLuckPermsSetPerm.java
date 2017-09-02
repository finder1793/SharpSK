package me.sharpjaws.sharpSK.hooks.LuckPerms;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.exceptions.ObjectLacksException;


public class EffLuckPermsSetPerm extends Effect{
private Expression<OfflinePlayer> offplayer;
private Expression<String> perm;
private Expression<Boolean> bool;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		perm = (Expression<String>) expr[0];
		bool = (Expression<Boolean>)expr[1];
		offplayer = (Expression<OfflinePlayer>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "[sharpsk] luckperms set permission %string% to %boolean% for [player] %offlineplayer%";
	}

	@Override
	protected void execute(Event e) {
		if (offplayer == null){return;}
		if  (offplayer.getSingle(e) == null) {return;}
		final LuckPermsApi api = LuckPerms.getApi();
		Node node = api.getNodeFactory().newBuilder(perm.getSingle(e)).setValue(bool.getSingle(e)).build();
		if (offplayer.getSingle(e).isOnline()){
			User user = api.getUser(offplayer.getSingle(e).getPlayer().getUniqueId());
		if (user == null) {
		   return;
		}
			
			try {
				user.unsetPermission(node);
				user.setPermissionUnchecked(node);
			} catch (ObjectLacksException ex1) {
				user.setPermissionUnchecked(node);
			}		
			
			//Workaround for getting changes to take immediate effect instead of having to relog on the server.
			 api.getStorage().saveUser(user);
			 api.getStorage().loadUser(user.getUuid(),user.getName());
			 api.getStorage().saveUser(user);
			 api.getStorage().loadUser(user.getUuid(),user.getName());

		
		}else{
			User user = api.getUser(offplayer.getSingle(e).getUniqueId());
			
			
			api.getStorage().loadUser(user.getUuid(), "null").thenComposeAsync(success -> {
			    if (!success) {
			        return CompletableFuture.completedFuture(false);
			    }
			    try {	
					user.unsetPermission(node);
					user.setPermissionUnchecked(node);
					} catch (ObjectLacksException ex1) {
						user.setPermissionUnchecked(node);
					}
			        
			        // first save the user
			        return api.getStorage().saveUser(user)
			                .thenCompose(b -> {          
			                    api.cleanupUser(user);
			                    return CompletableFuture.completedFuture(b);
			                });
			        
			    
			}, api.getStorage().getAsyncExecutor());	
			
		}
	}

}
