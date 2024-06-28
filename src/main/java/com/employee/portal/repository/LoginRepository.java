package com.employee.portal.repository;

import com.employee.portal.model.Login;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login, String> {
    @Transactional
    @Modifying
    @Query("UPDATE Login SET privilege = :newPrivilege WHERE username = :username")
    void updatePrivilegeUsingId(String newPrivilege, String username);

    @Query("SELECT l FROM Login l WHERE l.privilege = 'admin' or l.privilege = 'owner'")
    List<Login> getAdminOwner();

    @Transactional
    @Modifying
    @Query("UPDATE Login SET password = :password WHERE username = :username")
    void updatePasswordById(String username, String password);
}
