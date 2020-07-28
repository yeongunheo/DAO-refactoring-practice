package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;

public abstract class JdbcTemplate {
    public abstract void setValues(PreparedStatement pstmt, Connection con, String sql) throws SQLException;
    
    public void update(String sql) throws SQLException {
        PreparedStatement pstmt = null;
        Connection con = ConnectionManager.getConnection();
        try {
            setValues(pstmt, con, sql);
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
