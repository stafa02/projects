package registrationScheduler.driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import registrationScheduler.store.Results;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Scheduler;

public class Driver {

	public static void main(String args[]) {
		Driver dr = new Driver();
		dr.validateArgs(args);
		try {
			FileProcessor pref = new FileProcessor(new BufferedReader(new FileReader(args[0])));
			FileProcessor addDrop = new FileProcessor(new BufferedReader(new FileReader(args[1])));
			Results result = new Results();
			Scheduler scheduler = new Scheduler(result);
			CreateWorkers create = new CreateWorkers(pref, addDrop, scheduler);
			create.startWorkers(Integer.parseInt(args[3]));
			result.setPreferenceScore(scheduler.calculatePreferenceScore());
			result.writeSchedulesToFile(args[2]);
			result.writeScheduleToStdout();
		} catch (IOException e) {
			System.err.println("error reading input file");
			e.printStackTrace();
			System.exit(1);
		} finally {

		}
	}

	/**
	 * 
	 * @param args command line arguments
	 */
	private void validateArgs(String args[]) {
		if (args.length == 5) {

			try {
				int threads = Integer.parseInt(args[3]);
				int debugLevel = Integer.parseInt(args[4]);
				Logger.setDebugValue(debugLevel);

				if (debugLevel < 0 || debugLevel > 4) {
					System.err.println("Invalid value for debug level, expected range between 0-4");
					System.exit(1);
				}
				if (threads < 1 || threads > 3) {
					System.err.println("Invalid value for threads, expected range between 1-3");
					System.exit(1);
				}

			} catch (IllegalArgumentException ex) {
				System.err.println("NumberFormatException-Cannot parse to integer.");
				ex.printStackTrace();
				System.exit(1);
			}
		} else {
			System.err.println("Invalid number of arguments. Expected 5 arguments");
			System.exit(1);
		}
	}
	
	@Override
	public String toString(){
		return "Registration scheduler driver";
	}
}