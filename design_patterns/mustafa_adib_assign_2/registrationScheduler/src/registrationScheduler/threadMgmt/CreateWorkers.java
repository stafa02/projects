package registrationScheduler.threadMgmt;

import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Scheduler;

public class CreateWorkers {
	private FileProcessor pref,addDrop;
	private Scheduler scheduler;

	/**
	 * @param prefIn preference input file
	 * @param addDropIn preference input file
	 * @param schedulerIn scheduler instance
	 */
	public CreateWorkers(FileProcessor prefIn, FileProcessor addDropIn, Scheduler schedulerIn) {
		Logger.writeMessage("create workers constructor invoked\n", Logger.DebugLevel.CONSTRUCTOR);
		pref = prefIn;
		addDrop = addDropIn;
		scheduler = schedulerIn;
	}

	/**
	 * 
	 * @param noOfThreads total threads to run
	 */
	public void startWorkers(int noOfThreads) {
		Thread[] workers = new Thread[noOfThreads];
		for (int i = 0; i < noOfThreads; i++) {
			WorkerThread worker = new WorkerThread(pref,addDrop,scheduler);
			workers[i] = new Thread(worker, "thread " + (i + 1));
			workers[i].start();
		}

		for (int j = 0; j < noOfThreads; ++j) {
			try {
				workers[j].join(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	@Override
	public String toString(){
		return "creates workers";
	}
}