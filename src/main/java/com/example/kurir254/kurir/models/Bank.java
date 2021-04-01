package com.example.kurir254.kurir.models;

import javax.persistence.*;

@Entity
@Table(name="m_bank")
public class Bank extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "name", length = 100 )
    private String Name;

    @Column(name = "va_code")
    private String VaCode;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVaCode() {
        return VaCode;
    }

    public void setVaCode(String vaCode) {
        VaCode = vaCode;
    }
}
