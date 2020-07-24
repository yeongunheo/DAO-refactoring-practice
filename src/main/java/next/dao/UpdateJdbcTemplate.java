package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;
import next.model.User;

public abstract class UpdateJdbcTemplate {
    public abstract void setValues(User user, PreparedStatement pstmt, Connection con) throws SQLException;
    public abstract String createQuery();
    
    public void update(User user) throws SQLException {
        PreparedStatement pstmt = null;
        Connection con = ConnectionManager.getConnection();
        try {
            setValues(user, pstmt, con);
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
