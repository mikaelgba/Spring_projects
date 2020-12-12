package test_Spring.packages.project_two.util;
import test_Spring.packages.project_two.resquest.HQPutRequestBody;

public class HQPutRequestBodyCreator {
    public static HQPutRequestBody creatorHQPutRequestBody(){
        return HQPutRequestBody.builder()
                .id(HQCreator.CreateHQ_Valid_Update().getId())
                .name(HQCreator.CreateHQ_Valid_Update().getName())
                .build();
    }
}