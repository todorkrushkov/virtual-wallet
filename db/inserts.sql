USE virtual_wallet;

INSERT INTO roles (name)
VALUES
    ('USER'),
    ('ADMIN');

INSERT INTO currencies (currency_code, name, symbol, decimals, is_active)
VALUES
    ('EUR', 'Euro', '€', 2, TRUE);

INSERT INTO users
(username, password_hash, first_name, last_name, email, phone_number, photo_url, role_id, is_blocked)
VALUES
    ('ivan',  '$2a$12$D16F1eglu1b2dtENlZ.EuukCkEBw8O6u7mFPwlvs3XQwTctnpmVJ2',  'Ivan',  'Petrov',  'ivan@test.com',  '0888000001', NULL, 1, FALSE),
    ('maria', '$2a$12$S8cFVCy311hXZhI61RPlFOM1OIWcYmXVyE5e6lA9yVZXylAo6YZ7G', 'Maria', 'Ivanova', 'maria@test.com', '0888000002', NULL, 1, FALSE),
    ('admin', '$2a$12$xorAQ5wMayFOM3hMh4iJDOOc6jadYMoWyO2oep0kWvHPnK1ka5x0S', 'Admin', 'Root',   'admin@test.com', NULL, NULL, 2, FALSE);

INSERT INTO wallets
(user_id, balance, currency_code, version)
VALUES
    (1, 100.00, 'EUR', 0),
    (2, 50.00,  'EUR', 0),
    (3, 1000.00,'EUR', 0);

INSERT INTO cards
(user_id, card_suffix, expiration_month, expiration_year, card_holder)
VALUES
    (1, '1111', 12, 2028, 'Ivan Petrov'),
    (2, '4444', 11, 2027, 'Maria Ivanova');

INSERT INTO transactions
(type, status, amount, currency_code,
 sender_wallet_id, recipient_wallet_id,
 sender_id, recipient_id)
VALUES
    ('TOP_UP',    'CONFIRMED', 100.00, 'EUR', NULL, 1, NULL, 1),
    ('TRANSFER',  'CONFIRMED', 20.00,  'EUR', 1, 2,    1,    2),
    ('PAYMENT',   'CONFIRMED', 10.00,  'EUR', 2, NULL,    2,    NULL);

