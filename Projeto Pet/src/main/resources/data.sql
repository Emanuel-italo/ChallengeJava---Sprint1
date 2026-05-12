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


------ veterinarios -----
INSERT INTO tb_veterinario (id, nome, crmv, especialidade, email, clinica_id, criado_em, atualizado_em)
VALUES (1, 'Dr. Carlos Mendes', 'SP-12345', 'Clínica Geral', 'carlos@vetamigo.com', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
 
INSERT INTO tb_veterinario (id, nome, crmv, especialidade, email, clinica_id, criado_em, atualizado_em)
VALUES (2, 'Dra. Patrícia Oliveira', 'SP-23456', 'Cardiologia', 'patricia@vetamigo.com', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_veterinario (id, nome, crmv, especialidade, email, clinica_id, criado_em, atualizado_em)
VALUES (3, 'Dr. Rafael Santos', 'RJ-34567', 'Ortopedia', 'rafael@petsaude.com', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

 
-- ---------- PETS ----------
INSERT INTO tb_pet (id, nome, especie, raca, data_nascimento, peso_kg, possui_doenca_cronica, nivel_risco_atual, tutor_id, criado_em, atualizado_em)
VALUES (1, 'Rex', 'CACHORRO', 'Golden Retriever', '2020-01-10', 28.50, FALSE, 'BAIXO', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO tb_pet (id, nome, especie, raca, data_nascimento, peso_kg, possui_doenca_cronica, nivel_risco_atual, tutor_id, criado_em, atualizado_em)
VALUES (2, 'Mia', 'GATO', 'Siamês', '2018-06-15', 4.20, TRUE, 'ALTO', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
 
INSERT INTO tb_pet (id, nome, especie, raca, data_nascimento, peso_kg, possui_doenca_cronica, nivel_risco_atual, tutor_id, criado_em, atualizado_em)
VALUES (3, 'Thor', 'CACHORRO', 'Labrador', '2015-03-22', 32.00, TRUE, 'MODERADO', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_pet (id, nome, especie, raca, data_nascimento, peso_kg, possui_doenca_cronica, nivel_risco_atual, tutor_id, criado_em, atualizado_em)
VALUES (4, 'Luna', 'GATO', 'Persa', '2022-09-01', 3.80, FALSE, 'BAIXO', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
 

-- ---------- CONSULTAS ----------
INSERT INTO tb_consulta (id, data_hora, tipo, status, motivo, diagnostico, prescricao, data_retorno_previsto, pet_id, veterinario_id, criado_em, atualizado_em)
VALUES (1, DATEADD('DAY', -30, CURRENT_TIMESTAMP), 'VACINACAO', 'REALIZADA',
        'Vacinação anual V10', 'Pet saudável - aplicação OK', 'Reforço em 12 meses',
        DATEADD('YEAR', 1, CURRENT_TIMESTAMP), 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
 
INSERT INTO tb_consulta (id, data_hora, tipo, status, motivo, diagnostico, prescricao, data_retorno_previsto, pet_id, veterinario_id, criado_em, atualizado_em)
VALUES (2, DATEADD('DAY', -60, CURRENT_TIMESTAMP), 'CONSULTA_ROTINA', 'REALIZADA',
        'Check-up cardiológico', 'Sopro cardíaco grau 2', 'Enalapril 5mg 1x ao dia',
        DATEADD('DAY', 30, CURRENT_TIMESTAMP), 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
 
INSERT INTO tb_consulta (id, data_hora, tipo, status, motivo, diagnostico, prescricao, data_retorno_previsto, pet_id, veterinario_id, criado_em, atualizado_em)
VALUES (3, DATEADD('DAY', 15, CURRENT_TIMESTAMP), 'RETORNO', 'AGENDADA',
        'Reavaliação cardiológica', NULL, NULL, NULL, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)