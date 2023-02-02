package userinyerface.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class LoginInfo {
    @Getter
    @JsonProperty
    private String password;
    @Getter
    @JsonProperty
    private String emailRecipientName;
    @Getter
    @JsonProperty
    private String emailDomain;
    @Getter
    @JsonProperty
    private String emailSubDomain;
}
