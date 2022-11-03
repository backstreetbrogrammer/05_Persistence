package com.backstreetbrogrammer.chapter09_serializationwithenum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeStatusTest {
    @Test
    @DisplayName("Test java.lang.Enum name() and values() methods")
    void testEnumNameMethodAndValuesMethod() {
        for (final TradeStatus tradeStatus : TradeStatus.values()) {
            System.out.printf("name()~>%s%n", tradeStatus.name());
            System.out.printf("toString()~>%s%n", tradeStatus);
        }
    }

    @Test
    @DisplayName("Test java.lang.Enum valueOf() method")
    void testEnumValueOfMethod() {
        assertEquals(TradeStatus.LIVE, TradeStatus.valueOf("LIVE"));
        assertEquals(TradeStatus.CLOSED, TradeStatus.valueOf("closed".toUpperCase(Locale.ENGLISH)));
    }
}
