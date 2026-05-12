-- ---------- TUTORES ----------
INSERT INTO tb_tutor (id, nome, cpf, email, telefone, data_nascimento, aceita_comunicacao, criado_em, atualizado_em)
VALUES (1, 'Maria da Silva', '123.456.789-00', 'maria@email.com', '(11) 98765-4321', '1990-05-20', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_tutor (id, nome, cpf, email, telefone, data_nascimento, aceita_comunicacao, criado_em, atualizado_em)
VALUES (2, 'João Pereira', '987.654.321-00', 'joao@email.com', '(11) 91234-5678', '1985-08-15', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
 
INSERT INTO tb_tutor (id, nome, cpf, email, telefone, data_nascimento, aceita_comunicacao, criado_em, atualizado_em)
VALUES (3, 'Ana Costa', '111.222.333-44', 'ana@email.com', '(21) 99999-0000', '1995-11-30', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);



-- ---------- CLINICAS ----------
INSERT INTO tb_clinica (id, razao_social, cnpj, endereco, telefone, ativa, criado_em, atualizado_em)
VALUES (1, 'Clínica VetAmigo Ltda', '12.345.678/0001-90', 'Av. Paulista, 1000 - São Paulo/SP', '(11) 3000-1000', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_clinica (id, razao_social, cnpj, endereco, telefone, ativa, criado_em, atualizado_em)
VALUES (2, 'Pet Saúde Premium SA', '98.765.432/0001-10', 'Rua das Flores, 500 - Rio de Janeiro/RJ', '(21) 4000-2000', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- ---------- VETERINARIOS ----------
INSERT INTO tb_veterinario (id, nome, crmv, especialidade, email, clinica_id, criado_em, atualizado_em)
VALUES (1, 'Dr. Carlos Mendes', 'SP-12345', 'Clínica Geral', 'carlos@vetamigo.com', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);