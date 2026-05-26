package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto;


import lombok.*;
import com.PascuanSilvestre.TorqTrace.common.utils.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.utils.ContactInfo;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkShopResponseDTO {

    private Long id;

    private String name;

    private String description;

    private AddressInfo workshopAddress;

    private ContactInfo workshopContactInfo;

    private boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
