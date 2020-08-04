package next.dao;

import java.sql.SQLException;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class FindAllJdbcTemplate {
    public List<User> findAll(UserDao userDao) throws SQLException {
        // TODO 구현 필요함.
        try {
            userDao.con = ConnectionManager.getConnection();
            String sql = userDao.createQueryForFindAll();
            userDao.pstmt = userDao.con.prepareStatement(sql);
            
            userDao.rs = userDao.pstmt.executeQuery();
            
            List<User> userList = (List<User>) userDao.mapRowForFindAll(userDao.rs);
            
            return userList;
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
