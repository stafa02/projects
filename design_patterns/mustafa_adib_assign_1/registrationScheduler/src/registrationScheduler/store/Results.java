package registrationScheduler.store;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import registrationScheduler.constants.SchedulerConstants;
import registrationScheduler.util.FileProcessor;

/**
 * This class writes result of registration to output file.
 */
public class Results implements StdoutDisplayInterface, FileDisplayInterface {
	private Map<String, List<String>> allocatedMap;
	private File outputFile;
	private float avg;

	public Results(Map<String, List<String>> allocatedMapIn, String outputFilePath, int sum) {
		allocatedMap = allocatedMapIn;
		outputFile = new File(outputFilePath);
		avg = (((float) sum) / SchedulerConstants.STUDENT_COUNT);
	}

	public void writeScheduleToScreen() {
	}

	/**
	 * @see registrationScheduler.store.FileDisplayInterface#writeScheduleToFile()
	 */
	public void writeScheduleToFile() {
		try {
			FileProcessor fp = new FileProcessor(outputFile, true);
			for (int i = 1; i <= 50; i++) {
				String student = SchedulerConstants.STUDENT_PREFIX + i;
				fp.write(student);
				for (String course : allocatedMap.get(student)) {
					fp.write(" " + course);
				}
				fp.write("\n");
			}
			fp.write("Average preference_score is: " + avg);
			fp.close();
		} catch (IOException e) {
			System.err.println("Error while writing output");
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
	}
	
	@Override
	public String toString(){
		return "Output File: "+outputFile.getName();
	}
}