package by.bntu.fitr.profilerclient.api.service;

import by.bntu.fitr.profilerclient.api.entity.PermissionEntity;

public interface PermissionService {

    PermissionEntity getPermissionByName(final String permission);
}
