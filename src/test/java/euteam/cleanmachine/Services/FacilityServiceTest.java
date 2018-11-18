package euteam.cleanmachine.Services;

import euteam.cleanmachine.CleanmachineApplication;
import euteam.cleanmachine.dao.FacilityDao;
import euteam.cleanmachine.dto.FacilityDto;
import euteam.cleanmachine.dto.MachineDto;
import euteam.cleanmachine.dto.NewFacilityDto;
import euteam.cleanmachine.dto.NewMachineDto;
import euteam.cleanmachine.model.enums.MachineType;
import euteam.cleanmachine.model.facility.Facility;
import euteam.cleanmachine.service.FacilityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CleanmachineApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FacilityServiceTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private FacilityDao facilityDao;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void facilityAdded() {
        NewFacilityDto newFacilityDto = new NewFacilityDto();
        newFacilityDto.setName("new");
        newFacilityDto.setAddress("address");

        FacilityDto facilityDto = facilityService.addFacility(newFacilityDto);

        // One facility is added with the import.sql
        assertEquals(facilityDao.count(), 2);

        assertEquals("address", facilityDto.getAddress());
        assertEquals("new", facilityDto.getName());
        assertEquals(null, facilityDto.getMachines());
        assertEquals(null, facilityDto.getSubscriptionPlans());
    }

    @Test
    public void addMachineToFacility() {
        NewMachineDto newMachineDto = new NewMachineDto();
        newMachineDto.setMachineType(MachineType.DRYER);

        MachineDto machineDto = facilityService.addNewMachineToFacility(100L, newMachineDto);

        assertEquals("Idle", machineDto.getState());
        assertEquals(MachineType.DRYER, machineDto.getMachineType());
        assertFalse(facilityDao.findById(100L).get().getMachines().isEmpty());
    }
}
