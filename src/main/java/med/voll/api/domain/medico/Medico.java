package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        if (dadosAtualizacaoMedico.nome() != null) {
            this.nome = dadosAtualizacaoMedico.nome();
        }

        if (dadosAtualizacaoMedico.telefone() != null) {
            this.telefone = dadosAtualizacaoMedico.telefone();
        }

        if (dadosAtualizacaoMedico.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosAtualizacaoMedico.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
