package me.sharpjaws.sharpSK.NBTApi;

import java.security.SecureRandom;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import de.tr7zw.itemnbtapi.NBTItem;
import de.tr7zw.itemnbtapi.NBTList;
import de.tr7zw.itemnbtapi.NBTListCompound;
import de.tr7zw.itemnbtapi.NBTType;
import me.sharpjaws.sharpSK.main;

public class EffAddAttribute extends Effect	{
	private Expression<String> attri;
	private Expression<Number> value;
	private Expression<ItemStack> item;
	private Expression<String> slot;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		attri = (Expression<String>) expr[0];
		value = (Expression<Number>) expr[1];
		item =(Expression<ItemStack>) expr[2];
		slot = (Expression<String>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] add attri[bute] %string% with value %number% to %itemstack% [on] [slot] [%string%]";
	}


	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		SecureRandom r = new SecureRandom();
		return r.nextInt((max - min) + 1) + min;
	}

	@Override
	protected void execute(Event e) {
		Boolean exist = false;

		Attribute ab = null;

		for (Attribute a : Attribute.values()) {

			if (attri.getSingle(e).equalsIgnoreCase(AttributeType.getActualAttribute(a))) {
				exist = true;
				ab = a;
			}
		}
		if (exist == false) {
			main core = (main)Bukkit.getPluginManager().getPlugin("SharpSK");
			core.getLogger().warning("Attribute: "+"\"" +attri.getSingle(e)+"\"" + " does not exist.");
			return;
		}


		NBTItem nbti = new NBTItem(item.getSingle(e));
		NBTList attribute = nbti.getList("AttributeModifiers", NBTType.NBTTagCompound);
		NBTListCompound newattri = attribute.addCompound();
		newattri.setDouble("Amount", value.getSingle(e).doubleValue());
		newattri.setString("AttributeName", AttributeType.getActualAttribute(ab));
		newattri.setString("Name", AttributeType.getActualAttribute(ab));
		newattri.setInteger("Operation", 0);
		newattri.setInteger("UUIDLeast", getRandomNumberInRange(10000, 100000));
		newattri.setInteger("UUIDMost",getRandomNumberInRange(10000, 100000) );
		if (slot != null) {
			boolean exist2 = false;
			if (slot != null) {

				for (NBTSlotType a : NBTSlotType.values()) {
					if (NBTSlotType.getActualAttributeSlot(a).equalsIgnoreCase(slot.getSingle(e))) {
						exist2 = true;	
					}

				}
			}
			if (exist2 == false) {
				main core = (main)Bukkit.getPluginManager().getPlugin("SharpSK");
				core.getLogger().warning("Invalid Slot "+"\"" +slot.getSingle(e)+"\"" + " does not exist.");
				return;
			}

			newattri.setString("Slot", slot.getSingle(e) );
		}
		item.getSingle(e).setItemMeta(nbti.getItem().getItemMeta());
		item.getSingle(e).setData(nbti.getItem().getData());
	}


}
