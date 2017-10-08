package me.sharpjaws.sharpSK;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.sharpjaws.sharpSK.NBTApi.ItemNBTAPIRegistry;
import me.sharpjaws.sharpSK.hooks.AuthmeReloaded.AuthmeRegistry;
import me.sharpjaws.sharpSK.hooks.CoreProtect.CorePRegistry;
import me.sharpjaws.sharpSK.hooks.FAWE.FAWERegisry;
import me.sharpjaws.sharpSK.hooks.GlowAPI.GlowAPIRegistry;
import me.sharpjaws.sharpSK.hooks.GroupManager.GroupManagerRegistry;
import me.sharpjaws.sharpSK.hooks.JobsReborn.JobsRegistry;
import me.sharpjaws.sharpSK.hooks.Kingdoms.KingdomsRegistry;
import me.sharpjaws.sharpSK.hooks.LightAPI.LightAPIRegistry;
import me.sharpjaws.sharpSK.hooks.LogBlock.LogBlockRegistry;
import me.sharpjaws.sharpSK.hooks.LuckPerms.LuckPermsRegistry;
import me.sharpjaws.sharpSK.hooks.Multiverse.MultiverseRegistry;
import me.sharpjaws.sharpSK.hooks.MythicMobs.MythicMobsRegistry;
import me.sharpjaws.sharpSK.hooks.PermissionsEx.PermissionsExRegistry;
import me.sharpjaws.sharpSK.hooks.Towny.TownyRegistry;
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
					mainp.getLogger().warning("Could not hook into JobsReborn." + " Version not supported");

				}
			}
		}

		if (Bukkit.getServer().getVersion().contains("MC: 1.9")||Bukkit.getServer().getVersion().contains("MC: 1.10")||Bukkit.getServer().getVersion().contains("MC: 1.11")) {
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
					mainp.getLogger().warning("Could not hook into mcMMO." + " Version not supported");
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
					mainp.getLogger().warning("Could not hook into AuthmeReloaded."+ " Version not supported");
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
					mainp.getLogger().warning("Could not hook into MythicMobs."+ " Version not supported");
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
					mainp.getLogger().warning("Could not hook into LightAPI."+ " Version not supported");
					ex.printStackTrace();
				} catch (NoClassDefFoundError ex2){
					mainp.getLogger().warning("Could not hook into LightAPI."+" Version not supported");
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
					mainp.getLogger().warning("Could not hook into CoreProtect."+ " Version not supported");
					ex.printStackTrace();
				} catch (NoClassDefFoundError ex2){
					mainp.getLogger().warning("Could not hook into CoreProtect."+ " Version not supported");
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
					mainp.getLogger().warning("Could not hook into uCars."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into uCars."+ " Version not supported");
				}	
			}
		}				
		if (Bukkit.getPluginManager().isPluginEnabled("PermissionsEx")) {
			if (mainp.getConfig().getBoolean("permissionsex") == true) {
				try {
					PermissionsExRegistry.registerPermissionsEx();
					mainp.getLogger().info("Hooked into PermissionsEx v" + Bukkit.getPluginManager()
					.getPlugin("PermissionsEx").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().warning("Could not hook into PermissionsEx."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into PermissionsEx."+ " Version not supported");
				}
			}					
		}

		if (Bukkit.getPluginManager().isPluginEnabled("Multiverse-Core")) {
			if (mainp.getConfig().getBoolean("multiverse") == true) {
				try {
					MultiverseRegistry.registerMultiverse();
					mainp.getLogger().info("Hooked into Multiverse v" + Bukkit.getPluginManager()
					.getPlugin("Multiverse-Core").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().warning("Could not hook into Multiverse."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into Multiverse."+ " Version not supported");
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
					mainp.getLogger().warning("Could not hook into WorldEdit."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into WorldEdit."+ " Version not supported");
				}
			}					
		}
		if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			if (mainp.getConfig().getBoolean("worldguard") == true) {
				try {
					WorldGuardRegistry.registerWorldGuard();
					mainp.getLogger().info("Hooked into WorldGuard v" + Bukkit.getPluginManager()
					.getPlugin("WorldEdit").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().warning("Could not hook into WorldGuard."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into WorldGuard."+ " Version not supported");
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
					mainp.getLogger().warning("Could not hook into LogBlock."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into LogBlock."+ " Version not supported");
				}
			}					
		}
		if (Bukkit.getPluginManager().isPluginEnabled("Towny")) {
			if (mainp.getConfig().getBoolean("towny") == true) {
				try {
					TownyRegistry.RegisterTowny();
					mainp.getLogger().info("Hooked into Towny v" + Bukkit.getPluginManager()
					.getPlugin("Towny").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().warning("Could not hook into Towny."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into Towny."+ " Version not supported");
				}
			}					
		}
		if (Bukkit.getPluginManager().isPluginEnabled("Kingdoms")) {
			if (mainp.getConfig().getBoolean("kingdoms") == true) {
				try {
					KingdomsRegistry.RegisterKingdoms();
					mainp.getLogger().info("Hooked into Kingdoms v" + Bukkit.getPluginManager()
					.getPlugin("Kingdoms").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().warning("Could not hook into Kingdoms."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into Kingdoms."+ " Version not supported");
				}
			}					
		}
		if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
			if (mainp.getConfig().getBoolean("luckperms") == true) {
				try {
					LuckPermsRegistry.registerLuckPerms();
					mainp.getLogger().info("Hooked into LuckPerms v" + Bukkit.getPluginManager()
					.getPlugin("LuckPerms").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().warning("Could not hook into LuckPerms."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into LuckPerms."+ " Version not supported");
				}
			}					
		}
		if (Bukkit.getPluginManager().isPluginEnabled("GroupManager")) {
			if (mainp.getConfig().getBoolean("groupmanager") == true) {
				try {
					GroupManagerRegistry.registerGroupManager();;
					mainp.getLogger().info("Hooked into GroupManager v" + Bukkit.getPluginManager()
					.getPlugin("GroupManager").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().warning("Could not hook into GroupManager."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into GroupManager."+ " Version not supported");
				}
			}					
		}
		if (Bukkit.getPluginManager().isPluginEnabled("ItemNBTAPI")) {
			if (mainp.getConfig().getBoolean("itemnbtapi") == true) {
				try {
				ItemNBTAPIRegistry.registerItemNBTAPI();
					mainp.getLogger().info("Hooked into ItemNBTAPI v" + Bukkit.getPluginManager()
					.getPlugin("ItemNBTAPI").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().warning("Could not hook into ItemNBTAPI."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into ItemNBTAPI."+ " Version not supported");
				}
			}					
		}
		if (Bukkit.getPluginManager().isPluginEnabled("FastAsyncWorldEdit")) {
			if (mainp.getConfig().getBoolean("fastasyncworldedit") == true) {
				try {
				FAWERegisry.registerFAWE();
					mainp.getLogger().info("Hooked into FastAsyncWorldEdit v" + Bukkit.getPluginManager()
					.getPlugin("FastAsyncWorldEdit").getDescription().getVersion());
				} catch (Exception ex) {
					mainp.getLogger().warning("Could not hook into FastAsyncWorldEdit."+ " Version not supported");
				} catch (NoClassDefFoundError e){
					mainp.getLogger().warning("Could not hook into FastAsyncWorldEdit."+ " Version not supported");
				}
			}					
		}
	}

}
