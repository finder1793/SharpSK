package me.sharpjaws.sharpSK.hooks.CoreProtect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.inventory.ItemStack;

public class CorePRegistry {

	public static void registerCoreP() {
		Skript.registerEffect(EffCoreRollback.class,
				"[(coreprotect|cp)] (rollback|revert|undo) [(0¦removal|1¦placement|2¦interaction)] changes [made by %-offlineplayers%] at [the] %location% in radius %number% [back] to %timespan% [ago] [and] [exclude %-itemstacks%]");
		Skript.registerEffect(EffCoreRestore.class,
				"[(coreprotect|cp)] restore [(0¦removal|1¦placement|2¦interaction)] [rollback] changes [made by %-offlineplayers%] at [the] %location% in radius %number% from %timespan% [ago] [and] [exclude %-itemstacks%]");
		Skript.registerEffect(EffCoreLog.class,
				"[(coreprotect|cp)] log block [(0¦removal|1¦placement|2¦interaction)] at %location% (for|by) %offlineplayer%");
		Skript.registerExpression(ExprCoreLookupData.class, ItemStack.class, ExpressionType.SIMPLE,
				"[(coreprotect|cp)] [(0¦removal|1¦placement|2¦interaction)] lookup data [from by %-offlineplayers%] from %location% in radius %number% from %timespan% ago [excluding %-itemstacks%]");
	}

}