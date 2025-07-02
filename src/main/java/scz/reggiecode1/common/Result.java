package scz.reggiecode1.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data){
        Result<T> result=new Result<>();
        result.code=1;
        result.data=data;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result=new Result<>();
        result.code=0;
        result.msg=msg;
        return result;
    }
}
