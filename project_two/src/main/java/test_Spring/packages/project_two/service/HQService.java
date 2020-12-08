package test_Spring.packages.project_two.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import test_Spring.packages.project_two.entites.HQ;

import test_Spring.packages.project_two.exception.BadRequestException;
import test_Spring.packages.project_two.mapper.HQMapper;
import test_Spring.packages.project_two.repository.HQRepository;
import test_Spring.packages.project_two.resquest.HQPostRequestBody;
import test_Spring.packages.project_two.resquest.HQPutRequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HQService {

    private final HQRepository hqRepository;

    public List<HQ> allHQs(){
        return hqRepository.findAll();
    }
    public List<HQ> findByName(String name){
        return hqRepository.findByName(name);
    }
    public HQ findByIdHQOrException(long id) {
        return hqRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(
                        "HQ com ID " + id + " não foi encontrado"));
    }
    @Transactional
    public HQ save(HQPostRequestBody hqPostRequestBody) {
        return hqRepository.save(HQ.builder()
                .name(hqPostRequestBody.getName())
                .build());
        //Usando mapeamendo do Mapstruct
        //return hqRepository.save(HQMapper.INSTANCE.toHQ(hqPostRequestBody));
    }
    public void update(HQPutRequestBody hqPutRequestBody){
        HQ ifExistHQDatabase = findByIdHQOrException(hqPutRequestBody.getId());
        HQ hq = HQ.builder()
                .id(ifExistHQDatabase.getId())
                .name(hqPutRequestBody.getName())
                .build();
        hqRepository.save(hq);
        //Usando mapeamendo do Mapstruct
        //HQ hq = HQMapper.INSTANCE.toHQ(hqPutRequestBody);
        //hq.setId(ifExistHQDatabase.getId());
        //hqRepository.save(hq);
        //Por algum motivo ainda não conseguir fazer o mapeamendo usando o MapStruct
        //Então por momento vai ser com o Lombok mesmo
    }
    public void delete(long id) {
        hqRepository.delete(findByIdHQOrException(id));
    }
}