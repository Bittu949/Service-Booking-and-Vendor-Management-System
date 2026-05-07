package com.sqts.sbvms.Repository;

import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Entity.VendorService;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VendorServiceRepository extends JpaRepository<VendorService, Long> {
}
