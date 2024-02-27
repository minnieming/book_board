package org.sparta.spring_week2.dto;

import lombok.Getter;
import org.sparta.spring_week2.entity.Rental;

import java.time.LocalDateTime;

@Getter
public class RentalRequestDto {

    // Rental
    private Long rentalId;
    private String is_return;
    private String returedDate;
    private LocalDateTime dueDate;
    private String bookId;
    private String memberId;
}