package com.employee.portal.repository;

import com.employee.portal.model.Session;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SessionRepository extends JpaRepository<Session, String> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Session s Where s.username = :user")
    void deleteByName(String user);


    @Transactional
    @Modifying
    @Query("UPDATE Session SET privilege = :priv WHERE username = :user")
    void updatePrivilegeByUsername(String user, String priv);
}
//UPDATE Login SET privilege = :newPrivilege WHERE username = :username