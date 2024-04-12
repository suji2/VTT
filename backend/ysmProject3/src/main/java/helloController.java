
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @GetMapping("/hellomvc")
    @ResponseBody
    public String hellomvc(){
        return "testRFFDF";
    }
}
