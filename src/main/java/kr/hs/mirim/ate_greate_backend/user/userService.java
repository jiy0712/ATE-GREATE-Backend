@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(userDTO dto) {
        if (userRepository.existsByUserid(dto.getUserid())) {
            throw new IllegalArgumentException("존재하는 아이디입니다.");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("존재하는 이메일입니다.");
        }

        String encodPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User();
        user.setUserid(dto.getUserid());
        user.setPassword(encodPassword);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthday(dto.getBirthday());
        user.setKeyword(dto.getKeyword());

        userRepository.save(user);
    }
}
