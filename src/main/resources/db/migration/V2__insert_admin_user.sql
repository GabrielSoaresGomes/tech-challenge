INSERT INTO users (name, email, login, password, address, lastModifiedDateTime)
VALUES (
    'Administrador',
    'admin@teste.com',
    'admin',
    'admin',
    'Rua Sem Nome, 123 – Centro',
    NOW()
)
ON CONFLICT (login) DO NOTHING;