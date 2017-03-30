package me.sharpjaws.sharpSK.hooks.LightAPI;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ru.beykerykt.lightapi.LightAPI;
import ru.beykerykt.lightapi.chunks.ChunkInfo;

public class EffCreateLight extends Effect {
	private Expression<Location> loc;
	private Expression<Integer> int1;
	private Expression<Boolean> async;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		loc = (Expression<Location>) expr[0];
		int1 = (Expression<Integer>) expr[1];
		async = (Expression<Boolean>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "create light at %location%";
	}
	@Override
	protected void execute(Event e) {
		if (Bukkit.getPluginManager().getPlugin("LightAPI").getDescription().getVersion().matches("3.\\d.\\d")){
			for(ChunkInfo info: LightAPI.collectChunks(loc.getSingle(e).getWorld(), loc.getSingle(e).getBlockX(), loc.getSingle(e).getBlockY(), loc.getSingle(e).getBlockZ())){
				LightAPI.updateChunk(info);
			}
	LightAPI.createLight(loc.getSingle(e).getWorld(), loc.getSingle(e).getBlockX(),loc.getSingle(e).getBlockY(),loc.getSingle(e).getBlockZ(), int1.getSingle(e), async.getSingle(e));
	
	}else{
		for(ChunkInfo info: LightAPI.collectChunks(loc.getSingle(e).getWorld(), loc.getSingle(e).getBlockX(), loc.getSingle(e).getBlockY(), loc.getSingle(e).getBlockZ())){
			LightAPI.updateChunk(info);
		}
	LightAPI.createLight(loc.getSingle(e).getWorld(), loc.getSingle(e).getBlockX(),loc.getSingle(e).getBlockY(),loc.getSingle(e).getBlockZ(), int1.getSingle(e), async.getSingle(e));
	
}
}
}
