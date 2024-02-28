package org.sparta.spring_week2.dto;

import lombok.Getter;
import lombok.Setter;
import org.sparta.spring_week2.entity.Book;
import org.sparta.spring_week2.entity.Member;
import org.sparta.spring_week2.entity.Rental;


@Getter
@Setter
public class RentalSearchResponseDto {
    private String name;
    private String phoneNumber;

    private String title;
    private String writer;


//    public RentalSearchResponseDto(Member member) {
//        this.name = getName();
//        this.phoneNumber = getPhoneNumber();
//    }
//
//    public RentalSearchResponseDto(Book book) {
//        this.title = getTitle();
//        this.writer = getWriter();
//    }

    public RentalSearchResponseDto(Member member, Book book) {
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
        this.title = book.getTitle();
        this.writer = book.getWriter();
    }
}
