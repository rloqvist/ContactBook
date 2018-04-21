package demo.module;

import demo.contacts.Contact;
import demo.contacts.ContactForm;
import demo.contacts.ContactsDao;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author rloqvist
 */
@Controller
public class ContactsModule {

    @GetMapping("/")
    public String home(ContactForm contactForm) {
        return "index";
    }

    @PostMapping("/addContact")
    public String addContact(@Valid ContactForm contactForm, BindingResult bindingResult) {

        if ( bindingResult.hasErrors() ) {
            return "index";
        }

        String fullname = contactForm.getFullname();
        String cell = contactForm.getCell();

        ContactsDao contactsDao = new ContactsDao();
        int contactId = contactsDao.createRecord(fullname, cell);

        Contact contact = new Contact(contactId, fullname, cell);

        System.out.println("Created contact: " + contact.toString());

        return "redirect:/";
    }

}
