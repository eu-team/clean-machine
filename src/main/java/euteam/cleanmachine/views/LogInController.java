package euteam.cleanmachine.views;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class LogInController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }

}