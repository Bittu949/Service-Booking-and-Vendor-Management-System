package com.sqts.sbvms.Service;

import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Repository.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    VendorRepository vendorRepository;
    public VendorService(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }
    public Vendor createVendor(Vendor vendor){
        return vendorRepository.save(vendor);
    }
}
