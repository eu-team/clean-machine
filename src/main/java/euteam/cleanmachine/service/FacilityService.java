package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.FacilityDao;
import euteam.cleanmachine.dto.FacilityDto;
import euteam.cleanmachine.dto.MachineDto;
import euteam.cleanmachine.dto.NewFacilityDto;
import euteam.cleanmachine.dto.NewMachineDto;
import euteam.cleanmachine.model.facility.Facility;
import euteam.cleanmachine.model.facility.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import java.util.List;

@Service
public class FacilityService {
    @Autowired
    private FacilityDao facilityDao;

    @Autowired
    private MachineService machineService;

    public FacilityDto addFacility(NewFacilityDto newFacilityDto) {
        Facility facility = new Facility();
        facility.setName(newFacilityDto.getName());
        facility.setAddress(newFacilityDto.getAddress());

        return new FacilityDto(facilityDao.save(facility));
    }

    public MachineDto addNewMachineToFacility(Long facilityId, NewMachineDto newMachineDto) {
        Facility facility = facilityDao.findById(facilityId).orElse(null);

        if(facility != null) {
            Machine machine = machineService.createMachine(newMachineDto);
            facility.addMachine(machine);
            facilityDao.save(facility);

            return new MachineDto(machine);
        } else {
            return null;
        }
    }
}
