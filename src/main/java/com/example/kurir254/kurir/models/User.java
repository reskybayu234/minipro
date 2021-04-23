package com.example.kurir254.kurir.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="m_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="biodata_id", nullable = true,insertable = false, updatable=false)
    public Biodata biodata;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = true,insertable = false, updatable=false)
    public Role role;

    @Column(name="email", length = 100)
    private String Email;

    @Column(name="password")
    private String Password;

    @Column(name="login_attemp")
    private Integer loginAttemp;

    @Column(name="is_locked")
    private Boolean isLocked;

    @Column(name="last_login")
    private Date lastLogin;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Biodata getBiodata() {
        return biodata;
    }

    public void setBiodata(Biodata biodata) {
        this.biodata = biodata;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getLoginAttemp() {
        return loginAttemp;
    }

    public void setLoginAttemp(Integer loginAttemp) {
        this.loginAttemp = loginAttemp;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
