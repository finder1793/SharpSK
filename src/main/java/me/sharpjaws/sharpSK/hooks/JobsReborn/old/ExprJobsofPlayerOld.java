package me.sharpjaws.sharpSK.hooks.JobsReborn.old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprJobsofPlayerOld extends SimpleExpression<Job> {
	private Expression<OfflinePlayer> p;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Job> getReturnType() {
		return Job.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		p = (Expression<OfflinePlayer>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "jobs of %offlineplayer%";
	}

	@Override
	@Nullable
	protected Job[] get(Event e) {

		List<Job> a = new ArrayList<Job>();
		try {
			if (!p.getSingle(e).isOnline()) {
				for (Job j : Jobs.getJobs()) {

					if (Jobs.getPlayerManager()
							.getJobsPlayerOffline(Jobs.getPlayerManager().getPlayerInfo(p.getSingle(e).getUniqueId()))
							.isInJob(j) == true) {
						a.add(j);
					}

				}
			} else {
				for (Job j : Jobs.getJobs()) {
					if (Jobs.getPlayerManager().getJobsPlayer(p.getSingle(e).getPlayer()).isInJob(j) == true) {
						a.add(j);
					}

				}
			}
		} catch (NullPointerException ex) {
			a.clear();
		}
		return Arrays.copyOf(a.toArray(), a.toArray().length, Job[].class);
	}

}
