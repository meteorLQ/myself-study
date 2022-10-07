package com.lq.springdata.repository;

import com.lq.springdata.entity.UserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LQ
 * @date 2020/08/27 17:07
 */
@Repository
public interface UserInfoRep extends CrudRepository<UserInfo, String>, PagingAndSortingRepository<UserInfo, String> {

    @Query("select u from UserInfo u where u.userId=?1")
    UserInfo findUserInfo(String userId);

    @Query("select u from  UserInfo u where u.userName =:userName")
    UserInfo findUserAll(@Param("userName") String userName);

    @Transactional
    @Modifying
    @Query("update UserInfo u set u.userName=?1 where u.userId=?2")
    int updateUserInfo(String userName, String userId);

    @Transactional
    @Modifying
    @Query("delete from UserInfo u where u.userId=?1")
    int deleteUserInfo(String userId);
}
