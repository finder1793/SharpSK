package me.sharpjaws.sharpSK.hooks.JobsReborn;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;



public class ExprJobLevelOld extends SimpleExpression<Integer> {
	
	
	private Expression<OfflinePlayer> p;
	private Expression<Job> j;

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		j = (Expression<Job>) expr[0];
		p = (Expression<OfflinePlayer>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
	return "job level of job %job% of player %offlineplayer%";
	}

	@Override
	@Nullable
	protected Integer[] get(Event e) {
		
		int level = 0;
		try{
			if (!p.getSingle(e).isOnline()){
        level = Jobs.getPlayerManager().getJobsPlayerOffline(p.getSingle(e)).getJobProgression(j.getSingle(e)).getLevel();			
			}else{
		 level = Jobs.getPlayerManager().getJobsPlayer(p.getSingle(e).getPlayer()).getJobProgression(j.getSingle(e)).getLevel();					
			}
					
		}catch(NullPointerException ex){
			level = 0;
		}
		return new Integer[]{level};
	}


	
}