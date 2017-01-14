package me.sharpjaws.sharpSK.hooks.AuthmeReloaded;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import fr.xephi.authme.api.API;
import fr.xephi.authme.events.AuthMeTeleportEvent;
import fr.xephi.authme.events.CustomEvent;
import fr.xephi.authme.events.LoginEvent;
import fr.xephi.authme.events.LogoutEvent;
import fr.xephi.authme.events.RestoreInventoryEvent;
import me.sharpjaws.sharpSK.hooks.LightAPI.ExprEvtLightLevel;

public class AuthmeRegistry {

	public static void registerAuthMe() {
					API.hookAuthMe();
					Skript.registerEvent("Authme debug", SimpleEvent.class, CustomEvent.class, "authme debug");
					Skript.registerEvent("Authme login", SimpleEvent.class, LoginEvent.class, "authme login");
					EventValues.registerEventValue(LoginEvent.class, Player.class,
							new Getter<Player, LoginEvent>() {
								@Override
								@Nullable
								public Player get(LoginEvent e) {
									Player p = e.getPlayer();
									return p;
								}
							}, 0);
					Skript.registerEvent("Authme logout", SimpleEvent.class, LogoutEvent.class, "authme logout");
					EventValues.registerEventValue(LogoutEvent.class, Player.class,
							new Getter<Player, LogoutEvent>() {
								@Override
								@Nullable
								public Player get(LogoutEvent e) {
									Player p = e.getPlayer();

									return p;
								}
							}, 0);
					Skript.registerEvent("Authme teleport", SimpleEvent.class, AuthMeTeleportEvent.class,
							"authme teleport");
					EventValues.registerEventValue(AuthMeTeleportEvent.class, Player.class,
							new Getter<Player, AuthMeTeleportEvent>() {
								@Override
								@Nullable
								public Player get(AuthMeTeleportEvent e) {
									Player p = e.getPlayer();
									return p;
								}
							}, 0);
					Skript.registerEvent("Authme inventory restore", SimpleEvent.class, RestoreInventoryEvent.class,
							"authme inventory restore");
					EventValues.registerEventValue(RestoreInventoryEvent.class, Player.class,
							new Getter<Player, RestoreInventoryEvent>() {
								@Override
								@Nullable
								public Player get(RestoreInventoryEvent e) {
									Player p = e.getPlayer();
									return p;
								}
							}, 0);
					Skript.registerExpression(ExprHashedPasswordOf.class, String.class, ExpressionType.PROPERTY, "[authme] hashed password of %player%");
					if (Bukkit.getPluginManager().getPlugin("AuthMe").getDescription().getVersion().contains("5.0")){
					Skript.registerEffect(EffAuthForceLogout.class, "[authme] force %player% to log[]out");
					Skript.registerEffect(EffAuthForceLogin.class, "[authme] force %player% to log[]in [with] pass[word] %string%");
					Skript.registerCondition(CondAuthIsRegisterd.class, "[authme] %player% is registerd");
					Skript.registerCondition(CondAuthIsNotRegisterd.class, "[authme] %player% is not registerd");
					
					}else if (Bukkit.getPluginManager().getPlugin("AuthMe").getDescription().getVersion().contains("5.2")) {
					Skript.registerExpression(ExprHashedPasswordOf.class, String.class, ExpressionType.PROPERTY, "[authme] hashed password of %player%");
					Skript.registerEffect(EffAuthForceLogoutNew.class, "[authme] force %player% to log[]out");
					Skript.registerEffect(EffAuthForceLoginNew.class, "[authme] force %player% to log[]in");	
					Skript.registerEffect(EffAuthForceRegister.class, "[authme] force %player% to register with pass[word] %string%");
					Skript.registerEffect(EffAuthForceUnregister.class, "[authme] force %player% to unregister");
					Skript.registerCondition(CondAuthIsRegisterd.class, "[authme] %player% is registerd");
					Skript.registerCondition(CondAuthIsNotRegisterd.class, "[authme] %player% is not registerd");

					}
				
			
	}
}
	
	

