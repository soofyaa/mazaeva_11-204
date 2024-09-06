package ru.itis.animerec.utils.handler;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.nio.file.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFound(NoHandlerFoundException ex) {
        log.error("Page not found: ", ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Page not found");
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDenied(AccessDeniedException ex) {
        log.error("Access denied: ", ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Access denied");
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ModelAndView handleBadCredentials(BadCredentialsException ex) {
        log.error("Bad credentials: ", ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Bad credentials");
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFound(EntityNotFoundException ex) {
        log.error("Entity not found: ", ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Entity not found");
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ModelAndView handleAuthenticationException(AuthenticationException ex) {
        log.error("Authentication error: ", ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Authentication error");
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex) {
        log.error("An error occurred: ", ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "An error occurred");
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleThrowable(Throwable ex) {
        log.error("Unexpected error: ", ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Unexpected error");
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error/error");
        return mav;
    }
}