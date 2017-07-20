package registrationScheduler.threadMgmt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Scheduler;

public class WorkerThread implements Runnable {
	private FileProcessor pref, addDrop;
	private Scheduler scheduler;
	
	/**
	 * 
	 * @param prefIn handle to preference file
	 * @param addDropIn handle to add drop file
	 * @param schedulerIn Scheduler instance
	 */
	public WorkerThread(FileProcessor prefIn, FileProcessor addDropIn, Scheduler schedulerIn) {
		Logger.writeMessage("worker thread constructor invoked\n", Logger.DebugLevel.CONSTRUCTOR);
		pref = prefIn;
		addDrop = addDropIn;
		scheduler = schedulerIn;
	}

	public void run() {
		Logger.writeMessage("threads run method invoked\n", Logger.DebugLevel.IN_RUN);
		try {
			String line;
			while ((line = pref.readLineFromFile()) != null) {
				String[] input = line.split("\\W+");
				List<String> courses = new ArrayList<String>();
				for (int i = 1; i < input.length; i++) {
					courses.add(input[i]);
				}
				scheduler.schedule(input[0], courses);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.err.println("Error in thread");
				System.exit(1);
			} finally {
			}
			while ((line = addDrop.readLineFromFile()) != null) {
				String[] input = line.split("\\W+");

				List<String> courses = new ArrayList<String>();
				for (int i = 2; i < input.length; i++) {
					courses.add(input[i]);
				}
				scheduler.modify(input[0], Integer.parseInt(input[1]), courses);
			}
		} catch (IOException e) {
			System.err.println("error reading from input file");
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
	}
	
	@Override
	public String toString(){
		return "Thread for registration scheduling";
	}
}