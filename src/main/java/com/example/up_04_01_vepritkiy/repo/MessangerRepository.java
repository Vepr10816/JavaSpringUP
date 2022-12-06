package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.Messanger;
import com.example.up_04_01_vepritkiy.models.Tasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessangerRepository extends CrudRepository<Messanger, Long> {
    Messanger findMessangerById(Long id);

    List<Messanger> findMessangerByTasks(Tasks tasks);
}
