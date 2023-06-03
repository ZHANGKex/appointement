package com.isep.appointement.Repository;

import com.isep.appointement.model.Reservation;
import com.isep.appointement.model.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {
}
