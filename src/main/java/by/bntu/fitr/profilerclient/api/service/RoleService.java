package by.bntu.fitr.profilerclient.api.service;

import by.bntu.fitr.profilerclient.api.entity.RoleEntity;

public interface RoleService {

    RoleEntity getRoleByName(final String name);
}
