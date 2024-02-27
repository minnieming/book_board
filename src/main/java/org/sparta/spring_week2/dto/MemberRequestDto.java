package org.sparta.spring_week2.dto;


import lombok.Getter;

@Getter
public class MemberRequestDto {
    // Member
    private Long memberId;
    private String name;
    private String gender;
    private String residentRegistrationNumber;
    private String phoneNumber;
    private String address;

}
