package fv.fvsaddam.security;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author fvsaddam - fvmonster.corp@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(AllowedForCommentModerator.condition)
public @interface AllowedForCommentModerator {

    String condition = "hasRole('ROLE_COMMENT_MODERATOR')";
}
