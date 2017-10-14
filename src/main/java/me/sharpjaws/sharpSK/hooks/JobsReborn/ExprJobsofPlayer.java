package me.sharpjaws.sharpSK.hooks.JobsReborn;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;


public class ExprJobsofPlayer extends SimpleExpression<Job> {
	private Expression<OfflinePlayer> p;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Job> getReturnType() {
		return Job.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(final Expression<?>[] expr, final int matchedPattern, final Kleenean paramKleenean,
			final SkriptParser.ParseResult ParseResult) {
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
		ArrayList<Job> a = new ArrayList<Job>();
		if (!a.isEmpty()){
			a.clear();
		}
		
		try {
		if (p.getSingle(e).isOnline()){		
		for (Job j : Jobs.getJobs()){		
		if (Jobs.getPlayerManager().getJobsPlayer(p.getSingle(e).getPlayer().getName()).isInJob(j)) {
		a.add(j);
	
				}
			}
		}else{
			for (Job j : Jobs.getJobs()){
				if (Jobs.getPlayerManager().getJobsPlayer(p.getSingle(e).getName()).isInJob(j)){
					a.add(j);
				}
			
			}
		}
		
		} catch (NullPointerException ex){
			return new Job[]{};
		}
		return a.toArray(new Job[a.size()]);

	}


}


