package com.maoxian.config;

import com.maoxian.dto.UserBaseInfoDTO;
import com.maoxian.dto.UserPasswordDTO;
import com.maoxian.pojo.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {
    // 模型映射器
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//
        // UserPasswordDTO-->User
//        modelMapper.typeMap(UserPasswordDTO.class, User.class)
//                .addMapping(UserPasswordDTO::getId, User::setId)
//                .addMapping(UserPasswordDTO::getNewPassword, User::setPassword);

//        modelMapper.addMappings(new PropertyMap<UserPasswordDTO, User>() {
//            @Override
//            protected void configure() {
//                using()
//            }
//        })

        return modelMapper;
    }
}
