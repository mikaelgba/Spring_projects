package test_Spring.packages.project_two.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import test_Spring.packages.project_two.entites.HQ;

public interface HQRepository extends JpaRepository<HQ, Long> {
}
