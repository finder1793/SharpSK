package me.sharpjaws.sharpSK.hooks.JobsReborn;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobsPlayer;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffJoinAllJobs extends Effect {
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		player = (Expression<Player>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "make %player% join all jobs";
	}

	@Override
	protected void execute(Event e) {
		JobsPlayer p = Jobs.getPlayerManager().getJobsPlayer(player.getSingle(e));
		List<Job> l = Jobs.getJobs();
		for (int i = 0; i < l.size(); i++) {
			Jobs.getPlayerManager().joinJob(p, l.get(i));
		}
	}
}
