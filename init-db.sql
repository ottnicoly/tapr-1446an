-- Script de Inicialização do PostgreSQL
-- Cria um banco de dados isolado para cada microserviço

CREATE DATABASE authdb;

CREATE DATABASE roomdb;

CREATE DATABASE bookingdb;

CREATE DATABASE notificationdb;

COMMENT ON DATABASE authdb IS 'Banco de dados do serviço de autenticação';
COMMENT ON DATABASE roomdb IS 'Banco de dados do serviço de salas';
COMMENT ON DATABASE bookingdb IS 'Banco de dados do serviço de reservas';
COMMENT ON DATABASE notificationdb IS 'Banco de dados do serviço de notificações';
