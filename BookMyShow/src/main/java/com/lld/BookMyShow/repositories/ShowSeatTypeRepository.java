package com.lld.BookMyShow.repositories;

import com.lld.BookMyShow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatTypeRepository, Long> {
    List<ShowSeatType> findAllByShow(Long showId);
}
