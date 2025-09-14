package com.lld.BookMyShow.services;

import com.lld.BookMyShow.models.Show;
import com.lld.BookMyShow.models.ShowSeat;
import com.lld.BookMyShow.models.ShowSeatType;
import com.lld.BookMyShow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {
    private ShowSeatTypeRepository showSeatTypeRepository;
    public PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public double calculatePrice(Show show, List<ShowSeat> showSeats) {
        double amount = 0.0;
        List<ShowSeatType> showSeatTypeList = this.showSeatTypeRepository.findAllByShow(show.getId());
        // Don't worry about time-complexity of the below loop
        for (ShowSeat showSeat: showSeats) { // we can 10 seats at max.
            for (ShowSeatType showSeatType: showSeatTypeList) { // 3-4 seat types at max
                if (showSeatType.getSeatType().equals(showSeat.getSeat().getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
