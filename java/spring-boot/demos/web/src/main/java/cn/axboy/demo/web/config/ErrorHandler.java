package cn.axboy.demo.web.config;

import cn.axboy.demo.web.common.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResult<?>> handleRuntimeException(RuntimeException e) {
        log.error(">>> RuntimeException: {}", e.getLocalizedMessage());
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            builder.append(stackTraceElement.toString()).append("\n");
        }
        log.error("非预期异常：{}", builder.toString());
        e.printStackTrace();
        BaseResult<?> result = new BaseResult<>(500, "SERVER EXCEPTION", null);
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResult<?>> handleException(IllegalArgumentException e) {
        log.error(">>> IllegalArgumentException: {}", e.getLocalizedMessage());
        BaseResult<?> result = new BaseResult<>(400, e.getMessage(), null);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResult<?>> handleException(MethodArgumentNotValidException e) {
        log.error(">>> MethodArgumentNotValidException: {}", e.getLocalizedMessage());
        BaseResult<?> result = new BaseResult<>(400, e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
