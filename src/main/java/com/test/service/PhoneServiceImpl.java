package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.Phone;
import com.test.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService{

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public List<Phone> getAll() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Phone getById(int id) throws NotFoundException {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        if (!optionalPhone.isPresent())
            throw new NotFoundException("Phone not found by this id!");
        return optionalPhone.get();
    }

    @Override
    public void removeById(int id) {
        phoneRepository.deleteById(id);

    }

    @Override
    public Phone update(Phone phone) throws NotFoundException {
        Phone updatedPhone = getById(phone.getId());
        updatedPhone.setModel(phone.getModel());
        updatedPhone.setUser(phone.getUser());
        save(updatedPhone);
        return updatedPhone;
    }

    @Override
    public List<Phone> getAllByModel(String model) {
        return phoneRepository.findAllByModel(model);
    }

    @Override
    public Phone getPhoneByModel(String model) {
        return phoneRepository.getPhoneByModel(model);
    }
}
