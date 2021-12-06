package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.Address;
import com.test.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Transactional
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getById(int id) throws NotFoundException {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            throw new NotFoundException("Address not found by this id!");
        return optionalAddress.get();
    }

    @Override
    public void removeById(int id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address update(Address address) throws NotFoundException {
        Address updatedAddress = getById(address.getId());
        updatedAddress.setCity(address.getCity());
        updatedAddress.setStreet(address.getStreet());
        updatedAddress.setBuildingNumber(address.getBuildingNumber());
        updatedAddress.setUsers(address.getUsers());
        save(updatedAddress);
        return updatedAddress;
    }

    @Override
    public Address getAddressByStreetAndBuildingNumber(String street, String buildingNumber) {
        return addressRepository.getAddressByStreetAndBuildingNumber(street, buildingNumber);
    }
}
