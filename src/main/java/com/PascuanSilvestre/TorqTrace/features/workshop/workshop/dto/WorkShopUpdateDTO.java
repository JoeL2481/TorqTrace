package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto;

import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkShopUpdateDTO {

    @Size(max = 255, message = "Workshop name cannot exceed 255 characters")
    private String name;

    private String description;

    @Valid
    private AddressInfo workshopAddress;

    @Valid
    private ContactInfo workshopContactInfo;

    private Boolean status;
}
