package me.sharpjaws.sharpsk.hooks.WorldEdit;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.SchematicFormat;
import com.sk89q.worldedit.session.ClipboardHolder;
import me.sharpjaws.sharpsk.SharpSK;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.io.File;
import java.util.regex.Matcher;

public class EffSaveClipToSchematic extends Effect {
    private Expression<Player> pl;
    private Expression<String> schem;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        pl = (Expression<Player>) expr[0];
        schem = (Expression<String>) expr[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "[sharpsk] [worldedit] save clipboard of %player% (to|as) [schem[atic]] %string%";
    }

    @Override
    protected void execute(Event e) {
        WorldEditPlugin wep = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        File file = new File(("plugins/WorldEdit/schematics/" + (schem.getSingle(e).contains(".") ? schem.getSingle(e)
                : schem.getSingle(e) + ".schematic"))
                .replaceAll("/", Matcher.quoteReplacement(File.separator)));
        try {
            LocalSession session = wep.getSession(pl.getSingle(e));
            ClipboardHolder selection = wep.getSession(pl.getSingle(e)).getClipboard();

            Vector min = selection.getClipboard().getMinimumPoint();
            Vector max = selection.getClipboard().getMaximumPoint();
            EditSession editSession = session.createEditSession(wep.wrapPlayer(pl.getSingle(e)));

            editSession.enableQueue();
            CuboidClipboard clipboard = new CuboidClipboard(max.subtract(min).add(new Vector(1, 1, 1)), min);
            clipboard.copy(editSession);
            SchematicFormat.MCEDIT.save(clipboard, file);
            editSession.flushQueue();

        } catch (EmptyClipboardException e1) {
            SharpSK core = SharpSK.instance;
            core.getLogger()
                    .warning("Failed to save schematic: " + "\"" + schem.getSingle(e) + "\"" + " Clipboard was empty");
        } catch (Exception e1) {
            SharpSK core = SharpSK.instance;
            core.getLogger()
                    .warning("Failed to save schematic: " + "\"" + schem.getSingle(e) + "\"" + " An error occurred");
        }

    }

}
