package com.kys.blog_app_bakend.services.impl;

import com.kys.blog_app_bakend.entities.User;
import com.kys.blog_app_bakend.exceptions.ResourceNotFoundException;
import com.kys.blog_app_bakend.payloads.UserDTO;
import com.kys.blog_app_bakend.repositories.UserRepository;
import com.kys.blog_app_bakend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = this.userDtoToUserEntity(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userEntityToUserDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepository.save(user);
        return this.userEntityToUserDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.userEntityToUserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();

        return users.stream().map(this::userEntityToUserDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(user);
    }

    public User userDtoToUserEntity(UserDTO userDto) {
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());

        return this.modelMapper.map(userDto, User.class);
    }

    public UserDTO userEntityToUserDto(User user) {
//        UserDTO userDto = new UserDTO();
//        userDto.setId(user.getId());
//        userDto.setEmail(user.getEmail());
//        userDto.setName(user.getName());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());

        return this.modelMapper.map(user, UserDTO.class);
    }

}
