package com.services.api.storage.repository;

import com.services.api.storage.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    public Account findAccountByUsername(String username);
    public Account findAccountByEmail(String email);
    public Account findAccountByResetPwdCode(String resetPwdCode);
    public Account findAccountByEmailOrUsername(String email,String username);
    public Page<Account> findAllByKind(int kind, Pageable pageable);

    void deleteByUsername(String username);

    @Modifying
    @Transactional
    @Query(
         value = "ALTER TABLE hq_qrcode_account AUTO_INCREMENT = 1",
            nativeQuery = true
    )
    void resetAutoIncrement();
}
