package com.jp.hruser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.hruser.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
