package me.sharpjaws.sharpSK.hooks.WorldGuard;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class WorldGuardRegistry {
	public static void registerwguard(){
		//PirateSK merged features
		if (Bukkit.getPluginManager().getPlugin("PirateSK") == null){
		 Skript.registerEffect(EffCreateRegion.class, new String[] { "create wg region %string% between %location% and %location% in [world] %world%" });
	       Skript.registerEffect(EffDeleteRegion.class, new String[] { "(delete|remove) wg region %string% (in|from) [world] %world%" });
	       Skript.registerEffect(EffAddOwner.class, new String[] { "add owner[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" });
	       Skript.registerEffect(EffRemoveOwner.class, new String[] { "remove owner %players/offlineplayers/strings% from wg region %string% in [world] %world%" });
	       Skript.registerEffect(EffAddMember.class, new String[] { "add member[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" });
	       Skript.registerEffect(EffRemoveMember.class, new String[] { "remove member[s] %players/offlineplayers/strings% from wg region %string% in [world] %world%" });
	       Skript.registerExpression(ExprGetPoint1.class, Location.class, ExpressionType.SIMPLE, new String[] {
	         "point[-| ]1 of wg region %string% in [world] %world%", 
	         "pos[ition][-| ]1 of wg region %string% in [world] %world%", 
	         "(bottom|lower) corner of wg region %string% in %world%" });
	       
	       Skript.registerExpression(ExprGetPoint2.class, Location.class, ExpressionType.SIMPLE, new String[] {
	         "point 2 of wg region %string% in [world] %world%", 
	         "pos[ition][ ]2 of wg region %string% in [world] %world%" });
	       
	       Skript.registerExpression(ExprRegionAt.class, String.class, ExpressionType.SIMPLE, new String[] { "wg region at %location%" });
	       Skript.registerExpression(ExprAllRegionsInWorld.class, String.class, ExpressionType.SIMPLE, new String[] { "[all] wg regions in %world%" });
	       Skript.registerEffect(EffSetBlocks.class, new String[] { "(set [all] blocks in|fill) wg region %string% in [world] %world% (to|with) %itemstack%" });
	     //---------------------
			}
		}

}
