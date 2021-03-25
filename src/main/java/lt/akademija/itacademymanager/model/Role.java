package lt.akademija.itacademymanager.model;

public enum Role {
    ADMIN("ADMIN"),
    LECTURER("LECTURER"),
    MANAGER("MANAGER");

    public final String value;

    Role(String role){
        this.value = role;
    }

    public String getRole(){
        return value;
    }
}
