package com.mailchimp.domain.model;

/**
 * Represents a customer. Contains customer information and their service tier.
 *
 * @author Anastasia Arnold
 * @version 1.0
 * @since 1.0
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Tier tier;
    private int serviceNumber;

    /**
     * Creates a customer with specified first name, last name, phone number, and service tier.
     *
     * @param firstName   The customer’s first name.
     * @param lastName    The customer’s last name.
     * @param phoneNumber The customer’s phone number.
     * @param tier        The customer’s service tier (VIP or Regular).
     * @throws IllegalArgumentException if any parameter is null or empty, or if the tier is null.
     */
    public Customer(String firstName, String lastName, String phoneNumber, Tier tier) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        if (tier == null) {
            throw new IllegalArgumentException("Customer tier cannot be null.");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.phoneNumber = phoneNumber.trim();
        this.tier = tier;
    }

    // Getters and setters for customer attributes
    public int getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public Tier getTier() {
        return tier;
    }

    // toString method for printing customer object
    @Override
    public String toString() {
        return "com.mailchimp.domain.model.Customer{" +
                "serviceNumber=" + serviceNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", customerType=" + tier +
                '}';
    }
}
