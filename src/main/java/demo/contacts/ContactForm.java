package demo.contacts;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author rloqvist
 */
public class ContactForm {

    @NotNull
    @Size(min=2, max=30)
    private String fullname;

    @NotNull
    @Size(min=2, max=20)
    private String cell;

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String toString() {
        return "Contact(Name: " + this.fullname + ", Cell: " + this.cell + ")";
    }
}