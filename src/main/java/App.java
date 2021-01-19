import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class App {
    Client client;
    ConsoleEventLogger eventLogger;

    public App(Client cl, ConsoleEventLogger el) {
        this.client = cl;
        this.eventLogger = el;
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml"); // создаем
        // контекст нашего приложения
        App app = (App) ctx.getBean("app");
        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");

    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Event event = (Event) ctx.getBean("event");
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}
