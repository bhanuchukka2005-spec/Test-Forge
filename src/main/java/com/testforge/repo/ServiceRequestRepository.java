package com.testforge.repo;
import com.testforge.domain.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByRollNumberOrderByCreatedAtDesc(String rollNumber);
}
