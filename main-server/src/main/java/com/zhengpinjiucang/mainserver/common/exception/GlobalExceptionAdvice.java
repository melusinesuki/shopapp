package com.zhengpinjiucang.mainserver.common.exception;

import com.zhengpinjiucang.mainserver.common.bean.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
/**全局异常处理**/
public class GlobalExceptionAdvice {
    @ExceptionHandler(NomalException.class)
    public ResultBean nomalExceptionHandler(NomalException e) {
        log.info("业务报错{}",e.getMessage());
        return new ResultBean(500,e.getMessage(),null);

    }
    public ResultBean exceptionHandler(Exception e) {
        log.info("系统报错{}",e.getMessage());
        return new ResultBean(500,"您的网络不稳定",null);

    }
}
