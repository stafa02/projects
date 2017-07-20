package registrationScheduler.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registrationScheduler.constants.SchedulerConstants;
import registrationScheduler.objectPool.CoursePool;
import registrationScheduler.store.Results;

public class Scheduler {
	private Results result;
	private Map<String, List<String>> coursePreferenceMap;

	/**
	 * 
	 * @param resultIn 
	 */
	public Scheduler(Results resultIn) {
		Logger.writeMessage("Scheduler constructor invoked\n", Logger.DebugLevel.CONSTRUCTOR);
		result = resultIn;
		coursePreferenceMap = new HashMap<String, List<String>>();
	}

	/**
	 * 
	 * @param student
	 * @param courses list of course of preference
	 */
	public synchronized void schedule(String student, List<String> courses) {
		coursePreferenceMap.put(student, courses);
		List<String> allocatedCourses = new ArrayList<String>();
		for (String course : courses) {
			if (CoursePool.getInstance().fetch(course)) {
				allocatedCourses.add(course);
			}
		}
		if (allocatedCourses.size() != 5) {
			for (int i = 0; i < SchedulerConstants.COURSES.length; i++) {
				if (allocatedCourses.contains(SchedulerConstants.COURSES[i])) {
					continue;
				}
				if (CoursePool.getInstance().fetch(SchedulerConstants.COURSES[i])) {
					allocatedCourses.add(SchedulerConstants.COURSES[i]);
				}
				if (allocatedCourses.size() == 5) {
					break;
				}
			}
		}
		result.addToResults(student, allocatedCourses);
	}

	/**
	 * 
	 * @param student
	 * @param flag
	 * @param courses
	 */
	public synchronized void modify(String student, int flag, List<String> courses) {
		List<String> allocatedCourses = result.getStudentCourses(student);
		if (flag == 0) {
			for (String course : courses) {
				allocatedCourses.remove(course);
				CoursePool.getInstance().putBack(course);
			}
		} else {
			for (String course : courses) {
				if (CoursePool.getInstance().fetch(course)) {
					allocatedCourses.add(course);
				}
				if (allocatedCourses.size() == 5) {
					break;
				}
			}
		}
		result.addToResults(student, allocatedCourses);
	}

	/**
	 * 
	 * @return float average preference score
	 */
	public synchronized float calculatePreferenceScore() {
		int sum = 0;
		for (String student : coursePreferenceMap.keySet()) {
			int score = 0, i = 0, count = 0;
			List<String> preferences = coursePreferenceMap.get(student);
			List<String> allocated = result.getStudentCourses(student);
			for (String course : preferences) {
				if (allocated.contains(course)) {
					score += (6 - i);
					count++;
				}
				i++;
			}
			score += (allocated.size() - count);
			allocated.add("" + score);
			sum += score;
		}
		return (((float) sum) / SchedulerConstants.STUDENT_COUNT);
	}
	
	@Override
	public String toString(){
		return "Scheduler to assign courses based on preference";
	}
}
