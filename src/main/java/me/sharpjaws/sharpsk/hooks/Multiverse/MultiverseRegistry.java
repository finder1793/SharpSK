package me.sharpjaws.sharpsk.hooks.Multiverse;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.onarandombox.MultiverseCore.event.MVConfigReloadEvent;
import com.onarandombox.MultiverseCore.event.MVPlayerTouchedPortalEvent;
import com.onarandombox.MultiverseCore.event.MVWorldDeleteEvent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class MultiverseRegistry {

    @SuppressWarnings("unchecked")
    public static void registerMultiverse() {
        Skript.registerEvent("MV On Portal Touch", SimpleEvent.class, MVPlayerTouchedPortalEvent.class,
                "(mv|multiverse) portal ([touch|enter])");
        EventValues.registerEventValue(MVPlayerTouchedPortalEvent.class, Player.class,
                new Getter<Player, MVPlayerTouchedPortalEvent>() {
                    @Override
                    @Nullable
                    public Player get(MVPlayerTouchedPortalEvent e) {
                        return e.getPlayer();
                    }
                }, 0);
        EventValues.registerEventValue(MVPlayerTouchedPortalEvent.class, Location.class,
                new Getter<Location, MVPlayerTouchedPortalEvent>() {
                    @Override
                    @Nullable
                    public Location get(MVPlayerTouchedPortalEvent e) {
                        return e.getPlayer().getLocation();
                    }
                }, 0);
        Skript.registerEvent("MV On World Delete", SimpleEvent.class, MVWorldDeleteEvent.class,
                "(mv|multiverse) world delet[e|ion]");
        EventValues.registerEventValue(MVWorldDeleteEvent.class, World.class, new Getter<World, MVWorldDeleteEvent>() {
            @Override
            @Nullable
            public World get(MVWorldDeleteEvent e) {
                return e.getWorld().getCBWorld();
            }
        }, 0);
        EventValues.registerEventValue(MVWorldDeleteEvent.class, String.class,
                new Getter<String, MVWorldDeleteEvent>() {
                    @Override
                    @Nullable
                    public String get(MVWorldDeleteEvent e) {
                        return e.getWorld().getName();
                    }
                }, 0);
        Skript.registerEvent("MV On Config Reload", SimpleEvent.class, MVConfigReloadEvent.class,
                "(mv|multiverse) config reload");

        Skript.registerEffect(EffMVLoadWorld.class, "(mv|multiverse) load world %string%");
        Skript.registerEffect(EffMVUnloadWorld.class, "(mv|multiverse) unload world %string%");
        Skript.registerEffect(EffMVDeleteWorld.class, "(mv|multiverse) delete world %string%");
        Skript.registerEffect(EffMVCloneWorld.class, "(mv|multiverse) (clone|copy) world %string% to %string%");
        Skript.registerEffect(EffMVRemovePlayersFromWorld.class, "(mv|multiverse) remove players from world %string%");

        Skript.registerExpression(ExprUnloadedWorlds.class, String.class, ExpressionType.SIMPLE,
                "[(mv|multiverse)] [all] [the] unloaded worlds");

        Skript.registerCondition(CondMVUnloadedWorld.class, "[(mv|multiverse)] world %string% is unloaded");
        Skript.registerCondition(CondMVNotUnloadedWorld.class, "[(mv|multiverse)] world %string% is not unloaded");

    }

}
