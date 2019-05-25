package me.sharpjaws.sharpsk.hooks.WorldEdit;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;

public class WorldEditRegistry {
    public static void registerWorldEdit() {

        if (Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") != null) {
            // PirateSK merged features
            if (Bukkit.getPluginManager().getPlugin("PirateSK") != null) {
                Skript.registerEffect(EffPasteSchematic.class, "sharpsk [worldedit] paste schematic %string% at %location% [exclude air %-boolean%] [rotate [by] %-number% [degrees]]");
            } else {
                Skript.registerEffect(EffPasteSchematic.class, "[sharpsk] [worldedit] paste schematic %string% at %location% [exclude air %-boolean%] [rotate [by] %-number% [degrees]]");
            }
            // -------------------------
            Skript.registerEffect(EffSaveClipToSchematic.class,
                    "[sharpsk] [worldedit] save clipboard of %player% (to|as) [schem[atic]] %string%");
            Skript.registerEffect(EffSaveSelectionToClipboard.class, "[sharpsk] [worldedit] save [selection] p[oint][ ]1 %location% p[oint][ ]2 %location% [with origin %-location%] to clip[board] of [player] %player%");

        }

    }
}
