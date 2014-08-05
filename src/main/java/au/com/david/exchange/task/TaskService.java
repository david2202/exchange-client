package au.com.david.exchange.task;

import java.util.List;

import com.microsoft.schemas.exchange.services._2006.types.TaskType;

public interface TaskService {
	/**
	 * Create a new task.
	 * 
	 * @param task
	 *            The task to be created
	 * @return The identifier of the created task.
	 */
	String createTask(Task task);
	
	List<TaskType> findItems(FindItem findItem);
	
	void deleteItems(DeleteItem deleteItem);
	
	boolean taskExists(String id);
}
