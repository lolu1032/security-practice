package me.sung.security.service;

import me.sung.security.dto.JoinDTO;
import me.sung.security.entity.UserEntity;
import me.sung.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void joinFrom(JoinDTO joinDTO) {

        // db에 이미 동일한 username을 가진 회원이 존재하는가 ?
        boolean inUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(inUser) {
            return;
        }


        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
