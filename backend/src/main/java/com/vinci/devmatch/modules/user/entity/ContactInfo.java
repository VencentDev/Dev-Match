package com.vinci.devmatch.modules.user.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfo {
    private String country;
    private String address;
    private String phone;
}

