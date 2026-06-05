package com.PascuanSilvestre.TorqTrace.auth.config;

import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.RoleEntity;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.RoleRepository;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRoleIfNotExists(Roles.ROLE_ADMIN);
        createRoleIfNotExists(Roles.ROLE_USER);
        createRoleIfNotExists(Roles.ROlE_OWNER);
        createRoleIfNotExists(Roles.ROLE_MANAGER);
        createRoleIfNotExists(Roles.ROLE_MECHANIC);
        createRoleIfNotExists(Roles.ROLE_RECEPTIONIST);
    }

    private void createRoleIfNotExists(Roles role) {

        if (!roleRepository.existsByRole(role)) {

            RoleEntity roleEntity = RoleEntity.builder()
                    .role(role)
                    .build();

            roleRepository.save(roleEntity);
            System.out.println("Rol creado: " + role);
        } else {
            System.out.println("El rol ya existe: " + role);
        }
        }
}
