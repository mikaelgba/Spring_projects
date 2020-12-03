package test_Spring.packages.controller;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test_Spring.packages.spring_boot.HQ;
import test_Spring.packages.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("hq")
@Log4j2
public class HQController {

    private DateUtil dateUtil;
    public HQController(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @GetMapping(path="list")
    public List<HQ> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new HQ("Superman"), new HQ("X-men"));
    }

    @GetMapping(path="list2")
    public List<HQ> list2(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new HQ("Batman"), new HQ("Blade"));
    }
}
