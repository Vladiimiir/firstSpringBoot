package com.test.service;


import com.test.exception.BadRequestException;
import com.test.exception.NotFoundException;
import com.test.exception.NotVerifiedUserException;
import com.test.model.Address;
import com.test.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    void save(User user) throws NotFoundException;
    User getById(int id) throws NotFoundException;
    void removeById(int id);
    User update(User user) throws NotFoundException;
    User getByEmail(String email) throws NotFoundException;
    List<User> getAllByName(String name);
    List<User> getAllByAddress(Address address);
    User getByEmailAndPassword(String email, String password) throws NotVerifiedUserException, NotFoundException, BadRequestException;
    void verify(String email);
    void sendEmail(String email);
    User upToResetPassword(String email) throws NotFoundException;
    void resetPassword(User user, String newPassword, String repeatedPassword) throws NotFoundException, BadRequestException;
}
