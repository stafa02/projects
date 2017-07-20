
package registrationScheduler.driver;

import java.util.List;
import java.util.Map;

import registrationScheduler.scheduler.Scheduler;
import registrationScheduler.store.FileDisplayInterface;
import registrationScheduler.store.Results;

public class Driver {

	/**
	 * Client for registration scheduler
	 * 
	 * @param args[0]
	 *            path to preference input file
	 * @param args[1]
	 *            path to registration input file
	 * @param args[2]
	 *            path to output file
	 * @param args[3]
	 *            debug level
	 */
	public static void main(String args[]) {
		if (args.length != 4) {
			System.err.println("Invalid  arguments, 3 expected");
			System.exit(1);
		}
		try {
			int debugLevel = Integer.parseInt(args[3]);
		} catch (NumberFormatException e) {
			System.err.println("4th argument should be a number");
			System.exit(1);
		} finally {
		}
		Scheduler scheduler = new Scheduler(args[0], args[1]);
		Map<String, List<String>> allocatedMap = scheduler.allocateCourses();
		FileDisplayInterface writeOutput = new Results(allocatedMap, args[2], scheduler.getSum());
		writeOutput.writeScheduleToFile();
	}

	@Override
	public String toString() {
		return "Registration scheduler driver";
	}

}
