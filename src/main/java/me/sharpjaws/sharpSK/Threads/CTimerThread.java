package me.sharpjaws.sharpSK.Threads;

import org.bukkit.Bukkit;

import me.sharpjaws.sharpSK.EvtTimerComplete;
import me.sharpjaws.sharpSK.EvtTimerTick;

public class CTimerThread extends Thread{
	
	
	private int secs;
	private String Tname;
	private int Countdown;
	
	public CTimerThread(String name, int seconds){
	
		this.secs = seconds;
		this.Tname = name;		
	}
	
	
	@Override
    public void run() {
		this.instance().Countdown = secs +1;
		this.setName(Tname);	
		try {
		
		while (!(Countdown < 2)){
			Countdown--;
			EvtTimerTick ev = new EvtTimerTick(this.getName(),Countdown);
				Bukkit.getServer().getPluginManager().callEvent(ev);
				CTimerThread.sleep(1000);		
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
			

}
