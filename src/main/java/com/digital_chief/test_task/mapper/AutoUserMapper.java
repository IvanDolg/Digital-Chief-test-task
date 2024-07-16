package com.digital_chief.test_task.mapper;

import com.digital_chief.test_task.dto.UserDto;
import com.digital_chief.test_task.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}