package com.tgdipayments.domain.transaction;

import com.tgdipayments.domain.Sistema.TaxaSistema;
import com.tgdipayments.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name="receiver_id")
    private User receiver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxasistema_id")
    private TaxaSistema taxaSistema;

    public void aplicarTaxaSistema(){
        if(taxaSistema != null){
            this.valor = valor.add(taxaSistema.getTaxa());
        }
    }

    public void setTaxaSistema(TaxaSistema taxaSistema){
        this.taxaSistema = taxaSistema;
    }
}

