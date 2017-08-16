package me.sharpjaws.sharpSK;

import static org.junit.Assert.fail;

import org.junit.Test;

import me.sharpjaws.sharpSK.Threads.CTimerThread;




public class SharpSKTest {
	@Test
	public void Timertest(){
System.out.println("Testing SharpSK Timers...");



		CTimerThread testtimer = new CTimerThread("test", 10,0, true);
		testtimer.instance().start();
		Boolean checkT = false;
		for (Thread t : Thread.getAllStackTraces().keySet()) {
	        if (t instanceof CTimerThread) {
	        	System.out.println("Thread: "+((CTimerThread)t).instance().getName() + " created. OK");
	        	checkT = true;
	        }
		}
		if (!checkT){
			fail("Timer Thread failed to create.");	
		}

			
				Thread test2 = new Thread(new Runnable() {

					@Override
					public void run() {
						assert testtimer != null;
						
						while (testtimer.getTime() > 9){
							try {
								 Thread.sleep(30);
							} catch (InterruptedException e) {}
						}
						assert testtimer != null;
						testtimer.pauseTimer(testtimer.getName());
						try {
							 Thread.sleep(50);
						} catch (InterruptedException e) {}
					
						if (testtimer.isPaused()){
						System.out.println("TimerThread: "+testtimer.getName() + " paused. OK");
						}else{
						System.out.println("TimerThread: "+testtimer.getName() + " was not paused successfully. FAILURE");
						 fail("Timer Thread failed to pause.");	
						}

						assert testtimer != null;
						testtimer.stopTimer(testtimer.getName());
						try {
							 Thread.sleep(200);
							} catch (InterruptedException e) {}
						
						
						for (Thread t : Thread.getAllStackTraces().keySet()) {
					        if (t instanceof CTimerThread) {
					        	System.out.println("TimerThread: "+((CTimerThread)t).instance().getName() + " was not terminated successfully. FAILURE");		        	
					        	fail("Timer Thread failed to stop.");	
					        	break;
					        	
					        }
						}
						System.out.println("Thread: "+testtimer.getName() + " stopped. OK");
						
					}});
					test2.run();
					
				
				
				
				
				
		
	}

}
