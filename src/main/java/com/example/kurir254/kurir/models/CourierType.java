package com.example.kurir254.kurir.models;

import javax.persistence.*;

@Entity
@Table(name = "m_courier_type")
public class CourierType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long Id;

    @Column(name="courier_id")
    private Long CourierId;

    @Column(name="name", length = 20)
    private String Name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getCourierId() {
        return CourierId;
    }

    public void setCourierId(Long courierId) {
        CourierId = courierId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
