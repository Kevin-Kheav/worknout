package com.mdi.worknout.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private String location;
    private Date date;
    @OneToMany
    private List<User> listUsers;

    public Event(){

    }

    public Event(String title, String description, String location , Date date ){
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.listUsers = new ArrayList<User>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", Title=" + title+
                ", Description=" + description +
                ", Location=" + location +
                ", Participants=" + listUsers.toString() +
                '}';
    }
}
