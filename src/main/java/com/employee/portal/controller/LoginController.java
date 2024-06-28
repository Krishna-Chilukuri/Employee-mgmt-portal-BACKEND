package com.employee.portal.controller;

import com.employee.portal.factory.Logger;
import com.employee.portal.model.Login;
import com.employee.portal.model.Session;
import com.employee.portal.model.viewableUser;
import com.employee.portal.service.implementation.LoginServiceImplementation;
import com.employee.portal.service.implementation.SessionServiceImplementation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "https://portal-employee-management.web.app/")
@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final LoginServiceImplementation loginServiceImplementation;
    private final SessionServiceImplementation sessionServiceImplementation;

    public LoginController(LoginServiceImplementation loginServiceImplementation, SessionServiceImplementation sessionServiceImplementation) {
        this.loginServiceImplementation = loginServiceImplementation;
        this.sessionServiceImplementation = sessionServiceImplementation;
    }

//    @RequestMapping("/findId")
//    public Login getLogin(@RequestParam(name = "id") String username, @RequestParam(name = "password") String password) {
//        Login res = loginServiceImplementation.getLoginById(username);
//        if (res.getPassword().equals(password)) {
//            return res;
//        }
//        else {
//            return null;
//        }
//    }


    @GetMapping(value = "/getId", produces = "application/json")
    public Session getLogin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpServletRequest request) throws IOException {
        Login login = loginServiceImplementation.getLoginById(username);
        Logger lg = Logger.getInstance();
        if (login.getUsername() == null) {
            lg.log("Returning null 1");
            return new Session();
        }
        // Logger lg = Logger.getInstance();
        // lg.log("Login Attempted");
        if (login.getPassword().equals(password)) {
            //            Cookie
            //            Logger lg= Logger.getInstance();
            //            Cookie ck = new Cookie("sessionId", "kp");
            //            response.addCookie(ck);
            
            Session sessionRecord = new Session();
            
            //            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            //            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            String clientIp = request.getRemoteAddr();
            sessionRecord.setSessionId(sessionId);
            sessionRecord.setIpAddress(clientIp);
            sessionRecord.setUsername(login.getUsername());
            sessionRecord.setPrivilege(login.getPrivilege());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 15);
            Timestamp expiryTs = new Timestamp(calendar.getTimeInMillis());
            sessionRecord.setExpiry(expiryTs);
            
            sessionServiceImplementation.saveSession(sessionRecord);
            
            
            lg.log(username + " login successful! session Record : " + sessionRecord);
            
            return sessionRecord;
            //            return Response.ok().entity(    )
        }
        else {
            // lg.log("Login Failed");
            lg.log("Returning null 2");
            return new Session();
        }
    }

    @RequestMapping("/checkSession")
    public Session checkSession(@RequestParam(name = "sessionId") String sessionId, HttpServletRequest request) throws IOException {
        Logger lg = Logger.getInstance();
        // lg.log("Checking Session upon page load: " + sessionId);
        Session currSession = sessionServiceImplementation.getSessionById(sessionId);
        // lg.log("Session: " + currSession);
        Calendar calendar = Calendar.getInstance();
        Timestamp currTs = new Timestamp(calendar.getTimeInMillis());
        HttpSession session = request.getSession();
        String currIp = request.getRemoteAddr();
        if (currSession.getSessionId() == null) {
            //No session
            // lg.log("Session not Found");
            return new Session();//empty priv and sessionId
        }
        else if(currTs.after(currSession.getExpiry())) {
            // lg.log("Session Expired");
            sessionServiceImplementation.deleteSessionById(sessionId);
            return new Session();
        }
        else if(!currSession.getIpAddress().equals(currIp)) {
            lg.log("Mismatch IP");
            sessionServiceImplementation.deleteSessionById(sessionId);
            return new Session();
        }
        return currSession;
    }

    @RequestMapping("/promoteToOwner")
    void promoteAdminToOwner(@RequestParam(name = "adminId") String adminId) throws IOException {
        Logger lg = Logger.getInstance();
        Login log = loginServiceImplementation.getLoginById(adminId);
        if (log.getUsername() == null) {
            // lg.log("User not Found in Login");
            return;
        } else if (Objects.equals(log.getPrivilege(), "guest") || Objects.equals(log.getPrivilege(), "priv_user")) {
            // lg.log("User is not an Admin to be promoted");
            return;
        } else if (Objects.equals(log.getPrivilege(), "owner")) {
            // lg.log("User is already an Owner");
            return;
        }
        // lg.log("User " + adminId + " is being promoted to Owner");
        loginServiceImplementation.updatePrivilege(adminId, "owner");
    }

    @RequestMapping("/demoteOwner")
    void demoteOwnerToAdmin(@RequestParam(name = "ownerId") String ownerId) throws IOException {
        Logger lg = Logger.getInstance();
        Login log = loginServiceImplementation.getLoginById(ownerId);
        if (log.getUsername() == null) {
            // lg.log("User not Found in Login");
            return;
        } else if (Objects.equals(log.getPrivilege(), "guest") || Objects.equals(log.getPrivilege(), "priv_user")) {
            // lg.log("User is not an Admin to be promoted");
            return;
        } else if (Objects.equals(log.getPrivilege(), "admin")) {
            // lg.log("User is already an Admin");
            return;
        }
        // lg.log("User " + ownerId + " is demoted to Admin");
        loginServiceImplementation.updatePrivilege(ownerId, "admin");
    }

    @RequestMapping("/removeAdminOwner")
    void removeAdminOwner(@RequestParam(name = "username") String username) throws IOException {
        Logger lg = Logger.getInstance();
        Login log = loginServiceImplementation.getLoginById(username);
        if (log.getUsername() == null) {
            // lg.log("User not Found in Login");
            return;
        } else if (Objects.equals(log.getPrivilege(), "guest") || Objects.equals(log.getPrivilege(), "priv_user")) {
            // lg.log("User is not an Admin / Owner");
            return;
        }
        // lg.log("User " + username + " is removed");
        loginServiceImplementation.removeLogin(username);
    }

    @RequestMapping("/viewAdminOwner")
    List<viewableUser> viewAdminOwner() throws IOException {
        Logger lg = Logger.getInstance();
        // lg.log("in viewAdminOwner");
        List<viewableUser> retList = new ArrayList<>();
        for (Login log: loginServiceImplementation.getAdminOwners()) {
            viewableUser user = new viewableUser(log);
//            lg.log("Returing : " + user );
            retList.add(user);
        }
//        lg.log("RETURN OF ADMIN OWNER :  " + loginServiceImplementation.getAdminOwners());
        return retList;
//        for (Login log: loginServiceImplementation.getAdminOwners()) {
//
//        }
    }

    @RequestMapping("/changePassword")
    String changePassword(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) throws IOException {
        Logger lg = Logger.getInstance();
        try{
            // lg.log("Change Password request received for "+username);
            loginServiceImplementation.updatePassword(username, password);
        }
        catch (Exception e) {
            return e.toString();
        }
        return "Password Changed Successfully !!!";
    }


//    @RequestMapping("/getAll")
//    public List<Login> getAllLogins() {
//        return loginServiceImplementation.loginRepository.findAll();
//    }
}

class LoginReturn {
    private String privilege;
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}