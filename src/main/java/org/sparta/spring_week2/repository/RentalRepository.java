package org.sparta.spring_week2.repository;

import org.sparta.spring_week2.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    boolean existsByBookIdAndIsAvailableFalse(Long bookId);

    List<Rental> findByMemberId(Long memberId);

    Optional<Rental> findAllByOrderByDueDateAsc();

}
