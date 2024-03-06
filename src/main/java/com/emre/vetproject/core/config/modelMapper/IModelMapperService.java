package com.emre.vetproject.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


public interface IModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
