package com.test.service;

import com.test.exception.BadRequestException;
import com.test.exception.NotFoundException;
import com.test.exception.NotVerifiedUserException;
import com.test.model.Address;
import com.test.model.Status;
import com.test.model.User;
import com.test.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void save(User user) throws NotFoundException {
//        Address address = addressService.getAddressByStreetAndBuildingNumber(user.getAddress().getStreet(), user.getAddress().getBuildingNumber());
//        if (address != null)
//            user.setAddress(new Address(address.getId()));
//        else
//            addressService.save(user.getAddress());
        addressService.save(user.getAddress());
        phoneService.save(user.getPhone());
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        String link = "http://localhost:8090/users/verify?email=" + user.getEmail();
        userRepository.save(user);
        mailSender.sendSimpleMessage(user.getEmail(), "Verify", link);
    }

    @Override
    public User getById(int id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            throw new NotFoundException("User not found by this id!");
        return optionalUser.get();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.GetByEmail(email);
    }

    @Override
    public List<User> getAllByName(String name) {
        return userRepository.getAllByName(name);
    }

    @Override
    public List<User> getAllByAddress(Address address) {
        return userRepository.getAllByAddress(address);
    }

    @Override
    public User getByEmailAndPassword(String email, String password) throws NotVerifiedUserException, NotFoundException, BadRequestException {
        User user = userRepository.getUserByEmailAndPassword(email, password);
        if (user == null)
            throw new NotFoundException("wrong email or password!");
        else if (user.getStatus() == Status.UNVERIFIED)
            throw new NotVerifiedUserException("Unverified user. Please pass the verification!");
        return user;
    }


    @Transactional
    @Override
    public void verify(String email) {
        userRepository.GetByEmail(email).setStatus(Status.VERIFIED);
    }

    @Override
    public void sendEmail(String email) {
        mailSender.sendSimpleMessage(email, "Please pass the verification", "vonces?");
    }


    @Override
    public void removeById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) throws NotFoundException {
        User updatedUser = getById(user.getId());
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setGender(user.getGender());
        updatedUser.setStatus(user.getStatus());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setAuthorities(user.getAuthorities());
        updatedUser.setResetPasswordToken(user.getResetPasswordToken());
        save(updatedUser);
        return updatedUser;
    }

    @Transactional
    @Override
    public User upToResetPassword(String email) throws NotFoundException {
        User user = getByEmail(email);
        if (user != null && user.getStatus().equals(Status.VERIFIED)) {
            String message = "Your one-time verification key is  ";
            String token = RandomString.make(24);
            user.setResetPasswordToken(token);
            update(user);
            mailSender.sendSimpleMessage(user.getEmail(), message, token);
            return user;
        } else
            throw new NotFoundException("user not found by this email");
    }

    @Transactional
    @Override
    public void resetPassword(User user, String newPassword, String repeatedPassword) throws NotFoundException {
        if (!newPassword.equals(repeatedPassword))
            throw new InputMismatchException("Passwords doesn't match");
//        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(newPassword);
        update(user);
    }
}
