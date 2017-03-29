package edu.itdc.training.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.itdc.training.demo.AddressBook;
import edu.itdc.training.demo.AddressBookEntry;

public class TestForAddressBook {
	public AddressBook book;
	
	@Before
	public void init() {
		book = AddressBook.newInstance();
	}
	
	@After() 
	public void destroy() throws Exception {
		// Singleton instance cannot be reset directly "book = null " 
		// use reflection to set instance of singleton to null 
		Field instanceField = AddressBook.class.getDeclaredField("instance");
		instanceField.setAccessible(true);
		instanceField.set(book, null);
	}
	
	@Test
	public void whenCreatingEmptyAddressBook_thenInitSize() throws Exception {		
		Field sizeField = AddressBook.class.getDeclaredField("size");
		sizeField.setAccessible(true);
		assertEquals( 0, sizeField.get(book));
		
		Field listField = AddressBook.class.getDeclaredField("list");
		listField.setAccessible(true);
		assertEquals(AddressBook.DEFAULT_CAPACITY, ( (AddressBookEntry[]) listField.get(book)).length );
	}
	
	@Test
	public void whenAddingNewEntries_thenSucceed() throws Exception {
		Field sizeField = AddressBook.class.getDeclaredField("size");
		sizeField.setAccessible(true);
		
		book.addAddressBookEntry( new AddressBookEntry("Jose Rizal") );
		assertEquals( 1, sizeField.get(book));
		
		book.addAddressBookEntry( new AddressBookEntry("Jose Manalo") );
		assertEquals( 2, sizeField.get(book));
		
		book.addAddressBookEntry( new AddressBookEntry("Maria Makiling") );
		assertEquals( 3, sizeField.get(book));
		
		book.addAddressBookEntry( new AddressBookEntry("Paulino Alcantara") );
		assertEquals( 4, sizeField.get(book));	
	}
	
	@Test
	public void whenFindingByRecordNumber_ThenSucceed() {
		book.addAddressBookEntry( new AddressBookEntry("Jose Rizal") );		
		book.addAddressBookEntry( new AddressBookEntry("Jose Manalo") );	
		book.addAddressBookEntry( new AddressBookEntry("Maria Makiling") );
		book.addAddressBookEntry( new AddressBookEntry("Paulino Alcantara") );

		AddressBookEntry entry = book.findAddressBookEntryByRecordNo(1);
		assertEquals("Jose Rizal", entry.getName());
		
		entry = book.findAddressBookEntryByRecordNo(2);
		assertEquals("Jose Manalo", entry.getName());
		
		entry = book.findAddressBookEntryByRecordNo(3);
		assertEquals("Maria Makiling", entry.getName());
		
		entry = book.findAddressBookEntryByRecordNo(4);
		assertEquals("Paulino Alcantara", entry.getName());
	}
	
	
}
