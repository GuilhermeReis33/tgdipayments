package com.tgidpayments.services;

import com.tgidpayments.domain.Sistema.TaxaSistema;
import com.tgidpayments.domain.transaction.Transaction;
import com.tgidpayments.domain.user.User;
import com.tgidpayments.dtos.TransactionDTO;
import com.tgidpayments.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private EmailService emailService;
    public Transaction generateTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        Transaction newTransaction = new Transaction();
        newTransaction.setValor(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);

        if (transaction.taxa() != null && transaction.taxa().compareTo(BigDecimal.ZERO) >= 0) {
            BigDecimal taxaDecimal = transaction.taxa().divide(BigDecimal.valueOf(100));
            BigDecimal valorTaxa = transaction.value().multiply(taxaDecimal);

            TaxaSistema taxaSistema = new TaxaSistema(valorTaxa);
            newTransaction.setTaxaSistema(taxaSistema);
            newTransaction.aplicarTaxaSistema();
        } else {
            throw new Exception("Taxa inválida");
        }

        sender.setSaldo(sender.getSaldo().subtract(newTransaction.getValor()));
        receiver.setSaldo(receiver.getSaldo().add(newTransaction.getValor()));

        emailService.EnviarEmail(sender.getEmail(), "Notificação de Transação", "Sua Transação foi realizada com sucesso");

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        return newTransaction;
    }
}
