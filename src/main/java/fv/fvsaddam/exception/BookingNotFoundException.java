package fv.fvsaddam.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 *
 * @author fvsaddam - fvmonster.corp@gmail.com
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "tidak ada booking")
public class BookingNotFoundException extends RuntimeException {

}
