package lt.akademija.itacademymanager.model;

public enum Role {
    ADMIN("ADMIN"),
    LECTURER("LECTURER"),
    MANAGER("MANAGER");

    public final String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
