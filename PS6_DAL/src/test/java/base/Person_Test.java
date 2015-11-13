package base;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import domain.PersonDomainModel;
import java.util.Date;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.text.ParseException;

public class Person_Test {
	private static PersonDomainModel Person;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//build a fake person for testing
		Date birthday = new SimpleDateFormat("YYYY-MM-DD").parse("1996-03-22");
		PersonDomainModel Person = new PersonDomainModel();
		Person.setFirstName("Timothy");
		Person.setLastName("Marcus");
		Person.setStreet("1190 Muirfield Dr.");
		Person.setPostalCode(19382);
		Person.setCity("West Chester");
		Person.setBirthday(birthday);
		Person.setPersonID(UUID.randomUUID());
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel person;	
		PersonDAL.deletePerson(Person.getPersonID());
		person = PersonDAL.getPerson(Person.getPersonID());
		assertNull(person);	
		
	}

	@Test
	public void AddPersonTest() {
		
		PersonDomainModel TestPerson;
		TestPerson = PersonDAL.getPerson(Person.getPersonID());
		assertNull(TestPerson);
		PersonDAL.addPerson(Person);
		
		TestPerson = PersonDAL.getPerson(Person.getPersonID());
		assertNotNull(TestPerson);
		//check to make sure it isnt there,add it, make sure its there
	}
	
	@Test
	public void DeletePersonTest(){
		PersonDomainModel DeleteTestPerson;
		DeleteTestPerson = PersonDAL.getPerson(Person.getPersonID());
		PersonDAL.addPerson(Person);
		assertNotNull(Person);
		//make sure person is in database before deletion
		
		PersonDAL.deletePerson(Person.getPersonID());
		DeleteTestPerson = PersonDAL.getPerson(Person.getPersonID());
		// Make sure they were deleted from the database
		assertNull(DeleteTestPerson);
	}


@Test
public void UpdatePersonTest()
{
	PersonDomainModel UpdateTester;
	String New_FName = "John";
	
	UpdateTester = PersonDAL.getPerson(Person.getPersonID());
	PersonDAL.addPerson(Person);
	
	Person.setFirstName(New_FName);
	PersonDAL.updatePerson(Person);
	
	UpdateTester = PersonDAL.getPerson(Person.getPersonID());
	
	assertTrue(UpdateTester.getFirstName() == New_FName);
}
	
}