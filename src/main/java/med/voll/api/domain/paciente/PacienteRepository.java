package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable pageable);

    boolean findAtivoById(@NotNull Long idPaciente);
}
