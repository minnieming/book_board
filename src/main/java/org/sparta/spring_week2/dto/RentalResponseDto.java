package org.sparta.spring_week2.dto;

import lombok.Getter;
import lombok.Setter;
import org.sparta.spring_week2.entity.Rental;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class RentalResponseDto {
    // Rental
    private Long rentalId;
    private String is_return;
    private LocalDateTime returnedDate;
    private LocalDate dueDate;
    private LocalDateTime createdAt;

    public RentalResponseDto(Rental rental) {
        this.is_return = getIs_return();
        this.returedDate = getReturedDate();
        this.dueDate = getDueDate();
        this.createdAt = getCreatedAt();
    }
}
