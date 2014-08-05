package au.com.david.exchange.task;

import java.util.List;

/**
 * A task to be performed.
 * 
 * @author howed
 */
public class Task {

	private String subject;
	private String body;
	private BodyType bodyType;
	private List<String> categories;
	private Importance importance;
	private Status status;

	/**
	 * Constructor.
	 * 
	 * @param subject
	 *            The subject.
	 * @param body
	 *            The body.
	 * @param categories
	 *            The categories.
	 * @param importance
	 *            The importance.
	 * @param status
	 *            The status.
	 */
	public Task(String subject, String body, BodyType bodyType, List<String> categories,
			Importance importance, Status status) {
		this.subject = subject;
		this.body = body;
		this.bodyType = bodyType;
		this.categories = categories;
		this.importance = importance;
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public BodyType getBodyType() {
		return bodyType;
	}
	
	public List<String> getCategories() {
		return categories;
	}

	public Importance getImportance() {
		return importance;
	}

	public Status getStatus() {
		return status;
	}

	public enum Importance {
		Low, Normal, High;
	}

	public enum Status {
		NotStarted, InProgress, Completed, WaitingOnOthers, Deferred
	}
	
	public enum BodyType {
		HTML, Text;
	}
}
