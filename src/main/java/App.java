import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;


    public App(Client cl, Map<EventType, EventLogger> loggers) {
        this.client = cl;
        this.loggers = loggers;
    }

    @Autowired
    @Qualifier("cacheFileEventLogger")
    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        // создаем
        // контекст нашего приложения
        //              Насследование контекстов ( для веб приложений полезно в основном ) :
        //  ApplicationContext parent = new ClassPathXmlApplicationContext("logger.xml");
        // ApplicationContext child = new ClassPathXmlApplicationContext("others.xml",parent);
        App app = (App) context.getBean("app");
        Event event = (Event) context.getBean("event");
        app.logEvent("Some event for user 1", EventType.ERROR, event);
        app.logEvent("Some event for user 2", EventType.INFO, event);
        app.logEvent("Some event for user 3", null, event);

    }

    private void logEvent(String msg, EventType eventType, Event event) throws IOException {
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(message);
        if (eventType == null) {
            EventLogger eventLogger = defaultLogger;
            eventLogger.logEvent(event);
        } else {
            EventLogger eventLogger = loggers.get(eventType);
            eventLogger.logEvent(event);
        }
    }
}
