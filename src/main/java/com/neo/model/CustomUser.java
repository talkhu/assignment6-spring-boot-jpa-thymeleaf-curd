package com.neo.model;

import com.neo.security.IsUser;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import java.util.Collection;
import java.security.Principal;

public class CustomUser extends User {
    @Id
    private long id;

    public CustomUser(long id, String userName, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userName, password, authorities);
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
