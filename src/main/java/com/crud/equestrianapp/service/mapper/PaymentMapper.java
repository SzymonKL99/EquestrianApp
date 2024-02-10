package com.crud.equestrianapp.service.mapper;

import com.crud.equestrianapp.domain.Payment;
import com.crud.equestrianapp.service.dto.PaymentCreationDto;
import com.crud.equestrianapp.service.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMapper {

    @Autowired
    ClientMapper clientMapper;

    public PaymentDto mapToPaymentDto(final Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getPrice(),
                payment.getPaymentDate(),
                clientMapper.mapToClientDto(payment.getClient()),
                payment.getLessonId()
        );
    }

    public Payment mapToPayment(final PaymentCreationDto paymentCreationDto) {
        return new Payment(
                paymentCreationDto.getId(),
                paymentCreationDto.getPaymentDate(),
                paymentCreationDto.getPrice(),
                paymentCreationDto.getLessonId()

        );
    }
    public List<PaymentDto> mapToPaymentDtoList(List<Payment> paymentList) {
        return paymentList.stream()
                .map(this::mapToPaymentDto)
                .collect(Collectors.toList());
    }
}