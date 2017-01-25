package me.sharpjaws.sharpSK.Threads;

import javax.annotation.concurrent.ThreadSafe;

import org.bukkit.Bukkit;

import me.sharpjaws.sharpSK.EvtTimerComplete;
import me.sharpjaws.sharpSK.EvtTimerTick;

public class CTimerThread extends Thread{
	
	
	private int secs;
	private String Tname;
	
	public CTimerThread(String name, int seconds){
	
		secs = seconds;
		Tname = name;		
	}
	
	
	@Override
    public void run() {
		int Countdown = secs +1;
		this.setName(Tname);	
		
		while (Countdown >= 1){
			Countdown--;
			try {
				EvtTimerTick ev = new EvtTimerTick(this.getName(),Countdown);
				Bukkit.getServer().getPluginManager().callEvent(ev);
				sleep(1000);
			} catch (InterruptedException e) {
				this.interrupt();
			}
			
		}
		
		EvtTimerComplete ev2 = new EvtTimerComplete(this.getName());
		Bukkit.getServer().getPluginManager().callEvent(ev2);
		this.interrupt();
		
		
	}

}
