package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "fullname", nullable = false)
    String name;
    @Column(nullable = false)
    String email;
    String pet;

    public Contact() { }

    public Contact(Integer id, String name, String email, String pet) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pet = pet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pet='" + pet + '\'' +
                '}';
    }
}
