package com.sqts.sbvms.Service;

import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Exception.NoVendorFoundException;
import com.sqts.sbvms.Repository.VendorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public Vendor displayVendor(Long id){
        Optional<Vendor> vendorOpt = vendorRepository.findById(id);
        if(vendorOpt.isEmpty())
            throw new NoVendorFoundException("Vendor not found.");
        return vendorOpt.get();
    }
    public List<Vendor> filterVendor(String name, String skills){
        List<Vendor> allVendors = vendorRepository.findAll();
        if(name!=null && !name.trim().isBlank()) {
            allVendors = allVendors.stream()
                    .filter(v -> v != null &&
                                 v.getName() != null &&
                                 v.getName().trim().toLowerCase().contains(name.trim().toLowerCase()))
                    .toList();
        }
        if(skills!=null && !skills.trim().isBlank()){
            allVendors = allVendors.stream()
                    .filter(v -> v != null &&
                                 v.getSkills()!=null &&
                                 v.getSkills().trim().toLowerCase().contains(skills.trim().toLowerCase()))
                    .toList();
        }
        return allVendors;
    }
}
