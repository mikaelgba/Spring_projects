package test_Spring.packages.project_two.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test_Spring.packages.project_two.entites.HQ;
import test_Spring.packages.project_two.resquest.HQPostRequestBody;
import test_Spring.packages.project_two.resquest.HQPutRequestBody;
import test_Spring.packages.project_two.service.HQService;
import test_Spring.packages.project_two.util.DateUtil;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("hqs")
@Log4j2
@RequiredArgsConstructor
public class HQController {

    private final DateUtil dataUtil;
    private final HQService hqService;

    @GetMapping
    public ResponseEntity<List<HQ>> list(){
        log.info(dataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(hqService.allHQs(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<HQ> findById(@PathVariable long id){
        log.info(dataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(hqService.findByIdHQOrException(id), HttpStatus.OK);
    }
    @GetMapping(path = "/find")
    public ResponseEntity<List<HQ>> findByName(@RequestParam(required = false) String name){
        log.info(dataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(hqService.findByName(name), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<HQ> save(@RequestBody @Valid HQPostRequestBody hqPostRequestBody){
        return new ResponseEntity<>(hqService.save(hqPostRequestBody), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<HQ> update(@RequestBody HQPutRequestBody hqPutRequestBody){
        hqService.update(hqPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HQ> delete(@PathVariable long id){
        hqService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}