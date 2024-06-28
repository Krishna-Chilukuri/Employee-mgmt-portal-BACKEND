package com.employee.portal.service.implementation;

import com.employee.portal.model.Login;
import com.employee.portal.repository.LoginRepository;
import com.employee.portal.repository.SessionRepository;
import com.employee.portal.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LoginServiceImplementation implements LoginService {
    public LoginRepository loginRepository;
    public SessionRepository sessionRepository;

    public LoginServiceImplementation(LoginRepository loginRepository, SessionRepository sessionRepository) {
        super();
        this.loginRepository = loginRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Login getLoginById(String loginId) {
        Optional<Login> testLog = loginRepository.findById(loginId);
        return testLog.orElseGet(Login::new);
    }

    @Override
    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Login saveLogin(Login log) {
        return loginRepository.save(log);
    }

    @Override
    public void removeLogin(String username) {
        sessionRepository.deleteByName(username);
        loginRepository.deleteById(username);
    }

    @Override
    public void updatePrivilege(String username, String privilege) {
        loginRepository.updatePrivilegeUsingId(privilege, username);
        sessionRepository.updatePrivilegeByUsername(String.valueOf(username), privilege);
    }

    @Override
    public List<Login> getAdminOwners() {
//        return loginRepository.
        return loginRepository.getAdminOwner();
    }


    public void updatePassword(String username, String password) {
        loginRepository.updatePasswordById(username, password);
    }
}
