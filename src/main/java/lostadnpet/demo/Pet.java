package lostadnpet.demo;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String date;
    private String description;
    private String status;

    @ManyToOne
    private User user;


    public Pet() {
    }

    public Pet(String name, String date, String description, String status, User user) {
        this.setName(name);
        this.setDate(date);
        this.setDescription(description);
        this.setStatus(status);
        this.setUser(user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
