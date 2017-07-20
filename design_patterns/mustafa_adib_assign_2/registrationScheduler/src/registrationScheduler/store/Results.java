
package registrationScheduler.store;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registrationScheduler.util.FileDisplayInterface;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.StdoutDisplayInterface;

public class Results implements StdoutDisplayInterface, FileDisplayInterface {

	private Map<String, List<String>> allocatedCourses;
	private float preferenceScore;

	public Results() {
		Logger.writeMessage("Results constructor invoked\n", Logger.DebugLevel.CONSTRUCTOR);
		allocatedCourses = new HashMap<String, List<String>>();
	}

	@Override
	public void writeScheduleToStdout() {
		for (String student : allocatedCourses.keySet()) {
			System.out.print(student);
			for (String course : allocatedCourses.get(student)) {
				System.out.print(" " + course);
			}
			System.out.println();
		}
		System.out.println("Average preference_score is: " + preferenceScore);
	}

	@Override
	public void writeSchedulesToFile(String outFileName) {
		try {
			FileProcessor outFile = new FileProcessor(outFileName);
			for (String student : allocatedCourses.keySet()) {
				outFile.writeLineToFile(student);
				for (String course : allocatedCourses.get(student)) {
					outFile.writeLineToFile(" " + course);
				}
				outFile.writeLineToFile("\n");
			}
			outFile.writeLineToFile("Average preference_score is: " + preferenceScore);
			outFile.close();
		} catch (IOException e) {
			System.err.println("Error writing to output file");
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
	}

	/**
	 * 
	 * @param student
	 * @param courses
	 *            list of allocated courses
	 */
	public synchronized void addToResults(String student, List<String> courses) {
		Logger.writeMessage(student + " added to results\n", Logger.DebugLevel.IN_RESULTS);
		allocatedCourses.put(student, courses);
	}

	/**
	 * 
	 * @param id
	 * @return courses list of allocated courses
	 */
	public synchronized List<String> getStudentCourses(String id) {
		return allocatedCourses.get(id);
	}

	/**
	 * 
	 * @param score
	 *            avg preference score
	 */
	public void setPreferenceScore(float score) {
		preferenceScore = score;
	}

	@Override
	public String toString() {
		return "avg preference score = " + preferenceScore;
	}
}