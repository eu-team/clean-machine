package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.FacilityDao;
import euteam.cleanmachine.dto.FacilityDto;
import euteam.cleanmachine.dto.NewFacilityDto;
import euteam.cleanmachine.model.facility.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {
    @Autowired
    private FacilityDao facilityDao;

    public FacilityDto addFacility(NewFacilityDto newFacilityDto) {
        Facility facility = new Facility();
        facility.setName(newFacilityDto.getName());
        facility.setAddress(newFacilityDto.getAddress());

        return new FacilityDto(facilityDao.save(facility));
    }
}
