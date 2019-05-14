package me.sharpjaws.sharpsk.hooks.LuckPerms;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class LuckPermsRegistry {

	public static void registerLuckPerms() {

		// Effects
		Skript.registerEffect(EffLuckPermsSetPerm.class,
				"[sharpsk] (luckperms|lp) set (-1¦transient perm[ission]|1¦perm[ission]) %string% to %boolean% for [player] %offlineplayer%");
		Skript.registerEffect(EffLuckPermsUnsetPerm.class,
				"[sharpsk] (luckperms|lp) unset (-1¦transient perm[ission]|1¦perm[ission]) %string% for [player] %offlineplayer%");
		Skript.registerEffect(EffLuckPermsCreateGroup.class,
				"[sharpsk] (luckperms|lp) create group %string% [with permissions %-strings%]");
		Skript.registerEffect(EffLuckPermsDeleteGroup.class, "[sharpsk] (luckperms|lp) (delete|remove) group %string%");

		// Expressions
		Skript.registerExpression(ExprLuckPermsAllPermissionsOfPlayer.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] (luckperms|lp) [(all|the)] (-1¦transient perm[ission]s|1¦perm[ission]s) of %player%");
		Skript.registerExpression(ExprLuckPermsAllGroups.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] (luckperms|lp) [(all|the)] groups");
		Skript.registerExpression(ExprLuckPermsAllGroupsOfPlayer.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] (luckperms|lp) [(all|the)] groups of %player%");
	}
}
