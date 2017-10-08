package me.sharpjaws.sharpSK.NBTApi;

import ch.njol.skript.Skript;

public class ItemNBTAPIRegistry {
	public static void registerItemNBTAPI() {
		Skript.registerEffect(EffAddAttribute.class, "[sharpsk] add attri[bute] %string% with value %number% to %itemstack% [on] [slot] [%-string%]");
	}
}
