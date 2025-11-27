package com.example.devSns.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor; // 추가
import lombok.Builder;          // 추가
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor // Builder 패턴 사용 시 필요
@Builder            // ★ 클래스 레벨에 추가하여 builder() 메서드 자동 생성 ★
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // 로그인 ID

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Authority authority; // 권한

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default // Builder 사용 시 초기화 값을 유지하기 위해 필요
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<PostLike> likes = new ArrayList<>();

    // 특정 필드만 사용하는 생성자가 필요하다면 유지, @Builder가 클래스에 있으면 생략 가능하나
    // 명시적인 생성자가 필요할 경우 아래와 같이 별도 @Builder 적용 가능
    // 여기서는 클래스 레벨 @Builder로 통일하는 것을 권장합니다.

    public Member(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}