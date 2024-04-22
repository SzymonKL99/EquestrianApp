package com.crud.equestrianapp.testMapper;

import com.crud.equestrianapp.domain.Client;
import com.crud.equestrianapp.domain.Payment;
import com.crud.equestrianapp.service.dto.PaymentCreationDto;
import com.crud.equestrianapp.service.dto.PaymentDto;
import com.crud.equestrianapp.service.mapper.PaymentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PaymentMapperTest {

    @Autowired
    PaymentMapper paymentMapper;

    @Test
    void testMapToPayment() {
        //Given
        PaymentCreationDto paymentCreationDto = new PaymentCreationDto(1L, 100, LocalDate.of(2024,
                04, 1), 2L, 3L);

        //When
        Payment payment = paymentMapper.mapToPayment(paymentCreationDto);

        //Then
        assertNull(payment.getClient());
        assertEquals(paymentCreationDto.getPrice(), payment.getPrice());
        assertEquals(paymentCreationDto.getLessonId(), payment.getLessonId());
        assertEquals(paymentCreationDto.getPaymentDate(), payment.getPaymentDate());
    }

    @Test
    void testMapToPaymentDto() {
        //Given
        Client client = new Client(1L, "Adam", "Kowalski", "123456789",
                "kowalski@example.com");
        Payment payment = new Payment(1L, client, LocalDate.of(2024, 04, 11),
                100, 3L);

        //When
        PaymentDto paymentDto =  paymentMapper.mapToPaymentDto(payment);

        //Then
        assertEquals(paymentDto.getPaymentDate(), paymentDto.getPaymentDate());
        assertEquals(client.getId(), paymentDto.getClientDto().getId());
    }
}
