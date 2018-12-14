package com.vote.vote.utils;

import java.util.Arrays;
import java.util.Objects;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -4754310636975621327L;
	private final String field;
	private final String msg;
	private final Object[] params;

	public ServiceException(String msg) {
		super("Service Message msg: " + msg);
		this.field = "message";
		this.msg = msg;
		this.params = new Object[0];
	}

	public ServiceException(String field, String msg) {
		super("Service Message msg: " + msg);
		this.field = field;
		this.msg = msg;
		this.params = new Object[0];
	}

	public ServiceException(String msg, Object... params) {
		super("Service Message msg: " + msg, parseCause(params));
		this.field = "";
		this.msg = msg;
		this.params = parseParams(params);
	}

	public ServiceException(String field, String msg, Object... params) {
		super("Service Message msg: " + msg, parseCause(params));
		this.field = field;
		this.msg = msg;
		this.params = parseParams(params);
	}

	private static Throwable parseCause(Object[] params) {
		if (Objects.nonNull(params) && params.length > 0) {
			Object lastParam = params[params.length - 1];
			if (Objects.nonNull(lastParam) && lastParam instanceof Throwable) {
				return (Throwable) lastParam;
			}
		}
		return null;
	}

	private Object[] parseParams(Object[] params) {
		if (Objects.nonNull(params) && params.length > 0) {
			Object lastParam = params[params.length - 1];
			if (Objects.nonNull(lastParam) && lastParam instanceof Throwable) {
				return Arrays.copyOfRange(params, 0, params.length - 1);
			} else {
				return params;
			}
		} else {
			return new Object[0];
		}
	}

	public String getMsg() {
		return msg;
	}

	public Object[] getParams() {
		return params;
	}

	public String getField() {
		return field;
	}
}
