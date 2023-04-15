package by.bntu.fitr.profilerclient.api.service;

import by.bntu.fitr.profilerclient.api.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.entity.UserEntity;

public interface UserService {

    UserEntity createUser(final UserCreateRequestDTO userCreateRequestDTO);

    boolean isValidUser(final String authenticationToken);

    UserEntity getUserByName(final String name);

    UserEntity getUserById(final Long id);

    void deleteUserById(final Long id);

    void addRoleToUser(final Long userId, final Long roleId);
}
