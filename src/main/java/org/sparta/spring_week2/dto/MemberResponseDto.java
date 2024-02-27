package org.sparta.spring_week2.dto;

import lombok.Getter;
import lombok.Setter;
import org.sparta.spring_week2.entity.Member;

@Getter
@Setter
public class MemberResponseDto {
    // Member
    private Long memberId;
    private String name;
    private String gender;
//    private String residentRegistrationNumber;
    private String phoneNumber;
    private String address;

    public MemberResponseDto(Member member) { // Service : Entity → ResponseDto

        this.memberId = member.getMemberId(); // ID 넣기? 안넣어도 돼요! 자동생성됩니다.
        this.name = member.getName();
        this.gender = member.getGender();
//        this.residentRegistrationNumber = member.getResidentRegistrationNumber();
        this.phoneNumber = member.getPhoneNumber();
        this.address = member.getAddress();


    }
}
