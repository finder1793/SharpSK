package me.sharpjaws.sharpSK.hooks.Towny;


import javax.annotation.Nullable;

import org.bukkit.entity.Entity;

import com.palmergames.bukkit.towny.event.DeleteNationEvent;
import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import com.palmergames.bukkit.towny.event.MobRemovalEvent;
import com.palmergames.bukkit.towny.event.NationAddTownEvent;
import com.palmergames.bukkit.towny.event.NationRemoveTownEvent;
import com.palmergames.bukkit.towny.event.NewNationEvent;
import com.palmergames.bukkit.towny.event.NewTownEvent;
import com.palmergames.bukkit.towny.event.RenameNationEvent;
import com.palmergames.bukkit.towny.event.RenameTownEvent;
import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.event.TownClaimEvent;
import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.event.TownUnclaimEvent;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class TownyRegistry {

	public static void RegisterTowny() {
		
		//Towny Events:
		Skript.registerEvent("Towny Mob Removal", SimpleEvent.class, MobRemovalEvent.class, "[towny] mob remov([al]|ed])");
		EventValues.registerEventValue(MobRemovalEvent.class, Entity.class,
				new Getter<Entity,   MobRemovalEvent>() {
					@Override
					@Nullable
					public Entity get( MobRemovalEvent e) {
						Entity en = e.getEntity();
						return en;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Create", SimpleEvent.class, NewNationEvent.class, "[towny] nation create[d]");
		EventValues.registerEventValue(NewNationEvent.class, String.class,
				new Getter<String,   NewNationEvent>() {
					@Override
					@Nullable
					public String get( NewNationEvent e) {
						String s = e.getNation().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Delete", SimpleEvent.class, DeleteNationEvent.class, "[towny] nation delete[d]");
			EventValues.registerEventValue(DeleteNationEvent.class, String.class,
					new Getter<String, DeleteNationEvent>() {
						@Override
						@Nullable
						public String get(DeleteNationEvent e) {
							String s = e.getNationName();
							return s;
						}
					}, 0);
		Skript.registerEvent("Towny Town Delete", SimpleEvent.class, DeleteTownEvent.class, "[towny] town delete[d]");
		EventValues.registerEventValue(DeleteTownEvent.class, String.class,
				new Getter<String, DeleteTownEvent>() {
					@Override
					@Nullable
					public String get(DeleteTownEvent e) {
						String s = e.getTownName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Add Town", SimpleEvent.class, NationAddTownEvent.class, "[towny] nation town add[ed]");
		EventValues.registerEventValue(NationAddTownEvent.class, String.class,
				new Getter<String,  NationAddTownEvent>() {
					@Override
					@Nullable
					public String get( NationAddTownEvent e) {
						String s = e.getTown().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Town Remove", SimpleEvent.class, NationRemoveTownEvent.class, "[towny] nation town remove[d]");
		EventValues.registerEventValue(NationRemoveTownEvent.class, String.class,
				new Getter<String,  NationRemoveTownEvent>() {
					@Override
					@Nullable
					public String get( NationRemoveTownEvent e) {
						String s = e.getTown().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Town Create", SimpleEvent.class, NewTownEvent.class, "[towny] town create[d]");
		EventValues.registerEventValue(NewNationEvent.class, String.class,
				new Getter<String,   NewNationEvent>() {
					@Override
					@Nullable
					public String get( NewNationEvent e) {
						String s = e.getNation().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Nation Rename", SimpleEvent.class, RenameNationEvent.class, "[towny] nation rename[d]");
		EventValues.registerEventValue(NewNationEvent.class, String.class,
				new Getter<String,   NewNationEvent>() {
					@Override
					@Nullable
					public String get( NewNationEvent e) {
						String s = e.getNation().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Town Rename", SimpleEvent.class, RenameTownEvent.class, "[towny] town rename[d]");
		EventValues.registerEventValue(RenameTownEvent.class, String.class,
				new Getter<String,  RenameTownEvent>() {
					@Override
					@Nullable
					public String get( RenameTownEvent e) {
						String s = e.getTown().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Town Claim", SimpleEvent.class, TownClaimEvent.class, "[towny] town claim[ed]");	
		EventValues.registerEventValue(TownClaimEvent.class, String.class,
				new Getter<String,   TownClaimEvent>() {
					@Override
					@Nullable
					public String get(  TownClaimEvent e) {
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
				new Getter<String,   TownUnclaimEvent>() {
					@Override
					@Nullable
					public String get( TownUnclaimEvent e) {	
						String s = e.getTown().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Resident Add", SimpleEvent.class, TownAddResidentEvent.class, "[towny] resident add[ed]");
		EventValues.registerEventValue(TownAddResidentEvent.class, String.class,
				new Getter<String,   TownAddResidentEvent>() {
					@Override
					@Nullable
					public String get( TownAddResidentEvent e) {	
						String s = e.getResident().getName();
						return s;
					}
				}, 0);
		Skript.registerEvent("Towny Resident Remove", SimpleEvent.class, TownRemoveResidentEvent.class, "[towny] resident remove[d]");
		EventValues.registerEventValue(TownRemoveResidentEvent.class, String.class,
				new Getter<String,  TownRemoveResidentEvent>() {
					@Override
					@Nullable
					public String get( TownRemoveResidentEvent e) {	
						String s = e.getResident().getName();
						return s;
					}
				}, 0);
		
		
		//Towny Effects:
		
		
		
		//Towny Expressions:

		Skript.registerExpression(ExprTownyAllNations.class, String.class, ExpressionType.SIMPLE, "[towny] (all|the) nations");
		Skript.registerExpression(ExprTownyAllTowns.class, String.class, ExpressionType.SIMPLE, "[towny] (all|the) towns");
		
	}
	
}
