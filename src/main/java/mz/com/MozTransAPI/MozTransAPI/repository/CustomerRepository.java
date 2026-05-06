package mz.com.MozTransAPI.MozTransAPI.repository;

import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Custumer,Long> {

    Optional<Custumer> findByEmail(String email);
}
