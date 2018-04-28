package me.sharpjaws.sharpSK.Threads;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitScheduler;

import me.sharpjaws.sharpSK.TimerHandler;

public class CTickTimerThread extends Thread {

	private int ticks;
	private String Tname;
	private boolean active;
	private int countdown;
	private int timetointv;
	private int interv;
	private boolean paused;
	Map<String, Integer> timer;

	public CTickTimerThread(String name, int ticks, Boolean activeT, int interval) {
		this.active = activeT;
		this.ticks = ticks;
		this.Tname = name;
		this.interv = interval;
		timer = new HashMap<String, Integer>();
	}

	File cache = new File(Bukkit.getPluginManager().getPlugin("SharpSK").getDataFolder(), "TTickcache.yml");
	YamlConfiguration Tcache = YamlConfiguration.loadConfiguration(cache);
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

	@Override
	public void run() {
		this.instance().countdown = ticks + 1;
		this.setName(Tname);
		if (this.instance().countdown < interv) {
			this.interv = 0;
		} else {
			timetointv = -1;
		}

		try {
			while (!(countdown < 2)) {
				synchronized (this) {
					while (paused) {
						wait();
					}
				}
				countdown--;
				if (interv > 0) {
					timetointv++;

				}
				if (active == true) {
					timer.put(this.getName(), this.getTime());
					Tcache.createSection("timers", timer);
					Tcache.getMapList("timers").add(timer);
					try {
						Tcache.save(cache);
					} catch (IOException e) {
						e.printStackTrace();
						this.interrupt();
					}
				}
				if (interv > 0) {
					if (timetointv >= interv) {
						scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"),
								new TimerHandler(Tname, countdown, 1, 2));
						timetointv = 0;
					}
				} else {
					scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"),
							new TimerHandler(Tname, countdown, 1, 2));
				}
				CTickTimerThread.sleep(50);
			}

			scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"), new TimerHandler(Tname, countdown, 2, 2));
			if (active == true) {
				timer.put(this.getName(), 0);
				Tcache.createSection("timers", timer);
				Tcache.getMapList("timers").add(timer);
				try {
					Tcache.save(cache);
				} catch (IOException e) {
					this.instance().interrupt();
				}
			}
			this.instance().interrupt();

		} catch (InterruptedException e) {
			this.instance().interrupt();
		}
	}

	// Method Calls:

	public void addTime(int time) {
		this.instance().countdown = this.instance().countdown + time;
	}

	public void setTime(int time) {
		this.instance().countdown = time;
	}

	public void pauseTimer(String name) {
		if (name.contains(Tname)) {
			paused = true;
		}
	}

	public synchronized void resumeTimer(String name) {
		if (name.contains(Tname)) {
			paused = false;
			notify();
		}
	}

	public int getTime() {
		return this.instance().countdown;
	}

	public void stopTimer(String name) {
		if (name.contains(Tname)) {
			this.instance().countdown = 0;
			if (this.instance().active) {
				this.instance().active = false;
			}
			this.instance().interrupt();
		}
	}

	public void removeTime(int time) {
		this.instance().countdown = this.instance().countdown - time;

	}

	public CTickTimerThread instance() {
		return this;
	}

	public Boolean isActive() {
		return this.active;
	}

	public Boolean isPaused() {
		return this.paused;
	}

}
