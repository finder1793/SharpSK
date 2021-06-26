package me.sharpjaws.sharpsk;

import me.sharpjaws.sharpsk.hooks.AuthmeReloaded.AuthmeRegistry;
import me.sharpjaws.sharpsk.hooks.CoreProtect.CorePRegistry;
import me.sharpjaws.sharpsk.hooks.FAWE.FAWERegisry;
import me.sharpjaws.sharpsk.hooks.GlowAPI.GlowAPIRegistry;
import me.sharpjaws.sharpsk.hooks.GroupManager.GroupManagerRegistry;
import me.sharpjaws.sharpsk.hooks.JobsReborn.JobsRegistry;
import me.sharpjaws.sharpsk.hooks.Kingdoms.KingdomsRegistry;
import me.sharpjaws.sharpsk.hooks.LightAPI.LightAPIRegistry;
import me.sharpjaws.sharpsk.hooks.LogBlock.LogBlockRegistry;
import me.sharpjaws.sharpsk.hooks.LuckPerms.LuckPermsRegistry;
import me.sharpjaws.sharpsk.hooks.Multiverse.MultiverseRegistry;
import me.sharpjaws.sharpsk.hooks.MythicMobs.MythicMobsRegistry;
import me.sharpjaws.sharpsk.hooks.Slimefun.SlimefunRegistry;
import me.sharpjaws.sharpsk.hooks.Towny.TownyRegistry;
import me.sharpjaws.sharpsk.hooks.WorldEdit.WorldEditRegistry;
import me.sharpjaws.sharpsk.hooks.WorldGuard.WorldGuardRegistry;
import me.sharpjaws.sharpsk.hooks.mcMMO.mcMMORegistry;
import me.sharpjaws.sharpsk.hooks.uCars.uCarsRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

class HookManager {

    public void RegisterHooks() {
        JavaPlugin mainp = SharpSK.plugin;
        mainp.getLogger().info("Registering available hooks...");
        if (Bukkit.getPluginManager().getPlugin("Jobs") != null) {
            if (mainp.getConfig().getBoolean("jobsreborn")) {
                try {
                    JobsRegistry.registerJobs();
                    mainp.getLogger().info("Hooked into JobsReborn v"
                            + Bukkit.getPluginManager().getPlugin("Jobs").getDescription().getVersion());
                } catch (NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into JobsReborn." + " Version not supported");

                }
            }
        }

        if (mainp.getConfig().getBoolean("glowapi")) {
            if (Bukkit.getPluginManager().getPlugin("GlowAPI") != null) {
                try {
                    mainp.getLogger().info("Hooked into GlowAPI v"
                            + Bukkit.getPluginManager().getPlugin("GlowAPI").getDescription().getVersion());
                    GlowAPIRegistry.registerGlowAPI();
                } catch (NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into GlowAPI." + " Version not supported");
                }
            }
        }


        if (Bukkit.getPluginManager().getPlugin("mcMMO") != null) {
            if (mainp.getConfig().getBoolean("mcmmo")) {
                try {
                    mcMMORegistry.registermcMMO();
                    mainp.getLogger().info("Hooked into mcMMO v"
                            + Bukkit.getPluginManager().getPlugin("mcMMO").getDescription().getVersion());
                } catch (NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into mcMMO." + " Version not supported");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("AuthMe") != null) {
            if (mainp.getConfig().getBoolean("authmereloaded")) {
                try {
                    AuthmeRegistry.registerAuthMe();
                    mainp.getLogger().info("Hooked into AuthmeReloaded v"
                            + Bukkit.getPluginManager().getPlugin("AuthMe").getDescription().getVersion());
                } catch (NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into AuthmeReloaded." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().getPlugin("MythicMobs") != null) {
            if (mainp.getConfig().getBoolean("mythicmobs")) {
                try {
                    MythicMobsRegistry.RegisterMythicMobs();
                    mainp.getLogger().info("Hooked into MythicMobs v"
                            + Bukkit.getPluginManager().getPlugin("MythicMobs").getDescription().getVersion());
                } catch (NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into MythicMobs." + " Version not supported");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("LightAPI") != null) {
            if (mainp.getConfig().getBoolean("lightapi")) {
                try {
                    LightAPIRegistry.registerLightAPI();
                    mainp.getLogger().info("Hooked into LightAPI v"
                            + Bukkit.getPluginManager().getPlugin("LightAPI").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into LightAPI." + " Version not supported");
                    ex.printStackTrace();
                }

            }

        }
        if (Bukkit.getPluginManager().getPlugin("CoreProtect") != null) {
            if (mainp.getConfig().getBoolean("coreprotect")) {
                try {
                    CorePRegistry.registerCoreP();
                    mainp.getLogger().info("Hooked into CoreProtect v"
                            + Bukkit.getPluginManager().getPlugin("CoreProtect").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into CoreProtect." + " Version not supported");
                    ex.printStackTrace();
                }

            }

        }

        if (Bukkit.getPluginManager().isPluginEnabled("uCars")) {
            if (mainp.getConfig().getBoolean("ucars")) {
                try {
                    uCarsRegistry.registeruCars();

                    mainp.getLogger().info("Hooked into uCars v"
                            + Bukkit.getPluginManager().getPlugin("uCars").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into uCars." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("Multiverse-Core")) {
            if (mainp.getConfig().getBoolean("multiverse")) {
                try {
                    MultiverseRegistry.registerMultiverse();
                    mainp.getLogger().info("Hooked into Multiverse v"
                            + Bukkit.getPluginManager().getPlugin("Multiverse-Core").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into Multiverse." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("WorldEdit")) {
            if (mainp.getConfig().getBoolean("worldedit")) {
                try {
                    WorldEditRegistry.registerWorldEdit();
                    mainp.getLogger().info("Hooked into WorldEdit v"
                            + Bukkit.getPluginManager().getPlugin("WorldEdit").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    if (Boolean.getBoolean("sharpsk.whyitdoesntworkiwantdetails"))
                    	ex.printStackTrace();
                    mainp.getLogger().warning("Could not hook into WorldEdit." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            if (mainp.getConfig().getBoolean("worldguard")) {
                try {
                    WorldGuardRegistry.registerWorldGuard();
                    mainp.getLogger().info("Hooked into WorldGuard v"
                            + Bukkit.getPluginManager().getPlugin("WorldEdit").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    if (Boolean.getBoolean("sharpsk.whyitdoesntworkiwantdetails"))
                    	ex.printStackTrace();
                    mainp.getLogger().warning("Could not hook into WorldGuard." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("LogBlock")) {
            if (mainp.getConfig().getBoolean("logblock")) {
                try {
                    LogBlockRegistry.registerLogBlock();
                    mainp.getLogger().info("Hooked into LogBlock v"
                            + Bukkit.getPluginManager().getPlugin("LogBlock").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into LogBlock." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("Towny")) {
            if (mainp.getConfig().getBoolean("towny")) {
                try {
                    TownyRegistry.RegisterTowny();
                    mainp.getLogger().info("Hooked into Towny v"
                            + Bukkit.getPluginManager().getPlugin("Towny").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into Towny." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("Kingdoms")) {
            if (mainp.getConfig().getBoolean("kingdoms")) {
                try {
                    KingdomsRegistry.RegisterKingdoms();
                    mainp.getLogger().info("Hooked into Kingdoms v"
                            + Bukkit.getPluginManager().getPlugin("Kingdoms").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into Kingdoms." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            if (mainp.getConfig().getBoolean("luckperms")) {
                try {
                    LuckPermsRegistry.registerLuckPerms();
                    mainp.getLogger().info("Hooked into LuckPerms v"
                            + Bukkit.getPluginManager().getPlugin("LuckPerms").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into LuckPerms." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("GroupManager")) {
            if (mainp.getConfig().getBoolean("groupmanager")) {
                try {
                    GroupManagerRegistry.registerGroupManager();
                    mainp.getLogger().info("Hooked into GroupManager v"
                            + Bukkit.getPluginManager().getPlugin("GroupManager").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into GroupManager." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("Slimefun")) {
            if (mainp.getConfig().getBoolean("slimefun")) {
                try {
                    SlimefunRegistry.registerSlimefun();
                    mainp.getLogger().info("Hooked into Slimefun v"
                            + Bukkit.getPluginManager().getPlugin("Slimefun").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into Slimefun." + " Version not supported");
                }
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("FastAsyncWorldEdit")) {
            if (mainp.getConfig().getBoolean("fastasyncworldedit")) {
                try {
                    FAWERegisry.registerFAWE();
                    mainp.getLogger().info("Hooked into FastAsyncWorldEdit v"
                            + Bukkit.getPluginManager().getPlugin("FastAsyncWorldEdit").getDescription().getVersion());
                } catch (Exception | NoClassDefFoundError ex) {
                    mainp.getLogger().warning("Could not hook into FastAsyncWorldEdit." + " Version not supported");
                }
            }
        }
    }

}
