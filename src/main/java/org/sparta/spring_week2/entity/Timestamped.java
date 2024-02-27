package org.sparta.spring_week2.entity;

// 은미님 이 부분 추가 했습니다.
// memo 프로젝트 만들 때 있었던 Timestamped 내용입니다. (내용은 코드스니펫 복붙입니다)
// 사용 방법 : 각 entity 에 extends Timestamped 하면 됩니다.

// 제가 생각하기에 공통된 부분들은 다른 클래스에 묶어서 보관하는 용도로 사용하려고 따로 만드는 것 같아요!
// 이 클래스만 만들어 놓고, 아직 extends Timestamped 는 하지 않은 상황입니다.

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  // 자동으로 시간을 넣어줌
public abstract class Timestamped {

    @CreatedDate  // entity 객체가 생성되어 저장이 될 때, 시간이 자동으로 저장됨. 최초 시간만 저장되고, 그 이후에는 수정이 되면 안되니깐
    @Column(updatable = false)  // 시간 업데이트가 되지 않게 막아줌.
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

//    @LastModifiedDate // 조회하는 entity 객체의 값을 변경할 때, 변경된 시간이 자동으로 저장됨.
//    @Column
//    @Temporal(TemporalType.TIMESTAMP)  // 자바의 date, calender 처럼 날짜 데이터를 매핑할 때 사용
//    private LocalDateTime modifiedAt;
}