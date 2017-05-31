package romaniancoder.booking;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by adi_c on 31/05/2017.
 */
@RestController
public class DemoController {

    @RequestMapping("/hello")
    public String test() {
        return "Bossi";
    }

}
