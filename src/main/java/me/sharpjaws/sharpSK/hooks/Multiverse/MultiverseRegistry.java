package me.sharpjaws.sharpSK.hooks.Multiverse;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import com.onarandombox.MultiverseCore.event.MVConfigReloadEvent;
import com.onarandombox.MultiverseCore.event.MVPlayerTouchedPortalEvent;
import com.onarandombox.MultiverseCore.event.MVWorldDeleteEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class MultiverseRegistry {
	
	@SuppressWarnings("unchecked")
	public static void registerMultiv(){
Skript.registerEvent("MV On Portal Touch",SimpleEvent.class, MVPlayerTouchedPortalEvent.class, "(mv|multiverse) portal ([touch|enter])");
EventValues.registerEventValue(MVPlayerTouchedPortalEvent.class, Player.class,
		new Getter<Player, MVPlayerTouchedPortalEvent>() {
			@Override
			@Nullable
			public Player get(MVPlayerTouchedPortalEvent e) {
				Player p = e.getPlayer();
				return p;
			}
		}, 0);
EventValues.registerEventValue(MVPlayerTouchedPortalEvent.class, Location.class,
		new Getter<Location, MVPlayerTouchedPortalEvent>() {
			@Override
			@Nullable
			public Location get(MVPlayerTouchedPortalEvent e) {
				Location loc = e.getPlayer().getLocation();
				return loc;
			}
		}, 0);
Skript.registerEvent("MV On World Delete",SimpleEvent.class, MVWorldDeleteEvent.class, "(mv|multiverse) world delet[e|ion]");
EventValues.registerEventValue(MVWorldDeleteEvent.class, World.class,
		new Getter<World, MVWorldDeleteEvent>() {
			@Override
			@Nullable
			public World get(MVWorldDeleteEvent e) {
				World w = e.getWorld().getCBWorld();
				return w;
			}
		}, 0);
EventValues.registerEventValue(MVWorldDeleteEvent.class, String.class,
		new Getter<String, MVWorldDeleteEvent>() {
			@Override
			@Nullable
			public String get(MVWorldDeleteEvent e) {
				String wn = e.getWorld().getName();
				return wn;
			}
		}, 0);
Skript.registerEvent("MV On Config Reload",SimpleEvent.class, MVConfigReloadEvent.class, "(mv|multiverse) config reload");

	Skript.registerEffect(EffMVLoadWorld.class, "(mv|multiverse) load world %string%");
	Skript.registerEffect(EffMVUnloadWorld.class, "(mv|multiverse) unload world %string%");
	Skript.registerEffect(EffMVDeleteWorld.class, "(mv|multiverse) delete world %string%");
	Skript.registerEffect(EffMVCloneWorld.class, "(mv|multiverse) (clone|copy) world %string% to %string%");
	Skript.registerEffect(EffMVRemovePlayersFromWorld.class, "(mv|multiverse) remove players from world %string%");
	
	Skript.registerExpression(ExprUnloadedWorlds.class, String.class, ExpressionType.SIMPLE, "[(mv|multiverse)] [all] [the] unloaded worlds");
	
	Skript.registerCondition(CondMVUnloadedWorld.class, "[(mv|multiverse)] world %string% is unloaded");
	Skript.registerCondition(CondMVNotUnloadedWorld.class, "[(mv|multiverse)] world %string% is not unloaded");
	
	}

}
