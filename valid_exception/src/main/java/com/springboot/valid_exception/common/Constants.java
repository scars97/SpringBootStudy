package com.springboot.valid_exception.common;

public class Constants {//커스텀 예외

	public enum ExceptionClass{
		
		PRODUCT("Product");
		
		
		private String exceptionClass;
		
		ExceptionClass(String exceptionClass){
			this.exceptionClass = exceptionClass;
		}
		
		public String getExceptionClass() {
			return exceptionClass;
		}
		
		@Override
		public String toString() {
			return getExceptionClass() + " Exception. ";
		}
	}
}
