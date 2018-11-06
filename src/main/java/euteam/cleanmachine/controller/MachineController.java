package euteam.cleanmachine.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
public class MachineController {
    /*
    @Autowired
    MachineService machineService;
    @Autowired
    AuthItemService authItemService;
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;


    @RequestMapping(path="/machine/getPrograms", method = RequestMethod.GET)
    public Iterable<ProgramDto> getPrograms(@NotNull Long MachineId){
        return machineService.getProgramsFromMachine(MachineId);
    }
    @RequestMapping(path="/machine/getUserBalance", method = RequestMethod.GET)
    public void getUserBalance(@NotNull Long MachineId, @NotNull Long cardId){
        AuthItemDto authItemDto =authItemService.getAuthItemById(cardId);

    }
    */
}
