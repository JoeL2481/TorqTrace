package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto;


import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientResponseDTO;
import lombok.*;
import com.PascuanSilvestre.TorqTrace.common.utils.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.utils.ContactInfo;

import java.time.LocalDateTime;
import java.util.List;

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

    private List<WorkShopClientResponseDTO> clients;

    private LocalDateTime createdAt;
}
