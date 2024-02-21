package com.mailchimp.domain.model;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Manages scheduling and prioritization of customers for an in-person customer service center.
 *
 * @author Anastasia Arnold
 * @version 1.0
 * @since 1.0
 */
public class ServiceScheduler {
    private Queue<Customer> vipQueue = new ConcurrentLinkedQueue<>();
    private Queue<Customer> regularQueue = new ConcurrentLinkedQueue<>();
    private int currentServiceNumber = 0;
    private int vipCounter = 0;

    /**
     * Checks in a customer for service by assigning a service number and adding customer to
     * their respective customer queue based upon service tier.
     *
     * @param customer The customer to be checked in.
     * @throws IllegalArgumentException if customer is null.
     */
    public void checkIn(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        customer.setServiceNumber(++currentServiceNumber);

        if (customer.getTier() == Tier.VIP) {
            vipQueue.add(customer);
        } else {
            regularQueue.add(customer);
        }
    }

    /**
     * Retrieves next customer to be served. Prioritizes all VIP customers to be served first.
     * If there are no VIP customers, regular customers will be retrieved for service.
     *
     * @return The next customer to be served, or null if no customers are in the queue.
     */
    public Customer getNextCustomer() {
        if (!vipQueue.isEmpty()) {
            return vipQueue.poll();
        } else if (!regularQueue.isEmpty()) {
            return regularQueue.poll();
        } else {
            return null;
        }
    }

    /**
     * Retrieves next customer to be served. Prioritizes customers at a 2:1 rate, serving two VIP customers
     * before serving one regular customer.
     * If there are no VIP customers, regular customers will be retrieved for service.
     *
     * @return The next customer to be served at 2:1 VIP to regular rate, or null if no customers are in the queue.
     */
    public Customer getNextCustomer2VipTo1Regular() {
        if (vipQueue.isEmpty() && regularQueue.isEmpty()) {
            return null;
        }
        if (!vipQueue.isEmpty() && vipCounter < 2) {
            vipCounter++;
            return vipQueue.poll();
        } else if (!regularQueue.isEmpty() && (vipCounter >= 2 || vipQueue.isEmpty())) {
            vipCounter = 0;
            return regularQueue.poll();
        }

        return null;
    }

    // Getters for VIP and regular customer queues in the service scheduler
    public Queue<Customer> getVipQueue() {
        return vipQueue;
    }

    public Queue<Customer> getRegularQueue() {
        return regularQueue;
    }
}
