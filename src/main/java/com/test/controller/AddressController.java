package com.test.controller;

import com.test.exception.NotFoundException;
import com.test.model.Address;
import com.test.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping()
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable int id) throws NotFoundException {
        return addressService.getById(id);
    }

    @PostMapping("/save")
    public Address save(@RequestBody Address address) {
        return addressService.save(address);
    }

    @DeleteMapping("/remove/{id}")
    public void removeById(@PathVariable int id) {
        addressService.removeById(id);
    }

    @PutMapping("/update")
    public Address update(Address address) throws NotFoundException {
        return addressService.update(address);
    }

    @GetMapping("/get/")
    public Address getByStreetAndBuildingNumber(@RequestParam String street, @RequestParam String buildingNumber) {
        return addressService.getAddressByStreetAndBuildingNumber(street, buildingNumber);
    }

}
