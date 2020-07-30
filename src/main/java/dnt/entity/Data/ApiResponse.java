package dnt.entity.Data;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    @NonNull
    private boolean success;

    @NonNull
    private String message;

}
