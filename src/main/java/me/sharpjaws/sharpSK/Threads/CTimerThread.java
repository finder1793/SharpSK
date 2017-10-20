package me.sharpjaws.sharpSK.Threads;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitScheduler;

import me.sharpjaws.sharpSK.TimerHandler;

public class CTimerThread extends Thread {

	private int secs;
	private String Tname;
	private boolean active;
	private int Countdown;
	private int timetointv;
	private int interv;
	private boolean paused;
	private boolean testmode;

	public CTimerThread(String name, int seconds, Boolean activeT, int interval) {
		this.active = activeT;
		this.secs = seconds;
		this.Tname = name;
		this.interv = interval;
		this.testmode = false;

	}

	public CTimerThread(String name, int seconds, int interval, Boolean testmode) {
		this.active = false;
		this.secs = seconds;
		this.Tname = name;
		this.interv = interval;
		this.testmode = testmode;

	}

	File cache = null;
	YamlConfiguration Tcache = null;
	BukkitScheduler scheduler = null;

	@Override
	public void run() {

		if (!testmode) {
			scheduler = Bukkit.getServer().getScheduler();
		}
		this.instance().Countdown = secs + 1;
		this.setName(Tname);
		if (this.instance().Countdown < interv) {
			this.interv = 0;
		} else {
			timetointv = -1;
		}

		try {
			Map<String, Integer> timer = new HashMap<String, Integer>();
			if (active && !testmode) {
				cache = new File(Bukkit.getPluginManager().getPlugin("SharpSK").getDataFolder(), "Tcache.yml");
				Tcache = YamlConfiguration.loadConfiguration(cache);
			}
			while (!(Countdown < 2)) {
				synchronized (this) {
					while (paused) {
						wait();
					}
				}
				Countdown--;
				if (interv > 0) {
					timetointv++;

				}
				if (active && !testmode) {

					timer.put(this.getName(), this.getTime());
					Tcache.createSection("timers", timer);
					Tcache.getMapList("timers").add(timer);
					try {
						Tcache.save(cache);
					} catch (IOException e) {

					}
				}
				if (interv > 0) {
					if (timetointv >= interv) {
						if (!testmode) {
							scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"),
									new TimerHandler(Tname, Countdown, 1, 1));
						}
						timetointv = 0;
					}
				} else {
					if (!testmode) {
						scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"),
								new TimerHandler(Tname, Countdown, 1, 1));
					}
				}

				CTimerThread.sleep(1000);
			}
			if (!testmode) {
				scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"),
						new TimerHandler(Tname, Countdown, 2, 1));
				if (active) {

					timer.put(this.getName(), 0);
					Tcache.createSection("timers", timer);
					Tcache.getMapList("timers").add(timer);
					try {
						Tcache.save(cache);
					} catch (IOException e) {

					}
				}
			}
			this.instance().interrupt();

		} catch (InterruptedException e) {

			this.instance().interrupt();
		}

	}

	// Method Calls:

	public void addTime(int time) {
		this.instance().Countdown = this.instance().Countdown + time;
	}

	public void setTime(int time) {
		this.instance().Countdown = time;
	}

	public int getTime() {
		return this.instance().Countdown;
	}

	public void stopTimer(String name) {
		if (name.contains(Tname)) {
			if (paused) {
				resumeTimer(this.getName());
			}
			this.instance().Countdown = 0;
			this.instance().interrupt();
		}
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

	public void removeTime(int time) {
		this.instance().Countdown = this.instance().Countdown - time;

	}

	public CTimerThread instance() {
		return this;
	}

	public Boolean isActive() {
		return this.active;
	}

	public Boolean isPaused() {
		return this.paused;
	}

}
