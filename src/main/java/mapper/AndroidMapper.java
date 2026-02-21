package mapper;

import com.dataproject.yorha.DTO.Android.CreateAndroidDTO;
import com.dataproject.yorha.DTO.Android.GetAllAndroidDTO;
import com.dataproject.yorha.DTO.Android.GetOneAndroidDTO;
import com.dataproject.yorha.model.Android;
import com.dataproject.yorha.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AndroidMapper {
    AndroidMapper INSTANCE = Mappers.getMapper(AndroidMapper.class);

    @Mapping(target = "type", source = "type")
    Android toEntity(CreateAndroidDTO dto, Type type);

    @Mapping(target = "name", expression = "java(android.getName())")
    @Mapping(target = "status", expression = "java(android.getStatus().toString())")
    GetAllAndroidDTO toGetAllDto(Android android);

    @Mapping(target = "name", expression = "java(android.getName())")
    @Mapping(target = "status", expression = "java(android.getStatus().toString())")
    GetOneAndroidDTO toGetOneDto(Android android);

}
