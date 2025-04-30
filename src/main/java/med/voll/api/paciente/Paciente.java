package med.voll.api.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    private boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {
        if (dadosAtualizacaoPaciente.nome() != null) {
            this.nome = dadosAtualizacaoPaciente.nome();
        }

        if (dadosAtualizacaoPaciente.telefone() != null) {
            this.telefone = dadosAtualizacaoPaciente.telefone();
        }

        if (dadosAtualizacaoPaciente.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosAtualizacaoPaciente.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
