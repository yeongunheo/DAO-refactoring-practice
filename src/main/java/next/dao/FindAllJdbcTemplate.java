package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public abstract class FindAllJdbcTemplate {
    public abstract Object mapRow(ResultSet rs) throws SQLException;
    public abstract String createQuery();
    
    public List<User> findAll(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
        // TODO 구현 필요함.
        try {
            con = ConnectionManager.getConnection();
            String sql = createQuery();
            pstmt = con.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            
            List<User> userList = (List<User>) mapRow(rs);
            
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
}
