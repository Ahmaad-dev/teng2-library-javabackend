package model.dto;

/**
 * Data Transfer Object für das Erstellen/Aktualisieren von Kunden
 */
public class CreateClientRequest {
    private String name;
    private String email;
    private String phone;

    // Konstruktoren
    public CreateClientRequest() {}

    public CreateClientRequest(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "CreateClientRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}