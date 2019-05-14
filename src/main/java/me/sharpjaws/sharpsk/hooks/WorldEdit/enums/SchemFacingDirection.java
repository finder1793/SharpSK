package me.sharpjaws.sharpsk.hooks.WorldEdit.enums;

public enum SchemFacingDirection {
	NORTH, EAST, SOUTH, WEST, INVALID;

	public final static Integer getDegree(SchemFacingDirection dir) {
		switch (dir) {
		case NORTH:
			return 0;

		case EAST:
			return 90;

		case SOUTH:
			return 180;

		case WEST:
			return 270;

		default:
			return -1;

		}

	}

	public final static SchemFacingDirection getFacingDir(Integer degree) {
		switch (degree) {
		case 0:
		case 360:
			return NORTH;

		case 90:
			return EAST;

		case 180:
			return SOUTH;

		case 270:
			return WEST;

		case -1:
			return INVALID;

		}
		return null;
	}
}
