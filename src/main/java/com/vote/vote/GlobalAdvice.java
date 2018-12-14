package com.vote.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vote.vote.utils.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@ControllerAdvice
public class GlobalAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalAdvice.class);

	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@ExceptionHandler(ServiceException.class)//统一处理某一类异常，从而能够减少代码重复率和复杂度
	@ResponseStatus(HttpStatus.BAD_REQUEST)//可以将某种异常映射为HTTP状态码
	public Map<String, Object> businessExceptionHandler(ServiceException be) {
		LOGGER.error(be.getMessage(), be);
		final Locale locale = LocaleContextHolder.getLocale(); 
		final String message = messageSource.getMessage(be.getMsg(), be.getParams(), locale);//获取ServiceException的Code与Params的参数
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = -2147774529887359231L;
			{
				put(be.getField(), message);//将获取到的错误传到前台客户端
			}
		};
	}

	/**
	 * bean校验未通过异常
	 * 
	 * @see javax.validation.Valid
	 * @see org.springframework.validation.Validator
	 * @see org.springframework.validation.DataBinder
	 */
	@ResponseBody
	@ExceptionHandler(BindException.class)
	public Map<String, Object> validExceptionHandler(BindException ex) {
		final Locale locale = LocaleContextHolder.getLocale();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = -3222861572243435035L;
			{
				for (FieldError error : fieldErrors) {
					Object value = ex.getFieldValue(error.getField());
					String message = messageSource.getMessage(error.getDefaultMessage(), new Object[] { value },
							locale);
					put(error.getField(), message);
				}
			}
		};
	}
}
