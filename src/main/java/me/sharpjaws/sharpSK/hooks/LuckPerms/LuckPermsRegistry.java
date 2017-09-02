package me.sharpjaws.sharpSK.hooks.LuckPerms;

import ch.njol.skript.Skript;

public class LuckPermsRegistry {

	public static void registerLuckPerms() {		
		//Effects
		
		Skript.registerEffect(EffLuckPermsSetPerm.class, "[sharpsk] luckperms set permission %string% to %boolean% for [player] %offlineplayer%");
		Skript.registerEffect(EffLuckPermsUnsetPerm.class, "[sharpsk] luckperms unset permission %string% for [player] %offlineplayer%");
		Skript.registerEffect(EffLuckPermsCreateGroup.class, "[sharpsk] luckperms create group %string% [with permissions %-strings%]");
	}
}
