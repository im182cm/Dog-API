package philip.com.dogapi.model.dto;

/**
 * Created by Philip on 2018. 1. 8..
 */

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
}
