package com.example.crud.dto.member;

import com.example.crud.dto.param.MemberJoinParam;
import com.example.crud.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MemberJoinRequest {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호는 '010-2222-1111' 형식이어야 합니다.")
    private String phoneNumber;

    private Gender gender;
    private LocalDate birth;
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
    @NotBlank
    private String password;

    public MemberJoinParam toParam() {
        return MemberJoinParam.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .birth(birth)
                .email(email)
                .password(password)
                .build();
    }

    public String toString() {
        return "Member{" +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                ", email='" + email + '\'' +
                '}';
    }
}
