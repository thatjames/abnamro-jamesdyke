package xyz.slimjim.hungrytales.storage.postgres.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.slimjim.hungrytales.common.auth.LoginRequest;
import xyz.slimjim.hungrytales.common.auth.RegisterRequest;
import xyz.slimjim.hungrytales.common.auth.User;
import xyz.slimjim.hungrytales.common.exceptions.HungryTalesException;
import xyz.slimjim.hungrytales.common.exceptions.RegistrationException;
import xyz.slimjim.hungrytales.storage.service.AuthDAO;

@Component
public class AuthDAOImpl implements AuthDAO {

    private static final String REGISTER_INSERT = "insert into auth (username, name, surname, password, salt) values (?, ?, ?, ?, ?)";
    private static final String GET_AUTH_DATA = "select * from auth where username = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void register(RegisterRequest registerRequest) {
        try {
            jdbcTemplate.update(REGISTER_INSERT, registerRequest.getUsername(), registerRequest.getName(), registerRequest.getSurname(), registerRequest.getPassword(), registerRequest.getSalt());
        } catch (DuplicateKeyException dke) {
            throw new RegistrationException("username already exists");
        } catch (DataAccessException dax) {
            throw new HungryTalesException("postgres error", dax);
        }
    }

    @Override
    public User getUser(LoginRequest loginRequest) {
        try {
            return jdbcTemplate.queryForObject(GET_AUTH_DATA, BeanPropertyRowMapper.newInstance(User.class), loginRequest.getUsername());
        } catch (DataAccessException dax) {
            throw new HungryTalesException("postgress error", dax);
        }
    }
}
