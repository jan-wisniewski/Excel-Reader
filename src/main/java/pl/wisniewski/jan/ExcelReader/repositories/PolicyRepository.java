package pl.wisniewski.jan.ExcelReader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wisniewski.jan.ExcelReader.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
