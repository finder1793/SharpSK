package me.sharpjaws.sharpSK.hooks.GroupManager;

import ch.njol.skript.Skript;

public class GroupManagerRegistry {
	public static void registerGroupManager() {
		Skript.registerEffect(EffGroupManagerAddPermission.class,
				"[sharpsk] (gman|group[ ]manager) add perm[ission] %string% to [player] %offlineplayer% [in [world] %-world%]");
		Skript.registerEffect(EffGroupManagerRemovePermission.class,
				"[sharpsk] (gman|group[ ]manager) (remove|delete) perm[ission] %string% from [player] %offlineplayer% [in [world] %-world%]");
		Skript.registerEffect(EffGroupManagerSetGroupOfPlayer.class,
				"[sharpsk] (gman|group[ ]manager) set [main] group of [player] %offlineplayer% to %string% [in [world] %-world%]");
		Skript.registerEffect(EffGroupManagerAddSubGroupToPlayer.class,
				"[sharpsk] (gman|group[ ]manager) add [sub] group %string% to %offlineplayer% [in [world] %-world%]");
	}

}
