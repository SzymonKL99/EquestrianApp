package com.crud.equestrianapp.controller;

import com.crud.equestrianapp.service.ClientService;
import com.crud.equestrianapp.service.mapper.ClientMapper;
import com.crud.equestrianapp.service.mapper.LessonMapper;
import com.crud.equestrianapp.service.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final PaymentMapper paymentMapper;
    private final LessonMapper lessonMapper;
}
