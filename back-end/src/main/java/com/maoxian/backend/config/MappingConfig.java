package com.maoxian.backend.config;

import com.maoxian.backend.dto.UserPasswordDTO;
import com.maoxian.backend.pojo.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lin
 * @date 2023/11/5 9:14
 */
@Configuration
public class MappingConfig {
    /**
     * 模型映射器
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // 这会创建并返回一个TypeMap，但这样做会考虑自己写的PropertyMap
        modelMapper.addMappings(new PropertyMap<UserPasswordDTO, User>() {
            @Override
            protected void configure() {
                /*
                ModelMapper会生成隐式映射
                由于UserPasswordDTO含有oldPassword和newPassword字段，User含有字段password
                导致setPassword有多个可能的映射，产生冲突
                使用显示声明的方式指定映射
                 */
                map().setPassword(source.getNewPassword());
            }
        });

        return modelMapper;
    }
}
