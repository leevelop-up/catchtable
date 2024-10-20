package com.example.crud.dto.param;

import com.example.crud.domain.Member;
import com.example.crud.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateParam {
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private Gender gender;
    @NotNull
    private LocalDate birth;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public String toString() {
        return "Member{" +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public void updateFromDto(MemberJoinParam param) {
        this.name = param.getName();
        this.phoneNumber = param.getPhoneNumber();
        this.gender = param.getGender();
        this.email = param.getEmail();
        this.birth = param.getBirth();
    }
}
