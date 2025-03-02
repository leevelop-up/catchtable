package com.example.crud.domain;

import com.example.crud.dto.member.MemberUpdateParam;
import com.example.crud.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birth;
    private String email;
    private String password;
    private String role;



    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public void update(MemberUpdateParam param) {
        this.name = param.getName();
        this.phoneNumber = param.getPhoneNumber();
        this.gender = param.getGender();
        this.email = param.getEmail();
        this.birth = param.getBirth();
    }
}
