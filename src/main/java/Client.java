public class Client {
    private String id;
    private String fullName;

    public Client(String id,String fullName) {
        this.id=id;
        this.fullName=fullName;

    }

    private void live(){
        System.out.println("клиент живет");
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
}
