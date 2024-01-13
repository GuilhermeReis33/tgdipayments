package com.tgdipayments.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId, BigDecimal taxa) {
}
