package com.testProject.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bookingid {
    private Integer bookingid;
    private Booking booking;
}
