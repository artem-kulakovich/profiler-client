package by.bntu.fitr.profilerclient.api.service.impl;

import by.bntu.fitr.profilerclient.api.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.entity.PermissionEntity;
import by.bntu.fitr.profilerclient.api.entity.RoleEntity;
import by.bntu.fitr.profilerclient.api.entity.UserEntity;
import by.bntu.fitr.profilerclient.api.exception.AlreadyExistsException;
import by.bntu.fitr.profilerclient.api.exception.CommonException;
import by.bntu.fitr.profilerclient.api.exception.NotFoundException;
import by.bntu.fitr.profilerclient.api.mapper.UserRolePermissionMapper;
import by.bntu.fitr.profilerclient.api.model.Base64Token;
import by.bntu.fitr.profilerclient.api.repository.UserRepository;
import by.bntu.fitr.profilerclient.api.service.PermissionService;
import by.bntu.fitr.profilerclient.api.service.RoleService;
import by.bntu.fitr.profilerclient.api.service.UserService;
import by.bntu.fitr.profilerclient.api.uti.AuthorizationTokenUtil;
import by.bntu.fitr.profilerclient.api.uti.PasswordEncoder;
import by.bntu.fitr.profilerclient.core.constant.CommonConstant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRolePermissionMapper userRolePermissionMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final AuthorizationTokenUtil authorizationTokenUtil;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final UserRolePermissionMapper userRolePermissionMapper,
                           final PasswordEncoder passwordEncoder,
                           final RoleService roleService,
                           final PermissionService permissionService,
                           final AuthorizationTokenUtil authorizationTokenUtil) {
        this.userRepository = userRepository;
        this.userRolePermissionMapper = userRolePermissionMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.authorizationTokenUtil = authorizationTokenUtil;
    }

    @Transactional
    @Override
    public UserEntity createUser(UserCreateRequestDTO userCreateRequestDTO) {
        UserEntity user = userRolePermissionMapper.mapToUserEntity(userCreateRequestDTO);
        user.setCreateAt(LocalDateTime.now());

        String password = userCreateRequestDTO.getPassword();
        if (password.equals(userCreateRequestDTO.getRepeatedPassword())) {
            throw new CommonException("Password missmatches");
        }

        if (userRepository.findByUserName(userCreateRequestDTO.getUserName()).isPresent()) {
            throw new AlreadyExistsException("User already exists");
        }

        user.setPassword(passwordEncoder.encodeWithMD5(password));
        RoleEntity role = roleService.getRoleByName(CommonConstant.USER_ROLE);
        PermissionEntity readPermission = permissionService.getPermissionByName(CommonConstant.READ_PERMISSION);
        PermissionEntity writePermission = permissionService.getPermissionByName(CommonConstant.WRITE_PERMISSION);

        role.addPermission(readPermission);
        role.addPermission(writePermission);

        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public boolean isValidUser(final String authenticationToken) {
        if (!authorizationTokenUtil.isTokenValid(authenticationToken)) {
            return false;
        }

        Base64Token base64Token = authorizationTokenUtil.getTokenParts(authenticationToken);

        UserEntity user = userRepository.findByUserName(base64Token.getUserName()).orElse(null);

        if (user == null) {
            return false;
        }

        if (passwordEncoder.encodeWithMD5(base64Token.getPassword()).equals(user.getPassword())) {
            return false;
        }

        return true;
    }

    @Override
    public UserEntity getUserByName(final String name) {
        return userRepository.findByUserName(name).orElseThrow(() -> new CommonException("user not found"));
    }

    @Override
    public UserEntity getUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public void deleteUserById(final Long id) {
        UserEntity user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public void addRoleToUser(final Long userId, final Long roleId) {

    }
}
