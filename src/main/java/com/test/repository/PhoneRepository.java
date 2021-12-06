package com.test.repository;

import com.test.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    List<Phone> findAllByModel(String model);
    Phone getPhoneByModel(String model);
}
