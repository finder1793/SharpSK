package me.sharpjaws.sharpSK.hooks.GroupManager;

import ch.njol.skript.Skript;

public class GroupManagerRegistry {
	public static void registerGroupManager() {
		Skript.registerEffect(EffGroupManagerAddPermission.class, "[sharpsk] (gm|group[ ]manager add perm[ission] %string% to [player] %offlineplayer%");
		Skript.registerEffect(EffGroupManagerRemovePermission.class, "[sharpsk] (gm|group[ ]manager (remove|delete) perm[ission] %string% from [player] %offlineplayer%");
	}

}
