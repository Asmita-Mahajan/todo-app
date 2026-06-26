package com.todoapp.backend.service;

import com.todoapp.backend.dto.UserDto;
import com.todoapp.backend.mapper.UserMapper;
import com.todoapp.backend.model.User;
import com.todoapp.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto saveUser(UserDto userDto){
        User entity= userMapper.toEntity(userDto);
        System.out.println("asmita11"+userDto+entity);
         User savedUser= userRepository.save(entity);
        return userMapper.toDto(savedUser);
    }

    public List<UserDto> getAllUsers(){
        List<User> users=userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDto getUsersById(long id){
        User user= userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return userMapper.toDto(user);

    }
    public UserDto updateUserById( long id, UserDto userDto){
        User entity= userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        userMapper.updateEntityFromDto(userDto,entity);
        userRepository.save(entity);
        return userMapper.toDto(entity);
    }

    public Void deleteUserById(long id){
//        User entity= userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
//        userRepository.delete(entity);

        userRepository.deleteById(id);
        return null;
    }
}
