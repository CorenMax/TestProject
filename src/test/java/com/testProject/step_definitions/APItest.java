package com.testProject.step_definitions;

import com.testProject.utilities.BookingUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class APItest {
    /**
     * Be careful, created APIs are automatically deleted by system in a while.
     * Periodically even ids are changed, so before using any http method please check the existence of id!
     */

    @DisplayName("Authentication")
    @Test
    public void test1() {
        System.out.println(BookingUtils.getToken());
    }

    @DisplayName("Get booking id's")
    @Test
    public void test2() {
        BookingUtils.getBookingIds();
    }

    @DisplayName("Get book by id")
    @Test
    public void test3() {

        System.out.println(BookingUtils.getBookingById(6));
    }

    @DisplayName("Create Booking")
    @Test
    public void test4() {

        System.out.println(BookingUtils.createBooking(BookingUtils.generateBooking()));
    }

    @DisplayName("Booking - UpdateBooking")
    @Test
    public void test5() {
        System.out.println(BookingUtils.updateBooking(314, BookingUtils.generateBooking()));
    }

    @DisplayName("Booking - PartialUpdateBooking")
    @Test
    public void test6() {
        System.out.println(BookingUtils.partialUpdate("John", "Black", 314));
    }

    @DisplayName("Booking - DeleteBooking")
    @Test
    public void test7() {
        BookingUtils.deleteBooking(1717);
    }

}
