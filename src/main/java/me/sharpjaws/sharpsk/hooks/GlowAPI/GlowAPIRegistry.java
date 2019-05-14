package me.sharpjaws.sharpsk.hooks.GlowAPI;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.inventivetalent.glow.GlowAPI;

import javax.annotation.Nullable;

public class GlowAPIRegistry {
	public static void registerGlowAPI() {
		Classes.registerClass(
				new ClassInfo<GlowAPI.Color>(GlowAPI.Color.class, "glowapicolor").parser(new Parser<GlowAPI.Color>() {
					@Override
					public String getVariableNamePattern() {
						return ".+";
					}

					@Override
					@Nullable
					public GlowAPI.Color parse(String s, ParseContext cont) {
						try {
							return GlowAPI.Color.valueOf(s.replace(" ", "_").trim().toUpperCase());
						} catch (IllegalArgumentException e) {
							return null;
						}
					}

					@Override
					public String toString(GlowAPI.Color eff, int i) {
						return eff.name().replace("_", " ").toUpperCase();
					}

					@Override
					public String toVariableNameString(GlowAPI.Color eff) {
						return eff.name().replace("_", " ").toUpperCase();
					}

				}));
		Skript.registerExpression(ExprGlowingColorOf.class, GlowAPI.Color.class, ExpressionType.SIMPLE,
				"glow[ing] color of %entity%");
	}
}
