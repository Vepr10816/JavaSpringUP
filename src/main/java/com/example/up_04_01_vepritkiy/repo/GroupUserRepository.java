package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.GroupUser;
import com.example.up_04_01_vepritkiy.models.Groups;
import com.example.up_04_01_vepritkiy.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface GroupUserRepository extends CrudRepository<GroupUser,Long> {

    GroupUser findGroupUserById(Long id);

    List<GroupUser> findGroupUserByUser(User user);
}
