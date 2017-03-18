package me.sharpjaws.sharpSK;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.sharpjaws.sharpSK.hooks.AuthmeReloaded.AuthmeRegistry;
import me.sharpjaws.sharpSK.hooks.CoreProtect.CorePRegistry;
import me.sharpjaws.sharpSK.hooks.GlowAPI.GlowAPIRegistry;
import me.sharpjaws.sharpSK.hooks.JobsReborn.JobsRegistry;
import me.sharpjaws.sharpSK.hooks.LightAPI.LightAPIRegistry;
import me.sharpjaws.sharpSK.hooks.LogBlock.LogBlockRegistry;
import me.sharpjaws.sharpSK.hooks.Multiverse.MultiverseRegistry;
import me.sharpjaws.sharpSK.hooks.MythicMobs.MythicMobsRegistry;
import me.sharpjaws.sharpSK.hooks.PermissionsEx.PermissionsExRegistry;
import me.sharpjaws.sharpSK.hooks.WorldEdit.WorldEditRegistry;
import me.sharpjaws.sharpSK.hooks.WorldGuard.WorldGuardRegistry;
import me.sharpjaws.sharpSK.hooks.mcMMO.mcMMORegistry;
import me.sharpjaws.sharpSK.hooks.uCars.uCarsRegistry;


public class HookManager {

	public void RegisterHooks(){
		JavaPlugin mainp = main.plugin;
		mainp.getLogger().info("Registering available hooks...");
		if (Bukkit.getPluginManager().getPlugin("Jobs") != null) {
			if (mainp.getConfig().getBoolean("jobsreborn") == true) {
				try {
					JobsRegistry.registerJobs();
					mainp.getLogger().info("Hooked into JobsReborn v"+ Bukkit.getPluginManager().getPlugin("Jobs").getDescription().getVersion());
				}catch (NoClassDefFoundError ex) {
					mainp.getLogger().info("Could not hook into JobsReborn v"
							+ Bukkit.getPluginManager().getPlugin("Jobs").getDescription().getVersion()
							+ " Because errors occured.");
					
				}
			}
		}

		if (Bukkit.getServer().getVersion().contains("MC: 1.9")||Bukkit.getServer().getVersion().contains("MC: 1.10")) {
		if (mainp.getConfig().getBoolean("glowapi") == true) {
			if (Bukkit.getPluginManager().getPlugin("GlowAPI") != null) {
				try{
				mainp.getLogger().info("Hooked into GlowAPI v" + Bukkit.getPluginManager().getPlugin("GlowAPI").getDescription().getVersion());
				GlowAPIRegistry.registerGlowAPI();
				}catch(NoClassDefFoundError ex){
					
				}	
			}	
		}		
	}

		if (Bukkit.getPluginManager().getPlugin("mcMMO") != null) {
			if (mainp.getConfig().getBoolean("mcmmo") == true) {
				try {
				mcMMORegistry.registermcMMO();
				mainp.getLogger().info("Hooked into mcMMO v"+ Bukkit.getPluginManager().getPlugin("mcMMO").getDescription().getVersion());
				}catch (NoClassDefFoundError ex) {
					mainp.getLogger().info("Could not hook into mcMMO v"
							+ Bukkit.getPluginManager().getPlugin("mcMMO").getDescription().getVersion()
							+ " Because errors occured.");
		}
	}
}			
				
		
		if (Bukkit.getPluginManager().getPlugin("AuthMe") != null) {
			if (mainp.getConfig().getBoolean("authmereloaded") == true) {
				try {
				AuthmeRegistry.registerAuthMe();
					mainp.getLogger().info("Hooked into AuthmeReloaded v"
							+ Bukkit.getPluginManager().getPlugin("AuthMe").getDescription().getVersion());
				} catch (NoClassDefFoundError ex) {
					mainp.getLogger().info("Could not hook into AuthmeReloaded v"
							+ Bukkit.getPluginManager().getPlugin("AuthMe").getDescription().getVersion()
							+ " Because errors occured.");
				}
			}
		}
		if (Bukkit.getPluginManager().getPlugin("MythicMobs") != null) {
			if (mainp.getConfig().getBoolean("mythicmobs") == true) {
				try {
				MythicMobsRegistry.RegisterMythicMobs();
					mainp.getLogger().info("Hooked into MythicMobs v"
							+ Bukkit.getPluginManager().getPlugin("MythicMobs").getDescription().getVersion());
				} catch (NoClassDefFoundError ex) {
					mainp.getLogger().info("Could not hook into MythicMobs v"
							+ " Because errors occured.");
				}
			}
		}

		if (Bukkit.getPluginManager().getPlugin("LightAPI") != null) {
			if (mainp.getConfig().getBoolean("lightapi") == true) {
				try {
				LightAPIRegistry.registerLightAPI();
				mainp.getLogger().info("Hooked into LightAPI v"
						+ Bukkit.getPluginManager().getPlugin("LightAPI").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().info("Could not hook into LightAPI v"
							+ Bukkit.getPluginManager().getPlugin("LightAPI").getDescription().getVersion()
							+ " Because errors occured.");
					ex.printStackTrace();
				} catch (NoClassDefFoundError ex2){
					mainp.getLogger().info("Could not hook into LightAPI v"
							+ Bukkit.getPluginManager().getPlugin("LightAPI").getDescription().getVersion()
							+ " Because errors occured.");
					ex2.printStackTrace();
				}
				
			}

		}
		if (Bukkit.getPluginManager().getPlugin("CoreProtect") != null) {
			if (mainp.getConfig().getBoolean("coreprotect") == true) {
				try {
				CorePRegistry.registerCoreP();
				mainp.getLogger().info("Hooked into CoreProtect v"
						+ Bukkit.getPluginManager().getPlugin("CoreProtect").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().info("Could not hook into CoreProtect v"
							+ Bukkit.getPluginManager().getPlugin("CoreProtect").getDescription().getVersion()
							+ " Because errors occured.");
					ex.printStackTrace();
				} catch (NoClassDefFoundError ex2){
					mainp.getLogger().info("Could not hook into CoreProtect v"
							+ Bukkit.getPluginManager().getPlugin("CoreProtect").getDescription().getVersion()
							+ " Because errors occured.");
					ex2.printStackTrace();
				}
				
			}

		}
		
		
		if (Bukkit.getPluginManager().isPluginEnabled("uCars")) {
			if (mainp.getConfig().getBoolean("ucars") == true) {
				try {
					uCarsRegistry.registeruCars();
				
					mainp.getLogger().info("Hooked into uCars v"
							+ Bukkit.getPluginManager().getPlugin("uCars").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().info("Could not hook into uCars v"
							+ Bukkit.getPluginManager().getPlugin("uCars").getDescription().getVersion()
							+ " Because errors occured.");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().info("Could not hook into uCars v"
							+ Bukkit.getPluginManager().getPlugin("uCars").getDescription().getVersion()
							+ " Because errors occured.");
				}	
			}
		}				
			if (Bukkit.getPluginManager().isPluginEnabled("PermissionsEx")) {
				if (mainp.getConfig().getBoolean("permissionsex") == true) {
					try {
						PermissionsExRegistry.registerPermX();
						mainp.getLogger().info("Hooked into PermissionsEx v" + Bukkit.getPluginManager()
						.getPlugin("PermissionsEx").getDescription().getVersion());
					} catch (Exception ex) {
						mainp.getLogger().info("Could not hook into PermissionsEx v"
								+ Bukkit.getPluginManager().getPlugin("PermissionsEx").getDescription().getVersion()
								+ " Because errors occured.");
					} catch (NoClassDefFoundError e){
						mainp.getLogger().info("Could not hook into PermissionsEx v"
								+ Bukkit.getPluginManager().getPlugin("PermissionsEx").getDescription().getVersion()
								+ " Because errors occured.");
					}
				}					
			}
	
			if (Bukkit.getPluginManager().isPluginEnabled("Multiverse-Core")) {
				if (mainp.getConfig().getBoolean("multiverse") == true) {
					try {
						MultiverseRegistry.registerMultiv();
						mainp.getLogger().info("Hooked into Multiverse v" + Bukkit.getPluginManager()
						.getPlugin("Multiverse-Core").getDescription().getVersion());
					} catch (Exception ex) {
						mainp.getLogger().info("Could not hook into Multiverse v"
								+ Bukkit.getPluginManager().getPlugin("Multiverse-Core").getDescription().getVersion()
								+ " Because errors occured.");
					} catch (NoClassDefFoundError e){
						mainp.getLogger().info("Could not hook into Multiverse v"
								+ Bukkit.getPluginManager().getPlugin("Multiverse-Core").getDescription().getVersion()
								+ " Because errors occured.");
					}
				}					
			}
			if (Bukkit.getPluginManager().isPluginEnabled("WorldEdit")) {
				if (mainp.getConfig().getBoolean("worldedit") == true) {
					try {
						WorldEditRegistry.registerWorldEdit();
						mainp.getLogger().info("Hooked into WorldEdit v" + Bukkit.getPluginManager()
						.getPlugin("WorldEdit").getDescription().getVersion());
					} catch (Exception ex) {
						mainp.getLogger().info("Could not hook into WorldEdit v"
								+ Bukkit.getPluginManager().getPlugin("WorldEdit").getDescription().getVersion()
								+ " Because errors occured.");
					} catch (NoClassDefFoundError e){
						mainp.getLogger().info("Could not hook into WorldEdit v"
								+ Bukkit.getPluginManager().getPlugin("WorldEdit").getDescription().getVersion()
								+ " Because errors occured.");
					}
				}					
			}
			if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
				if (mainp.getConfig().getBoolean("worldguard") == true) {
					try {
						WorldGuardRegistry.registerwguard();
						mainp.getLogger().info("Hooked into WorldGuard v" + Bukkit.getPluginManager()
						.getPlugin("WorldEdit").getDescription().getVersion());
					} catch (Exception ex) {
						mainp.getLogger().info("Could not hook into WorldGuard v"
								+ Bukkit.getPluginManager().getPlugin("WorldGuard").getDescription().getVersion()
								+ " Because errors occured.");
					} catch (NoClassDefFoundError e){
						mainp.getLogger().info("Could not hook into WorldGuard v"
								+ Bukkit.getPluginManager().getPlugin("WorldGuard").getDescription().getVersion()
								+ " Because errors occured.");
					}
				}					
			}
			if (Bukkit.getPluginManager().isPluginEnabled("LogBlock")) {
				if (mainp.getConfig().getBoolean("logblock") == true) {
					try {
						LogBlockRegistry.registerLogBlock();
						mainp.getLogger().info("Hooked into LogBlock v" + Bukkit.getPluginManager()
						.getPlugin("LogBlock").getDescription().getVersion());
					} catch (Exception ex) {
						mainp.getLogger().info("Could not hook into LogBlock v"
								+ Bukkit.getPluginManager().getPlugin("LogBlock").getDescription().getVersion()
								+ " Because errors occured.");
					} catch (NoClassDefFoundError e){
						mainp.getLogger().info("Could not hook into LogBlock v"
								+ Bukkit.getPluginManager().getPlugin("LogBlock").getDescription().getVersion()
								+ " Because errors occured.");
					}
				}					
			}
	}
	
}
