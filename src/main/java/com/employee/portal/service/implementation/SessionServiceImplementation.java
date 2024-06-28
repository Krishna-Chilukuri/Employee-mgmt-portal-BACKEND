package com.employee.portal.service.implementation;

import com.employee.portal.model.Session;
import com.employee.portal.repository.SessionRepository;
import com.employee.portal.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionServiceImplementation implements SessionService {
    public SessionRepository sessionRepository;

    public SessionServiceImplementation(SessionRepository sessionRepository) {
        super();
        this.sessionRepository = sessionRepository;
    }


    @Override
    public Session getSessionById(String sessionId) {
        Optional<Session> testSucc = sessionRepository.findById(sessionId);
        return testSucc.orElseGet(Session::new);
    }

    @Override
    public Session saveSession(Session logSS) {
        return sessionRepository.save(logSS);
    }

    @Override
    public void deleteSessionById(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    @Override
    public void deleteSessionByUsername(String username) {
        sessionRepository.deleteByName(username);
    }

    @Override
    public void updatePrivilege(String username, String privilege) {
        sessionRepository.updatePrivilegeByUsername(username, privilege);
    }
}
