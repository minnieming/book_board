package org.sparta.spring_week2.dto;

import lombok.Getter;
import lombok.Setter;
import org.sparta.spring_week2.entity.Member;
import org.sparta.spring_week2.entity.Rental;

import java.awt.print.Book;

@Getter
@Setter
public class RentalSearchResponseDto {
    private String name;
    private String phoneNumber;

    private String title;
    private String writer;

    public RentalSearchResponseDto(Member member) {
        this.name = getName();
        this.phoneNumber = getPhoneNumber();
    }

    public RentalSearchResponseDto(Book book) {
        this.title = getTitle();
        this.writer = getWriter();
    }
}
