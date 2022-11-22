package com.abnamro.recipe.response;

import java.util.Objects;

public class ErrorResponse {

    private final String message;
    private final int status;

    public ErrorResponse(String message,int status) {
        this.message = message;
        this.status=status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
		return status;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorResponse that = (ErrorResponse) o;

        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
