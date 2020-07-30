package dnt.entity.Data;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private int status;
    private String message;
}
