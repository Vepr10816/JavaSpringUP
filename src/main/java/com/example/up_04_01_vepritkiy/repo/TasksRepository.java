package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.Groups;
import com.example.up_04_01_vepritkiy.models.Tasks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface TasksRepository extends CrudRepository<Tasks,Long> {
    @Query("SELECT m FROM Tasks m WHERE m.descriptionTask LIKE ?1%")
    List<Tasks> searchByRatingStartsWith(String full_text);

    Tasks findTasksById(Long id);


    //Collection<Tasks> findTasksByGroups(Collection<Groups> groups);
}
