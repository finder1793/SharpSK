 package me.sharpjaws.sharpSK;
 
 import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.UnknownDependencyException;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
 
 public class EffLoadPlugin
   extends Effect
 {
   private Expression<?> plugin;
   
   public boolean init(Expression<?>[] expresion, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
   {
     this.plugin = expresion[0];
     return true;
   }
   
   public String toString(Event e, boolean debug) {
     return "loads plugin";
   }
   
   protected void execute(Event event) {
     String name = (String)this.plugin.getSingle(event);
     
     name = name.replaceAll("/", File.separator);
     
     File plugin = new File(name);
     try {
       Bukkit.getPluginManager().loadPlugin(plugin);
     } catch (UnknownDependencyException|InvalidPluginException|InvalidDescriptionException e) {
       e.printStackTrace();
     }
   }
 }


