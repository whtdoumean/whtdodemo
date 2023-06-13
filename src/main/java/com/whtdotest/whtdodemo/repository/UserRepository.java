package com.whtdotest.whtdodemo.repository;

import com.whtdotest.whtdodemo.entity.User;
import com.whtdotest.whtdodemo.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByPassportNumber(String passportNumber);
    List<UserProjection> findByPassportNumberIs(String passportNumber);
    List<UserProjection> findByFirstNameLike(String firstName);
}
