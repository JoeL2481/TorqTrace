package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto;


import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopClientUpdateDTO {

    @Size(min = 1, max = 300)
    private String description;

}

