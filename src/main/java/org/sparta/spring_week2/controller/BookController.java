package org.sparta.spring_week2.controller;

import lombok.RequiredArgsConstructor;
import org.sparta.spring_week2.dto.*;
import org.sparta.spring_week2.entity.Book;
import org.sparta.spring_week2.entity.Member;
import org.sparta.spring_week2.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;


    // 도서 등록 기능
    @PostMapping("/books")
    public BookResponseDto createBook(@RequestBody BookRequestDto bookRequestDto) {
        return bookService.createBook(bookRequestDto);
    }

    // 선택한 도서 정보 조회 기능
    @GetMapping("/books/{bookId}")
    public BookResponseDto updateBook(@PathVariable Long bookId, @RequestBody BookRequestDto bookRequestDto) {
        return bookService.updateBook(bookId);
    }

    // 도서 목록 조회 기능
    @GetMapping("/books")
    public List<BookResponseDto> getBooks() {
        return bookService.getBook();
    }

    // 도서관 회원 등록 기능
    @PostMapping ("/members")
    public MemberResponseDto createMember (@RequestBody MemberRequestDto memberRequestDto) { // JSON으로 보내는 형식
        return bookService.createMember(memberRequestDto);
    }


    // 선택한 도서 대출 기능
    @PostMapping ("/rental/{bookId}")
    public String getLoanBook (@PathVariable Long bookId, Long memberId, @RequestBody MemberRequestDto memberRequestDto) {
        return bookService.getLoanBook(bookId, memberId, memberRequestDto);
    }

    // 선택한 도서 반납 기능
    @PutMapping ("/rental/{rentalId}/return")
    public Long getReturnBook (@PathVariable Long rentalId, @RequestBody RentalRequestDto rentalRequestDto) {
        return bookService.getReturnBook(rentalId, rentalRequestDto);
    }


    // 대출 내역 조회 기능
    @GetMapping ("/rental/{rentalId}")
    public List<RentalResponseDto> getReturnList (@PathVariable Long rentalId) {
        return bookService.getReturnList(rentalId);
    }

}
