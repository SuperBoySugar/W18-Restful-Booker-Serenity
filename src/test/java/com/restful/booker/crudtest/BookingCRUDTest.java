package com.restful.booker.crudtest;

import com.restful.booker.bookinginfo.BookingSteps;
import com.restful.booker.model.BookingDates;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class BookingCRUDTest extends TestBase {

    static String firstname = "Harry" + TestUtils.getRandomValue();
    static String lastname = "potter" + TestUtils.getRandomValue();
    static int totalprice = 200;
    static Boolean depositpaid = true;
    static Date checkin = new Date(2022,01,23);
    static Date checkout = new Date(2022,01,27);
    static String additionalneeds = "Breakfast";
    static int bookingId;

    @Steps
    BookingSteps bookingSteps;

    @Title("This will create new booking")
    @Test
    public void test001(){
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        ValidatableResponse response = bookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, bookingDates, additionalneeds);
        response.log().all().statusCode(500);
    }
    @Title("Verify if the Booking was done correctly")
    @Test
    public void test002(){
        ArrayList<HashMap<String,Object>> value = bookingSteps.getBookingInfoFirstname(firstname);
        Assert.assertThat(value.get(0), hasValue(firstname));
        bookingId = (Integer) value.get(0).get("bookingid");
        System.out.println(bookingId);
    }
    @Title("Update the Booking and verify the updated information")
    @Test
    public void test003(){
        firstname = firstname + " (Updated)";
        lastname = lastname + " (Updated";
        additionalneeds = "Vegetarian Meal";

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        bookingSteps.updateBooking(bookingId, firstname, lastname, totalprice, depositpaid, bookingDates, additionalneeds).log().all().statusCode(200);
        ArrayList<HashMap<String, Object>> value = bookingSteps.getBookingInfoFirstname(firstname);
        Assert.assertThat(value.get(0), hasValue(firstname));

    }
    @Title("Delete the Booking and verify if the Booking is deleted")
    @Test
    public void test004(){
        bookingSteps.deleteBooking(bookingId).statusCode(201); //it should be 204
        bookingSteps.getBookingId(bookingId).statusCode(404);

    }
}
