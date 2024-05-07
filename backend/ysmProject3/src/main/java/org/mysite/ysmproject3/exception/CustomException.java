package org.mysite.ysmproject3.exception;

public class CustomException extends RuntimeException {
    // 추가적인 필드 선언 (필요한 경우)
    private int statusCode; // 예외 상태 코드를 저장할 필드

    // 생성자
    public CustomException(String message) {
        super(message);
    }

    // 상태 코드를 포함하는 생성자
    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    // 메시지와 원인 예외를 포함하는 생성자
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    // 메시지, 원인 예외, 상태 코드를 모두 포함하는 생성자
    public CustomException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    // 상태 코드를 반환하는 메소드
    public int getStatusCode() {
        return statusCode;
    }

    // 상태 코드를 설정하는 메소드
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
