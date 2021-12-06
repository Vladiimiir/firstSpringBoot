package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAll();
    Address save(Address address);
    Address getById(int id) throws NotFoundException;
    void removeById(int id);
    Address update(Address address) throws NotFoundException;
    Address getAddressByStreetAndBuildingNumber(String street, String buildingNumber);
}
