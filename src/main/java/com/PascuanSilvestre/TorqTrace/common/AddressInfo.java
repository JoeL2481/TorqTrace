package com.PascuanSilvestre.TorqTrace.common;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressInfo {

    @NotBlank(message = "Street is required")
    @Size(max = 150, message = "Street must not exceed 150 characters")
    private String street;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State must not exceed 100 characters")
    private String state;

    @NotBlank(message = "Zip code is required")
    @Size(max = 20, message = "Zip code must not exceed 20 characters")
    private String zipCode;
}
