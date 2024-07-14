package com.studentmanager.studentmanger.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DTOToEntityViceVersa {


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
