package me.sharpjaws.sharpSK.hooks.uCars;

import com.useful.uCarsAPI.uCarCrashEvent;
import com.useful.uCarsAPI.uCarRespawnEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;

public class uCarsRegistry {
	public static void registeruCars() {
		Skript.registerEvent("uCars Car Respawn", SimpleEvent.class, uCarRespawnEvent.class,
				"[on] [ucars] car respawn");
		Skript.registerEvent("uCars Car crash", SimpleEvent.class, uCarCrashEvent.class,
				" [on] [ucars] car crash");
		Skript.registerCondition(ConduCarsIsInCar.class, "[ucars] %player% is in [a] car");
		Skript.registerCondition(ConduCarsIsInCar.class, "[ucars] %player% is not in [a] car");
}}
