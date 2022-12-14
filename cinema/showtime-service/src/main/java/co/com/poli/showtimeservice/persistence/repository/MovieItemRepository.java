package co.com.poli.showtimeservice.persistence.repository;

import co.com.poli.showtimeservice.persistence.entity.MovieItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieItemRepository  extends JpaRepository<MovieItem,Long> {
}
