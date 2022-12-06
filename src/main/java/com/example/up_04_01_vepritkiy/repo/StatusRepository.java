package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {

    Status findStatusById(Long id);
}
