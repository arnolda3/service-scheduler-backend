package com.mailchimp.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceSchedulerTest {
    private static ServiceScheduler serviceScheduler;

    @BeforeAll
    static void setup() {
        serviceScheduler = new ServiceScheduler();
    }

    @Test
    void testNullCustomerCheckInThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> serviceScheduler.checkIn(null));
    }

    @Test
    void testCheckInAndGetNextRegularCustomer() {
        Customer regularCustomer1 = new Customer("Olivia", "Miller", "669-720-8489", Tier.REGULAR);
        Customer regularCustomer2 = new Customer("David", "Hernandez", "208-391-1072", Tier.REGULAR);

        serviceScheduler.checkIn(regularCustomer1);
        serviceScheduler.checkIn(regularCustomer2);

        assertEquals(regularCustomer1, serviceScheduler.getNextCustomer());
        assertEquals(regularCustomer2, serviceScheduler.getNextCustomer());
        assertNull(serviceScheduler.getNextCustomer());
    }

    @Test
    void testCheckInAndGetNextCustomer() {
        Customer vipCustomer1 = new Customer("William", "Brown", "677-914-0506", Tier.VIP);
        Customer vipCustomer2 = new Customer("Sophia", "Garcia", "395-433-5788", Tier.VIP);
        Customer vipCustomer3 = new Customer("Michael", "Williams", "344-431-6485", Tier.VIP);
        Customer regularCustomer1 = new Customer("David", "Hernandez", "208-391-1072", Tier.REGULAR);

        serviceScheduler.checkIn(regularCustomer1);
        serviceScheduler.checkIn(vipCustomer1);
        serviceScheduler.checkIn(vipCustomer2);
        serviceScheduler.checkIn(vipCustomer3);

        assertEquals(vipCustomer1, serviceScheduler.getNextCustomer());
        assertEquals(vipCustomer2, serviceScheduler.getNextCustomer());
        assertEquals(vipCustomer3, serviceScheduler.getNextCustomer());
        assertEquals(regularCustomer1, serviceScheduler.getNextCustomer());
        assertNull(serviceScheduler.getNextCustomer());
    }

    @Test
    void testCheckInAndGetNextCustomer2VIPTo1Regular() {
        Customer vipCustomer1 = new Customer("William", "Brown", "677-914-0506", Tier.VIP);
        Customer vipCustomer2 = new Customer("Sophia", "Garcia", "395-433-5788", Tier.VIP);
        Customer vipCustomer3 = new Customer("Michael", "Williams", "344-431-6485", Tier.VIP);
        Customer regularCustomer1 = new Customer("David", "Hernandez", "208-391-1072", Tier.REGULAR);

        serviceScheduler.checkIn(regularCustomer1);
        serviceScheduler.checkIn(vipCustomer1);
        serviceScheduler.checkIn(vipCustomer2);
        serviceScheduler.checkIn(vipCustomer3);

        assertEquals(vipCustomer1, serviceScheduler.getNextCustomer2VipTo1Regular());
        assertEquals(vipCustomer2, serviceScheduler.getNextCustomer2VipTo1Regular());
        assertEquals(regularCustomer1, serviceScheduler.getNextCustomer2VipTo1Regular());
        assertEquals(vipCustomer3, serviceScheduler.getNextCustomer2VipTo1Regular());
        assertNull(serviceScheduler.getNextCustomer());
    }
}