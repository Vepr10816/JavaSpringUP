package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.User;
import com.example.up_04_01_vepritkiy.models.UserTaskStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserTaskStatusRepository extends CrudRepository<UserTaskStatus,Long> {
    UserTaskStatus findUserTaskStatusById(Long id);

    List<UserTaskStatus> findUserTaskStatusByUser(User user);

}
