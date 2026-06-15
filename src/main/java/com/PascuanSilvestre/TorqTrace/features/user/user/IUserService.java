package com.PascuanSilvestre.TorqTrace.features.user.user;

import com.PascuanSilvestre.TorqTrace.auth.dto.NewAccountRequest;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface IUserService<RegisterDTO, CreateDTO, UpdateDTO, ResponseDTO, DetailedResponseDTO, ID> {
    ResponseDTO register(RegisterDTO request);

    ResponseDTO create(CreateDTO request);

    List<ResponseDTO> getAll();

    ResponseDTO getById(ID id);

    DetailedResponseDTO getDetailedByID(ID id);

    ResponseDTO update(ID id, UpdateDTO request);

    ResponseDTO delete(ID id);

    ResponseDTO getMyProfile();

    DetailedResponseDTO getMyProfileDetails();

    ResponseDTO updateMyProfile(UpdateDTO request);

    boolean existUser(Long id);
}
