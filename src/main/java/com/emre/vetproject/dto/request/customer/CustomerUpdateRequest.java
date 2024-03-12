package com.emre.vetproject.dto.request.customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    @Positive(message = "ID değeri pozitif olmak zorunda!")
    private Long id;
    @NotNull(message = "İsim boş bırakılamaz!")
    private String name;
    @NotNull(message = "Telefon NO boş bırakılamaz!")
    private String phone;
    @NotNull(message = "Mail boş bırakılamaz!")
    private String mail;
    @NotNull(message = "Adres boş bırakılamaz!")
    private String address;
    @NotNull(message = "Şehir boş bırakılamaz!")
    private String city;

}
