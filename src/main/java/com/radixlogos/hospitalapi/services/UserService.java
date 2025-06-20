package com.radixlogos.hospitalapi.services;


import com.radixlogos.hospitalapi.dtos.insertions.UserInsertDTO;
import com.radixlogos.hospitalapi.dtos.login.LoginRequestDTO;
import com.radixlogos.hospitalapi.dtos.responses.JwtResponseDTO;
import com.radixlogos.hospitalapi.dtos.responses.UserResponseDTO;
import com.radixlogos.hospitalapi.entities.User;
import com.radixlogos.hospitalapi.enums.RoleType;
import com.radixlogos.hospitalapi.repositories.UserRepository;
import com.radixlogos.hospitalapi.security.JwtUtil;
import com.radixlogos.hospitalapi.services.exceptions.AlreadyExistsException;
import com.radixlogos.hospitalapi.services.exceptions.InvalidPasswordException;
import com.radixlogos.hospitalapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public JwtResponseDTO loginByEmail(LoginRequestDTO login){
        Optional<User> user = userRepository.findByEmail(login.email());
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Email inválido!");
        }
        var entityUser = user.get();
        if(!passwordEncoder.matches(login.password(), entityUser.getPassword())){
            throw new InvalidPasswordException("Senha inválida!");
        }
        return new JwtResponseDTO(jwtUtil.generateToken(login.email()));
    }
    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findAllUsers(Pageable pageable){
        return userRepository.findAll(pageable).map(UserResponseDTO::fromUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findUserById(Long id){
         var entity = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado"));
        return UserResponseDTO.fromUser(entity);
    }
    @Transactional
    public UserResponseDTO insertUser(UserInsertDTO insertDTO) {
        if (userRepository.existsByEmail(insertDTO.email()))
            throw new AlreadyExistsException("Esse email já foi cadastrado!");
        User entity = new User();
        copyDtoToEntity(entity, insertDTO);
        entity = userRepository.save(entity);
        return UserResponseDTO.fromUser(entity);
    }

    @Transactional
    public UserResponseDTO giveRole(Long id, RoleType role){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        var entityUser = userRepository.getReferenceById(id);
        entityUser.setRole(role);
        userRepository.save(entityUser);
        return UserResponseDTO.fromUser(entityUser);
    }


    @Transactional
    public UserResponseDTO updateUser(Long id, UserInsertDTO insertDTO) {
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        var entity = userRepository.getReferenceById(id);
        entity.setEmail(insertDTO.email());
        entity.setPassword(passwordEncoder.encode(insertDTO.password()));
        userRepository.save(entity);
        return UserResponseDTO.fromUser(entity);
    }

    @Transactional
    public void deleteUser(Long id)  {
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }

    private void copyDtoToEntity(User entityUser,UserInsertDTO insertDTO) {
        entityUser.setEmail(insertDTO.email());
        String rawPassword = insertDTO.password();
        String encryptPassword = passwordEncoder.encode(rawPassword);
        entityUser.setPassword(encryptPassword);
    }


}
