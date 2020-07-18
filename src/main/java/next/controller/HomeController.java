package next.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.UserDao;

public class HomeController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UserDao userDao = new UserDao();
        try {
            req.setAttribute("users", userDao.findAll());    
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        
        return "home.jsp";
    }
}
