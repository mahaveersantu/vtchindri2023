package com.VTSangaliya.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUserIdAndUserPassword(String userId, String userPassword);

}
