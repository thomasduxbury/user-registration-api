package com.thomasduxbury.userregistrationapi.repository;

import com.thomasduxbury.userregistrationapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
