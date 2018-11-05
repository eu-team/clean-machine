package euteam.cleanmachine.views;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class WelcomeController {
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mav) {

        mav.setViewName("welcome");
        mav.addObject("message", "welcome!");

        return mav;
    }

}
