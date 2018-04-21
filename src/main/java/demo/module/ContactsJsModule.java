package demo.module;

import demo.contacts.Contact;
import demo.contacts.ContactsDao;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rloqvist
 */
@RestController
public class ContactsJsModule {

    @RequestMapping("/listContacts")
    public Map<String, List<Contact>> listContacts() {

        ContactsDao contactsDao = new ContactsDao();
        List<Contact> contacts = contactsDao.listRecords();

        Map<String, List<Contact>> retval = new HashMap<>();

        retval.put("data", contacts);

        return retval;
    }

    @RequestMapping("/listContactsDt")
    public Map<String, List<List<String>>> listContactsDt() {

        ContactsDao contactsDao = new ContactsDao();
        List<Contact> contacts = contactsDao.listRecords();

        Map<String, List<List<String>>> retval = new HashMap<>();

        List<List<String>> contactsList = new ArrayList<>();

        for (Contact contact : contacts) {
            List<String> values = new ArrayList<>();
            values.add(contact.getFullname());
            values.add(contact.getCell());
            values.add(String.valueOf(contact.getContactId()));
            contactsList.add(values);
        }

        retval.put("data", contactsList);

        return retval;
    }

    @RequestMapping(method=RequestMethod.POST, value="/deleteContact")
    public Map<String, String> deleteContact(@RequestParam(value="contactId", defaultValue="0") String contactIdStr) {

        int contactId;
        try {
            contactId = Integer.parseInt(contactIdStr);
        } catch (Exception ex){
            contactId = 0;
            ex.printStackTrace();
        }

        ContactsDao contactsDao = new ContactsDao();
        boolean status = contactsDao.deleteRecord(contactId);

        Map<String, String> retval = new HashMap<>();

        if ( status ) {
            retval.put("status", "ok");
        } else {
            retval.put("status", "error");
        }

        return retval;
    }

}
