import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Int id;

    @NotEmpty
    private String userid;

    @NotEmpty
    @Size
    private String password;

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private List<String> keyword;
}
