package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto;

import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkShopCreateDTO {

    @NotBlank(message = "Workshop name is required")
    @Size(max = 255, message = "Workshop name cannot exceed 255 characters")
    private String name;

    @NotBlank(message = "Workshop description is required")
    private String description;

    @Valid
    @NotNull(message = "Workshop address is required")
    private AddressInfo workshopAddress;

    @Valid
    @NotNull(message = "Workshop contact info is required")
    private ContactInfo workshopContactInfo;

    @NotNull(message = "Workshop status is required")
    private Boolean status;
}
