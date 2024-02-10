package com.crud.equestrianapp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PAYMENTS")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "price")
    private int price;

    @Column(name = "lesson_id")
    private Long lessonId;

    public Payment(Long id, LocalDate paymentDate, int price, Long lessonId) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.price = price;
        this.lessonId = lessonId;
    }

}
