package org.wecancodeit.medicinecabinetapp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.wecancodeit.medicinecabinetapp.Login;
import org.wecancodeit.medicinecabinetapp.base.classes.User;	

public class UserRepositoryImpl {

	@Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	  public void register(User user) {
	    String sql = "insert into users values(?,?,?,?,?,?,?)";
	    jdbcTemplate.update(sql, new Object[] { user.getFirstName(), user.getUserPassword(), user.getFirstName(),
	    user.getLastName(), user.getUserEmail(), user.getUserPhone() });
	    }
	    public User validateUser(Login login) {
	    String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword()
	    + "'";
	    List<User> users = jdbcTemplate.query(sql, new UserMapper());
	    return users.size() > 0 ? users.get(0) : null;
	    }
	
	  }
	  class UserMapper implements RowMapper<User> {
	  public User mapRow(ResultSet rs, int arg1) throws SQLException {
	    User user = new User();
	    user.setFirstName(rs.getString("firstname"));
	    user.setLastName(rs.getString("lastname"));
	    user.setUserName(rs.getString("userName"));
	    user.setUserPassword(rs.getString("password"));
	    user.setUserEmail(rs.getString("email"));
	    user.setUserPhone(rs.getString("phone"));
	    return user;
	  }
	}
	

