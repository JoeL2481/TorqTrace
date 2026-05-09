package com.PascuanSilvestre.TorqTrace.features.user.user.dto;

import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.user.enums.UserStatus;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
public class UserResponseDTO {

    private UUID publicId;
    private String firstName;
    private String lastName;
    private AddressInfo userAddress;
    private ContactInfo userContactInfo;
    private UserStatus userStatus;


}
