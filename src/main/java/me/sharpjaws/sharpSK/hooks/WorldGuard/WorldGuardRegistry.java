package me.sharpjaws.sharpSK.hooks.WorldGuard;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.sharpjaws.sharpSK.hooks.WorldEdit.EffSetBlocks;

import com.gamingmesh.jobs.api.JobsLevelUpEvent;
import com.sk89q.worldguard.protection.events.DisallowedPVPEvent;

public class WorldGuardRegistry {
	public static void registerwguard(){
		//PirateSK merged features
				 Skript.registerEffect(EffCreateRegion.class,  "[sharpsk] [(wg|worldguard)] create wg region %string% between %location% and %location% in [world] %world%" );
			       Skript.registerEffect(EffDeleteRegion.class, "[sharpsk] [(wg|worldguard)] (delete|remove) wg region %string% (in|from) [world] %world%" );
			       Skript.registerEffect(EffAddOwner.class,  "[sharpsk] [(wg|worldguard)] add owner[s] %players/offlineplayers/strings% [(-1在y name|1在y uuid)] to wg region %string% in [world] %world%" );
			       Skript.registerEffect(EffRemoveOwner.class,  "[sharpsk] [(wg|worldguard)] remove owner %players/offlineplayers/strings% [(-1在y name|1在y uuid)] from wg region %string% in [world] %world%" );
			       Skript.registerEffect(EffAddMember.class,  "[sharpsk] [(wg|worldguard)] add member[s] %players/offlineplayers/strings% [(-1在y name|1在y uuid)] to wg region %string% in [world] %world%" );
			       Skript.registerEffect(EffRemoveMember.class, "[sharpsk] [(wg|worldguard)] remove member[s] %players/offlineplayers/strings% [(-1在y name|1在y uuid)] from wg region %string% in [world] %world%" );
			       Skript.registerExpression(ExprGetPoint1.class, Location.class, ExpressionType.SIMPLE, "[sharpsk] [(wg|worldguard)] (point|pos[ition])[ ] 1 of wg region %string% in [world] %world%");
			       Skript.registerExpression(ExprGetPoint2.class, Location.class, ExpressionType.SIMPLE,"[sharpsk] [(wg|worldguard)] (point|pos[ition])[ ] 2 of wg region %string% in [world] %world%");
			       Skript.registerExpression(ExprRegionAt.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [(wg|worldguard)] region at %location%" );
			       Skript.registerExpression(ExprAllRegionsInWorld.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [(wg|worldguard)] [all] wg regions in %world%" );
			       Skript.registerEffect(EffSetBlocks.class, "[sharpsk] [(wg|worldguard)] (set [all] blocks in|fill) region %string% in [world] %world% (to|with) %itemstack%" );	
		   //---------------------
		
		   Skript.registerExpression(ExprAllMembers.class, String.class, ExpressionType.SIMPLE,  "[sharpsk] [(wg|worldguard)] [all] members of wg region %string% in [world] %world%" );
		   Skript.registerExpression(ExprAllOwners.class, String.class, ExpressionType.SIMPLE,  "[sharpsk] [(wg|worldguard)] [all] owners of wg region %string% in [world] %world%" );
		   Skript.registerExpression(ExprAllFlagsOfRegion.class, String.class, ExpressionType.SIMPLE,  "[sharpsk] [(wg|worldguard)] [all] flags of wg region %string% in [world] %world%" );
		   Skript.registerExpression(ExprFlagValueOfFlagOfRegion.class, String.class, ExpressionType.SIMPLE,  "[sharpsk] [(wg|worldguard)] value of flag %string% of wg region %string% in [world] %world%" );
		   Skript.registerEvent("Worldguard PVP Disallowed Event",SimpleEvent.class, DisallowedPVPEvent.class, "[sharpsk] [wg] pvp disallow[ed]");
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
