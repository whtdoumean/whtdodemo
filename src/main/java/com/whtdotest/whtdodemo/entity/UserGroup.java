package com.whtdotest.whtdodemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private UUID id;
    @Column
    private String user_group_name;
    @JoinColumn(name = "user_group_id")
    @OneToMany
    private List<User> users = new ArrayList<>();
}
