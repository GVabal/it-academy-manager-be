package lt.akademija.itacademymanager.model;

public enum Roles {
    ADMIN("admin"),
    LECTURER("lecturer"),
    MANAGER("manager");

    public final String role;

    Roles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
