package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE) // Xác định các fiel trong class này đều là private
public class UserUpdateRequest {
     String password;
     String firstName;
     String lastName;
     LocalDate dob;

}
