package test_Spring.packages.project_two.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test_Spring.packages.project_two.entites.HQ;
import test_Spring.packages.project_two.service.HQService;
import test_Spring.packages.project_two.util.DateUtil;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("hqs")
@Log4j2
@RequiredArgsConstructor
public class HQController {

    private final DateUtil dateUtil;
    private final HQService hqService;

    @GetMapping
    public ResponseEntity<List<HQ>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(hqService.allHqs());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<HQ> findById(@PathVariable long id){
        return ResponseEntity.ok(hqService.findById(id));
    }
    @PostMapping
    public ResponseEntity<HQ> save(@RequestBody HQ hq){
        return new ResponseEntity<>(hqService.save(hq), HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HQ> delete(@PathVariable long id){
        hqService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<HQ> update(@RequestBody HQ hq){
        hqService.update(hq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}