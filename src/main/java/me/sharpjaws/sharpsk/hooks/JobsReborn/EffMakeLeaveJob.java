package me.sharpjaws.sharpsk.hooks.JobsReborn;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobsPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffMakeLeaveJob extends Effect {
	private Expression<Player> player;
	private Expression<Job> j;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		player = (Expression<Player>) expr[0];
		j = (Expression<Job>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "make %player% leave job %job%";
	}

	@Override
	protected void execute(Event e) {
		JobsPlayer p = Jobs.getPlayerManager().getJobsPlayer(player.getSingle(e));
		try {
			Jobs.getPlayerManager().leaveJob(p, j.getSingle(e));
		} catch (NullPointerException localNullPointerException) {
		}
	}
}
