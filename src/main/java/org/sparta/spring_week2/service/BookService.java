package org.sparta.spring_week2.service;

import lombok.RequiredArgsConstructor;
import org.sparta.spring_week2.dto.*;
import org.sparta.spring_week2.entity.Book;
import org.sparta.spring_week2.entity.Member;
import org.sparta.spring_week2.entity.Rental;
import org.sparta.spring_week2.repository.BookRepository;
import org.sparta.spring_week2.repository.MemberRepository;
import org.sparta.spring_week2.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final RentalRepository rentalRepository;

    // 도서 등록 기능
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        // RequestDto -> Entity
        Book book = new Book(bookRequestDto);

        // DB에 저장
        Book saveBook = bookRepository.save(book);

        // Entity -> ResponseDto
        BookResponseDto bookResponseDto = new BookResponseDto(book);

        return bookResponseDto;
    }

    @Transactional
    // 선택한 도서 정보 조회 기능
    public BookResponseDto updateBook(Long bookId) {

        // 해당 도서가 book DB에 존재하는지 확인
        Book book = findBook(bookId);

        return new BookResponseDto(book);
    }

    // 도서 목록 조회 기능
    public List<BookResponseDto> getBook() {
        // DB 조회
        return bookRepository.findAllByOrderByCreatedAtAsc()
                .stream()
                .map(BookResponseDto::new).toList();
    }


    // 도서관 회원 등록 기능
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {

        // RequestDto → Entity
        Member member = new Member(memberRequestDto); // Entity에 Member 생성자 만듦

        // DB에 저장
        Member saveMember = memberRepository.save(member);

        // Entity → ResponseDto
        MemberResponseDto memberResponseDto = new MemberResponseDto(member); // ResponseDto에 Member 생성자 만듦

        return memberResponseDto;
    }


    @Transactional // 영속성 트랜젝션 사용 가능 / 변경 감지 가능
    // 선택한 도서 대출 기능
    public String getLoanBook(Long bookId, Long memberId) {

//       Member member = new Member(memberRequestDto);


        // 회원 여부 확인
        boolean isMember = isMember(memberId);
        if (!isMember) {
            return "회원이 아닙니다.";
        }

        // 해당 회원이 반납하지 않은 도서가 있는지 확인
        boolean hasBooks = hasBooks(bookId);
        if (hasBooks) {
            return "반납하지 않은 도서가 있어 대출이 불가능합니다.";
        }


        // 해당 도서가 이미 대출 중인지 확인
        boolean alreadyBorrowed = rentalRepository.existsByBookIdAndIsAvailableFalse(bookId);
        if (alreadyBorrowed) {
            return "해당 도서는 이미 대출 중입니다.";
        }

        // 대출 내역 기록
        Rental rental = new Rental();
        rental.setMemberId(memberId);
        rental.setBookId(bookId);
        rental.setReturnStatus(false); // 반납 상태를 false로 설정하여 대출 중임을 표시

        // 대출일 설정
        rental.setDueDate(LocalDate.now()); // 현재 시간을 대출일로 설정
        rental.setReturnedDate(LocalDateTime.now().plusDays(7)); // 반납일은 초기에 null로 설정되어야 함

        rentalRepository.save(rental); // 대출 내역 저장

        return "성공적으로 대출이 되었습니다.";
    }

// 보류한 코드
//    @Transactional
//    // 선택한 도서 반납 기능 : 도서 반납 → 기록 삭제
//    public Long getReturnBook(Long rentalId) {
//
//        // 해당 rentalId가 DB에 존재하는지 확인
//        Optional<Rental> returnBook = rentalRepository.findById(rentalId); // ? RentalRepository에 추가 시킨거 맞는건가?
//
//        Rental rental = new Rental();
//
//        // 빌린 기록 변경하기
//        if (returnBook.isPresent()) { // 확인한 rentalId 값이 있다면
//            rental.putIs_returned(true);
//            rental.setReturnedDate(LocalDateTime.now());
//
//            rentalRepository.save(rental);
//
//        } else { // 확인한 rentalId 값이 없다면
//            throw new IllegalArgumentException("해당 대출이 존재하지 않습니다");
//        }
//
//        return rentalId; // ? 이걸 리턴시키는게 맞나
//    }


    // DTO 새롭게 연결하려는 코드
//    @Transactional
    // 대출 내역 조회 기능
//    public List<RentalResponseDto> getReturnList(Long rentalId) {
//
//
//
//
//
// // 기존 코드 전체
//        해당 rentalId를 가지는 대출 내역이 있는지 확인
//        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
//
//        if (optionalRental.isEmpty()) {  // isPresent : 값이 존재하는지! ! 대출 내역이 존재하면
//            return Collections.emptyList(); // 빈 리스트 반환
//        }
//
//        Rental rental = optionalRental.get();   // rentalRepository 에서 가져온 rental 을 반환
//        Rental rental = optionalRental.get();   // rentalRepository 에서 가져온 rental 을 반환
//        return rentalRepository.findAllByOrderByDueDateAsc()
//                .stream()
//                .map(rentalEntity -> new RentalResponseDto(rentalEntity))
//                .collect(Collectors.toList());
//    }
// DTO 추가한거 연결하는 코드
//        List<RentalSearchResponseDto> rentalSearchResponseDtos = new ArrayList<>();
//        return RentalSearchResponseDtos;
// 기존 코드
//        return rentalRepository.findAllByOrderByDueDateAsc()
//                .stream()
//                .map(rentalEntity -> new RentalResponseDto(rentalEntity))
//                .toList();
//}



//-----------------------------------메 서 드-----------------------------------------

// 도서 조회 findBook 메서드
private Book findBook(Long bookId) {
    return bookRepository.findById(bookId).orElseThrow(() ->
            new IllegalArgumentException("선택한 책은 존재하지 않습니다.")
    );
}

// 선택한 도서 대출 기능 - 회원 여부 확인 메서드
private boolean isMember(Long memberId) {
    if (memberId == null) {
        throw new IllegalArgumentException("해당 아이디가 존재하지 않습니다");
        // ID가 null이 아닌 경우에 수행할 로직
    }
    return memberRepository.existsById(memberId);
}

// 선택한 도서 대출 기능 - 반납하지 않은 책 확인 유무 메서드
public boolean hasBooks(Long memberId) {
    // 회원이 대출한 도서 목록을 조회합니다.
    List<Rental> rentalsForMember = rentalRepository.findByMemberId(memberId);

    // 대출된 도서 중 반납되지 않은 도서가 있는지 확인합니다.
    for (Rental rental : rentalsForMember) {
        if (!rental.isAvailable()) {
            // 반납되지 않은 도서가 있으면 true를 반환합니다.
            return true;
        }
    }
    // 반납되지 않은 도서가 없으면 false를 반환합니다.
    return false;
}

}
