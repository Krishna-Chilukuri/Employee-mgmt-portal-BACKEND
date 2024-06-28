package com.employee.portal.service;

import com.employee.portal.model.Session;

public interface SessionService {
    Session getSessionById(String sessionId);
    Session saveSession(Session logSS);
    void deleteSessionById(String sessionId);
    void deleteSessionByUsername(String username);
    void updatePrivilege(String username, String privilege);
}
