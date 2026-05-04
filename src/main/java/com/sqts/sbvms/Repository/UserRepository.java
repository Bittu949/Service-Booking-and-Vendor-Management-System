package com.sqts.sbvms.Repository;

import com.sqts.sbvms.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
