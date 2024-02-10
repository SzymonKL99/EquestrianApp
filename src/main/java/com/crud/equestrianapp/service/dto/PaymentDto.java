package com.crud.equestrianapp.service.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PaymentDto {
    private Long id;
    private int price;
    private LocalDate paymentDate;
    private ClientDto clientDto;
    private Long lessonId;
}
