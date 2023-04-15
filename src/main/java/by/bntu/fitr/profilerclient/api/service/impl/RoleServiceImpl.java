package by.bntu.fitr.profilerclient.api.service.impl;

import by.bntu.fitr.profilerclient.api.entity.RoleEntity;
import by.bntu.fitr.profilerclient.api.exception.CommonException;
import by.bntu.fitr.profilerclient.api.repository.RoleRepository;
import by.bntu.fitr.profilerclient.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity getRoleByName(final String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new CommonException("Role not found"));
    }
}
