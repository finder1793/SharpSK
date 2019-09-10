package me.sharpjaws.sharpsk.threads;

import me.sharpjaws.sharpsk.TimerHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CTimerThread extends Thread {

    private final int secs;
    private final String Tname;
    private final boolean testmode;
    private final Map<String, Integer> timer;
    private boolean active;
    private int countdown;
    private int timetointv;
    private int interv;
    private boolean paused;
    private File cache = null;
    private YamlConfiguration Tcache = null;
    private BukkitScheduler scheduler = null;
    public CTimerThread(String name, int seconds, Boolean activeT, int interval) {
        this.active = activeT;
        this.secs = seconds;
        this.Tname = name;
        this.interv = interval;
        this.testmode = false;
        timer = new HashMap<>();
    }
    public CTimerThread(String name, int seconds, int interval, Boolean testmode) {
        this.active = false;
        this.secs = seconds;
        this.Tname = name;
        this.interv = interval;
        this.testmode = testmode;
        timer = new HashMap<>();
    }

    @Override
    public void run() {

        if (!testmode) {
            scheduler = Bukkit.getServer().getScheduler();
        }
        this.instance().countdown = secs + 1;
        this.setName(Tname);
        if (this.instance().countdown < interv) {
            this.interv = 0;
        } else {
            timetointv = -1;
        }
        try {
            if (active && !testmode) {
                cache = new File(Bukkit.getPluginManager().getPlugin("SharpSK").getDataFolder(), "Tcache.yml");
                Tcache = YamlConfiguration.loadConfiguration(cache);
            }
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
                if (active && !testmode) {
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
                        if (!testmode) {
                            scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"),
                                    new TimerHandler(Tname, countdown, 1, 1));
                        }
                        timetointv = 0;
                    }
                } else {
                    if (!testmode) {
                        scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"),
                                new TimerHandler(Tname, countdown, 1, 1));
                    }
                }

                CTimerThread.sleep(1000);
            }
            if (!testmode) {
                scheduler.runTask(Bukkit.getPluginManager().getPlugin("SharpSK"),
                        new TimerHandler(Tname, countdown, 2, 1));
                if (active) {
                    timer.put(this.getName(), 0);
                    Tcache.createSection("timers", timer);
                    Tcache.getMapList("timers").add(timer);
                    try {
                        Tcache.save(cache);
                    } catch (IOException e) {
                        e.printStackTrace();
                        this.interrupt();
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
        this.instance().countdown = this.instance().countdown + time;
    }

    public int getTime() {
        return this.instance().countdown;
    }

    public void setTime(int time) {
        this.instance().countdown = time;
    }

    public void stopTimer(String name) {
        if (name.contains(Tname)) {
            if (paused) {
                resumeTimer(this.getName());
            }
            if (this.instance().active) {
                this.instance().active = false;
                try {
                    Tcache.save(cache);
                } catch (IOException e) {
                    this.instance().countdown = 0;
                    this.instance().interrupt();
                }
            }
            this.instance().countdown = 0;
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
        this.instance().countdown = this.instance().countdown - time;
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
