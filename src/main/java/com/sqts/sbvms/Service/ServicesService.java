package com.sqts.sbvms.Service;

import com.sqts.sbvms.Exception.NoServiceFoundException;
import com.sqts.sbvms.Repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {
    ServiceRepository serviceRepository;
    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    public com.sqts.sbvms.Entity.Service addService(com.sqts.sbvms.Entity.Service service){
        serviceRepository.save(service);
        return service;
    }
    public List<com.sqts.sbvms.Entity.Service> displayServices(){
        List<com.sqts.sbvms.Entity.Service> services = serviceRepository.findAll();
        if(services.isEmpty())
            throw new NoServiceFoundException("No service found.");
        return services;
    }
    public com.sqts.sbvms.Entity.Service updateService(Long id, com.sqts.sbvms.Entity.Service updatedService){
        Optional<com.sqts.sbvms.Entity.Service> serviceOpt = serviceRepository.findById(id);
        if(serviceOpt.isEmpty())
            throw new NoServiceFoundException("No service found");
        com.sqts.sbvms.Entity.Service service = serviceOpt.get();
        if(updatedService.getName()!=null && !updatedService.getName().isEmpty())
            service.setName(updatedService.getName());
        if(updatedService.getDescription()!=null && !updatedService.getDescription().isEmpty())
            service.setDescription(updatedService.getDescription());
        if(updatedService.getPrice()!=null)
            service.setPrice(updatedService.getPrice());
        if(updatedService.getDuration()!=null)
            service.setDuration(updatedService.getDuration());
        serviceRepository.save(service);
        return service;
    }
}
