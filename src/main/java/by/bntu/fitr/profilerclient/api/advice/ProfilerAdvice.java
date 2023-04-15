package by.bntu.fitr.profilerclient.api.advice;

import by.bntu.fitr.profilerclient.api.exception.AlreadyExistsException;
import by.bntu.fitr.profilerclient.api.exception.CommonException;
import by.bntu.fitr.profilerclient.api.exception.NotFoundException;
import by.bntu.fitr.profilerclient.api.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProfilerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<HttpResponse> handleAlreadyExistsException(final AlreadyExistsException e) {
        HttpResponse httpResponse = new HttpResponse(false, e.getMessage());
        return new ResponseEntity<>(httpResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponse> handleNotFoundException(final NotFoundException e) {
        HttpResponse httpResponse = new HttpResponse(false, e.getMessage());
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<HttpResponse> handleCommonException(final CommonException e) {
        HttpResponse httpResponse = new HttpResponse(false, e.getMessage());
        return new ResponseEntity<>(httpResponse, HttpStatus.CONFLICT);
    }
}
