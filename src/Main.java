import ru.webapp.model.Contact;
import ru.webapp.model.ContactType;

public class Main {
    public static void main(String[] args) {
        Contact contact = new Contact(ContactType.PHONE_NUMBER, "+79780987204");
        System.out.println(contact.getContactType() + " " + contact.getValue());
    }
}
