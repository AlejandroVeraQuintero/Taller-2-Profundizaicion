package co.com.poli.showtimeservice.persistence.repository;

import co.com.poli.showtimeservice.persistence.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime,Long> {
}
