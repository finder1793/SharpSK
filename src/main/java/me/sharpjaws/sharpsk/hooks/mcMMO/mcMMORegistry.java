package me.sharpjaws.sharpsk.hooks.mcMMO;

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
import com.gmail.nossr50.datatypes.skills.AbilityType;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.chat.McMMOPartyChatEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.gmail.nossr50.events.party.McMMOPartyLevelUpEvent;
import com.gmail.nossr50.events.party.McMMOPartyXpGainEvent;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityActivateEvent;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityDeactivateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class mcMMORegistry {

    public static void registermcMMO() {
        Boolean failed = false;

        try {
            Classes.registerClass(new ClassInfo<>(SkillType.class, "skilltype").name("SkillType")
                    .parser(new Parser<SkillType>() {

                        @Override
                        public String getVariableNamePattern() {
                            return ".+";
                        }

                        @Override
                        @Nullable
                        public SkillType parse(String s, ParseContext cont) {
                            try {
                                return SkillType.valueOf(s.replace(" ", "_").trim().toUpperCase());
                            } catch (IllegalArgumentException e) {
                                return null;
                            }
                        }

                        @Override
                        public boolean canParse(final ParseContext cont) {
                            return true;
                        }

                        @Override
                        public String toString(SkillType skilltype, int i) {
                            return skilltype.name().replace("_", " ").toUpperCase();
                        }

                        @Override
                        public String toVariableNameString(SkillType skilltype) {
                            return skilltype.name().replace("_", " ").toUpperCase();
                        }
                    }).serializer(new EnumSerializer<>(SkillType.class)).user("skill ?types?"));

            Converters.registerConverter(SkillType.class, String.class, new Converter<SkillType, String>() {

                @Override
                @Nullable
                public String convert(SkillType skill) {
                    return skill.getName();
                }
            });
            Converters.registerConverter(String.class, SkillType.class, new Converter<String, SkillType>() {

                @Override
                @Nullable
                public SkillType convert(String skillname) {
                    try {
                        return SkillType.valueOf(skillname.toUpperCase());
                    } catch (IllegalArgumentException ex) {
                        return null;
                    }

                }
            });

        } catch (IllegalArgumentException ex) {
            failed = true;
            // ExperienceAPI Stuff
            if (Bukkit.getPluginManager().getPlugin("ExertSK") != null
                    && Bukkit.getPluginManager().getPlugin("Umbaska") == null
                    && Bukkit.getPluginManager().getPlugin("SkRambled") == null) {
                Skript.registerExpression(ExprmcMMOSkillLevel.class, Number.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] %mcmmoskill% level of %offlineplayer%");
                Skript.registerExpression(ExprmcMMOSkillLevelXP.class, Number.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] %mcmmoskill% [e]xp[erience] of %offlineplayer%");
                Skript.registerExpression(ExprmcMMOSkillLevelReXP.class, Integer.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] remaining %mcmmoskill% [e]xp[erience] of %offlineplayer%");
            } else if (Bukkit.getPluginManager().getPlugin("ExertSK") != null
                    && Bukkit.getPluginManager().getPlugin("Umbaska") == null
                    && Bukkit.getPluginManager().getPlugin("SkRambled") != null) {
                Skript.registerExpression(ExprmcMMOSkillLevel.class, Number.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] %mcmmoskill% level of %offlineplayer%");
                Skript.registerExpression(ExprmcMMOSkillLevelXP.class, Number.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] %mcmmoskill% [e]xp[erience] of %offlineplayer%");
                Skript.registerExpression(ExprmcMMOSkillLevelReXP.class, Integer.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] remaining %mcmmoskill% [e]xp[erience] of %offlineplayer%");
            } else if (Bukkit.getPluginManager().getPlugin("ExertSK") != null
                    && Bukkit.getPluginManager().getPlugin("SkRambled") != null
                    && Bukkit.getPluginManager().getPlugin("Umbaska") != null) {
                Skript.registerExpression(ExprmcMMOSkillLevel.class, Number.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] %mcmmoskill% level of %offlineplayer%");
                Skript.registerExpression(ExprmcMMOSkillLevelXP.class, Number.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] %mcmmoskill% [e]xp[erience] of %offlineplayer%");
                Skript.registerExpression(ExprmcMMOSkillLevelReXP.class, Integer.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] remaining %mcmmoskill% [e]xp[erience] of %offlineplayer%");
            } else if (Bukkit.getPluginManager().getPlugin("ExertSK") != null
                    && Bukkit.getPluginManager().getPlugin("SkRambled") == null
                    && Bukkit.getPluginManager().getPlugin("Umbaska") != null) {
                Skript.registerExpression(ExprmcMMOSkillLevel.class, Number.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] %mcmmoskill% level of %offlineplayer%");
                Skript.registerExpression(ExprmcMMOSkillLevelXP.class, Number.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] %mcmmoskill% [e]xp[erience] of %offlineplayer%");
                Skript.registerExpression(ExprmcMMOSkillLevelReXP.class, Integer.class, ExpressionType.SIMPLE,
                        "[sharpsk] [mcmmo] remaining %mcmmoskill% [e]xp[erience] of %offlineplayer%");
            }
        }

        try {
            Classes.registerClass(new ClassInfo<>(AbilityType.class, "abilitytype").name("AbilityType")
                    .parser(new Parser<AbilityType>() {

                        @Override
                        public String getVariableNamePattern() {
                            return ".+";
                        }

                        @Override
                        @Nullable
                        public AbilityType parse(String s, ParseContext cont) {
                            try {
                                return AbilityType.valueOf(s.replace(" ", "_").trim().toUpperCase());
                            } catch (IllegalArgumentException e) {
                                return null;
                            }
                        }

                        @Override
                        public boolean canParse(final ParseContext cont) {
                            return true;
                        }

                        @Override
                        public String toString(AbilityType abilitytype, int i) {
                            return abilitytype.name().replace("_", " ").toUpperCase();
                        }

                        @Override
                        public String toVariableNameString(AbilityType abilitytype) {
                            return abilitytype.name().replace("_", " ").toUpperCase();
                        }

                    }).serializer(new EnumSerializer<>(AbilityType.class)).user("ability ?types?"));

        } catch (IllegalArgumentException ignored) {

        }

        Converters.registerConverter(AbilityType.class, String.class, new Converter<AbilityType, String>() {

            @Override
            @Nullable
            public String convert(AbilityType ab) {
                return ab.getName();
            }
        });
        Converters.registerConverter(String.class, AbilityType.class, new Converter<String, AbilityType>() {

            @Override
            @Nullable
            public AbilityType convert(String skillname) {
                try {
                    return AbilityType.valueOf(skillname.toUpperCase());
                } catch (IllegalArgumentException ex) {
                    return null;
                }

            }
        });

        if (failed.equals(false)) {
            Skript.registerExpression(ExprmcMMOSkillLevel.class, Number.class, ExpressionType.SIMPLE,
                    "[sharpsk] [mcmmo] %skilltype% level of %offlineplayer%");
            Skript.registerExpression(ExprmcMMOSkillLevelReXP.class, Integer.class, ExpressionType.SIMPLE,
                    "[sharpsk] [mcmmo] remaining %skilltype% [e]xp[erience] of %offlineplayer%");
            Skript.registerExpression(ExprmcMMOSkillLevelXP.class, Number.class, ExpressionType.SIMPLE,
                    "[sharpsk] [mcmmo] %skilltype% [e]xp[erience] of %offlineplayer%");
        }
        Skript.registerEvent("McMMO Skill Level Up", SimpleEvent.class, McMMOPlayerLevelUpEvent.class,
                "[sharpsk] [mcmmo] skill level[ ]up");
        EventValues.registerEventValue(McMMOPlayerLevelUpEvent.class, Player.class,
                new Getter<Player, McMMOPlayerLevelUpEvent>() {
                    @Override
                    @Nullable
                    public Player get(McMMOPlayerLevelUpEvent e) {
                        return e.getPlayer();
                    }
                }, 0);
        EventValues.registerEventValue(McMMOPlayerLevelUpEvent.class, Number.class,
                new Getter<Number, McMMOPlayerLevelUpEvent>() {
                    @Override
                    @Nullable
                    public Number get(McMMOPlayerLevelUpEvent e) {
                        return e.getLevelsGained();
                    }
                }, 0);
        Skript.registerEvent("McMMO Party Level Up", SimpleEvent.class, McMMOPartyLevelUpEvent.class,
                "[sharpsk] [mcmmo] party level[ ]up");
        EventValues.registerEventValue(McMMOPartyLevelUpEvent.class, Number.class,
                new Getter<Number, McMMOPartyLevelUpEvent>() {
                    @Override
                    @Nullable
                    public Number get(McMMOPartyLevelUpEvent e) {
                        return e.getLevelsChanged();
                    }
                }, 0);
        Skript.registerEvent("McMMO Ability Activate", SimpleEvent.class, McMMOPlayerAbilityActivateEvent.class,
                "[sharpsk] [mcmmo] ability activate");
        EventValues.registerEventValue(McMMOPlayerAbilityActivateEvent.class, Player.class,
                new Getter<Player, McMMOPlayerAbilityActivateEvent>() {
                    @Override
                    @Nullable
                    public Player get(McMMOPlayerAbilityActivateEvent e) {
                        return e.getPlayer();
                    }
                }, 0);
        Skript.registerEvent("McMMO Ability Deactivate", SimpleEvent.class, McMMOPlayerAbilityDeactivateEvent.class,
                "[sharpsk] [mcmmo] ability deactivate");
        EventValues.registerEventValue(McMMOPlayerAbilityDeactivateEvent.class, Player.class,
                new Getter<Player, McMMOPlayerAbilityDeactivateEvent>() {
                    @Override
                    @Nullable
                    public Player get(McMMOPlayerAbilityDeactivateEvent e) {
                        return e.getPlayer();
                    }
                }, 0);

        Skript.registerEvent("McMMO Party chat", SimpleEvent.class, McMMOPartyChatEvent.class,
                "[sharpsk] [mcmmo] party chat");
        EventValues.registerEventValue(McMMOPartyChatEvent.class, Player.class,
                new Getter<Player, McMMOPartyChatEvent>() {
                    @Override
                    @Nullable
                    public Player get(McMMOPartyChatEvent e) {
                        return Bukkit.getPlayer(e.getSender());
                    }
                }, 0);
        EventValues.registerEventValue(McMMOPartyChatEvent.class, String.class,
                new Getter<String, McMMOPartyChatEvent>() {
                    @Override
                    @Nullable
                    public String get(McMMOPartyChatEvent e) {
                        return e.getMessage();
                    }
                }, 0);

        Skript.registerEvent("McMMO Player Exp Gain", SimpleEvent.class, McMMOPlayerXpGainEvent.class,
                "[sharpsk] [mcmmo] player exp[erience] gain");
        EventValues.registerEventValue(McMMOPlayerXpGainEvent.class, Player.class,
                new Getter<Player, McMMOPlayerXpGainEvent>() {
                    @Override
                    @Nullable
                    public Player get(McMMOPlayerXpGainEvent e) {
                        return e.getPlayer();
                    }
                }, 0);
        EventValues.registerEventValue(McMMOPartyXpGainEvent.class, Number.class,
                new Getter<Number, McMMOPartyXpGainEvent>() {
                    @Override
                    @Nullable
                    public Number get(McMMOPartyXpGainEvent e) {
                        return e.getRawXpGained();
                    }
                }, 0);

        Skript.registerEvent("McMMO Party Exp Gain", SimpleEvent.class, McMMOPartyXpGainEvent.class,
                "[sharpsk] [mcmmo] party exp[erience] gain");
        EventValues.registerEventValue(McMMOPartyXpGainEvent.class, Number.class,
                new Getter<Number, McMMOPartyXpGainEvent>() {
                    @Override
                    @Nullable
                    public Number get(McMMOPartyXpGainEvent e) {
                        return e.getRawXpGained();
                    }
                }, 0);

        Skript.registerExpression(ExprEvtParty.class, String.class, ExpressionType.SIMPLE, "event-[mcmmo]party");
        Skript.registerExpression(ExprEvtAbilityType.class, AbilityType.class, ExpressionType.SIMPLE,
                "event-abilitytype");
        Skript.registerExpression(ExprEvtSkillType.class, SkillType.class, ExpressionType.SIMPLE, "event-skilltype");

        // PartyAPI Stuff
        Skript.registerExpression(ExprmcMMOParty.class, String.class, ExpressionType.SIMPLE,
                "%player%'s [mcmmo] party");
        Skript.registerExpression(ExprmcMMOPartyLeader.class, String.class, ExpressionType.SIMPLE,
                "[mcmmo] [the] [party][]leader of [party] %string%");
        Skript.registerExpression(ExprmcMMOAllParties.class, String.class, ExpressionType.SIMPLE,
                "[mcmmo] [(the|all)] [of] [the] part(ies|y's)");
        Skript.registerExpression(ExprmcMMOAllPartyMembers.class, String.class, ExpressionType.SIMPLE,
                "[mcmmo] [(the|all)] members of party %string%");
        Skript.registerCondition(CondmcMMOSameParty.class, "%player% is [in] [the] same party as %player%");
        Skript.registerCondition(CondmcMMOPartyHasAlly.class, "[mcmmo] party %string% has [a] ally");
        Skript.registerExpression(ExprmcMMOPartyAllyName.class, String.class, ExpressionType.SIMPLE,
                "[mcmmo] [party] ally of party %string%");
        Skript.registerEffect(EffmcMMORemoveFromParty.class,
                "[mcmmo] (kick|remove) %player% from (its [own]|own) party");
        Skript.registerExpression(ExprmcMMOPartyEXP.class, Number.class, ExpressionType.SIMPLE,
                "[sharpsk] [mcmmo] exp[erience] of party %string%");
        Skript.registerExpression(ExprmcMMOPartyLevel.class, Number.class, ExpressionType.SIMPLE,
                "[sharpsk] [mcmmo] level of party %string%");
        Skript.registerEffect(EffmcMMOUnlockParty.class, "[mcmmo] unlock party %string%");
        Skript.registerEffect(EffmcMMOLockParty.class, "[mcmmo] lock party %string%");

        // SkillAPI Stuff
        Skript.registerExpression(ExprmcMMOAllSkillTypes.class, SkillType.class, ExpressionType.COMBINED,
                "[(all|the)] (skilltypes|1¦mcmmoskills)");

        // AbilityAPI Stuff
        Skript.registerEffect(EffmcMMOResetCooldowns.class, "[mcmmo] reset all [ability] cooldowns of %player%");
        Skript.registerEffect(EffmcMMOResetAbilityCooldown.class,
                "[mcmmo] reset cooldown of %abilitytype% of %player%");
        Skript.registerCondition(CondmcMMOAbilityEnabled.class, "[mcmmo] %offlineplayer%['s] %abilitytype% is enabled");
        Skript.registerCondition(CondmcMMOAbilityNotEnabled.class,
                "[mcmmo] %offlineplayer%['s] %abilitytype% is not enabled");
        Skript.registerExpression(ExprmcMMOAllAbilityTypes.class, AbilityType.class, ExpressionType.COMBINED,
                "[(all|the)] (ability[]types|1¦mcmmo[]abilities)");

        // ChatAPI Stuff
        Skript.registerEffect(EffmcMMOPartyMsg.class, "[mcmmo] send %string% to party %string% as [sender] %string%");
        Skript.registerEffect(EffmcMMOAdminMsg.class, "[mcmmo] send %string% to admin chat as [sender] %string%");
        Skript.registerCondition(CondmcMMOAdminChat.class, "[mcmmo] %player% is using admin[]chat");
        Skript.registerCondition(CondmcMMOPartyChat.class, "[mcmmo] %player% is using party[]chat");
        Skript.registerEffect(EffmcMMOToggleParty.class, "[mcmmo] toggle %player%['s] party[]chat");
        Skript.registerEffect(EffmcMMOToggleAdmin.class, "[mcmmo] toggle %player%['s] admin[]chat");

        // Other mcMMO Stuff
        Skript.registerExpression(ExprmcMMOPowerLevel.class, Integer.class, ExpressionType.SIMPLE,
                "[mcmmo] power[ ]level of %offlineplayer%");
        Skript.registerExpression(ExprmcMMOPowerLvlcap.class, Integer.class, ExpressionType.SIMPLE,
                "[mcmmo] power[ ]level cap[acity]");

    }

}
