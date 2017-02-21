package me.sharpjaws.sharpSK.Threads;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import me.sharpjaws.sharpSK.EvtTimerComplete;
import me.sharpjaws.sharpSK.EvtTimerTick;

public class CTimerThread extends Thread{
	
	
	private int secs;
	private String Tname;
	private boolean active;
	private int Countdown;

	public CTimerThread(String name, int seconds, Boolean activeT){
		this.active = activeT;
		this.secs = seconds;
		this.Tname = name;			
	}
	
	
	@Override
    public void run() {
		this.instance().Countdown = secs +1;
		this.setName(Tname);
		 Map<String, Integer> timer = new HashMap<String,Integer>();
		timer.put(this.Tname, this.getTime());
		File cache = new File(Bukkit.getPluginManager().getPlugin("SharpSK").getDataFolder(), "Tcache.yml");
		YamlConfiguration Tcache = YamlConfiguration.loadConfiguration(cache);
		Tcache.createSection("timers", timer);
		try {
		
		while (!(Countdown < 2)){
			Countdown--;
			try {
			EvtTimerTick ev = new EvtTimerTick(this.getName(),Countdown);
				Bukkit.getServer().getPluginManager().callEvent(ev);
			}catch (Exception ex){
		
			}
							
			if (active == true){
			Tcache.getMapList("timers").add(timer);	
			try {
				Tcache.save(cache);
			} catch (IOException e) {

			}
		}
				CTimerThread.sleep(1000);		
		}
		if (active == true){	
		}
		EvtTimerComplete ev2 = new EvtTimerComplete(this.getName());
		Bukkit.getServer().getPluginManager().callEvent(ev2);
		this.instance().interrupt();
		
		} catch (InterruptedException e) {
			this.instance().interrupt();
		}
	}
	public void addTime(int time){
		this.instance().Countdown = Countdown + time;
	}
	
	public void setTime(int time){
		this.instance().Countdown = time;
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
		this.Countdown = this.instance().Countdown - time;
		
	}
	public CTimerThread instance(){
	return this;
	}
	public Boolean isActive(){
		return this.active;
	}
			

}
