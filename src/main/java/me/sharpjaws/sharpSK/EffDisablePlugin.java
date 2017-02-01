 package me.sharpjaws.sharpSK;
 
 import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
 
 public class EffDisablePlugin extends Effect
 {
   private Expression<?> plugin;
   
   public boolean init(Expression<?>[] expresion, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
   {
     this.plugin = expresion[0];
     return true;
   }
   
   public String toString(Event e, boolean debug) {
     return "disable plugin";
   }
   
   protected void execute(Event event) {
     String name = (String)this.plugin.getSingle(event);
     
     Plugin plugin = Bukkit.getPluginManager().getPlugin(name);
     
     if ((plugin != null) && (plugin.isEnabled())) {
       Bukkit.getPluginManager().disablePlugin(plugin);
     }
   }
 }


