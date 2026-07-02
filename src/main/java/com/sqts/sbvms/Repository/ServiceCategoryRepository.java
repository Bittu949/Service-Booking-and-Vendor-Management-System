package com.sqts.sbvms.Repository;

import com.sqts.sbvms.Entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    Optional<ServiceCategory> findByServiceNameIgnoreCase(String serviceName);
    boolean existsByServiceNameIgnoreCase(String serviceName);
}
