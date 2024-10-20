package com.example.crud.dto.member;

import com.example.crud.domain.Member;
import com.example.crud.enums.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberListResponse {
    private String returnStatus;
    private String returnMessage;
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDate birth;
    private Gender gender;
    private String role;

    public MemberListResponse(Member member) {
        this.returnMessage = member.getName();
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
        this.email = member.getEmail();
        this.birth = member.getBirth();
        this.gender = member.getGender();
        this.role = member.getRole();
    }

    @Builder
    public MemberListResponse(String returnStatus, String returnMessage) {
        this.returnStatus = returnStatus;
        this.returnMessage = returnMessage;
    }
}
