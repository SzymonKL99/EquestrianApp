package com.crud.equestrianapp.service.dto;
import lombok.*;

@AllArgsConstructor
@Getter
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}