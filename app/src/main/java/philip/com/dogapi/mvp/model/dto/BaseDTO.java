package philip.com.dogapi.mvp.model.dto;

import android.support.annotation.VisibleForTesting;

public class BaseDTO<T> {
    private String status;
    private T message;

    @Override
    public String toString() {
        return "BaseDTO{" +
                "status='" + status + '\'' +
                ", message=" + message +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public T getMessage() {
        return message;
    }

    @VisibleForTesting
    public BaseDTO(String status, T message) {
        this.status = status;
        this.message = message;
    }
}
