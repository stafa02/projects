package registrationScheduler.scheduler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registrationScheduler.constants.SchedulerConstants;
import registrationScheduler.util.FileProcessor;

/**
 * This class performs computations to allocate courses to students. Allocations
 * are decided based on supplied preferences on a FCFS basis based on
 * registration time.
 */
public class Scheduler {
	private File regFile, prefFile;
	private Map<String, List<String>> preferenceMap;
	private Map<String, Integer> availabilityMap;
	private List<Integer> regTimeList;
	private Map<Integer, List<String>> regTimeMap;
	private Map<String, List<String>> allocatedMap;
	private int sum;

	/**
	 * Constructor for this class
	 * 
	 * @param prefFilePath
	 *            path to preference input file.
	 * @param regFilePath
	 *            path to registration input file.
	 */
	public Scheduler(String prefFilePath, String regFilePath) {
		regFile = new File(regFilePath);
		prefFile = new File(prefFilePath);
	}

	/**
	 * This method allocates max 4 courses to each student.
	 * 
	 * @return Map<String,List<String>> This returns a map which has allocated
	 *         courses for each student.
	 */
	public Map<String, List<String>> allocateCourses() {
		try {
			allocatedMap = new HashMap<String, List<String>>();
			populateAvailability();
			populatePreferences();
			populateRegistrationTime();
		} catch (IOException e) {
			System.err.println("Error reading input");
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
		Collections.sort(regTimeList);
		int sum = 0;
		for (int time : regTimeList) {
			Integer prefScore = 0;
			for (String student : regTimeMap.get(time)) {
				List<String> prefList = preferenceMap.get(student);
				List<String> allocated = new ArrayList<>();
				for (int i = 0; i < 4; i++) {
					if (availabilityMap.get(prefList.get(i)) > 0) {
						allocated.add(prefList.get(i));
						prefScore += i + 1;
						availabilityMap.put(prefList.get(i), availabilityMap.get(prefList.get(i)) - 1);
					}
				}
				if (allocated.size() < 4) {
					prefScore += fillMissingCourses(allocated);
				}
				sum += prefScore;
				allocated.add(prefScore.toString());
				allocatedMap.put(student, allocated);
			}
		}
		setSum(sum);
		return allocatedMap;
	}

	/**
	 * This methods allocates courses if students preference cannot be
	 * satisfied.
	 * 
	 * @param allocated
	 *            list of courses assigned to the student
	 * @return int This is sum of preferences.
	 */
	private int fillMissingCourses(List<String> allocated) {
		int sum = 0;
		int has = allocated.size();
		for (int i = 0; i < (4 - has); i++) {
			for (int j = 0; j < SchedulerConstants.COURSES.length; j++) {
				if (!allocated.contains(SchedulerConstants.COURSES[j])
						&& availabilityMap.get(SchedulerConstants.COURSES[j]) > 0) {
					allocated.add(SchedulerConstants.COURSES[j]);
					sum += 5;
					availabilityMap.put(SchedulerConstants.COURSES[j],
							availabilityMap.get(SchedulerConstants.COURSES[j]) - 1);
					break;
				}
			}
		}
		if (allocated.size() < 4) {
			sum += ((4 - allocated.size()) * 6);
		}
		return sum;
	}

	/**
	 * This initializes availability map
	 */
	private void populateAvailability() {
		availabilityMap = new HashMap<String, Integer>();
		for (int i = 0; i < SchedulerConstants.COURSES.length; i++) {
			availabilityMap.put(SchedulerConstants.COURSES[i], SchedulerConstants.MAX_CAPACITY);
		}
	}

	/**
	 * This builds the preference map
	 * 
	 * @throws IOException
	 */
	private void populatePreferences() throws IOException {
		preferenceMap = new HashMap<String, List<String>>();
		String line;
		int count = 0;
		FileProcessor fp = new FileProcessor(prefFile, false);
		while (((line = fp.readLine()) != null) && (count < SchedulerConstants.STUDENT_COUNT)) {
			String[] input = line.split(" ");
			List<String> prefList = new ArrayList<String>(4);
			prefList.add(input[1]);
			prefList.add(input[2]);
			prefList.add(input[3]);
			prefList.add(input[4]);
			preferenceMap.put(input[0], prefList);
			count++;
		}
		fp.close();
		if (count != SchedulerConstants.STUDENT_COUNT) {
			System.err.println("preference file does not have " + SchedulerConstants.STUDENT_COUNT + " entries");
			System.exit(1);
		}
	}

	/**
	 * This builds the registration map and list
	 * 
	 * @throws IOException
	 */
	private void populateRegistrationTime() throws IOException {
		regTimeList = new ArrayList<Integer>();
		regTimeMap = new HashMap<Integer, List<String>>();
		int count = 0;
		String line;
		FileProcessor fp = new FileProcessor(regFile, false);
		while (((line = fp.readLine()) != null) && (count < SchedulerConstants.STUDENT_COUNT)) {
			String[] input = line.split(" ");
			int time = Integer.parseInt(input[1]);
			regTimeList.add(time);
			count++;
			if (!regTimeMap.containsKey(time)) {
				List<String> studentList = new ArrayList<String>();
				studentList.add(input[0]);
				regTimeMap.put(time, studentList);
				continue;
			}
			regTimeMap.get(time).add(input[0]);
		}
		fp.close();
		if (count != SchedulerConstants.STUDENT_COUNT) {
			System.err.println("registration file does not have " + SchedulerConstants.STUDENT_COUNT + " entries");
			System.exit(1);
		}
	}

	/**
	 * @return this returns sum of preferences
	 */
	public int getSum() {
		return sum;
	}

	private void setSum(int sumIn) {
		sum = sumIn;
	}

	@Override
	public String toString() {
		return "Preference File: " + prefFile.getName() + " Registration file: " + regFile.getName();
	}
}