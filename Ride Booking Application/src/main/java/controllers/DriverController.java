package controllers;

import dtos.ApiResponse;
import dtos.DriverAcceptRideRequestDto;
import exceptions.BookingNotFoundException;
import exceptions.DriverNotFoundException;
import services.DriverService;

public class DriverController {
    private DriverService driverService;
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }
    public ApiResponse<String> acceptRide(DriverAcceptRideRequestDto requestDto) {
        try {
            boolean success = this.driverService.acceptRide(requestDto.getDriverId(), requestDto.getBookingId());
            if (success) {
                return new ApiResponse<>(200, "Ride accepted", "SUCCESS");
            } else {
                return new ApiResponse<>(409, "Ride already taken", null);
            }
        } catch (DriverNotFoundException | BookingNotFoundException exception) {
            return new ApiResponse<>(404, exception.getMessage(), "null");
        } catch (Exception exception) {
            return new ApiResponse<>(500, exception.getMessage(), null);
        }
    }
}
