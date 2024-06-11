package org.mysite.ysmproject3.repository;


import org.mysite.ysmproject3.domain.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserInfoEntity, String> {

    UserInfoEntity findByClientUserId(String clientUserId);
    List<UserInfoEntity> findAll();
    void delete(UserInfoEntity userInfoEntity);
}