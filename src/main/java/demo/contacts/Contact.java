package demo.contacts;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author rloqvist
 */
public class Contact {
    
    private int contactId;
    private String fullname;
    private String cell;
    
    public Contact(int contactId, String fullname, String cell) {
        this.contactId = contactId;
        this.fullname = fullname;
        this.cell = cell;
    }

    @JsonInclude
    public int getContactId() {
        return this.contactId;
    }

    @JsonInclude
    public String getFullname() {
        return this.fullname;
    }

    @JsonInclude
    public String getCell() {
        return this.cell;
    }

    @Override
    public String toString() {
        return "Contact{" + "contactId=" + contactId + ", fullname=" + fullname + ", cell=" + cell + '}';
    }
}
