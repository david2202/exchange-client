package au.com.david.exchange.task;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import au.com.david.exchange.task.Task.BodyType;
import au.com.david.exchange.task.Task.Importance;
import au.com.david.exchange.task.Task.Status;

import com.microsoft.schemas.exchange.services._2006.messages.CreateItemType;

public class CreateItemBuilderTest {
	private CreateItemBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		builder = new CreateItemBuilder();
	}

	@Test
	public void testCreateTaskText() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.High, Status.InProgress);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}

	@Test
	public void testCreateTaskHTML() {
		String body = "<html><head><title>Title</title></head><body>This <b>is</b> the body</body></html>";
		Task task = new Task("This is a HTML task", body,
				BodyType.HTML,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.High, Status.InProgress);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}

	@Test
	public void testCreateTaskImportanceHigh() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.High, Status.InProgress);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}

	@Test
	public void testCreateTaskImportanceNormal() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.Normal, Status.InProgress);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}

	@Test
	public void testCreateTaskImportanceLow() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.Normal, Status.InProgress);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}

	@Test
	public void testCreateTaskStatusNotStarted() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.Normal, Status.NotStarted);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}
	
	@Test
	public void testCreateTaskStatusInProgress() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.Normal, Status.InProgress);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}
	
	@Test
	public void testCreateTaskStatusCompleted() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.Normal, Status.Completed);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}

	@Test
	public void testCreateTaskStatusDeferred() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.Normal, Status.Deferred);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}
	@Test
	public void testCreateTaskStatusWaitingOnOthers() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.Normal, Status.WaitingOnOthers);
		CreateItemType createItem = builder.build(task);
		
		// TODO assert everything has built OK
	}
}
