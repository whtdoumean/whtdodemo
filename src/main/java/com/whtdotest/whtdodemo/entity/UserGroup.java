package com.whtdotest.whtdodemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

@Entity
@Table(name = "user_group")
@Data // add getters and setters
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // generation of uuid
public class UserGroup {
    @Id
    @Column(name = "user_group_id")
    @EqualsAndHashCode.Include
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    UUID id;
    @Column(name = "user_group_name")
    String userGroupName;

    @OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL)
    List<User> users = new ArrayList<>();

    public void addUser(User user) {


        if (user.getId() == null || !users.contains(user)) {
            user.setUserGroup(this);
            users.add(user);
        }
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
