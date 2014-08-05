package au.com.david.exchange.task;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import au.com.david.exchange.task.AdditionalProperty;
import au.com.david.exchange.task.DeleteItem;
import au.com.david.exchange.task.FindItem;
import au.com.david.exchange.task.Task;
import au.com.david.exchange.task.TaskService;
import au.com.david.exchange.task.AdditionalProperty.Type;
import au.com.david.exchange.task.Task.BodyType;
import au.com.david.exchange.task.Task.Importance;
import au.com.david.exchange.task.Task.Status;

import com.microsoft.schemas.exchange.services._2006.types.DisposalType;
import com.microsoft.schemas.exchange.services._2006.types.DistinguishedFolderIdNameType;
import com.microsoft.schemas.exchange.services._2006.types.ExtendedPropertyType;
import com.microsoft.schemas.exchange.services._2006.types.MapiPropertyTypeType;
import com.microsoft.schemas.exchange.services._2006.types.PathToExtendedFieldType;
import com.microsoft.schemas.exchange.services._2006.types.TaskType;

public class ExchangeIntegrationTest {
	static private ClassPathXmlApplicationContext context;
	static private TaskService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext(
				"exchangeIntegrationTestContext.xml");
		service = (TaskService) context.getBean("exchangeTaskService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testCreateTaskText() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.High, Status.InProgress);
		String id = service.createTask(task);
		assertNotNull("id", id);
	}

	@Test
	public void testCreateTaskHtml() {
		String body = "<html><head><script>alert(\"HEllo\");</script><title>Title</title></head><body>This <b>is</b> the body</body></html>";
		Task task = new Task("This is a HTML task", body, BodyType.HTML,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.High, Status.InProgress);
		String id = service.createTask(task);
		assertNotNull("id", id);
	}

	@Test
	public void testFindItems() {
		List<AdditionalProperty> additionalProperties = new ArrayList<AdditionalProperty>();
		additionalProperties.add(new AdditionalProperty(Type.ExtendedField,
				"Customer", MapiPropertyTypeType.STRING));
		additionalProperties.add(new AdditionalProperty(Type.ExtendedField,
				"Amount", MapiPropertyTypeType.DOUBLE));
		additionalProperties.add(new AdditionalProperty(Type.Field,
				"task:Status"));
		FindItem findItem = new FindItem(DistinguishedFolderIdNameType.INBOX,
				additionalProperties);
		List<TaskType> tasks = service.findItems(findItem);
		for (TaskType task : tasks) {
			List<ExtendedPropertyType> extendedProperties = task
					.getExtendedProperty();
			for (ExtendedPropertyType extendedProperty : extendedProperties) {
				PathToExtendedFieldType field = extendedProperty
						.getExtendedFieldURI();
				field.getPropertyName();
				extendedProperty.getValue();
			}
		}
	}

	@Test
	public void testDeleteItems() {
		Task task = new Task("This is a text task", "This is the body",
				BodyType.Text,
				Arrays.asList(new String[] { "Human workflow" }),
				Importance.High, Status.InProgress);
		String id = service.createTask(task);
		assertNotNull("id", id);
		System.out.println(id);
		List<String> ids = new ArrayList<String>();
		ids.add(id);
		DeleteItem deleteItem = new DeleteItem(ids, DisposalType.HARD_DELETE);
		service.deleteItems(deleteItem);
	}
}
