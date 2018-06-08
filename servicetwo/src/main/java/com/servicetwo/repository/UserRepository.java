package com.servicetwo.repository;

import com.servicetwo.entitys.News;
import com.servicetwo.entitys.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Modifying(clearAutomatically = true)
//    @Query("update User u set u.setFollowingSubjects =:followingSubjects where u.id =:userId")
//    public void insertNews(@Param("userId") long userId, @Param("followingNews") List<News> followingNews);
    
    @Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name)")
    public User findByName(@Param("name") String name);
}
