package com.example.demo.UserService;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.reponsitory.UserReponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Tạo một constructor cho tất cả các biến mà khi define có từ khóa là final, nó tự động inject dependency vào
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // Bất kỳ field nào được khai báo đềi là private final
public class UserService {
    UserReponsitory userReponsitory;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if (userReponsitory.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXIST);

        //Mapper bằng mapstruct
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userReponsitory.save(user);

        // Cách mapper cũ
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());
//        return userReponsitory.save(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
       User user = userReponsitory.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
       //Mapper bằng mapstruct
        userMapper.updateUser(user, request);

       // Cách mapper thủ công
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());
//        return userReponsitory.save(user);

        return userMapper.toUserResponse(userReponsitory.save(user));
    }

    public void deleteUser(String userId) {
        userReponsitory.deleteById(userId);
    }

    public List<User> getUsers() {
        return userReponsitory.findAll();
    }

    public UserResponse getUserById(String userId) {
        return userMapper.toUserResponse(userReponsitory.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
    }
}
