package mz.com.MozTransAPI.MozTransAPI.repository;

import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Custumer,Long> {
}
