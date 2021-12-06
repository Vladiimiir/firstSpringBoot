package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.Phone;

import java.util.List;

public interface PhoneService {

    List<Phone> getAll();
    Phone save(Phone phone);
    Phone getById(int id) throws NotFoundException;
    void removeById(int id);
    Phone update(Phone phone) throws NotFoundException;
    List<Phone> getAllByModel(String model);
    Phone getPhoneByModel(String model);

}
