package me.sharpjaws.sharpSK.hooks.JobsReborn;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.api.JobsJoinEvent;
import com.gamingmesh.jobs.api.JobsLeaveEvent;
import com.gamingmesh.jobs.api.JobsLevelUpEvent;
import com.gamingmesh.jobs.api.JobsPaymentEvent;
import com.gamingmesh.jobs.container.Job;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Converter;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.DefaultExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.Converters;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class JobsRegistry {

	public static void registerJobs(){
		Skript.registerEvent("Job Join", SimpleEvent.class, JobsJoinEvent.class, "job join");
		EventValues.registerEventValue(JobsJoinEvent.class, Player.class,
				new Getter<Player, JobsJoinEvent>() {
					@Override
					@Nullable
					public Player get(JobsJoinEvent e) {
						Player p = e.getPlayer().getPlayer();
						return p;
					}
				}, 0);
		Skript.registerEvent("Job Leave", SimpleEvent.class, JobsLeaveEvent.class, "job leave");
		EventValues.registerEventValue(JobsLeaveEvent.class, Player.class,
				new Getter<Player, JobsLeaveEvent>() {
					@Override
					@Nullable
					public Player get(JobsLeaveEvent e) {
						Player p = e.getPlayer().getPlayer();
						return p;
					}
				}, 0);
		Skript.registerEvent("Job Payment", SimpleEvent.class, JobsPaymentEvent.class, "job payment");
		EventValues.registerEventValue(JobsPaymentEvent.class, Player.class,
				new Getter<Player, JobsPaymentEvent>() {
					@Override
					@Nullable
					public Player get(JobsPaymentEvent e) {
						Player p = e.getPlayer().getPlayer();
						return p;
					}
				}, 0);
		Skript.registerEvent("Job Levelup", SimpleEvent.class, JobsLevelUpEvent.class, "job levelup");
		EventValues.registerEventValue(JobsLevelUpEvent.class, Player.class,
				new Getter<Player, JobsLevelUpEvent>() {
					@Override
					@Nullable
					public Player get(JobsLevelUpEvent e) {
						Player p = e.getPlayer().getPlayer();
						return p;
					}
				}, 0);
		
		try {

			Classes.registerClass(new ClassInfo<Job>(Job.class, "job").name("Job").parser(new Parser<Job>() {
			
			@Override
			public String getVariableNamePattern() {
			return ".+";
			}
			
			@Override
			public boolean canParse(final ParseContext cont) {
				return true;
}
			@Override
			@Nullable
			public Job parse(String s, ParseContext cont) {
				try {
					return Jobs.getJob(s.replace(" ", "_").toUpperCase());
				} catch (IllegalArgumentException e) {
					return null;
				}
			}

			@Override
			public String toString(Job job, int i) {
				return job.getName().replace("_", " ").toUpperCase();
			}

			@Override
			public String toVariableNameString(Job job) {
				return job.getName().replace("_", " ").toUpperCase();
			}		
		}).user("job?s?"));
	}catch (IllegalArgumentException ex){

	}
		Converters.registerConverter(String.class, Job.class, new Converter<String, Job>(){

			@Override
			@Nullable
			public Job convert(String s) {
				return Jobs.getJob(s.replace(" ", "_").toUpperCase());
			}
			
		});
		Converters.registerConverter(Job.class, String.class, new Converter<Job, String>() {

			@Override
			@Nullable
			public String convert(Job j) {
				return j.getName().replace("_", " ").toUpperCase();
			}
	
});
		
	
		Skript.registerEffect(EffMakeJoinJob.class, "make %player% join job %job%");
		Skript.registerEffect(EffMakeLeaveJob.class, "make %player% leave job %job%");
		Skript.registerEffect(EffLeaveAllJobs.class, "make %player% leave all jobs ");
		Skript.registerEffect(EffJoinAllJobs.class, "make %player% join all jobs");
		Skript.registerCondition(CondPlayerInJob.class, "%player% is in job %job%");
		Skript.registerCondition(CondPlayerNotInJob.class, "%player% is not in job %job%");
		Skript.registerExpression(ExprAllJobs.class, Job.class, ExpressionType.COMBINED, "[(all|the)] (jobs|1¦JRJobs)");
		
		if (Bukkit.getPluginManager().getPlugin("Jobs").getDescription().getVersion().contains("3.5") || Bukkit.getPluginManager().getPlugin("Jobs").getDescription().getVersion().contains("3.6") || Bukkit.getPluginManager().getPlugin("Jobs").getDescription().getVersion().contains("3.7")|| Bukkit.getPluginManager().getPlugin("Jobs").getDescription().getVersion().contains("3.8")|| Bukkit.getPluginManager().getPlugin("Jobs").getDescription().getVersion().contains("3.9")){
		
		Skript.registerExpression(ExprJobsofPlayer.class, Job.class, ExpressionType.SIMPLE, "jobs of %offlineplayer%");
		Skript.registerExpression(ExprJobLevel.class, Integer.class, ExpressionType.SIMPLE, "[the] level of job %job% of %offlineplayer%");
		}else{			
		Skript.registerExpression(ExprJobsofPlayerOld.class, Job.class, ExpressionType.PROPERTY, "jobs of %offlineplayer%");
		Skript.registerExpression(ExprJobLevelOld.class, Integer.class, ExpressionType.SIMPLE, "[the] level of job %job% of %offlineplayer%");
				
		}
		
	}
}

		
