package me.sharpjaws.sharpSK.hooks.WorldGuard;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class WorldGuardRegistry {
	public static void registerwguard(){
		//PirateSK merged features
		if (Bukkit.getPluginManager().getPlugin("PirateSK") != null){
		 Skript.registerEffect(EffCreateRegion.class, "[sharpsk] [(wg|worldguard)] create wg region %string% between %location% and %location% in [world] %world%" );
	       Skript.registerEffect(EffDeleteRegion.class,  "[sharpsk] [(wg|worldguard)] (delete|remove) wg region %string% (in|from) [world] %world%" );
	       Skript.registerEffect(EffAddOwner.class, "[sharpsk] [(wg|worldguard)] add owner[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" );
	       Skript.registerEffect(EffRemoveOwner.class,  "[sharpsk] [(wg|worldguard)] remove owner %players/offlineplayers/strings% from wg region %string% in [world] %world%" );
	       Skript.registerEffect(EffAddMember.class,  "[sharpsk] [(wg|worldguard)] add member[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" );
	       Skript.registerEffect(EffRemoveMember.class,  "[sharpsk] [(wg|worldguard)] remove member[s] %players/offlineplayers/strings% from wg region %string% in [world] %world%" );
	       Skript.registerExpression(ExprGetPoint1.class, Location.class, ExpressionType.SIMPLE, "[sharpsk] [wg|worldguard] (point|pos[ition])[ ] 1 of wg region %string% in [world] %world%");
	       Skript.registerExpression(ExprGetPoint2.class, Location.class, ExpressionType.SIMPLE,"[sharpsk] [wg|worldguard] (point|pos[ition])[ ] 2 of wg region %string% in [world] %world%");
	       Skript.registerExpression(ExprRegionAt.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [wg|worldguard] wg region at %location%");
	       Skript.registerExpression(ExprAllRegionsInWorld.class, String.class, ExpressionType.SIMPLE,  "[sharpsk] [wg|worldguard] [all] wg regions in %world%" );
	       Skript.registerEffect(EffSetBlocks.class, "[sharpsk] [wg|worldguard] (set [all] blocks in|fill) wg region %string% in [world] %world% (to|with) %itemstack%" );
	  
			}else{
				 Skript.registerEffect(EffCreateRegion.class,  "[(wg|worldguard)] create wg region %string% between %location% and %location% in [world] %world%" );
			       Skript.registerEffect(EffDeleteRegion.class, "[(wg|worldguard)] (delete|remove) wg region %string% (in|from) [world] %world%" );
			       Skript.registerEffect(EffAddOwner.class,  "[(wg|worldguard)] add owner[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" );
			       Skript.registerEffect(EffRemoveOwner.class,  "[(wg|worldguard)] remove owner %players/offlineplayers/strings% from wg region %string% in [world] %world%" );
			       Skript.registerEffect(EffAddMember.class,  "[(wg|worldguard)] add member[s] %players/offlineplayers/strings% to wg region %string% in [world] %world%" );
			       Skript.registerEffect(EffRemoveMember.class, "[(wg|worldguard)] remove member[s] %players/offlineplayers/strings% from wg region %string% in [world] %world%" );
			       Skript.registerExpression(ExprGetPoint1.class, Location.class, ExpressionType.SIMPLE, "[(wg|worldguard)] (point|pos[ition])[ ] 1 of wg region %string% in [world] %world%");
			       Skript.registerExpression(ExprGetPoint2.class, Location.class, ExpressionType.SIMPLE,"[(wg|worldguard)] (point|pos[ition])[ ] 2 of wg region %string% in [world] %world%");
			       
			       Skript.registerExpression(ExprRegionAt.class, String.class, ExpressionType.SIMPLE, "[(wg|worldguard)] wg region at %location%" );
			       Skript.registerExpression(ExprAllRegionsInWorld.class, String.class, ExpressionType.SIMPLE, "[(wg|worldguard)] [all] wg regions in %world%" );
			       Skript.registerEffect(EffSetBlocks.class, "[(wg|worldguard)] (set [all] blocks in|fill) wg region %string% in [world] %world% (to|with) %itemstack%" );
			}
		   //---------------------
		}

}
