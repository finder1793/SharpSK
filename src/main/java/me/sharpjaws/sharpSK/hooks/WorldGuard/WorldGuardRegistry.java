package me.sharpjaws.sharpSK.hooks.WorldGuard;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.protection.events.DisallowedPVPEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.sharpjaws.sharpSK.hooks.WorldEdit.EffSetBlocks;

public class WorldGuardRegistry {
	public static void registerWorldGuard() {
		// PirateSK merged features
		Skript.registerEffect(EffCreateRegion.class,
				"[sharpsk] [worldguard] create region %string% between %location% and %location% in [world] %world%");
		Skript.registerEffect(EffDeleteRegion.class,
				"[sharpsk] [worldguard] (delete|remove) region %string% (in|from) [world] %world%");
		Skript.registerEffect(EffAddOwner.class,
				"[sharpsk] [worldguard] add owner[s] %players/offlineplayers/strings% [(-1在y name|1在y uuid)] to region %string% in [world] %world%");
		Skript.registerEffect(EffRemoveOwner.class,
				"[sharpsk] [worldguard] remove owner %players/offlineplayers/strings% [(-1在y name|1在y uuid)] from region %string% in [world] %world%");
		Skript.registerEffect(EffAddMember.class,
				"[sharpsk] [worldguard] add member[s] %players/offlineplayers/strings% [(-1在y name|1在y uuid)] to region %string% in [world] %world%");
		Skript.registerEffect(EffRemoveMember.class,
				"[sharpsk] [worldguard] remove member[s] %players/offlineplayers/strings% [(-1在y name|1在y uuid)] from region %string% in [world] %world%");
		Skript.registerExpression(ExprGetPoint1.class, Location.class, ExpressionType.SIMPLE,
				"[sharpsk] [worldguard] (point|pos[ition])[ ] 1 of region %string% in [world] %world%");
		Skript.registerExpression(ExprGetPoint2.class, Location.class, ExpressionType.SIMPLE,
				"[sharpsk] [worldguard] (point|pos[ition])[ ] 2 of region %string% in [world] %world%");
		Skript.registerExpression(ExprRegionAt.class, String.class, ExpressionType.SIMPLE,
				"sharpsk [worldguard] region at %location%");
		Skript.registerExpression(ExprAllRegionsInWorld.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] [all] [worldguard] regions in %world%");
		Skript.registerExpression(ExprRegionsOfPlayer.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] [all] regions of [player] %offlineplayer% [in %-world%]");
		Skript.registerEffect(EffSetBlocks.class,
				"[sharpsk] [worldguard] (set [all] blocks in|fill) region %string% in [world] %world% (to|with) %itemstack%");
		// ---------------------

		Skript.registerExpression(ExprAllMembers.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] [worldguard] [all] members of region %string% in [world] %world%");
		Skript.registerExpression(ExprAllOwners.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] [worldguard] [all] owners of region %string% in [world] %world%");
		Skript.registerExpression(ExprAllFlagsOfRegion.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] [worldguard] [all] flags of region %string% in [world] %world%");
		Skript.registerExpression(ExprFlagValueOfFlagOfRegion.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] value of flag %string% of [worldguard] region %string% in [world] %world%");
		Skript.registerEvent("Worldguard PVP Disallowed Event", SimpleEvent.class, DisallowedPVPEvent.class,
				"[sharpsk] [worldguard] pvp disallow[ed]");
		EventValues.registerEventValue(DisallowedPVPEvent.class, Player.class,
				new Getter<Player, DisallowedPVPEvent>() {
					@Override
					@Nullable
					public Player get(DisallowedPVPEvent e) {
						Player p = e.getAttacker();
						return p;
					}
				}, 0);
		EventValues.registerEventValue(DisallowedPVPEvent.class, Location.class,
				new Getter<Location, DisallowedPVPEvent>() {
					@Override
					@Nullable
					public Location get(DisallowedPVPEvent e) {
						Location l = e.getAttacker().getLocation();
						return l;
					}
				}, 0);
	}

}
