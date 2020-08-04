package next.dao;

import java.sql.SQLException;

import core.jdbc.ConnectionManager;
import next.model.User;

public class FindByUserIdJdbcTemplate {
    public User findByUserId(UserDao userDao, String userId) throws SQLException {
        try {
            userDao.con = ConnectionManager.getConnection();
            String sql = userDao.createQueryForByUserId();
            userDao.pstmt = userDao.con.prepareStatement(sql);
            userDao.setValuesForByUserId(userDao.pstmt, userId);

            userDao.rs = userDao.pstmt.executeQuery();

            User user = (User) userDao.mapRowForByUserId(userDao.rs);

            return user;
        } finally {
            if (userDao.rs != null) {
                userDao.rs.close();
            }
            if (userDao.pstmt != null) {
                userDao.pstmt.close();
            }
            if (userDao.con != null) {
                userDao.con.close();
            }
        }
    }
}
