package test_Spring.packages.project_two.util;
import test_Spring.packages.project_two.entites.HQ;

public class HQCreator {

    public static HQ CreateHQ_Saved(){
        return HQ.builder()
                .name("Shazam")
                .build();
    }
    public static HQ CreateHQ_Valid(){
        return HQ.builder()
                .name("Shazam")
                .id(1L)
                .build();
    }
    public static HQ CreateHQ_Valid_Update(){
        return HQ.builder()
                .name("Shazam!!!!")
                .id(1L)
                .build();
    }
}