package me.sharpjaws.sharpSK.hooks.MythicMobs;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent;

public class ExprEvtMMDrops extends SimpleExpression<ItemStack> {

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "event-mmdrops";
	}


	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
		if (!ScriptLoader.isCurrentEvent(MythicMobDeathEvent.class)) {
			return false ;		
		}
		return true;
	}

	@Override
	@Nullable
	protected ItemStack[] get(Event e) {	
		if (e.getEventName().equals("MythicMobDeathEvent")) {
			List<ItemStack> a = ((MythicMobDeathEvent)e).getDrops();
		    ItemStack[] b = a.toArray(new ItemStack[a.size()]);
			return  b;
		}
		return null; 
	}
	@SuppressWarnings("unchecked")
	@Override
	public void change(Event e, Object[] deltas, Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.REMOVE_ALL) {			
				final List<ItemStack> drops = ((MythicMobDeathEvent)e).getDrops();
				drops.clear();
				((MythicMobDeathEvent)e).setDrops(drops);
		
			}
		if (mode == Changer.ChangeMode.DELETE) {	
				final List<ItemStack> drops = ((MythicMobDeathEvent)e).getDrops();
				drops.clear();
				((MythicMobDeathEvent)e).setDrops(drops);
		}
		if (mode == Changer.ChangeMode.SET) {	
			
			List<ItemStack> drops = ((MythicMobDeathEvent) e).getDrops();
		    
			drops.clear();
			((MythicMobDeathEvent)e).setDrops(drops);
		
			List<ItemStack> list = new ArrayList<ItemStack>(); 
		    
		   ItemStack[] items = (ItemStack[])deltas;
		  
		   for (ItemStack delta: items){
			   list.add(delta);
		   }
((MythicMobDeathEvent)e).setDrops(list);
		}
	}

	

	
		
	

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.REMOVE_ALL)
			return CollectionUtils.array(new Class[] { ItemStack.class });
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(new Class[] {ItemStack[].class, ItemStack.class});
		if (mode == Changer.ChangeMode.DELETE )
			return CollectionUtils.array(new Class[] { ItemStack.class });
		return null;
	}
}


