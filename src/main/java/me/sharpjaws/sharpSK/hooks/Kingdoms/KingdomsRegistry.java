package me.sharpjaws.sharpSK.hooks.Kingdoms;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.kingdoms.events.KingdomCreateEvent;
import org.kingdoms.events.KingdomDeleteEvent;
import org.kingdoms.events.KingdomMemberJoinEvent;
import org.kingdoms.events.KingdomMemberLeaveEvent;
import org.kingdoms.events.KingdomPlayerLostEvent;
import org.kingdoms.events.KingdomPlayerWonEvent;
import org.kingdoms.events.KingdomResourcePointChangeEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class KingdomsRegistry {

	public static void RegisterKingdoms() {
		//Kingdoms Events
		Skript.registerEvent("Kingdoms Kingdom Create" , SimpleEvent.class, KingdomCreateEvent.class, "[kingdoms] kingdom create[d]");
		EventValues.registerEventValue(KingdomCreateEvent.class, String.class,
				new Getter<String, KingdomCreateEvent>() {
					@Override
					@Nullable
					public String get(KingdomCreateEvent e) {
						String k = e.getKingdom().getKingdomName();
						return k;
					}
				}, 0);
		Skript.registerEvent("Kingdoms Kingdom Delete" , SimpleEvent.class, KingdomDeleteEvent.class, "[kingdoms] kingdom delete[d]");
		EventValues.registerEventValue( KingdomDeleteEvent.class,String.class,
				new Getter<String, KingdomDeleteEvent>() {
					@Override
					@Nullable
					public String get( KingdomDeleteEvent e) {
						String k = e.getKingdom().getKingdomName();
						return k;
					}
				}, 0);
		
		Skript.registerEvent("Kingdoms Kingdom Member Join" , SimpleEvent.class, KingdomMemberJoinEvent.class, "[kingdoms] kingdom member join[ed]");
		EventValues.registerEventValue(KingdomMemberJoinEvent.class, String.class,
				new Getter<String, KingdomMemberJoinEvent>() {
					@Override
					@Nullable
					public String get(KingdomMemberJoinEvent e) {
						String k = e.getKingdom().getKingdomName();
						return k;
					}
				}, 0);
		EventValues.registerEventValue(KingdomMemberJoinEvent.class, Player.class,
				new Getter<Player, KingdomMemberJoinEvent>() {
					@Override
					@Nullable
					public Player get(KingdomMemberJoinEvent e) {
						Player p = e.getKp().getKingdomPlayer().getPlayer();
						return p;
					}
				}, 0);
		Skript.registerEvent("Kingdoms Kingdom Member Leave" , SimpleEvent.class, KingdomMemberLeaveEvent.class, "[kingdoms] kingdom member leave[d]");
		EventValues.registerEventValue(KingdomMemberLeaveEvent.class, String.class,
				new Getter<String, KingdomMemberLeaveEvent>() {
					@Override
					@Nullable
					public String get(KingdomMemberLeaveEvent e) {
						String k = e.getKingdomName();
						return k;
					}
				}, 0);
		EventValues.registerEventValue(KingdomMemberLeaveEvent.class, Player.class,
				new Getter<Player, KingdomMemberLeaveEvent>() {
					@Override
					@Nullable
					public Player get(KingdomMemberLeaveEvent e) {
						Player p = e.getKp().getKingdomPlayer().getPlayer();
						return p;
					}
				}, 0);
		Skript.registerEvent("Kingdoms Player Lose" , SimpleEvent.class, KingdomPlayerLostEvent.class, "[kingdoms] champion [player] (lose|defeat)");
		EventValues.registerEventValue( KingdomPlayerLostEvent.class, String.class,
				new Getter<String,  KingdomPlayerLostEvent>() {
					@Override
					@Nullable
					public String get( KingdomPlayerLostEvent e) {
						String k = e.getDefender().getKingdomName();
						return k;
					}
				}, 0);
		Skript.registerEvent("Kingdoms Player Win" , SimpleEvent.class, KingdomPlayerWonEvent.class, "[kingdoms] champion [player] (win|victory)");
		EventValues.registerEventValue( KingdomPlayerWonEvent.class, String.class,
				new Getter<String,  KingdomPlayerWonEvent>() {
					@Override
					@Nullable
					public String get( KingdomPlayerWonEvent e) {
						String k = e.getLostKingdom().getKingdomName();
						return k;
					}
				}, 0);
		
		Skript.registerEvent("Kingdoms Resource Point Change" , SimpleEvent.class, KingdomResourcePointChangeEvent.class, "[kingdoms] [resource] point[s] change[d]");
		EventValues.registerEventValue(KingdomResourcePointChangeEvent.class, String.class,
				new Getter<String, KingdomResourcePointChangeEvent>() {
					@Override
					@Nullable
					public String get(KingdomResourcePointChangeEvent e) {
						String k = e.getKingdom().getKingdomName();
						return k;
					}
				}, 0);
		
		//Kingdoms Expressions
		Skript.registerExpression(ExprKingdomsKingOfKingdom.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] king of [kingdom] %string%");
		Skript.registerExpression(ExprKingdomsMembersOfKingdom.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] members (of|in) kingdom %string%");
		Skript.registerExpression(ExprKingdomsEnemiesOfKingdom.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] enemies of kingdom %string%");
		Skript.registerExpression(ExprKingdomsAlliesOfKingdom.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] allies of kingdom %string%");
		Skript.registerExpression(ExprKingdomsMaxMembersInKingdom.class, Number.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] (max[imum]|amount of) members (allowed in|limit of) kingdom %string%");
		Skript.registerExpression(ExprKingdomsRPOfKingdom.class, Number.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] (RP|resource[ ]points) of [kingdom] %string%");
		Skript.registerExpression(ExprKingdomsMightOfKingdom.class, Number.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] might of [kingdom] %string%");
		Skript.registerExpression(ExprKingdomsAllKingdoms.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] (all|the) kingdoms");
		Skript.registerExpression(ExprKingdomsLoreOfKingdom.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] lore of kingdom %string%");
		Skript.registerExpression(ExprKingdomsNexusLocOfKingdom.class, Location.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] nexus loc[ation] of kingdom %string%");
		Skript.registerExpression(ExprKingdomsHomeLocOfKingdom.class, Location.class, ExpressionType.SIMPLE, "[sharpsk] [kingdoms] home loc[ation] of kingdom %string%");
	
		
		
		//Kingdoms Effects:
		Skript.registerEffect(EffKingdomsKingdomDelete.class, "[sharpsk] [kingdoms] remove kingdom %string%");
		Skript.registerEffect(EffKingdomsKingdomMakeEnemy.class, "[sharpsk] [kingdoms] make kingdom %string% enemy of kingdom %string%");
		Skript.registerEffect(EffKingdomsKingdomMakeAlly.class, "[sharpsk] [kingdoms] make kingdom %string% ally of kingdom %string%");
		Skript.registerEffect(EffKingdomsKingdomCreate.class, "[sharpsk] [kingdoms] create kingdom %string% [with] king %player%");
		Skript.registerEffect(EffKingdomsKingdomAddMember.class, "[sharpsk] [kingdoms] add member %offlineplayer% to kingdom %string%");
		Skript.registerEffect(EffKingdomsKingdomGiveShield.class, "[sharpsk] [kingdoms] give [a] shield to kingdom %string%");
		Skript.registerEffect(EffKingdomsKingdomRemoveShield.class, "[sharpsk] [kingdoms] remove shield from kingdom %string%");
		
		
		//Kingdoms Conditions:
		Skript.registerCondition(CondKingdomsKingdomIsOnline.class, "[sharpsk] [kingdoms] kingdom %string% (0¦is|1¦is (not)) online");
		Skript.registerCondition(CondKingdomsKingdomHasShield.class, "[sharpsk] [kingdoms] kingdom %string% (0¦has|1¦doesn[']t (have|has)) [a] shield");
	}
	
}
