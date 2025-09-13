package controllers;

import enums.ResponseStatus;
import models.dtos.HallButtonPressedRequestDto;
import models.dtos.HallButtonPressedResponseDto;
import services.HallButtonService;

public class HallButtonController {
    private HallButtonService hallButtonService;

    public HallButtonController(HallButtonService hallButtonService) {
        this.hallButtonService = hallButtonService;
    }
    public HallButtonPressedResponseDto pressHallButton(HallButtonPressedRequestDto requestDto) {
        HallButtonPressedResponseDto responseDto = new HallButtonPressedResponseDto();
        try {
            this.hallButtonService.handleHallCall(requestDto.getBuilding(), requestDto.getFloor(), requestDto.getDirection());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception exception) {
            responseDto.setResponseStatus(ResponseStatus.ERROR);
        }
        return responseDto;
    }
}
