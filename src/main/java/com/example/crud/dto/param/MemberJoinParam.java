package com.example.crud.dto.param;

import com.example.crud.domain.Member;
import com.example.crud.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinParam {
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
    public Member toDomain() {
        return Member.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .birth(birth)
                .gender(gender)
                .role("USER")
                .build();
    }
}
