package me.sharpjaws.sharpSK.Threads;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitScheduler;

import me.sharpjaws.sharpSK.TimerHandler;

public class CTickTimerThread extends Thread{
	
	
	private int ticks;
	private String Tname;
	private boolean active;
	private int Countdown;
	private int timetointv;
	private int interv;
	private boolean paused;
	
	public CTickTimerThread(String name, int ticks, Boolean activeT, int interval){
		this.active = activeT;
		this.ticks = ticks;
		this.Tname = name;
		this.interv = interval;
	}
	File cache = new File(Bukkit.getPluginManager().getPlugin("SharpSK").getDataFolder(), "TTickcache.yml");
	YamlConfiguration Tcache = YamlConfiguration.loadConfiguration(cache);
	 BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
   
	
	
	@Override
    public void run() {
		
		
		this.instance().Countdown = ticks +1;
		this.setName(Tname);
		if (this.instance().Countdown < interv){
			this.interv = 0;
		}else{
			timetointv = -1;
		}

		
			
		try {
		Map<String, Integer> timer = new HashMap<String,Integer>();
		while (!(Countdown < 2)){
				 synchronized(this) {
		             while(paused) {
		                wait();
		             }
				 }
			Countdown--;
			if (interv > 0){
				timetointv++;
					
				}
			if (active == true){
				
			
				
			timer.put(this.getName(), this.getTime());
			Tcache.createSection("timers", timer);
			Tcache.getMapList("timers").add(timer);
			try {
				Tcache.save(cache);
			} catch (IOException e) {

				}
			}
			if (interv > 0){
				if (timetointv >= interv){
				scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"), new TimerHandler(Tname, Countdown, 1,2));
				timetointv = 0;
				}
			}else{
				scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"), new TimerHandler(Tname, Countdown, 1,2));	
			}
				CTickTimerThread.sleep(50);		
		}
		
		
	

		
		scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"), new TimerHandler(Tname, Countdown, 2,2));
		if (active == true){
			
			timer.put(this.getName(), 0);
			Tcache.createSection("timers", timer);
			Tcache.getMapList("timers").add(timer);
			try {
				Tcache.save(cache);
			} catch (IOException e) {

				}
			}
		this.instance().interrupt();
		
		} catch (InterruptedException e) {
		
			this.instance().interrupt();
		}
	}

	
	
	
	
	
	//Method Calls:
	
	
	
	
	
	
	
	
	
	
	public void addTime(int time){
		this.instance().Countdown = this.instance().Countdown + time;
	}
	
	public void setTime(int time){
		this.instance().Countdown = time;
	}
	
	public void pauseTimer(String name) {
		if (name.contains(Tname)){
		paused = true;
		}
	}
	public synchronized void resumeTimer(String name){
		if (name.contains(Tname)){
			paused = false;
			notify();
		}
	}
	public int getTime(){
		return this.instance().Countdown;
	}
	public void stopTimer(String name){
		if (name.contains(Tname)){
		this.instance().Countdown = 0;
		
		
		
		this.instance().interrupt();
		}
	}
	public void removeTime(int time){
		this.instance().Countdown = this.instance().Countdown - time;
		
	}
	public CTickTimerThread instance(){
	return this;
	}
	public Boolean isActive(){
		return this.active;
	}
	public Boolean isPaused(){
		return this.paused;
	}
			

}
