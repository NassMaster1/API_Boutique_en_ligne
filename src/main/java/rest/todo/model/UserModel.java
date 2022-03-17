package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserModel {

    private int idUsr;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;


    public UserModel(int idUsr, String firstName, String lastName, String email, String password, String role) {
        this.idUsr = idUsr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserModel(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getIdUsr() {
        return idUsr;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "idUsr=" + idUsr +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email=" + email +
                ", password=" + password +
                ", role='" + role + '\'' +
                '}';
    }
}
