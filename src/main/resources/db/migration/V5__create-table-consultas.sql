CREATE TABLE consultas(

    id SERIAL PRIMARY KEY,
    medico_id INTEGER NOT NULL,
    paciente_id INTEGER NOT NULL,
    data TIMESTAMP NOT NULL,

    CONSTRAINT fk_consultas_medico_id FOREIGN KEY(medico_id) REFERENCES medicos(id),
    CONSTRAINT fk_consultas_paciente_id FOREIGN KEY(paciente_id) REFERENCES pacientes(id)

);