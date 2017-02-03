package me.sharpjaws.sharpSK.hooks.WorldGuard;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class WorldGuardRegistry {
	public static void registerwguard(){
		//PirateSK merged features
		if (Bukkit.getPluginManager().getPlugin("PirateSK") != null){
		 Skript.registerEffect(EffCreateRegion.class, new String[] { "[sharpsk] create wg region %string% between %location% and %location% in [world] %world%" });
	       Skript.registerEffect(EffDeleteRegion.class, new String[] { "[sharpsk] (delete|remove) wg region %string% (in|from) [world] %world%" });
	       Skript.registerEffect(EffAddOwner.class, new String[] { "[sharpsk] add owner[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" });
	       Skript.registerEffect(EffRemoveOwner.class, new String[] { "[sharpsk] remove owner %players/offlineplayers/strings% from wg region %string% in [world] %world%" });
	       Skript.registerEffect(EffAddMember.class, new String[] { "[sharpsk] add member[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" });
	       Skript.registerEffect(EffRemoveMember.class, new String[] { "[sharpsk] remove member[s] %players/offlineplayers/strings% from wg region %string% in [world] %world%" });
	       Skript.registerExpression(ExprGetPoint1.class, Location.class, ExpressionType.SIMPLE, new String[] {
	         "[sharpsk] point[-| ]1 of wg region %string% in [world] %world%", 
	         "[sharpsk] pos[ition][-| ]1 of wg region %string% in [world] %world%", 
	         "[sharpsk] (bottom|lower) corner of wg region %string% in %world%" });
	       
	       Skript.registerExpression(ExprGetPoint2.class, Location.class, ExpressionType.SIMPLE, new String[] {
	         "[sharpsk] point 2 of wg region %string% in [world] %world%", 
	         "[sharpsk] pos[ition][ ]2 of wg region %string% in [world] %world%" });
	       
	       Skript.registerExpression(ExprRegionAt.class, String.class, ExpressionType.SIMPLE, "[sharpsk] wg region at %location%");
	       Skript.registerExpression(ExprAllRegionsInWorld.class, String.class, ExpressionType.SIMPLE,  "[sharpsk] [all] wg regions in %world%" );
	       Skript.registerEffect(EffSetBlocks.class, "[sharpsk] (set [all] blocks in|fill) wg region %string% in [world] %world% (to|with) %itemstack%" );
	  
			}else{
				 Skript.registerEffect(EffCreateRegion.class,  "create wg region %string% between %location% and %location% in [world] %world%" );
			       Skript.registerEffect(EffDeleteRegion.class, "(delete|remove) wg region %string% (in|from) [world] %world%" );
			       Skript.registerEffect(EffAddOwner.class,  "add owner[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" );
			       Skript.registerEffect(EffRemoveOwner.class,  "remove owner %players/offlineplayers/strings% from wg region %string% in [world] %world%" );
			       Skript.registerEffect(EffAddMember.class,  "add member[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" );
			       Skript.registerEffect(EffRemoveMember.class, "remove member[s] %players/offlineplayers/strings% from wg region %string% in [world] %world%" );
			       Skript.registerExpression(ExprGetPoint1.class, Location.class, ExpressionType.SIMPLE, "(point|pos[ition])[ ] 1 of wg region %string% in [world] %world%");
			       
			       Skript.registerExpression(ExprGetPoint2.class, Location.class, ExpressionType.SIMPLE,"(point|pos[ition])[ ] 2 of wg region %string% in [world] %world%");
			       
			       Skript.registerExpression(ExprRegionAt.class, String.class, ExpressionType.SIMPLE, new String[] { "wg region at %location%" });
			       Skript.registerExpression(ExprAllRegionsInWorld.class, String.class, ExpressionType.SIMPLE, new String[] { "[all] wg regions in %world%" });
			       Skript.registerEffect(EffSetBlocks.class, new String[] { "(set [all] blocks in|fill) wg region %string% in [world] %world% (to|with) %itemstack%" });
			}
		   //---------------------
		}

}
