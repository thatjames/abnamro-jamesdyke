package xyz.slimjim.hungrytales.storage.postgres.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import xyz.slimjim.hungrytales.common.auth.LoginRequest;
import xyz.slimjim.hungrytales.common.auth.RegisterRequest;
import xyz.slimjim.hungrytales.common.auth.User;
import xyz.slimjim.hungrytales.common.exceptions.HungryTalesException;
import xyz.slimjim.hungrytales.storage.service.AuthDAO;

public class AuthDAOImpl implements AuthDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void register(RegisterRequest registerRequest) {
        try {
            
        } catch (DataAccessException dax) {
            throw new HungryTalesException("postgres error", dax);
        }
    }

    @Override
    public User login(LoginRequest loginRequest) {
        return null;
    }
}
