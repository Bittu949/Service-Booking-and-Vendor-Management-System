package com.sqts.sbvms.Exception;

import com.sqts.sbvms.Dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordMismatchedException.class)
    public ResponseEntity<ApiResponse<Object>> handlePasswordMismatchedException(PasswordMismatchedException ex){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null,
                        LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidInputException(InvalidInputException ex){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null,
                        LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        false,
                        ex.getBindingResult()
                                .getFieldErrors()
                                .getFirst()
                                .getDefaultMessage(),
                        null,
                        LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoServiceFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoServiceFoundException(NoServiceFoundException ex){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null,
                        LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }
}
