package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.PrivateData;
import org.springframework.data.repository.CrudRepository;

public interface PrivateDataRepository extends CrudRepository<PrivateData, Long> {
    PrivateData findByNumber(String number);

}
