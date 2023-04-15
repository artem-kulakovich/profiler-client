package by.bntu.fitr.profilerclient.api.service.impl;

import by.bntu.fitr.profilerclient.api.entity.PermissionEntity;
import by.bntu.fitr.profilerclient.api.exception.CommonException;
import by.bntu.fitr.profilerclient.api.repository.PermissionRepository;
import by.bntu.fitr.profilerclient.api.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(final PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PermissionEntity getPermissionByName(final String permission) {
        return permissionRepository.findByName(permission).orElseThrow(() -> new CommonException("Permission not found"));
    }
}
