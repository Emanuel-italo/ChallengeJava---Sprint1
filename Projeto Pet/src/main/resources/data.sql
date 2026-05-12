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


-- ---------- PLANO DE CUIDADO (gerado a partir da consulta 1) ----------
INSERT INTO tb_plano_cuidado (id, titulo, descricao, data_inicio, data_fim_prevista, ativo, consulta_origem_id, criado_em, atualizado_em)
VALUES (1, 'Plano vacinacao — Rex', 'Plano gerado automaticamente a partir da consulta #1',
        DATEADD('DAY', -30, CURRENT_DATE), DATEADD('YEAR', 1, CURRENT_DATE), TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
 
INSERT INTO tb_evento_cuidado (id, descricao, data_prevista, concluido, notificacao_enviada, plano_cuidado_id, criado_em, atualizado_em)
VALUES (1, 'Reforço de vacinação anual', DATEADD('YEAR', 1, CURRENT_TIMESTAMP), FALSE, FALSE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- ---------- SCORES DE SAUDE (histórico longitudinal de Mia) ----------
INSERT INTO tb_score_saude (id, pontuacao, nivel, justificativa, calculado_em, pet_id, criado_em, atualizado_em)
VALUES (1, 30, 'MODERADO', 'Fatores: doença crônica', DATEADD('DAY', -60, CURRENT_TIMESTAMP), 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
 
INSERT INTO tb_score_saude (id, pontuacao, nivel, justificativa, calculado_em, pet_id, criado_em, atualizado_em)
VALUES (2, 45, 'ALTO', 'Fatores: doença crônica, 1 evento(s) vencido(s) sem adesão', DATEADD('DAY', -1, CURRENT_TIMESTAMP), 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
 
-- Ajusta os sequenciadores do H2 para que novos INSERTs não colidam
ALTER TABLE tb_tutor          ALTER COLUMN id RESTART WITH 100;
ALTER TABLE tb_clinica        ALTER COLUMN id RESTART WITH 100;
ALTER TABLE tb_veterinario    ALTER COLUMN id RESTART WITH 100;
ALTER TABLE tb_pet            ALTER COLUMN id RESTART WITH 100;
ALTER TABLE tb_consulta       ALTER COLUMN id RESTART WITH 100;
ALTER TABLE tb_plano_cuidado  ALTER COLUMN id RESTART WITH 100;
ALTER TABLE tb_evento_cuidado ALTER COLUMN id RESTART WITH 100;
ALTER TABLE tb_score_saude    ALTER COLUMN id RESTART WITH 100;