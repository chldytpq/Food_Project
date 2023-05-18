package kopo.poly.repository;

import kopo.poly.entity.Border;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorderRepository extends JpaRepository<Border, Long> {

}
