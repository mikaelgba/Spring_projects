package test_Spring.packages.project_two.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import test_Spring.packages.project_two.entites.HQ;
import test_Spring.packages.project_two.resquest.HQPostRequestBody;
import test_Spring.packages.project_two.resquest.HQPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class HQMapper {
    public static final HQMapper INSTANCE = Mappers.getMapper(HQMapper.class);
    public abstract HQ toHQ(HQPostRequestBody hqPostRequestBody);
    public abstract HQ toHQ(HQPutRequestBody hqPutRequestBody);
}