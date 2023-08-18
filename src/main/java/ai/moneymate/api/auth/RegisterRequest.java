package ai.moneymate.api.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 20)
    private String password;
}
