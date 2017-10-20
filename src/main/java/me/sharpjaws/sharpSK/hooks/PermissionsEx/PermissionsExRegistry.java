package me.sharpjaws.sharpSK.hooks.PermissionsEx;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class PermissionsExRegistry {

	public static void registerPermissionsEx() {
		Skript.registerEffect(EffRemovePexPerm.class,
				"pex (remove|delete) perm[ission] %string% from %offlineplayers%");
		Skript.registerEffect(EffGiveTimedPexPerm.class,
				"pex add timed perm[ission] %string% to %offlineplayers% (duration|for) %timespan%");
		Skript.registerEffect(EffAddPexPerm.class, "pex add perm[ission] %string% to %offlineplayers%");
		Skript.registerEffect(EffPexAddgroup.class, "pex add group %string% to %offlineplayers%");
		Skript.registerEffect(EffPexRemoveGroupFromPlayer.class,
				"pex (remove|delete) group %string% from %offlineplayers%");
		Skript.registerEffect(EffPexAddPermGroup.class, "pex add perm[ission] %string% to group %string%");
		Skript.registerEffect(EffPexRemovePermGroup.class,
				"pex (remove|delete) perm[ission] %string% from group %string%");
		Skript.registerEffect(EffPexAddAGroup.class,
				"pex create group %string% default %boolean% [with prefix %-string% [and]] [with suffix %-string%]");
		Skript.registerEffect(EffRemoveAGroup.class, "pex (remove|delete) group %string%");
		Skript.registerEffect(EffPexRenameGroup.class, "pex rename group %string% to %string%");
		Skript.registerExpression(ExprPexGroupRank.class, Number.class, ExpressionType.SIMPLE,
				"rank of [the] group %string%");
		Skript.registerExpression(ExprPexGroupOf.class, String.class, ExpressionType.COMBINED,
				"[pex] groups of %offlineplayer%");

		Skript.registerExpression(ExprPexGroupRankLadder.class, String.class, ExpressionType.SIMPLE,
				"rank[]ladder of [the] group %string%");
	}
}
