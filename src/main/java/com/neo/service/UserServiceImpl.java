package com.neo.service;

import com.neo.model.CustomUser;
import com.neo.model.User;
import com.neo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepository userRepositoryInit;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    public void UserInit() {
        User usr = new User();
        usr.setId(10);
        usr.setUserName("admin");
        usr.setPassword("123456");
        usr.setEmail("test@126.com");
        usr.setRole("ROLE_ADMIN");
        userRepository.save(usr);
    }


    @Override
    public void save(User user) {
        System.out.println("carylog password " + user.getPassword() );
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String userName) {
        User originUser = userRepository.findByUserName(userName);
        if (originUser == null) {
            return null;
        }
        return originUser;
    }

    public CustomUser getCustomUserByUserName(String userName) {
       User user = this.getUserByUsername(userName);
       CustomUser customUser = new CustomUser(user.getId(), user.getUserName(), getPassword(user.getPassword()), getGrants(user.getRole()));
       return customUser;

    }

    private String getPassword(String raw) {
        return passwordEncoder.encode(raw);
    }

    private Collection<GrantedAuthority> getGrants(String role) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }
}


