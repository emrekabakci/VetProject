package com.emre.vetproject.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull(message = "isim boş bırakılamaz!")
    private String name;
    @NotNull(message = "telefon no boş bırakılamaz!")
    private String phone;
    @NotNull(message = "mail boş bırakılamaz!")
    @Email
    private String mail;
    @NotNull(message = "adres boş bırakılamaz!")
    private String address;
    @NotNull(message = "şehir boş bırakılamaz!")
    private String city;
}
