package com.tgdipayments.dtos;

import com.tgdipayments.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String name, String document, BigDecimal saldo, String email, UserType userType) {
}
