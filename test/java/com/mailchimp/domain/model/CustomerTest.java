package com.mailchimp.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testEmptyCustomerThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(null, null, null, null));
    }
}