package by.bntu.fitr.profilerclient.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HttpResponse {
    @JsonProperty(value = "isSuccess")
    private boolean success;

    @JsonProperty(value = "msg")
    private String msg;

    public HttpResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
