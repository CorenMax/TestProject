package com.testProject.utilities;

import com.github.javafaker.Faker;
import com.testProject.pojo.Booking;
import com.testProject.pojo.Bookingdates;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingUtils {

    public static Booking generateBooking() {
        Booking booking = new Booking();
        Faker faker = new Faker();
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin(faker.numerify("2023-12-12"));
        bookingdates.setCheckout(faker.numerify("2023-12-12"));
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.number().randomDigit());
        booking.setDepositpaid(faker.random().nextBoolean());
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds(faker.chuckNorris().fact());
        return booking;
    }

    public static String getToken() {
        String body = "{\n" +
                "    \"username\" : \"" + Environment.USER_NAME + "\",\n" +
                "    \"password\" : \"" + Environment.USER_PASSWORD + "\"\n" +
                "}";

        String token = given().contentType(ContentType.JSON)
                .body(body).when().post(Environment.BASE_URI + "/auth")
                .then().statusCode(200).extract().jsonPath().get("token");

        return "token=" + token;
    }

    public static List<String> getBookingIds() {
        JsonPath jsonPath = given().accept(ContentType.JSON).when().get(Environment.BASE_URI + "/booking")
                .then().statusCode(HttpStatus.SC_OK).extract().jsonPath();
        return jsonPath.getList("bookingid");
    }

    public static Booking getBookingById(int id) {
        Booking getBooking = given().pathParam("id", id)
                .when().get(Environment.BASE_URI + "/booking/{id}").then()
                .statusCode(HttpStatus.SC_OK).and().extract().as(Booking.class);
        return getBooking;
    }

    public static Integer createBooking(Booking body) {
        return given().contentType(ContentType.JSON).and().body(body)
                .post(Environment.BASE_URI + "/booking").then().statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getInt("bookingid");
    }

    public static Booking updateBooking(int id, Booking body) {
        return given().contentType(ContentType.JSON).and()
                .header("Cookie", BookingUtils.getToken())
                .and().pathParam("id", id).when()
                .body(body).and().put(Environment.BASE_URI + "/booking/{id}")
                .then().statusCode(HttpStatus.SC_OK).extract().as(Booking.class);
    }

    public static Booking partialUpdate(String name, String lastName, int id) {
        String body = "{\n" +
                "    \"firstname\" : \"" + name + "\",\n" +
                "    \"lastname\" : \"" + lastName + "\"\n" +
                "}";

        return given().contentType(ContentType.JSON).and()
                .header("Cookie", BookingUtils.getToken())
                .and().pathParam("id", id).when()
                .body(body).and().patch(Environment.BASE_URI + "/booking/{id}")
                .then().statusCode(HttpStatus.SC_OK).extract().as(Booking.class);
    }

    public static String deleteBooking(int id) {
        given().header("Cookie", BookingUtils.getToken()).and().pathParam("id", id)
                .when().delete(Environment.BASE_URI + "/booking/{id}").then().statusCode(HttpStatus.SC_CREATED);
        return "Your Booking was deleted";
    }

}
