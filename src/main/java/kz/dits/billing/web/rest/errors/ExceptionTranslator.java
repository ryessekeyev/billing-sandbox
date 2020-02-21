package kz.dits.billing.web.rest.errors;

import kz.dits.billing.exception.AlreadyExistsException;
import kz.dits.billing.exception.EntityNotFoundException;
import kz.dits.billing.exception.NotFoundException;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@ControllerAdvice
public class ExceptionTranslator {

    private final Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);

    @ExceptionHandler(BillingRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processBillingRuntimeException(BillingRuntimeException ex) {
        return new ErrorVM(ErrorConstants.ERR_RUNTIME_EXCEPTION, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ErrorVM(ErrorConstants.ERR_RUNTIME_EXCEPTION, ex.getMessage());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorVM(ErrorConstants.ERR_RUNTIME_EXCEPTION, ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorVM processNotFoundException(AlreadyExistsException ex) {
        return new ErrorVM(ErrorConstants.ERR_RUNTIME_EXCEPTION, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public ErrorVM processNotFoundException(NotFoundException ex) {
        return new ErrorVM(ErrorConstants.ERR_RUNTIME_EXCEPTION, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorVM processEntityNotFoundException(EntityNotFoundException ex) {
        return new ErrorVM(ErrorConstants.ERR_RUNTIME_EXCEPTION, ex.getMessage());
    }

    @ExceptionHandler(ConcurrencyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorVM processAxonConcurrencyError(ConcurrencyException ex) {
        return new ErrorVM(ErrorConstants.ERR_CONCURRENCY_FAILURE, ex.getMessage());
    }

    @ExceptionHandler(ConcurrencyFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorVM processConcurrencyError(ConcurrencyFailureException ex) {
        return new ErrorVM(ErrorConstants.ERR_CONCURRENCY_FAILURE, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorVM dto = new ErrorVM(ErrorConstants.ERR_VALIDATION);
        for (FieldError fieldError : fieldErrors) {
            dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
        }
        return dto;
    }

    @ExceptionHandler(CustomParameterizedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ParameterizedErrorVM processParameterizedValidationError(CustomParameterizedException ex) {
        return ex.getErrorVM();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorVM processMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ErrorVM(ErrorConstants.ERR_METHOD_NOT_SUPPORTED, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorVM> processException(Exception ex) {
        if (log.isDebugEnabled()) {
            log.info("An unexpected error occured: {}", ex.getMessage(), ex);
        } else {
            log.error("An unexpected error occured: {}", ex.getMessage());
        }
        BodyBuilder builder;
        ErrorVM errorVM;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            errorVM = new ErrorVM("error." + responseStatus.value().value(), responseStatus.reason());
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            errorVM = new ErrorVM(ErrorConstants.ERR_INTERNAL_SERVER_ERROR, "Internal server error");
        }
        return builder.body(errorVM);
    }
}
