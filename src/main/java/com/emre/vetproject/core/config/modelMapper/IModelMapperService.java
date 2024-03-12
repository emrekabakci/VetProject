package com.emre.vetproject.core.config.modelMapper;

import org.modelmapper.ModelMapper;


public interface IModelMapperService {
    ModelMapper forRequest();

    ModelMapper forResponse();
}
