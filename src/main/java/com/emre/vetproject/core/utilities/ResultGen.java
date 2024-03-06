package com.emre.vetproject.core.utilities;

import com.emre.vetproject.core.result.Result;
import com.emre.vetproject.core.result.ResultData;
import com.emre.vetproject.dto.response.CursorResponse;
import com.emre.vetproject.dto.response.customer.CustomerResponse;
import org.springframework.data.domain.Page;

public class ResultGen {
    public static <T>ResultData<T> created(T data){
        return new ResultData<>(true, Message.CREATED, "201", data);
    }

    public static <T>ResultData<T> validateError(T data){
        return new ResultData<>(false, Message.VALIDATION_ERROR, "400", data);
    }

    public static <T>ResultData<T> success(T data){
        return new ResultData<>(true, Message.SUCCESS, "200", data);
    }

    public static Result notFoundError(String msg){
        return new Result(false, msg, "400");
    }

    public static Result ok(){
        return new Result(true, Message.SUCCESS, "200");
    }

    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData) {
        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());
        return ResultGen.success(cursor);
    }
}
