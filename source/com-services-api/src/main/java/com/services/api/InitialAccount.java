package com.services.api;

import com.services.api.constant.ServiceConstant;
import com.services.api.storage.model.Account;
import com.services.api.storage.model.Group;
import com.services.api.storage.model.Permission;
import com.services.api.storage.repository.AccountRepository;
import com.services.api.storage.repository.GroupRepository;
import com.services.api.storage.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class InitialAccount {

    private AccountRepository qrCodeStorageService;

    private PasswordEncoder passwordEncoder;

    private GroupRepository groupRepository;

    private PermissionRepository permissionRepository;

    private List<Permission> addAdminPermission(){
        List<Permission> results = new ArrayList<>();
        Permission[] listPermissions = new Permission[]{
                new Permission(1L, "/v1/group/create", "Create group", "Create Group", "Group", false),
                new Permission(2L, "/v1/group/get", "View group", "View Group", "Group", false),
                new Permission(3L, "/v1/group/update", "Update group", "Update Group", "Group", false),
                new Permission(4L, "/v1/permission/create", "Create permission", "Create Permission", "Permission", false)
        };

        for(Permission permission : listPermissions) {
            results.add(permissionRepository.save(permission));
        }
        return results;
    }

    private List<Permission> addCustomerPermission() {
        List<Permission> results = new ArrayList<>();
        Permission[] listPermissions = new Permission[]{
                new Permission(1L, "/v1/group/create", "Create group", "Create Group", "Group", false),
                new Permission(2L, "/v1/group/get", "View group", "View Group", "Group", false),
                new Permission(3L, "/v1/group/update", "Update group", "Update Group", "Group", false),
                new Permission(4L, "/v1/permission/create", "Create permission", "Create Permission", "Permission", false)
        };

        for(Permission permission : listPermissions) {
            Permission foundPermission = permissionRepository.findByName(permission.getName()).orElse(null);
            if(foundPermission == null) {
                results.add(permissionRepository.save(permission));
            }
            else {
                results.add(permission);
            }
        }
        return results;
    }

    private List<Permission> addAgencyPermission() {
        List<Permission> results = new ArrayList<>();
        Permission[] listPermissions = new Permission[]{
                new Permission(1L, "/v1/group/create", "Create group", "Create Group", "Group", false),
                new Permission(2L, "/v1/group/get", "View group", "View Group", "Group", false),
                new Permission(3L, "/v1/group/update", "Update group", "Update Group", "Group", false),
                new Permission(4L, "/v1/permission/create", "Create permission", "Create Permission", "Permission", false)
        };

        for(Permission permission : listPermissions) {
            Permission foundPermission = permissionRepository.findByName(permission.getName()).orElse(null);
            if(foundPermission == null) {
                results.add(permissionRepository.save(permission));
            }
            else {
                results.add(permission);
            }
        }
        return results;
    }

    private Group initAdminGroup(List<Permission> defaultPermission){
        Group superAdminGroup = new Group();
        superAdminGroup.setKind(ServiceConstant.USER_KIND_ADMIN);
        superAdminGroup.setName("ROLE SUPPER ADMIN");
        superAdminGroup.setId(1L);
        superAdminGroup.setPermissions(defaultPermission);
        return groupRepository.save(superAdminGroup);
    }

    private Group initCustomerGroup(List<Permission> customerPermissionList) {
        Group customerGroup = new Group();
        customerGroup.setKind(ServiceConstant.USER_KIND_CUSTOMER);
        customerGroup.setName("ROLE CUSTOMER");
        customerGroup.setId(2L);
        customerGroup.setPermissions(customerPermissionList);
        return groupRepository.save(customerGroup);
    }

    private Group initAgencyGroup(List<Permission> agencyPermissionList) {
        Group agencyGroup = new Group();
        agencyGroup.setKind(ServiceConstant.USER_KIND_AGENCY);
        agencyGroup.setName("ROLE AGENCY");
        agencyGroup.setId(3L);
        agencyGroup.setPermissions(agencyPermissionList);
        return groupRepository.save(agencyGroup);
    }

    public void createAllUserRole(){
        qrCodeStorageService.deleteAll();
        groupRepository.deleteAll();
        permissionRepository.deleteAll();

        qrCodeStorageService.resetAutoIncrement();
        groupRepository.resetAutoIncrement();
        permissionRepository.resetAutoIncrement();

        Account adminAccount = new Account();
        List<Permission> adminPermissionList = addAdminPermission();
        Group adminGroup = initAdminGroup(adminPermissionList);
        adminAccount.setKind(ServiceConstant.USER_KIND_ADMIN);
        adminAccount.setPassword(passwordEncoder.encode("admin123654"));
        adminAccount.setUsername("admin");
        adminAccount.setStatus(ServiceConstant.STATUS_ACTIVE);
        adminAccount.setGroup(adminGroup);
        adminAccount.setFullName("Root account");
        qrCodeStorageService.save(adminAccount);

        Account customerAccount = new Account();
        List<Permission> customerPermissionList = addCustomerPermission();
        Group customerGroup = initCustomerGroup(customerPermissionList);
        customerAccount.setUsername("customer");
        customerAccount.setPassword(passwordEncoder.encode("customer123654"));
        customerAccount.setKind(ServiceConstant.USER_KIND_CUSTOMER);
        customerAccount.setStatus(ServiceConstant.STATUS_ACTIVE);
        customerAccount.setGroup(customerGroup);
        customerAccount.setFullName("Customer account");
        qrCodeStorageService.save(customerAccount);

        Account agencyAccount = new Account();
        List<Permission> agencyPermissionList = addAgencyPermission();
        Group agencyGroup = initAgencyGroup(agencyPermissionList);
        agencyAccount.setUsername("agency");
        agencyAccount.setPassword(passwordEncoder.encode("agency123654"));
        agencyAccount.setKind(ServiceConstant.USER_KIND_AGENCY);
        agencyAccount.setStatus(ServiceConstant.STATUS_ACTIVE);
        agencyAccount.setFullName("Agency account");
        agencyAccount.setGroup(agencyGroup);
        qrCodeStorageService.save(agencyAccount);
    }

    public InitialAccount(AccountRepository qrCodeStorageService, PasswordEncoder passwordEncoder, GroupRepository groupRepository, PermissionRepository permissionRepository) {
        this.qrCodeStorageService = qrCodeStorageService;
        this.passwordEncoder = passwordEncoder;
        this.groupRepository = groupRepository;
        this.permissionRepository = permissionRepository;
    }
}
