package com.pedro.osworks.domain.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 6262190008798743955L;
	
	public NegocioException(String message) {
		super(message);
	}
}
