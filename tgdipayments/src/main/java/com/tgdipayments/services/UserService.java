package com.tgdipayments.services;

import com.tgdipayments.domain.user.User;
import com.tgdipayments.domain.user.UserType;
import com.tgdipayments.dtos.UserDTO;
import com.tgdipayments.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal saldo) throws Exception {
        if(sender.getSaldo().compareTo(saldo) < 0){
            throw new Exception("Saldo insuficiente!");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User createUser(UserDTO cache) throws Exception {
        validateUserTypeAndDocument(cache);
        User newUser = new User(cache);

        this.saveUser(newUser);
        return newUser;
    }

    private void validateUserTypeAndDocument(UserDTO userDTO) throws Exception {
        if (userDTO.userType() == UserType.CLIENTE){
            validarCPF(userDTO.document());
        } else if (userDTO.userType() == UserType.EMPRESA){
            validarCNPJ(userDTO.document());
        } else {
            throw new IllegalAccessException("Tipo de usuário inválido");
        }
    }

    private void validarCPF(String cpf) throws Exception{
        if(cpf == null || cpf.length() != 11){
            throw new IllegalAccessException("CPF INVALIDO");
        }
    }

    private void validarCNPJ(String cnpj) throws Exception{
        if(cnpj == null || cnpj.length() != 14){
            throw new IllegalAccessException("CNPJ INVALIDO");
        }
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }
    public void saveUser(User user){
        this.repository.save(user);
    }
}
