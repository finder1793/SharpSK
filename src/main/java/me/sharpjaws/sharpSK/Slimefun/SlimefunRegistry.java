package me.sharpjaws.sharpSK.Slimefun;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.mrCookieSlime.Slimefun.Events.ResearchUnlockEvent;

public class SlimefunRegistry {
	public static void registerSlimefun() {
		Skript.registerEvent("On slimefun research", SimpleEvent.class, ResearchUnlockEvent.class,
				"[(slimefun|sf)] research [unlock]");
		EventValues.registerEventValue(ResearchUnlockEvent.class, Player.class,
				new Getter<Player, ResearchUnlockEvent>() {
					@Override
					@Nullable
					public Player get(ResearchUnlockEvent e) {
						Player p = e.getPlayer();
						return p;
					}
				}, 0);
		Skript.registerExpression(ExprSlimefunEvtResearch.class, String.class, ExpressionType.SIMPLE, "event-research");

		Skript.registerEffect(EffSlimefunUnlockResearch.class,
				"[sharpsk] [(slimefun|sf)] unlock research %string% for %player%");
		Skript.registerExpression(ExprSlimefunAllResearches.class, String.class, ExpressionType.SIMPLE,
				"[sharpsk] [(slimefun|sf)] all [(of|the)] researches");
	}

}
