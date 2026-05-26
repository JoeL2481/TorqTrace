package com.PascuanSilvestre.TorqTrace.features.user.user.dto;

import com.PascuanSilvestre.TorqTrace.common.utils.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.utils.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.user.enums.UserStatus;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private UUID publicId;
    private String firstName;
    private String lastName;
    private AddressInfo userAddress;
    private ContactInfo userContactInfo;
    private UserStatus userStatus;


}
