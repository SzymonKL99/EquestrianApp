package com.crud.equestrianapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CLIENTS")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "email")
    private String email;


    @ManyToMany
    @JoinTable(
            name = "CLIENT_TO_LESSON",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id", referencedColumnName = "id")})
    private List<Lesson> lessonsList = new ArrayList<>();

    @OneToMany (targetEntity = Payment.class,
            mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Payment> payments = new ArrayList<>();

    public Client(Long id, String firstName, String lastName, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
}
