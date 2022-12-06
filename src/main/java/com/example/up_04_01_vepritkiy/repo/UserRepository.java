package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.PrivateData;
import com.example.up_04_01_vepritkiy.models.Role;
import com.example.up_04_01_vepritkiy.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long>{

        User findUserByUsername(String username);
        User findUsersByPrivateData(PrivateData privateData);
@Query("SELECT m FROM User m WHERE m.firstName LIKE ?1% or m.lastName LIKE ?1% or m.middleName LIKE ?1% or m.username LIKE ?1% ")
List<User> searchByRatingStartsWith(String full_text);

        @Query(value = "select * from user inner join user_role on user.id = user_role.user_id where user_role.roles = :nameRole",nativeQuery = true)
        List<User> findUsersByRole(String nameRole);

        User findUsersById(Long ID);

        //"select * from user inner join user_role on user.id = user_role.user_id where user_role.roles = 'млОТдел'"

}
