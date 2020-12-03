package test_Spring.packages.project_two.service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import test_Spring.packages.project_two.entites.HQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class HQService {

    private static List<HQ> Hqs;
    static {
       Hqs = new ArrayList<>(List.of(new HQ(1L,"Superman"), new HQ(2L,"X-men")));
    }

    public List<HQ> allHqs(){
        return Hqs;
    }
    public HQ findById(long id){
        return Hqs.stream()
                .filter(hq -> Objects.equals(hq.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Hq id não encontrado!"));
    }
    public HQ save(HQ hq) {
        hq.setId(ThreadLocalRandom.current().nextLong(3,1000));
        Hqs.add(hq);
        return hq;
    }
    public void delete(long id) {
        Hqs.remove(findById(id));
    }
    public void update(HQ hq) {
        delete(hq.getId());
        Hqs.add(hq);
    }
}