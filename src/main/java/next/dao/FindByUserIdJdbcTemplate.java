package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;
import next.model.User;

public abstract class FindByUserIdJdbcTemplate {
    public abstract void setValuesForByUserId(PreparedStatement pstmt, String userId) throws SQLException;
    public abstract Object mapRowForByUserId(ResultSet rs) throws SQLException;
    public abstract String createQueryForByUserId();
    
    public User findByUserId(Connection con, PreparedStatement pstmt, ResultSet rs, String userId) throws SQLException {
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
}
