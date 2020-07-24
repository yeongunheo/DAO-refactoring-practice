package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;
import next.model.User;

public class InsertJdbcTemplate {
    public void insert(User user, UserDao userDao) throws SQLException {
        PreparedStatement pstmt = null;
        Connection con = ConnectionManager.getConnection();
        try {
            userDao.setValuesForInsert(user, pstmt, con);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            
            if (con != null) {
                con.close();
            }
        }
    }
}
