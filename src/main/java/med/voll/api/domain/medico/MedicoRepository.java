package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    // Sintaxe JPQL
    @Query(""" 
            SELECT m.ativo,
                   m.especialidade
            FROM Medico m
            WHERE m.ativo = true
              and m.especialidade = :especialidade
              and m.id not in (
                SELECT c.medico.id FROM Consulta c
                WHERE c.data = :data
              )
            ORDER BY random()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, @NotNull @Future LocalDateTime data);
}
