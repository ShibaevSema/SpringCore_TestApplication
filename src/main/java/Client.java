import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Client {

    private String id;
    private String fullName;
    private String greeting;

    public Client(@Value("${id}")String id,@Value("${fullName}")String fullName)
    {
        this.id=id;
        this.fullName=fullName;
    }


    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setGreeting(@Value("${greeting}")String greeting){
        this.greeting=greeting;
    }
}

