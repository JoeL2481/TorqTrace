package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto;


import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockResponseDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkShopDetailedResponseDTO {

    private Long id;

    private String name;

    private String description;

    private AddressInfo workshopAddress;

    private ContactInfo workshopContactInfo;

    private boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Relaciones

    private List<WorkShopStaffResponseDTO> workers;

    private List<WorkShopClientResponseDTO> clients;

    private List<WorkShopStockResponseDTO> stockItems;

    private List<WorkOrderResponseDTO> orderItems;
}
