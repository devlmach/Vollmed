create table usuarios(

    id SERIAL PRIMARY KEY,
    login varchar(100) not null,
    senha varchar(255) not null

);