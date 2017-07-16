package me.sharpjaws.sharpSK.hooks.Towny;


import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.event.DeleteNationEvent;
import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import com.palmergames.bukkit.towny.event.DisallowedPVPEvent;
import com.palmergames.bukkit.towny.event.MobRemovalEvent;
import com.palmergames.bukkit.towny.event.NationAddTownEvent;
import com.palmergames.bukkit.towny.event.NationRemoveTownEvent;
import com.palmergames.bukkit.towny.event.NewNationEvent;
import com.palmergames.bukkit.towny.event.NewTownEvent;
import com.palmergames.bukkit.towny.event.PlayerChangePlotEvent;
import com.palmergames.bukkit.towny.event.PlotClearEvent;
import com.palmergames.bukkit.towny.event.RenameNationEvent;
import com.palmergames.bukkit.towny.event.RenameResidentEvent;
import com.palmergames.bukkit.towny.event.RenameTownEvent;
import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.event.TownBlockSettingsChangedEvent;
import com.palmergames.bukkit.towny.event.TownClaimEvent;
import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.event.TownUnclaimEvent;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownBlockType;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Converter;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.Converters;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class TownyRegistry {

	public static void RegisterTowny() {	
		//Town Types:
			Classes.registerClass(new ClassInfo<TownBlockType>(TownBlockType.class, "townblocktype").name("TownBlockType").parser(new Parser<TownBlockType>() {
				
				@Override
				public String getVariableNamePattern() {
					return ".+";
				}
				@Override
				@Nullable
				public TownBlockType parse(String s, ParseContext cont) {
					try {
						return TownBlockType.valueOf(s.replace(" ", "_").trim().toUpperCase());
					} catch (IllegalArgumentException e) {
						return null;
					}
				}
				@Override
				public boolean canParse(final ParseContext cont) {
					return true;
				}

				@Override
				public String toString(TownBlockType tbt, int i) {
					return tbt.name().replace("_", " ").toUpperCase();
				}

				@Override
				public String toVariableNameString(TownBlockType tbt) {
					return tbt.name().replace("_", " ").toUpperCase();
				}
			}).serializer(new EnumSerializer<TownBlockType>(TownBlockType.class)).user("town ?blocktypes?"));
			
			Converters.registerConverter(TownBlockType.class, String.class, new Converter<TownBlockType, String>() {

				@Override
				@Nullable
				public String convert(TownBlockType tbt) {
					return tbt.name();
				}
			}
		
		);
		
		
		
		//Towny Events:
		Skript.registerEvent("Towny Mob Removal", SimpleEvent.class, MobRemovalEvent.class, "[towny] mob remov([al]|ed])");
		EventValues.registerEventValue(MobRemovalEvent.class, Entity.class,
				new Getter<Entity,MobRemovalEvent>() {
					@Override
					@Nullable
					public Entity get( MobRemovalEvent e) {
						Entity en = e.getEntity();
						return en;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Create", SimpleEvent.class, NewNationEvent.class, "[towny] nation create[d]");
		EventValues.registerEventValue(NewNationEvent.class, String.class,
				new Getter<String,NewNationEvent>() {
					@Override
					@Nullable
					public String get(NewNationEvent e) {
						String s = e.getNation().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Delete", SimpleEvent.class, DeleteNationEvent.class, "[towny] nation delete[d]");
			EventValues.registerEventValue(DeleteNationEvent.class, String.class,
					new Getter<String,DeleteNationEvent>() {
						@Override
						@Nullable
						public String get(DeleteNationEvent e) {
							String s = e.getNationName();
							return s;
						}
					}, 0);
		Skript.registerEvent("Towny Town Delete", SimpleEvent.class, DeleteTownEvent.class, "[towny] town delete[d]");
		EventValues.registerEventValue(DeleteTownEvent.class, String.class,
				new Getter<String,DeleteTownEvent>() {
					@Override
					@Nullable
					public String get(DeleteTownEvent e) {
						String s = e.getTownName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Add Town", SimpleEvent.class, NationAddTownEvent.class, "[towny] nation town add[ed]");
		EventValues.registerEventValue(NationAddTownEvent.class, String.class,
				new Getter<String,NationAddTownEvent>() {
					@Override
					@Nullable
					public String get( NationAddTownEvent e) {
						String s = e.getTown().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Town Remove", SimpleEvent.class, NationRemoveTownEvent.class, "[towny] nation town remove[d]");
		EventValues.registerEventValue(NationRemoveTownEvent.class, String.class,
				new Getter<String,NationRemoveTownEvent>() {
					@Override
					@Nullable
					public String get( NationRemoveTownEvent e) {
						String s = e.getTown().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Town Create", SimpleEvent.class, NewTownEvent.class, "[towny] town create[d]");
		EventValues.registerEventValue(NewTownEvent.class, String.class,
				new Getter<String,NewTownEvent>() {
					@Override
					@Nullable
					public String get( NewTownEvent e) {
						String s = e.getTown().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Resident Rename", SimpleEvent.class, RenameResidentEvent.class, "[towny] resident rename[d]");
		EventValues.registerEventValue(RenameResidentEvent.class, String.class,
				new Getter<String,RenameResidentEvent>() {
					@Override
					@Nullable
					public String get( RenameResidentEvent e) {
						String s = e.getResident().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Rename", SimpleEvent.class, RenameNationEvent.class, "[towny] nation rename[d]");
		EventValues.registerEventValue(RenameNationEvent.class, String.class,
				new Getter<String,RenameNationEvent>() {
					@Override
					@Nullable
					public String get( RenameNationEvent e) {
						String s = e.getNation().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Town Rename", SimpleEvent.class, RenameTownEvent.class, "[towny] town rename[d]");
		EventValues.registerEventValue(RenameTownEvent.class, String.class,
				new Getter<String,RenameTownEvent>() {
					@Override
					@Nullable
					public String get( RenameTownEvent e) {
						String s = e.getOldName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Rename", SimpleEvent.class, RenameNationEvent.class, "[towny] nation rename[d]");
		EventValues.registerEventValue(RenameNationEvent.class, String.class,
				new Getter<String,RenameNationEvent>() {
					@Override
					@Nullable
					public String get( RenameNationEvent e) {
						String s = e.getNation().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Town Claim", SimpleEvent.class, TownClaimEvent.class, "[towny] town claim[ed]");	
		EventValues.registerEventValue(TownClaimEvent.class, String.class,
				new Getter<String,TownClaimEvent>() {
					@Override
					@Nullable
					public String get(TownClaimEvent e) {
						String s = "";
						try {
							s = e.getTownBlock().getTown().getName();
						} catch (NotRegisteredException e1) {
						
						}
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Town Unclaim", SimpleEvent.class, TownUnclaimEvent.class, "[towny] town unclaime[d]");
		EventValues.registerEventValue(TownUnclaimEvent.class, String.class,
				new Getter<String,TownUnclaimEvent>() {
					@Override
					@Nullable
					public String get( TownUnclaimEvent e) {	
						String s = e.getTown().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Resident Add", SimpleEvent.class, TownAddResidentEvent.class, "[towny] resident add[ed]");
		EventValues.registerEventValue(TownAddResidentEvent.class, String.class,
				new Getter<String,TownAddResidentEvent>() {
					@Override
					@Nullable
					public String get( TownAddResidentEvent e) {	
						String s = e.getResident().getName();
						
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Resident Remove", SimpleEvent.class, TownRemoveResidentEvent.class, "[towny] resident remove[d]");
		EventValues.registerEventValue(TownRemoveResidentEvent.class, String.class,
				new Getter<String,TownRemoveResidentEvent>() {
					@Override
					@Nullable
					public String get( TownRemoveResidentEvent e) {	
						String s = e.getResident().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Plot Clear", SimpleEvent.class, PlotClearEvent.class, "[towny] plot clear[ed]");
		Skript.registerEvent("Towny Player Plot Change", SimpleEvent.class, PlayerChangePlotEvent.class, "[towny] plot clear[ed]");
		EventValues.registerEventValue(PlayerChangePlotEvent.class, Player.class,
				new Getter<Player,PlayerChangePlotEvent>() {
					@Override
					@Nullable
					public Player get( PlayerChangePlotEvent e) {	
						Player p = e.getPlayer();
						return p;
					}
				}, 0);
		Skript.registerEvent("Towny PVP Disallow", SimpleEvent.class, DisallowedPVPEvent.class, "[towny] pvp disallow[ed]");
		EventValues.registerEventValue(DisallowedPVPEvent.class, Player.class,
				new Getter<Player,DisallowedPVPEvent>() {
					@Override
					@Nullable
					public Player get( DisallowedPVPEvent e) {	
						Player p = e.getAttacker();
						return p;
					}
				}, 0);
		Skript.registerEvent("Towny Town Block Settings Change", SimpleEvent.class,TownBlockSettingsChangedEvent.class, "[towny] town block settings chang(e|ed)");
		
		
		//Towny Expressions:

		Skript.registerExpression(ExprTownyAllNations.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [towny] (all|the) nations");
		Skript.registerExpression(ExprTownyAllTowns.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [towny] (all|the) towns");
		Skript.registerExpression(ExprTownyTownAtLocation.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [towny] town at %location%");
		Skript.registerExpression(ExprTownyNationBalance.class, Number.class, ExpressionType.SIMPLE, "[sharpsk] [towny] balance of nation %string%");
		Skript.registerExpression(ExprTownyTownBalance.class, Number.class, ExpressionType.SIMPLE, "[sharpsk] [towny] balance of town %string%");
		Skript.registerExpression(ExprTownyTownOfPlayer.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [towny] town of %offlineplayer%");
		Skript.registerExpression(ExprTownyNationOfPlayer.class, String.class, ExpressionType.SIMPLE, "[sharpsk] [towny] nation of %offlineplayer%");
		Skript.registerExpression(ExprTownyTownBlocktypeAtLocation.class, TownBlockType.class, ExpressionType.SIMPLE, "[sharpsk] [towny] [town] (block|plot)type at %location%");
		Skript.registerExpression(ExprTownyEventTown.class, String.class, ExpressionType.SIMPLE, "event-town");	
		Skript.registerExpression(ExprTownyEventNation.class, String.class, ExpressionType.SIMPLE, "event-nation");
			
		//Towny Effects:
		Skript.registerEffect(EffTownyCreateTown.class, "[sharpsk] [towny] create town %string% at %location% [with [bank] balance %-number%] [[and] with mayor %-offlineplayer%] [and residents %-offlineplayers%]");
		Skript.registerEffect(EffTownyDeleteTown.class, "[sharpsk] [towny] delete town %string%");
		Skript.registerEffect(EffTownyRenameTown.class, "[sharpsk] [towny] rename town %string% to %string%");
		Skript.registerEffect(EffTownyKickPlayerFromTown.class, "[sharpsk] [towny] kick %offlineplayer% from town %string%");
		Skript.registerEffect(EffTownyAddPlayerToTown.class, "[sharpsk] [towny] add %offlineplayer% to [town] %string%");
		Skript.registerEffect(EffTownyCreateNation.class, "[sharpsk] [towny] create nation %string% (of|in) town %string% [with [bank] balance %-number%]");
		Skript.registerEffect(EffTownyKickPlayerFromNation.class, "[sharpsk] [towny] kick %offlineplayer% from nation %string%");
		Skript.registerEffect(EffTownyAddPlayerToNation.class, "[towny] add %offlineplayer% to nation %string%");
		
	}
	
}
