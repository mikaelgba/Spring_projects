package test_Spring.packages.project_two.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import test_Spring.packages.project_two.entites.HQ;
import test_Spring.packages.project_two.mapper.HQMapper;
import test_Spring.packages.project_two.repository.HQRepository;
import test_Spring.packages.project_two.resquest.HQPostRequestBody;
import test_Spring.packages.project_two.resquest.HQPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HQService {

    private final HQRepository hqRepository;

    public List<HQ> allHQs(){
        return hqRepository.findAll();
    }
    public HQ findByIdHQOrException(long id) {
        return hqRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "HQ com esse ID não foi encontrado"));
    }
    public HQ save(HQPostRequestBody hqPostRequestBody) {
        //return hqRepository.save(HQ.builder().name(hqPostRequestBody.getName()).build());
        //usando o mapeamendo do MapStruct
        return hqRepository.save(HQMapper.INSTANCE.toHQ(hqPostRequestBody));
    }
    public void update(HQPutRequestBody hqPutRequestBody){
        HQ hqUpdate = findByIdHQOrException(hqPutRequestBody.getId());
        //usando o mapeamendo do MapStruct
        /*HQ hq = HQ.builder()
                .id(hqUpdate.getId())
                .name(hqUpdate.getName())
                .build();*/
        HQ hq = HQMapper.INSTANCE.toHQ(hqPutRequestBody);
        hq.setId(hqUpdate.getId());
        hqRepository.save(hq);
    }
    public void delete(long id) {
        hqRepository.delete(findByIdHQOrException(id));
    }
}