package com.pauldok.cruduserbase.repository;

import com.pauldok.cruduserbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBaseRepository extends JpaRepository<User, Integer> {
}
