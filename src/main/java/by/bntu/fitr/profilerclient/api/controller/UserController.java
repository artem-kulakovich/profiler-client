package by.bntu.fitr.profilerclient.api.controller;

import by.bntu.fitr.profilerclient.api.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.response.UserResponseDTO;
import by.bntu.fitr.profilerclient.api.entity.UserEntity;
import by.bntu.fitr.profilerclient.api.exception.CommonException;
import by.bntu.fitr.profilerclient.api.mapper.UserRolePermissionMapper;
import by.bntu.fitr.profilerclient.api.model.Base64Token;
import by.bntu.fitr.profilerclient.api.service.UserService;
import by.bntu.fitr.profilerclient.api.uti.AuthorizationTokenUtil;
import by.bntu.fitr.profilerclient.core.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserService userService;
    private final AuthorizationTokenUtil authorizationTokenUtil;
    private final UserRolePermissionMapper userRolePermissionMapper;

    @Autowired
    public UserController(final UserService userService,
                          final AuthorizationTokenUtil authorizationTokenUtil,
                          final UserRolePermissionMapper userRolePermissionMapper) {
        this.userService = userService;
        this.authorizationTokenUtil = authorizationTokenUtil;
        this.userRolePermissionMapper = userRolePermissionMapper;
    }

    @PostMapping(value = "")
    public ResponseEntity<UserResponseDTO> createUser(@RequestHeader(value = "Authorization") final String authorizationToken,
                                                      @RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        Base64Token base64Token = authorizationTokenUtil.getTokenParts(authorizationToken);

        UserEntity currentUser = userService.getUserByName(base64Token.getUserName());

        if (!currentUser.getRole().getName().equals(CommonConstant.ADMIN_ROLE)) {
            throw new CommonException("You dont have permission");
        }

        UserEntity user = userService.createUser(userCreateRequestDTO);

        return new ResponseEntity<>(userRolePermissionMapper.mapToUserResponseDTO(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(value = "id") Long id) {
        UserEntity user = userService.getUserById(id);
        return new ResponseEntity<>(userRolePermissionMapper.mapToUserResponseDTO(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(value = "id") Long id,
                                               @RequestHeader(value = "Authorization") final String authorizationToken) {
        Base64Token base64Token = authorizationTokenUtil.getTokenParts(authorizationToken);

        UserEntity currentUser = userService.getUserByName(base64Token.getUserName());

        if (!currentUser.getRole().getName().equals(CommonConstant.ADMIN_ROLE)) {
            throw new CommonException("You dont have permission");
        }

        userService.deleteUserById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
