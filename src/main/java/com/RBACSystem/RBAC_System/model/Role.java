package com.RBACSystem.RBAC_System.model;


import java.util.Set;

public enum Role {

    ROLE_ADMIN(Set.of(
            Permissions.MANAGER_READ,
            Permissions.MANAGER_WRITE,
            Permissions.MANAGER_UPDATE,
            Permissions.MANAGER_DELETE
    )),
    ROLE_MANAGER(Set.of(
            Permissions.CASHIER_READ,
            Permissions.CASHIER_WRITE,
            Permissions.CASHIER_UPDATE,
            Permissions.CASHIER_DELETE
    ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

}
