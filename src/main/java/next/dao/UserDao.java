package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            
            @Override
            public void setValues(PreparedStatement pstmt, Connection con, String sql) throws SQLException {
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());

                pstmt.executeUpdate();
            }
        };
        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            @Override
            public void setValues(PreparedStatement pstmt, Connection con, String sql) throws SQLException {
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
                
                pstmt.executeUpdate();                
            }
        };
        jdbcTemplate.update("UPDATE USERS SET password=?, name=?, email=? WHERE userId=?");
    }
    
    public List<User> findAll() throws SQLException {
        // TODO 구현 필요함.
        try {
            con = ConnectionManager.getConnection();
            String sql = createQueryForFindAll();
            pstmt = con.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            
            List<User> userList = (List<User>) mapRowForFindAll(rs);
            
            return userList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    private String createQueryForFindAll() {
        return "SELECT userId, password, name, email FROM USERS";
    }
    
    private Object mapRowForFindAll(ResultSet rs) throws SQLException {
        List<User> userList = new ArrayList<User>();
        while (rs.next()) {
            User user = new User(
                    rs.getString("userId"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"));
            userList.add(user);
        }
        
        return userList;
    }
    

    public User findByUserId(String userId) throws SQLException {
        try {
            con = ConnectionManager.getConnection();
            String sql = createQueryForByUserId();
            pstmt = con.prepareStatement(sql);
            setValuesForByUserId(pstmt, userId);

            rs = pstmt.executeQuery();

            User user = (User) mapRowForByUserId(rs);

            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    private String createQueryForByUserId() {
        return "SELECT userId, password, name, email FROM USERS WHERE userid=?";
    }
    
    private void setValuesForByUserId(PreparedStatement pstmt, String userId) throws SQLException {
        pstmt.setString(1, userId);
    }
    
    private Object mapRowForByUserId(ResultSet rs) throws SQLException {
        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                    rs.getString("email"));
        }
        return user;
    }
}