package me.sharpjaws.sharpsk.hooks.JobsReborn;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprJobLevel extends SimpleExpression<Integer> {

    private Expression<OfflinePlayer> p;
    private Expression<Job> j;

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
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
        return "job level of %job% of player %offlineplayer%";
    }

    @Override
    @Nullable
    protected Integer[] get(Event e) {

        int level = 0;
        try {
            if (!p.getSingle(e).isOnline()) {
                level = Jobs.getPlayerManager().getJobsPlayer(p.getSingle(e).getName())
                        .getJobProgression(j.getSingle(e)).getLevel();
            } else {
                level = Jobs.getPlayerManager().getJobsPlayer(p.getSingle(e).getPlayer())
                        .getJobProgression(j.getSingle(e)).getLevel();
            }
        } catch (NullPointerException ex) {
            level = 0;
        }
        return new Integer[]{level};
    }

}