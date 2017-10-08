package me.sharpjaws.sharpSK.NBTApi;

public enum NBTSlotType {
MAINHAND,OFFHAND,FEETS,LEGS,CHEST,HEAD;
public final static String getActualAttributeSlot(NBTSlotType a){
	switch(a) {
	case MAINHAND:
		return "mainhand";
	case OFFHAND :
		return "offhand";
	case FEETS:
		return "feet";
	case LEGS:
		return "legs";
	case CHEST:
		return "chest";
	case HEAD:
		return "head";
	default:
	return null;
	}
}

}
