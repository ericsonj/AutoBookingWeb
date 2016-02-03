package test.elements;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author ejoseph
 */
@Entity
@Table(name = "users")    
public class User implements Serializable{
    
    @Id
    @Column(name = "login")
    private String login;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "document")
    private String document;
    
    @Column(name = "deleted")
    private boolean deleted;

    public User() {
    }

    public User(String login, String password, String name, String email, String document, boolean deleted) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.document = document;
        this.deleted = deleted;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", password=" + password + ", name=" + name + ", email=" + email + ", document=" + document + ", deleted=" + deleted + '}';
    }
       
}
