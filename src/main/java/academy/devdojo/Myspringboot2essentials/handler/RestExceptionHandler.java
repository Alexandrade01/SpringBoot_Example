package academy.devdojo.Myspringboot2essentials.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import academy.devdojo.Myspringboot2essentials.exception.BadRequestException;
import academy.devdojo.Myspringboot2essentials.exception.BadRequestExceptionDetails;
import academy.devdojo.Myspringboot2essentials.util.DateUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class RestExceptionHandler {
	
	private final DateUtil dateUtil;
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException( BadRequestException bre){
		
		return new ResponseEntity<>(
				BadRequestExceptionDetails.builder()
							.title("Bad Request Exception, Check the Documentation")
							.status(HttpStatus.BAD_REQUEST.value())
							.details(bre.getMessage())
							.developerMessage(bre.getClass().toString())
							.timestamp(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()))
							.build(),HttpStatus.BAD_REQUEST
				
				
		);
		
	}
}
