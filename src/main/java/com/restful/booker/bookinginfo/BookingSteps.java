package com.restful.booker.bookinginfo;

import com.restful.booker.costants.EndPoints;
import com.restful.booker.model.BookingDates;
import com.restful.booker.model.BookingPojo;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingSteps {

    @Step("Creating new booking with firstName: {0}, lastName: {1}, totalPrice: {2}, depositPaid: {3}, " + " bookingDate: {4}, additionalNeeds: {5}")
    public ValidatableResponse createBooking(String firstName, String lastName, int totalPrice, boolean depositPaid, BookingDates bookingDate, String additionalNeeds) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid, bookingDate, additionalNeeds);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the booking information with firstName: {0}")
    public ArrayList<HashMap<String, Object>> getBookingInfoFirstname(String firstName){
        String p1 = "findAll{it.firstName='";
    String p2 = "'}";
    return SerenityRest.given().log().all()
            .when()
            .get(EndPoints.GET_ALL_BOOKING_BY_NAME + firstName)
            .then()
            .statusCode(200)
            .extract()
            .path(p1 + firstName + p2);
    }

    @Step("Updating booking information with bookingID: {0}, firstname: {1}, lastname: {2}, totalprice: {3}, depositpaid: {4}, bookingdates: {5}, additionalneeds: {6}")
    public ValidatableResponse updateBooking(int bookingId, String firstname, String lastname, int totalprice, boolean despostipaid, BookingDates bookingdates, String additionalneeds) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, despostipaid, bookingdates, additionalneeds);

        //To get Token after Authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme(); //Create object of PreemptiveBasicAuthScheme class
        authScheme.setUserName("admin"); // assign the username to object
        authScheme.setPassword("password123");
        RestAssured.authentication = authScheme; // assign the object to RestAssured.authentication class

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("bookingID", "bookingId")
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step("Deleting booking information with bookingID {0}")
    public ValidatableResponse deleteBooking(int bookingID) {
        // To get Token after Authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme(); //Create object of PreemtiveBasicAuthScheme
        authScheme.setUserName("admin"); // assign the username to object
        authScheme.setPassword("password123"); // assign the password to object
        RestAssured.authentication = authScheme; // assign the object to RestAssured.authentication class

        return SerenityRest
                .given()
                .pathParam("bookingID", bookingID)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }

    @Step("Getting booking information with bookingID: {0}")
    public ValidatableResponse getBookingId(int bookingID) {
        return SerenityRest
                .given()
                .pathParam("bookingID", bookingID)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }

}

