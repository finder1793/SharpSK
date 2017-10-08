package me.sharpjaws.sharpSK.NBTApi;

import org.bukkit.attribute.Attribute;

public enum AttributeType {
MAXHEALTH,FOLLOWRANGE,KNOCKBACK,MOVEMENTSPEED,ATTACKDAMAGE,ARMOR,ARMORTOUGHNESS,LUCK,ATTACKSPEED;
	
	public final static String getActualAttribute(Attribute a){
		switch(a) {
		case GENERIC_MAX_HEALTH:
			return "generic.maxHealth";
		case GENERIC_FOLLOW_RANGE :
			return "generic.followRange";
		case GENERIC_KNOCKBACK_RESISTANCE:
			return "generic.knockbackResistance";
		case GENERIC_MOVEMENT_SPEED:
			return "generic.movementSpeed";
		case GENERIC_ATTACK_DAMAGE:
			return "generic.attackDamage";
		case GENERIC_ARMOR:
			return "generic.armor";
		case GENERIC_ARMOR_TOUGHNESS:
			return "generic.armorToughness";
		case GENERIC_ATTACK_SPEED:
			return "generic.attackSpeed";
		case GENERIC_LUCK:
			return "generic.luck";
		default:
		return null;
		}
	}
	public final static String getActualAttributeType(AttributeType a){
	switch(a) {
	case MAXHEALTH:
		return "generic.maxHealth";
	case FOLLOWRANGE:
		return "generic.followRange";
	case KNOCKBACK:
		return "generic.knockbackResistance";
	case MOVEMENTSPEED:
		return "generic.movementSpeed";
	case ATTACKDAMAGE:
		return "generic.attackDamage";
	case ARMOR:
		return "generic.armor";
	case ARMORTOUGHNESS:
		return "generic.armorToughness";
	case ATTACKSPEED:
		return "generic.attackSpeed";
	case LUCK:
		return "generic.luck";
	default:
	return null;
	}
}
}
