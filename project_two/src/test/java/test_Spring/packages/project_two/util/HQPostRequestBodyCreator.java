package test_Spring.packages.project_two.util;
import test_Spring.packages.project_two.resquest.HQPostRequestBody;

public class HQPostRequestBodyCreator {

    public static HQPostRequestBody creatorHQPostRequestBody(){
        return HQPostRequestBody.builder()
                .name(HQCreator.CreateHQ_Saved().getName())
                .build();
    }
}