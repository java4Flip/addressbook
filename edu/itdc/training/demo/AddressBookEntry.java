package edu.itdc.training.demo;

public class AddressBookEntry {
    private final String name;        // Assuming name can no longer be change after creation of entry
    private String address;
    private String emailAdd;
    private String telNo;
    
    /**
     * Create a new AddressBookEntry 
     * 
     * @param name  Name of contact
     */
    public AddressBookEntry(String name) {
		this.name = name;
	}
    
    /**
     * Copy constructor for the AddressBookEntry
     * 
     * @param entry  The AddressBookEntry copied 
     */
    public AddressBookEntry(AddressBookEntry entry) {
    	this.name     = entry.name;
    	this.address  = entry.address;
    	this.emailAdd = entry.emailAdd;
    	this.telNo    = entry.telNo;	
    }

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getName() {
        return name;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }    
}
