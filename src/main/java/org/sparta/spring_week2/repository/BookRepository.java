package org.sparta.spring_week2.repository;

import org.sparta.spring_week2.entity.Book;
import org.sparta.spring_week2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByCreatedAtAsc();


}
