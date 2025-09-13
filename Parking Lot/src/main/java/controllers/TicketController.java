package controllers;

import models.Ticket;
import models.dto.IssueTicketRequestDTO;
import models.dto.IssueTicketResponseDTO;
import models.dto.ResponseStatus;
import services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO) {
        IssueTicketResponseDTO responseDTO = new IssueTicketResponseDTO();
        try {
            Ticket ticket = ticketService.issueTicket(requestDTO.getVehicleNumber(), requestDTO.getOwnerName(),
                                                      requestDTO.getGateId());
            responseDTO.setTicket(ticket);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            responseDTO.setResponseStatus(ResponseStatus.FAILIURE);
        }
        return responseDTO;
    }
}
