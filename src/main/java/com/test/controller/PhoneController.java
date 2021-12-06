package com.test.controller;

import com.test.exception.NotFoundException;
import com.test.model.Phone;
import com.test.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping()
    public List<Phone> getAll() {
        return phoneService.getAll();
    }

    @GetMapping("/{id}")
    public Phone getById(@PathVariable int id) throws NotFoundException {
        return phoneService.getById(id);
    }

    @PostMapping("/save")
    public Phone save(@RequestBody Phone phone) {
        return phoneService.save(phone);
    }

    @DeleteMapping("/remove/{id}")
    public void removeById(@PathVariable int id) {
        phoneService.removeById(id);
    }

    @PutMapping("/update")
    public Phone update(@RequestBody Phone phone) throws NotFoundException {
        return phoneService.update(phone);
    }

    @GetMapping("/all-by-model")
    public List<Phone> getAllByModel(@RequestParam String model) {
        return phoneService.getAllByModel(model);
    }

    @GetMapping("/by-model")
    public Phone getPhoneByModel(@RequestParam String model) {
        return phoneService.getPhoneByModel(model);
    }
}
