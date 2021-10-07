package pl.wisniewski.jan.ExcelReader.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.wisniewski.jan.ExcelReader.model.Policy;
import pl.wisniewski.jan.ExcelReader.model.PolicyDTO;

@Mapper(componentModel = "spring")
public interface PolicyMapper {

    PolicyMapper INSTANCE = Mappers.getMapper(PolicyMapper.class);

    @Mapping(target = "")
    PolicyDTO policyToPolicyDTO(Policy policy);

    @Mapping(target = "")
    Policy policyDTOToPolicy(PolicyDTO policyDTO);

}
