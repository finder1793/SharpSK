package me.sharpjaws.sharpSK;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Dropper;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EnderDragon.Phase;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import com.codingforcookies.armorequip.ArmorListener;
import com.codingforcookies.armorequip.ArmorunEquipEvent;
import com.codingforcookies.armorequip.ArmorunEquipListener;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.Timespan;
import me.sharpjaws.sharpSK.Threads.CTimerThread;
import me.sharpjaws.sharpSK.hooks.GlowAPI.ExprGlowingStateEntity;

public class main extends JavaPlugin implements Listener {

public static JavaPlugin plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sharpsk")) {
			if (sender.hasPermission("sharpsk.admin")) {
				if (args.length == 0) {
					sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK]" + org.bukkit.ChatColor.GREEN + " Use "
							+ org.bukkit.ChatColor.YELLOW + "/sharpsk help" + org.bukkit.ChatColor.GREEN
							+ " to see all the available commands");
					return false;
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						sender.sendMessage("");
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+org.bukkit.ChatColor.RED+ "====== SharpSK Commands =====");
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+org.bukkit.ChatColor.YELLOW + "/sharpsk help" + org.bukkit.ChatColor.GREEN + " // "+ "All of the plugin commands");
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+org.bukkit.ChatColor.YELLOW + "/sharpsk version "+ org.bukkit.ChatColor.GREEN + " // "+ "Current version of SharpSK");
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+org.bukkit.ChatColor.YELLOW + "/sharpsk check" + org.bukkit.ChatColor.GREEN + " // "+ "Checks for any new versions");
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+org.bukkit.ChatColor.YELLOW + "/sharpsk timers" + org.bukkit.ChatColor.GREEN + " // "+ "Count of all running timers");
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+org.bukkit.ChatColor.RED+ "====== SharpSK Commands =====");
						sender.sendMessage("");
					} else if (args[0].equalsIgnoreCase("version")) {
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] " + org.bukkit.ChatColor.GREEN
								+ "Running on version " + org.bukkit.ChatColor.YELLOW + "v"
								+ this.getDescription().getVersion());
					} else if (args[0].equalsIgnoreCase("check")){
							sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] " + org.bukkit.ChatColor.YELLOW +"Checking for any updates...");										
						try {
							Boolean update = main2();
							if (update == false) {
								sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+ org.bukkit.ChatColor.RED +"No Updates have been found");
								sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+ org.bukkit.ChatColor.GREEN +"You are running on the latest version");
							}
							if (update == true){
								sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+ org.bukkit.ChatColor.GREEN +"A new version has been found!");
								sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+ org.bukkit.ChatColor.GREEN + "Newest version is"+org.bukkit.ChatColor.YELLOW +" v"+Updater.main());
								sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+ org.bukkit.ChatColor.GREEN +"You are still running on"+org.bukkit.ChatColor.RED +" v"+this.getDescription().getVersion());				
							}
							Updater.main();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else if (args[0].equalsIgnoreCase("timers")){
						 
						int activetimers = 0;
						for (Thread t : Thread.getAllStackTraces().keySet()) {
						        if (t instanceof CTimerThread) {
						        	activetimers++;
						        }
						    }
						if (activetimers > 1) {
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+ org.bukkit.ChatColor.GREEN +"There are "+org.bukkit.ChatColor.YELLOW + activetimers + org.bukkit.ChatColor.GREEN + " Timers active.");
						}else if (activetimers > 0){
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+ org.bukkit.ChatColor.GREEN +"There is "+org.bukkit.ChatColor.YELLOW + activetimers +  org.bukkit.ChatColor.GREEN + " Timer active.");	
						}else{
							sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK] "+ org.bukkit.ChatColor.GREEN +"There are no running timers active");	
						}
					
					}else
						sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK]" + org.bukkit.ChatColor.GREEN
								+ " Use " + org.bukkit.ChatColor.YELLOW + "/sharpsk help" + org.bukkit.ChatColor.GREEN
								+ " to see all the available commands");
				} else
					sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK]" + org.bukkit.ChatColor.GREEN + " Use "
							+ org.bukkit.ChatColor.YELLOW + "/sharpsk help" + org.bukkit.ChatColor.GREEN
							+ " to see all the available commands");
			}

		}
		if (!sender.hasPermission("sharpsk.admin")) {
			sender.sendMessage(org.bukkit.ChatColor.AQUA + "[SharpSK]" + org.bukkit.ChatColor.RED
					+ " You do not have permission to run this command");
			return false;
		}
		return false;
	}

	@Override
	public void onEnable() {
		plugin = this;
		HookManager hman = new HookManager();
		File configfile = new File(getDataFolder(), "config.yml");   
		FileConfiguration config = YamlConfiguration.loadConfiguration(configfile);

		try{
		if (!config.getString("cfgver").contains(this.getDescription().getVersion())){	
			getLogger().info("A old config has been found and updated.");
			configfile.renameTo(new File(getDataFolder(),  "config_" + System.currentTimeMillis()+".yml.old"));
			this.saveDefaultConfig();
		}
		}catch(NullPointerException ex2){
			if (configfile.exists() == false){
			getLogger().info("Generating config...");
			this.saveDefaultConfig();
			}else{
				getLogger().info("A old config has been found and updated.");
				configfile.renameTo(new File(getDataFolder(), "config-"+ System.currentTimeMillis()+".yml.old"));
				this.saveDefaultConfig();
			}
		}
		
		if (this.getConfig().getBoolean("metrics") == true) {
			getLogger().info("Enabling Metrics...");
			try {
				Metrics metrics = new Metrics(this);
				metrics.start();
				getLogger().info("Metrics successfully enabled");
			} catch (IOException e) {
				getLogger().info("A error occured while trying to enable metrics. Skipping...");
			}
		}
		Plugin skript = Bukkit.getPluginManager().getPlugin("Skript");
	

		if (Bukkit.getPluginManager().isPluginEnabled(skript)) {
			if(Skript.isAcceptRegistrations() == true) {
			getLogger().info("Attempting to register Addon...");
			Skript.registerAddon(this);
			getLogger().info("Attempting to register stuff...");
			try {
				Skript.registerEvent("Firework Explode", SimpleEvent.class, FireworkExplodeEvent.class,
						"firework explode");
			} catch (NoClassDefFoundError ex) {
				getLogger().info("An error occurred while trying to register an event");
			}
			Skript.registerEvent("Shear", SimpleEvent.class,  PlayerShearEntityEvent.class,"[on] shear");
			Skript.registerEvent("Transfer", SimpleEvent.class,  InventoryMoveItemEvent.class,"[on] transfer");
			EventValues.registerEventValue(InventoryMoveItemEvent.class, ItemStack.class,
					new Getter<ItemStack, InventoryMoveItemEvent>() {
						@Override
						public ItemStack get(InventoryMoveItemEvent e) {
							ItemStack i = e.getItem();
							return i;
						}
					}, 0);
			if (Bukkit.getServer().getVersion().contains("MC: 1.9")|| Bukkit.getServer().getVersion().contains("MC: 1.10")||Bukkit.getServer().getVersion().contains("MC: 1.11") ){
			EventValues.registerEventValue(InventoryMoveItemEvent.class, Block.class,
					new Getter<Block, InventoryMoveItemEvent>() {
						@Override
						public Block get(InventoryMoveItemEvent e) {
							Block b = e.getSource().getLocation().getBlock();
							return b;
						}
					}, 0);
			EventValues.registerEventValue(InventoryMoveItemEvent.class, Location.class,
					new Getter<Location, InventoryMoveItemEvent>() {
						@Override
						public Location get(InventoryMoveItemEvent e) {
							Location l = e.getDestination().getLocation();
							return l;
						}
					}, 0);
			}else if (Bukkit.getServer().getVersion().contains("MC: 1.8")){
				EventValues.registerEventValue(InventoryMoveItemEvent.class, Block.class,
						new Getter<Block, InventoryMoveItemEvent>() {
							@Override
							public Block get(InventoryMoveItemEvent e) {
								InventoryHolder iH = e.getSource().getHolder();
								
								Block b3 = null;
								
								if(iH instanceof Chest){
								Chest b = (Chest) iH;
								BlockState b2 = b.getBlock().getState();
								b3 = b2.getBlock();	
								}else if(iH instanceof Hopper){
								Hopper b = (Hopper) iH;
								BlockState b2 = b.getBlock().getState();
								b3 = b2.getBlock();		
								}else if(iH instanceof Dispenser){
								Dispenser b = (Dispenser) iH;
								BlockState b2 = b.getBlock().getState();
								b3 = b2.getBlock();		
								}else if(iH instanceof Dropper){
								Dropper b = (Dropper) iH;
								BlockState b2 = b.getBlock().getState();
								b3 = b2.getBlock();
								}else if(iH instanceof DoubleChest){
									DoubleChest b = (DoubleChest) iH;
									Block b2 = b.getLocation().getBlock();
									b3 = b2;
								}else if(iH instanceof Furnace){
									Furnace b = (Furnace) iH;
									Block b2 = b.getLocation().getBlock();
									b3 = b2;
								}
								
								return b3;			
							}
						}, 0);
				EventValues.registerEventValue(InventoryMoveItemEvent.class, Location.class,
						new Getter<Location, InventoryMoveItemEvent>() {
							@Override
							public Location get(InventoryMoveItemEvent e) {
								InventoryHolder iH = e.getDestination().getHolder();
								
								Block b3 = null;
								
								if(iH instanceof Chest){
								Chest b = (Chest) iH;
								BlockState b2 = b.getBlock().getState();
								b3 = b2.getBlock();	
								}else if(iH instanceof Hopper){
								Hopper b = (Hopper) iH;
								BlockState b2 = b.getBlock().getState();
								b3 = b2.getBlock();		
								}else if(iH instanceof Dispenser){
								Dispenser b = (Dispenser) iH;
								BlockState b2 = b.getBlock().getState();
								b3 = b2.getBlock();		
								}else if(iH instanceof Dropper){
								Dropper b = (Dropper) iH;
								BlockState b2 = b.getBlock().getState();
								b3 = b2.getBlock();
								}else if(iH instanceof DoubleChest){
									DoubleChest b = (DoubleChest) iH;
									Block b2 = b.getLocation().getBlock();
									b3 = b2;
								}else if(iH instanceof Furnace){
								Furnace b = (Furnace) iH;
								Block b2 = b.getLocation().getBlock();
								b3 = b2;
							}
								
								return b3.getLocation();		
							}
						}, 0);
			}
			Skript.registerEvent("Pickup", SimpleEvent.class, InventoryPickupItemEvent.class, "[on] hopper pickup");
			EventValues.registerEventValue(InventoryPickupItemEvent.class, Inventory.class,
					new Getter<Inventory, InventoryPickupItemEvent>() {
						@Override
						public Inventory get(InventoryPickupItemEvent e) {
							Inventory s = e.getInventory();
							return s;
						}
					}, 0);
			EventValues.registerEventValue(InventoryPickupItemEvent.class, ItemStack.class,
					new Getter<ItemStack, InventoryPickupItemEvent>() {
						@Override
						public ItemStack get(InventoryPickupItemEvent e) {
							ItemStack s = e.getItem().getItemStack();
							return s;
						}
					}, 0);
			EventValues.registerEventValue(InventoryPickupItemEvent.class, Location.class,
					new Getter<Location, InventoryPickupItemEvent>() {
						@Override
						public Location get(InventoryPickupItemEvent e) {
							InventoryHolder inv = e.getInventory().getHolder();
							Location loc = null;
							if (inv instanceof Hopper) {
							    loc = ((Hopper) inv).getLocation();
							} else if(inv instanceof  HopperMinecart) {
							    loc= ((HopperMinecart) inv).getLocation();
							}
							return loc;
						}
					}, 0);
			if (Bukkit.getPluginManager().getPlugin("Umbaska") != null) {
				getLogger().info("Umbaska was found. Some event syntaxes are changed");
			Skript.registerEvent("Armor Equip", SimpleEvent.class, ArmorEquipEvent.class, "sharpsk [on] (armor|armour) equip");
			EventValues.registerEventValue( ArmorEquipEvent.class, ItemStack.class,
					new Getter<ItemStack, ArmorEquipEvent>() {
						@Override
						public ItemStack get( ArmorEquipEvent e) {
							ItemStack item = e.getItem();
							return item;
						}
					}, 0);
			Skript.registerEvent("Armor unEquip", SimpleEvent.class, ArmorunEquipEvent.class, "sharpsk [on] (armor|armour) unequip");
			EventValues.registerEventValue( ArmorunEquipEvent.class, ItemStack.class,
					new Getter<ItemStack,  ArmorunEquipEvent>() {
						@Override
						public ItemStack get( ArmorunEquipEvent e) {
							ItemStack i2 = e.getItem();
							return i2;
						}
					}, 0);
			}else{
				Skript.registerEvent("Armor Equip", SimpleEvent.class, ArmorEquipEvent.class, "[on] (armor|armour) equip");
				EventValues.registerEventValue( ArmorEquipEvent.class, ItemStack.class,
						new Getter<ItemStack, ArmorEquipEvent>() {
							@Override
							public ItemStack get( ArmorEquipEvent e) {
								ItemStack item = e.getItem();
								return item;
							}
						}, 0);
				Skript.registerEvent("Armor unEquip", SimpleEvent.class, ArmorunEquipEvent.class, "[on] (armor|armour) unequip");
				EventValues.registerEventValue( ArmorunEquipEvent.class, ItemStack.class,
						new Getter<ItemStack,  ArmorunEquipEvent>() {
							@Override
							public ItemStack get( ArmorunEquipEvent e) {
								ItemStack i2 = e.getItem();
								return i2;
							}
						}, 0);
			}
			Skript.registerEvent("Enderman Teleport", SimpleEvent.class, EntityTeleportEvent.class,
					"[on] enderman teleport");
			Skript.registerEvent("Extract", SimpleEvent.class, FurnaceExtractEvent.class, "extract");
			new ArmorListener(null, this);
			new ArmorunEquipListener(null, this);
			Skript.registerEvent("Server Command", SimpleEvent.class, ServerCommandEvent.class, "[on] (server|console) command");
			EventValues.registerEventValue(ServerCommandEvent.class, String.class,
					new Getter<String, ServerCommandEvent>() {
						@Override
						public String get(ServerCommandEvent e) {
							String s = e.getCommand();
							return s;
						}
					}, 0);
			new ExpChangeListener(this);
			Skript.registerEvent("Experience Change", SimpleEvent.class, EvtExpChange.class, "[on] exp[erience] change");
			EventValues.registerEventValue(EvtExpChange.class, Player.class,
					new Getter<Player, EvtExpChange>() {
						@Override
						public Player get(EvtExpChange e) {
							return e.getPlayer();
						}
					}, 0);
			EventValues.registerEventValue(EvtExpChange.class, Number.class,
					new Getter<Number, EvtExpChange>() {
						@Override
						public Number get(EvtExpChange e) {
							return e.getExp();
						}
					}, 0);
			Skript.registerEffect(EffBrewerInv.class, "open brewer inventory to %player% [with name %-string%]");
			Skript.registerEffect(EffHopperInv.class, "open hopper inventory to %player% [with name %-string%]");
			Skript.registerCondition(CondPlayerIsStandingOn.class, "%entity% is standing on %block%");
			Skript.registerCondition(CondNotPlayerStandingOn.class, "%entity% is not standing on %itemstack%");
			Skript.registerCondition(Condisleashed.class, "%entity% is leashed");
			Skript.registerCondition(CondNotleashed.class, "%entity% is not leashed");
			Skript.registerCondition(CondEventCancelled.class, "[the] event (is|was) cancelled");
			Skript.registerCondition(CondEventNotCancelled.class, "[the] event (is|was) not cancelled");
			Skript.registerExpression(ExprInvType.class, String.class, ExpressionType.SIMPLE,"%player%['s] [current] inventory type");
			Skript.registerEvent("World Change", SimpleEvent.class, PlayerChangedWorldEvent.class, "world change");
			EventValues.registerEventValue(PlayerChangedWorldEvent.class, Player.class,
					new Getter<Player, PlayerChangedWorldEvent>() {
						@Override
						public Player get(PlayerChangedWorldEvent e) {
							Player p = e.getPlayer();
							return p;
						}
					}, 0);
			Skript.registerExpression(ExprEventWorld.class, World.class, ExpressionType.SIMPLE,
					"[the] [(-1¦(past|former)|1¦future)] [event-]world");
			Skript.registerExpression(ExprEventWorldLoc.class, Location.class, ExpressionType.SIMPLE,
					"[the] [(-1¦(past|former)|1¦future)] event-location");
			
			if (this.getConfig().getBoolean("updater") == true) {
				 getLogger().info("Checking for any updates...");
			
			try {
				Boolean update = main2();
				if (update == false) {
					 getLogger().info("No Updates have been found");
					 getLogger().info("You are running on the latest version");
				}
				if (update == true){
					 getLogger().info("A new version has been found!");
					 getLogger().info("Newest version is v"+Updater.main());
					 getLogger().info("You are still running on v"+this.getDescription().getVersion());				
				}
				Updater.main();
			} catch (Exception e1) {
				e1.printStackTrace();			
			}
			}
			
			if (Bukkit.getServer().getVersion().contains("MC: 1.9")||Bukkit.getServer().getVersion().contains("MC: 1.10")||Bukkit.getServer().getVersion().contains("MC: 1.11")) {
				if (Bukkit.getServer().getVersion().contains("MC: 1.10")){
				getLogger().info("1.10+ Server detected! Registering some MC 1.9 related stuff..");
				}else if (Bukkit.getServer().getVersion().contains("MC: 1.11")) {
					getLogger().info("1.11+ Server detected! Registering some MC 1.9 related stuff..");
				}else if (Bukkit.getServer().getVersion().contains("MC: 1.9")) {
					getLogger().info("1.9 Server detected! Registering some MC 1.9 stuff..");	
				}
				
				Skript.registerEvent("Anvil Prepare", SimpleEvent.class, PrepareAnvilEvent.class, "anvil prepare");
				EventValues.registerEventValue(PrepareAnvilEvent.class, Player.class,
						new Getter<Player, PrepareAnvilEvent>() {
							@Override
							@Nullable
							public Player get(PrepareAnvilEvent e) {
								Player p = Bukkit.getPlayer(e.getView().getPlayer().getName());
					
								return p;
							}
						}, 0);	
				EventValues.registerEventValue(PrepareAnvilEvent.class, ItemStack.class,
						new Getter<ItemStack, PrepareAnvilEvent>() {
							@Override
							@Nullable
							public ItemStack get(PrepareAnvilEvent e) {
								ItemStack i = e.getInventory().getItem(0);
								return i;
							}
						}, 0);		
				Classes.registerClass(new ClassInfo<Phase>(Phase.class, "phase").parser(new Parser<Phase>() {
					@Override
					public String getVariableNamePattern() {
						return ".+";
					}

					@Override
					@Nullable
					public Phase parse(String s, ParseContext cont) {
						try {
							return Phase.valueOf(s.replace(" ", "_").trim().toUpperCase());
						} catch (IllegalArgumentException e) {
							return null;
						}
					}

					@Override
					public String toString(Phase eff, int i) {
						return eff.name().replace("_", " ").toUpperCase();
					}

					@Override
					public String toVariableNameString(Phase eff) {
						return eff.name().replace("_", " ").toUpperCase();
					}

				}));
				try {
				Skript.registerExpression(ExprEventAnvilCost.class, Number.class, ExpressionType.SIMPLE,
						"(anvil[]cost|event-[anvil]cost)");
				}catch(Exception ex){
					
				}
				Skript.registerExpression(ExprPhaseOf.class, Phase.class, ExpressionType.SIMPLE,
						"phase of [ender]dragon in %world%");
				Skript.registerEvent("Dragon Phase Change", SimpleEvent.class, EnderDragonChangePhaseEvent.class,
						"[ender]dragon phase change");
				EventValues.registerEventValue(EnderDragonChangePhaseEvent.class, Phase.class,
						new Getter<Phase, EnderDragonChangePhaseEvent>() {
							@Override
							@Nullable
							public Phase get(EnderDragonChangePhaseEvent e) {
								return e.getNewPhase();
							}
						}, 0);
				Skript.registerExpression(ExprGlowingStateEntity.class, Boolean.class, ExpressionType.SIMPLE,
						"glowing state of %entity%");
				Skript.registerExpression(ExprOffhandItem.class, ItemStack.class, ExpressionType.PROPERTY,
						"%player%'s offhand");
			}
		
			
			//Plugin Hook registration

			hman.RegisterHooks();
			
			//PirateSK Syntaxes
			//-------------------
			 Skript.registerEffect(EffSaveWorlds.class, "save %worlds%");
		        Skript.registerExpression(ExprExplodedBlocks.class, Block.class, ExpressionType.SIMPLE, "exploded[(-| )]blocks");
		        Skript.registerExpression(ExprEntityAI.class, Boolean.class, ExpressionType.PROPERTY, "[the] ai of %livingentities%", "%livingentities%'[s] ai");
		        Skript.registerExpression(ExprInvulnerabilityTime.class, Timespan.class, ExpressionType.PROPERTY, "[the] (invulnerability [time]|no damage [time]) of %livingentity%", "%livingentity%'s (invulnerability [time]|no damage [time])");
		        Skript.registerEffect(EffTame.class, "tame %entities% (to|for) %player%", "untame %entities%");
		        Skript.registerCondition(CondIsTamed.class, "%entity% is tamed", "%entity% (is not|isn't) tamed");
		        Skript.registerExpression(ExprTameOwner.class, Player.class, ExpressionType.PROPERTY, "%entities%'s (tamer|[pet] owner)", "[the] (tamer|[pet] owner) of %entities%");
		        
		    //-------------------
	
			
				Skript.registerEffect(EffTimerCreate.class, "create timer %string% for %timespan%");
				Skript.registerEvent("Timer Tick", SimpleEvent.class, EvtTimerTick.class, "timer tick");
				EventValues.registerEventValue(EvtTimerTick.class, String.class,
						new Getter<String, EvtTimerTick>() {
							@Override
							@Nullable
							public String get(EvtTimerTick e) {
								return e.getTimer();
							}
						}, 0);
				EventValues.registerEventValue(EvtTimerTick.class, Number.class,
						new Getter<Number, EvtTimerTick>() {
							@Override
							@Nullable
							public Number get(EvtTimerTick e) {
								return e.getTimeLeft();
							}
						}, 0);
				
				Skript.registerEvent("Timer Complete", SimpleEvent.class, EvtTimerComplete.class, "timer complete");
				EventValues.registerEventValue(EvtTimerComplete.class, String.class,
						new Getter<String, EvtTimerComplete>() {
							@Override
							@Nullable
							public String get( EvtTimerComplete e) {
								return e.getTimer();
							}
						}, 0);
				
				
				
			}else{
				getLogger().warning("Error: Unable to register the addon and the features");
				getLogger().warning("Error: Skript is not allowing registerations.");
				Bukkit.getPluginManager().disablePlugin(this);
			}
			}else if (!Bukkit.getPluginManager().isPluginEnabled(skript)) {
			getLogger().info("Error Skript was not found or enabled. Disabling...");
			Bukkit.getPluginManager().disablePlugin(this);
		}
     }

		
	
	



	@Override
	public void onDisable() {
		getLogger().info("Successfully disabled.");
	}
	
	
	public Boolean main2() throws Exception {
		String up = Updater.main(
				);
		Boolean check = false;
		try {
	    if (!up.equals(this.getDescription().getVersion())){
	   check = true;
	}else if (this.getDescription().getVersion().equals(Updater.main())){
	    check = false;
	}
	} catch(NullPointerException ex) {
	   check = false;
	}
		return check;
				
}
}