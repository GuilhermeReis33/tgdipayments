package com.tgdipayments.domain.user;

import com.tgdipayments.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public  User(UserDTO cache){
        this.name = cache.name();
        this.saldo = cache.saldo();
        this.email = cache.email();
        this.document = cache.document();
        this.userType = cache.userType();

    }

}
