 package me.sharpjaws.sharpSK.WorldEdit;
 
 import java.io.File;
import java.util.regex.Matcher;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.schematic.SchematicFormat;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
 
 
 public class EffPasteSchematic
   extends Effect
 {
   private Expression<?> name;
   private Expression<?> loc;
   
   public boolean init(Expression<?>[] expression, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
   {
     this.name = expression[0];
     this.loc = expression[1];
     
     return true;
   }
   
   public String toString(Event e, boolean debug) {
     return "Paste schematic \"" + this.name + "\" at " + this.loc;
   }
   
   protected void execute(Event event) {
     String name = (String)this.name.getSingle(event);
     Location loc = (Location)this.loc.getSingle(event);
     try
     {
       paste(name, loc);
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   
   private static boolean paste(String f, Location loc) throws Exception {
     File file;

     if (f.startsWith("/")) {
       file = new File(
         (f + ".schematic").replaceAll("/", 
         Matcher.quoteReplacement(File.separator)));
     } else {
       file = new File(
       
         ("plugins/WorldEdit/schematics/" + (f.contains(".") ? f : new StringBuilder(String.valueOf(f)).append(".schematic").toString())).replaceAll("/", 
         Matcher.quoteReplacement(File.separator)));
     }
     Vector v = new Vector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
     SchematicFormat format = SchematicFormat.getFormat(file);
     if ((!file.exists()) && (file.isDirectory())) {
       return false;
     }
     EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(loc.getWorld()), 200000);
     
     CuboidClipboard cc = null;
     try {
       cc = format.load(file);
     } catch (Exception e) {
       e.printStackTrace();
     }
     cc.paste(es, v, false);
     return true;
   
   }
 }

