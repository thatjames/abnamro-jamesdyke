package xyz.slimjim.hungrytales.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class WebResponse<R> {

    protected boolean result;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected String errorMessage;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected R data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "result=" + result +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
