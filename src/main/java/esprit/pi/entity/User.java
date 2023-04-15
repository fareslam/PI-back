package esprit.pi.entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id 
    @NotNull
    @Length(max = 8)
    @Column(name = "cin", unique = true)
    private String cin;
   
    @NotNull
 @Column(name = "name")
    private String name;
    
    @NotNull
    @Column(name = "surname")
    private String surname;
   
    @NotNull
    @Column(name = "email", unique = true)
    private String email;
  
    @NotNull 
    @Column(name = "tel")
    private long tel;
    
    @NotNull
    @Column(name = "date_birth")
    @Temporal(TemporalType.DATE)
    private Date dateBirth;
    
    @NotNull
    @Column(name = "password")
    private String password;
    
    @NotNull
    @Column(name = "username", unique = true)
    private String username;
 
    
    public String getCin() {
        return cin;
    }
    
    public void setCin(String cin) {
        this.cin = cin;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public long getTel() {
        return tel;
    }
    
    public void setTel(long tel) {
        this.tel = tel;
    }
    
    public Date getDateBirth() {
        return dateBirth;
    }
    
    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
}
