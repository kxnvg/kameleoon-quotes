package com.kxnvg.kameleoon.repository;

import com.kxnvg.kameleoon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
