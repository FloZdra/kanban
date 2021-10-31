package org.flozdra.kanban.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
// import javax.validation.constraints.Email;
// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.Size;

@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotBlank(message="Please enter your firstname")
    private String firstname;

    //    @NotBlank(message="Please enter your lastname")
    private String lastname;

    //    @Size(min=5, message="Your password must be at least 5-characters long")
    private String password;

    //    @NotBlank(message="Please enter your email")
    //    @Email(message="Invalid email")
    @Column(unique = true)
    private String email;

    private Date startContract;

    @ManyToMany(mappedBy = "developers", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Task> tasks;

    public Developer() {
    }

    public Developer(String firstname, String lastname, String password, String email, Date startContract) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.startContract = startContract;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartContract() {
        return startContract;
    }

    public void setStartContract(Date startContract) {
        this.startContract = startContract;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public String getFullName() {
        return this.firstname + ' ' + this.lastname + " (" + this.email + ")";
    }
}
