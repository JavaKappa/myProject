import ru.webapp.model.Contact;
import ru.webapp.model.ContactType;
import ru.webapp.model.Link;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Contact contact = new Contact(ContactType.PHONE_NUMBER, "+79780987204");
        System.out.println(contact.getContactType() + " " + contact.getValue());

        Link link = new Link("Google","www.google.com");

        for(Field field : link.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object val = field.get(link);
            if (val != null) {
                System.out.println(field.getName() + " = " + val);
            }
        }
    }
}
