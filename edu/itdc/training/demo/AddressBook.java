package edu.itdc.training.demo;

import java.util.ArrayList;
import java.util.Arrays;

public class AddressBook {
	public static final int DEFAULT_CAPACITY = 2; 
	private static final AddressBookEntry[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static AddressBook instance = null;
	private AddressBookEntry list[];
	
	/**
	 *  The size of our list (The number of AddressBookEntry it actually contains)
	 */
	private int size;
    
	/**
	 * Returns a singleton AddressBook 
	 * 
	 * @param  size     Size of new AddressBook
	 * @return instance The singleton AddressBook
	 */
	public static AddressBook newInstance(int size) {
		if( instance == null ) {
			 instance = new AddressBook(size);
		} 
		return instance;
	}
	
	/**
	 * Returns a singleton AddressBook 
	 * 
	 * @return instance The singleton AddressBook
	 */
	public static AddressBook newInstance() {
		if( instance == null ) {
			 instance = new AddressBook(DEFAULT_CAPACITY);
		} 
		return instance;
	}
	
    /**
     *  Create variable size AddressBook
     *  
     *  @param size  Size of new AddressBook
     */
    private AddressBook(int size) {    
        list = new AddressBookEntry[size];
    }
    
    public boolean addAddressBookEntry(AddressBookEntry entry) {
    	boolean success = false;
        for( int i = 0; i < list.length; i++ ) {
            // find next available empty slot
            if(list[i] == null) {
                list[i] = new AddressBookEntry(entry);  // Make a "defensive copy" of added entry
                success = true;
                break;
            }
        }
        // return false if no slot available, AddressBook is full, resize to accommodate additional data 
        if(!success) {
        	AddressBookEntry newlist[] = Arrays.copyOf( list, list.length + 
        			(list.length * 1/3 < 1 ? 1 : list.length * 1/3));

        	newlist[size] = new AddressBookEntry(entry);  // Make a "defensive copy" of added entry
        	list = newlist; success = true;
        }
        
        if(success) {
        	size++;
        }
        return success;
    }
    
    public boolean deleteAddressBookEntry(int recordNo) {
        int validRecord = 0;
        // Record number is always greater than the index number by 1 (index starts with 0)
        if( recordNo > 0 && (recordNo-1) < list.length ) {
            for(int i = 0; i < list.length; i++ ) {
            	// Consider only non-null as valid records
                if( list[i] != null ) {
                    validRecord += 1;
                }
                if(validRecord == recordNo ) {
                    list[i] = null;
                    size--;
                    return true; // return true if deletion is successful
                }
            } 
        }
        return false;
    }
    
    // find a specific record number using its record number
    public AddressBookEntry findAddressBookEntryByRecordNo(int recordNo) {
        if( recordNo > 0 && (recordNo-1) < list.length ) {
            if( list[recordNo-1]!= null ) {
                return new AddressBookEntry(list[recordNo-1]);
            }
        } 
        return null;
    }
    
    public AddressBookEntry[] findAddressBookEntryByName(String name) {
    	AddressBookEntry results[] = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    	for( int i = 0; i < list.length; i++ ) {   
            // compare search criteria with every entry 
            if( list[i] != null && list[i].getName().toLowerCase().contains(name) ) {
                 AddressBookEntry newResults[] = Arrays.copyOf(results, results.length + 1);
                 newResults[results.length] = new AddressBookEntry(list[i]);
                 results = newResults;
            }
        }
    	return results;
    }
    
    public boolean updateAddressBookEntry(int recordNo, AddressBookEntry entry) {
        if( recordNo > 0 && (recordNo-1) < list.length ) {
            list[recordNo-1] = entry;
            return true;
        }
        return false; 
    }
    
    /**
     * Return the list of AddressBook entries
     * @note    This is not a good practice, you should not directly 
     *          provide invoking methods direct access to your internal 
     *          data. This must be change to return an unmodifiable list.
     *          External methods can view your entries but cannot directly 
     *          change or modify your internal data.  
     *          
     * @return  list  The reference to AddressBookEntry[] data   
     */
    public AddressBookEntry[] getAllEntries() {
        return list;
    }
    
}
