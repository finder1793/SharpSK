package me.sharpjaws.sharpSK.Effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.SharpSK;
import me.sharpjaws.sharpSK.Threads.CTickTimerThread;
import me.sharpjaws.sharpSK.Threads.CTimerThread;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Timer Create")
@Description("Creates a timer")
@Examples({ "command /createtimer:", "trigger:", "\tcreate timer \"Test\" for 15 minutes ", " ", })
@Since("1.5, 1.6.4")
public class EffTimerCreate extends Effect {
	private Expression<String> s;
	private Expression<Timespan> duration;
	private Expression<Boolean> active;
	private Expression<Timespan> interval;
	int task;
	int mark;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult result) {
		s = (Expression<String>) expr[0];
		duration = (Expression<Timespan>) expr[1];
		active = (Expression<Boolean>) expr[2];
		interval = (Expression<Timespan>) expr[3];
		this.mark = result.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "create (-1¦timer|1¦timer in ticks) %string% for %timespan% [keep active %-boolean%] [[with] (interval|delay) %-timespan% [between ticks]]";
	}

	@Override
	protected void execute(final Event e) {
		String timer = s.getSingle(e).toLowerCase();
		for (Thread t : Thread.getAllStackTraces().keySet()) {

			if (t instanceof CTimerThread && t.isAlive()) {
				CTimerThread ti = (CTimerThread) t;
				if (ti.getName().equals(timer)) {
					SharpSK core = (SharpSK) Bukkit.getPluginManager().getPlugin("SharpSK");
					core.getLogger().warning("Timer " + "\"" + s.getSingle(e) + "\""
							+ " could not be created because a timer already exists with that name.");
					return;
				}
			} else if (t instanceof CTickTimerThread && t.isAlive()) {
				CTickTimerThread ti = (CTickTimerThread) t;
				if (ti.getName().equals(timer)) {
					SharpSK core = (SharpSK) Bukkit.getPluginManager().getPlugin("SharpSK");
					core.getLogger().warning("Timer " + "\"" + s.getSingle(e) + "\""
							+ " could not be created because a timer already exists with that name.");
					return;
				}
			}
		}

		if (mark == -1) {
			if (active == null) {
				if (interval != null) {
					CTimerThread th = new CTimerThread(s.getSingle(e), (int) duration.getSingle(e).getTicks_i() / 20,
							false, (int) interval.getSingle(e).getTicks_i() / 20);
					th.instance().start();
				} else {
					CTimerThread th = new CTimerThread(s.getSingle(e), (int) duration.getSingle(e).getTicks_i() / 20,
							false, 0);
					th.instance().start();
				}
			} else {
				if (!active.getSingle(e)) {

					if (interval != null) {
						CTimerThread th = new CTimerThread(s.getSingle(e),
								(int) duration.getSingle(e).getTicks_i() / 20, false,
								(int) interval.getSingle(e).getTicks_i() / 20);
						th.instance().start();
					} else {
						CTimerThread th = new CTimerThread(s.getSingle(e),
								(int) duration.getSingle(e).getTicks_i() / 20, false, 0);
						th.instance().start();
					}

				} else if (active.getSingle(e)) {
					if (interval != null) {
						CTimerThread th = new CTimerThread(s.getSingle(e),
								(int) duration.getSingle(e).getTicks_i() / 20, true,
								(int) interval.getSingle(e).getTicks_i() / 20);
						th.instance().start();
					} else {
						CTimerThread th = new CTimerThread(s.getSingle(e),
								(int) duration.getSingle(e).getTicks_i() / 20, true, 0);
						th.instance().start();
					}
				}
			}

		} else if (mark == 1) {
			if (active == null) {
				if (interval != null) {
					CTickTimerThread th = new CTickTimerThread(s.getSingle(e), (int) duration.getSingle(e).getTicks_i(),
							false, (int) interval.getSingle(e).getTicks_i());
					th.instance().start();
				} else {
					CTickTimerThread th = new CTickTimerThread(s.getSingle(e), (int) duration.getSingle(e).getTicks_i(),
							false, 0);
					th.instance().start();
				}
			} else {
				if (!active.getSingle(e)) {
					if (interval != null) {
						CTickTimerThread th = new CTickTimerThread(s.getSingle(e),
								(int) duration.getSingle(e).getTicks_i(), false,
								(int) interval.getSingle(e).getTicks_i());
						th.instance().start();
					} else {
						CTickTimerThread th = new CTickTimerThread(s.getSingle(e),
								(int) duration.getSingle(e).getTicks_i(), false, 0);
						th.instance().start();
					}
				} else if (active.getSingle(e)) {
					if (interval != null) {
						CTickTimerThread th = new CTickTimerThread(s.getSingle(e),
								(int) duration.getSingle(e).getTicks_i(), true,
								(int) interval.getSingle(e).getTicks_i());
						th.instance().start();
					} else {
						CTickTimerThread th = new CTickTimerThread(s.getSingle(e),
								(int) duration.getSingle(e).getTicks_i(), true, 0);
						th.instance().start();
					}
				}
			}
		}

	}
}
