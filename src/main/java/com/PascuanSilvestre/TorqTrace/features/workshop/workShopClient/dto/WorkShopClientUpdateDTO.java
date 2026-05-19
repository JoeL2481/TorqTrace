package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkShopClientUpdateDTO {

    @Size(min = 1, max = 300)
    private String description;

}

