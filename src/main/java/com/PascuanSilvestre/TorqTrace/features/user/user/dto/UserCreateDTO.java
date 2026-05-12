package com.PascuanSilvestre.TorqTrace.features.user.user.dto;

import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.user.enums.UserStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateDTO {


    @NotBlank(message = "First name is required")
    @Size(max=100, message = "First name must not exxceed 100 characters")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "First name must contain letters only")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "Last name must contain letters only")
    private String lastName;


    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String passwordHash;

    @Size(max = 255, message = "Avatar URL must not exceed 255 characters")
    private String avatarUrl;


    //Como AddressInfo y ContactInfo estan embebidos se puede ponerle validaciones directamente
    //No es necesario hacerles DTO
    @Valid
    @NotNull(message = "Address is required")
    private AddressInfo userAddress;
    @Valid
    @NotNull(message = "User contact info is required")
    private ContactInfo userContactInfo;



}
