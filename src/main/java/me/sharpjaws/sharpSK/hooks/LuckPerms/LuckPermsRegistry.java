package me.sharpjaws.sharpSK.hooks.LuckPerms;

import ch.njol.skript.Skript;

public class LuckPermsRegistry {

	public static void registerLuckPerms() {
		Skript.registerEffect(EffLuckPermsSetPerm.class, "[sharpsk] [luckperms] set permission %string% to %boolean% for [player] %player%");
	}
}
