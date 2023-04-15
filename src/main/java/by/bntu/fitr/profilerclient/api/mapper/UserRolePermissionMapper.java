package by.bntu.fitr.profilerclient.api.mapper;

import by.bntu.fitr.profilerclient.api.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.response.PermissionResponseDTO;
import by.bntu.fitr.profilerclient.api.dto.response.RoleResponseDTO;
import by.bntu.fitr.profilerclient.api.dto.response.UserResponseDTO;
import by.bntu.fitr.profilerclient.api.entity.PermissionEntity;
import by.bntu.fitr.profilerclient.api.entity.RoleEntity;
import by.bntu.fitr.profilerclient.api.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRolePermissionMapper {

    public UserEntity mapToUserEntity(UserCreateRequestDTO userCreateRequestDTO) {
        return new UserEntity(
                userCreateRequestDTO.getUserName(),
                userCreateRequestDTO.getPassword(),
                userCreateRequestDTO.getEmail()
        );
    }

    public UserResponseDTO mapToUserResponseDTO(UserEntity user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateAt(),
                mapToRoleResponseDTO(user.getRole())
        );
    }

    public RoleResponseDTO mapToRoleResponseDTO(RoleEntity role) {
        if (role == null) {
            return null;
        }
        return new RoleResponseDTO(
                role.getId(),
                role.getName(),
                role.getCreateAt(),
                mapToPermissionsResponseDTO(role.getPermissions())
        );
    }

    public PermissionResponseDTO mapToPermissionResponseDTO(PermissionEntity permission) {
        if (permission == null) {
            return null;
        }
        return new PermissionResponseDTO(
                permission.getId(),
                permission.getName(),
                permission.getCreateAt()
        );
    }

    public List<PermissionResponseDTO> mapToPermissionsResponseDTO(List<PermissionEntity> permissions) {
        if (permissions == null) {
            return null;
        }
        return permissions.stream().map(this::mapToPermissionResponseDTO).collect(Collectors.toList());
    }
}
