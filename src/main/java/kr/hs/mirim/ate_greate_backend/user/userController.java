@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/sign")
    public ResponseEntity<String> sign(@RequestBody @Valid userDTO dto) {
        userService.registerUser(dto); 
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공입니다.");
    }
}
