package com.sqts.sbvms.Service;

import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Exception.NoVendorFoundException;
import com.sqts.sbvms.Repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    VendorRepository vendorRepository;
    public VendorService(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }
    public Vendor createVendor(Vendor vendor){
        return vendorRepository.save(vendor);
    }
    public List<Vendor> displayVendors(){
        List<Vendor> vendors = vendorRepository.findAll();
        if(vendors.isEmpty())
            throw new NoVendorFoundException("No vendor found");
        return vendors;
    }
}
