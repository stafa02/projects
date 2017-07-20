package registrationScheduler.objectPool;

import java.util.HashMap;
import java.util.Map;

import registrationScheduler.constants.SchedulerConstants;
import registrationScheduler.util.Logger;


public class CoursePool {
	private Map<String, Integer> pool;
	private volatile static CoursePool coursePool;

	private CoursePool() {
		Logger.writeMessage("Course pool constructor invoked\n", Logger.DebugLevel.CONSTRUCTOR);
		pool = new HashMap<String, Integer>();
		allocatePool();
	}

	/**
	 * 
	 * @return CoursePool instance
	 */
	public static CoursePool getInstance() {
		if (coursePool == null) {
			synchronized (CoursePool.class) {
				if (coursePool == null)
					coursePool = new CoursePool();
			}
		}
		return coursePool;
	}

	private void allocatePool() {
		for (int i = 0; i < SchedulerConstants.COURSES.length; i++) {
			pool.put(SchedulerConstants.COURSES[i], 60);
		}
	}

	/**
	 * 
	 * @param course
	 * @return boolean indicating if course available
	 */
	public boolean fetch(String course) {
		if (pool.get(course) > 0) {
			pool.put(course, pool.get(course) - 1);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param course
	 */
	public void putBack(String course) {
		pool.put(course, pool.get(course) + 1);
	}
	
	@Override
	public String toString(){
		return "pool of courses";
	}

}