USE virtual_wallet;

INSERT INTO roles (name)
VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

INSERT INTO currencies (currency_code, name, symbol, decimals, is_active)
VALUES
    ('EUR', 'Euro', '€', 2, TRUE);

INSERT INTO users
(username, password_hash, first_name, last_name, email, phone_number, photo_url, role_id, is_blocked)
VALUES
    ('ivan',  'pass123',  'Ivan',  'Petrov',  'ivan@test.com',  '0888000001', NULL, 1, FALSE),
    ('maria', 'pass123', 'Maria', 'Ivanova', 'maria@test.com', '0888000002', NULL, 1, FALSE),
    ('admin', 'pass123', 'Admin', 'Root',   'admin@test.com', '0888000099', NULL, 2, FALSE);

INSERT INTO wallets
(user_id, balance, currency_code, version)
VALUES
    (1, 100.00, 'EUR', 0),
    (2, 50.00,  'EUR', 0),
    (3, 1000.00,'EUR', 0);

INSERT INTO cards
(user_id, card_number, expiration_month, expiration_year, card_holder, ccv)
VALUES
    (1, '4111111111111111', 12, 2028, 'Ivan Petrov',  '123'),
    (2, '5555555555554444', 11, 2027, 'Maria Ivanova','456');

INSERT INTO transactions
(type, status, amount, currency_code,
 sender_wallet_id, recipient_wallet_id,
 sender_id, recipient_id)
VALUES
    ('TOP_UP',    'CONFIRMED', 100.00, 'EUR', 1, NULL, 1, NULL),
    ('TRANSFER',  'CONFIRMED', 20.00,  'EUR', 1, 2,    1,    2),
    ('PAYMENT',   'CONFIRMED', 10.00,  'EUR', 2, 3,    2,    3);

