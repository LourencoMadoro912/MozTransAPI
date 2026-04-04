package mz.com.MozTransAPI.MozTransAPI.repository;

import mz.com.MozTransAPI.MozTransAPI.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long> {
}
