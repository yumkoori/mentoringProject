package com.yumkoori.mentoring.user.adapter.out.persistence.entity;

import com.yumkoori.mentoring.common.enums.AuthProvider;
import com.yumkoori.mentoring.common.enums.UserStatus;
import com.yumkoori.mentoring.common.enums.UserRole;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nick;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Timestamp
    private ZonedDateTime createdAt;
    @Timestamp
    private ZonedDateTime updatedAt;
    @Timestamp
    private ZonedDateTime deletedAt;

}
