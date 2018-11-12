package euteam.cleanmachine.views;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class CardController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mav) {

        mav.setViewName("user");


        return mav;
    }
}