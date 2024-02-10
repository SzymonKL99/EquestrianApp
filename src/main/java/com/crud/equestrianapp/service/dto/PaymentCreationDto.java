package com.crud.equestrianapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PaymentCreationDto {
    private Long id;
    private int price;
    private LocalDate paymentDate;
    private Long clientId;
    private Long lessonId;
}
