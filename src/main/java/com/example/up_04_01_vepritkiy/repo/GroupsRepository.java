package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.GroupUser;
import com.example.up_04_01_vepritkiy.models.Groups;
import com.example.up_04_01_vepritkiy.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface GroupsRepository extends CrudRepository<Groups,Long> {
    @Query("SELECT m FROM Groups m WHERE m.nameGroup LIKE ?1%")
    List<Groups> searchByRatingStartsWith(String full_text);

    @Query(value = "SELECT MAX(id) FROM groups",nativeQuery = true)
    Long findLastID();

    Groups findGroupsByNameGroup(String nameGroup);

    //Collection<Groups> findGroupsByGroupUserCollection(Collection<GroupUser> groups);
}
