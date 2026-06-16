package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto;


import com.PascuanSilvestre.TorqTrace.common.utils.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.utils.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto.WorkshopStaffResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockResponseDTO;
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

    private List<WorkshopStaffResponseDTO> workers;

    private List<WorkshopClientResponseDTO> clients;

    private List<WorkshopStockResponseDTO> stockItems;

    private List<WorkOrderResponseDTO> orderItems;
}
