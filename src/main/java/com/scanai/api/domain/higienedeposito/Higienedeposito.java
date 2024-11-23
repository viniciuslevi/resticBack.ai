package com.scanai.api.domain.higienedeposito;

import com.scanai.api.domain.higienedeposito.dto.DadosCadastroHigieneDeposito;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Table(name = "tb_higienedeposito")
@Entity(name = "tb_higienedeposito")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Higienedeposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate datahigiene;

    private Long fkdeposito;

    public Higienedeposito(DadosCadastroHigieneDeposito data) {
        this.datahigiene = data.datahigiene();
        this.fkdeposito = data.fkdeposito();
    }
}
