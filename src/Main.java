import ru.webapp.model.OrganizationSection;
import ru.webapp.model.Section;
import ru.webapp.model.TextSection;
import ru.webapp.model.TextSectionWithTitle;
import storage.SerializeFileStorage;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static int num2 = 0;
    static int num = 0;
    static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                num2++;
                synchronized (Main.class) {
                    num++;
                }
                integer.incrementAndGet();
            }).start();

        }
        System.out.println("Осталось пол секунды");
        Thread.sleep(500);
        System.out.println(num2);
        System.out.println(integer.get());
        System.out.println(num);
    }
}
