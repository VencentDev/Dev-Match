package com.vinci.devmatch.modules.user.dto;

import com.vinci.devmatch.modules.user.validation.phone.ValidPhoneWithCountry;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ValidPhoneWithCountry
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfo{

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    private String phone;
}


