package com.PascuanSilvestre.TorqTrace.features.user.user.dto;

import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
    public class UserUpdateDTO {

        @Size(max = 100, message = "First name must not exceed 100 characters")
        @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "First name must contain letters only")
        private String firstName;

        @Size(max = 100, message = "Last name must not exceed 100 characters")
        @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "Last name must contain letters only")
        private String lastName;

        @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
        private String password;

        @Size(max = 255, message = "Avatar URL must not exceed 255 characters")
        private String avatarUrl;

        @Valid
        private AddressInfo userAddress;

        @Valid
        private ContactInfo userContactInfo;
    }
}
