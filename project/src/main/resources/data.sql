INSERT INTO USER_ACCOUNT (id, name, email, password) VALUES (1, 'user1', 'dino@hotmail.com', '123456');
--INSERT INTO CRYPTO (id, name, bid_price, ask_price) VALUES (1, 'ETHUSDT', 1.0, 1.0);
--INSERT INTO CRYPTO (id, name, bid_price, ask_price) VALUES (2, 'BTCUSDT', 1.0, 1.0);
INSERT INTO WALLET (id, user_id, crypto_name, crypto_amount) VALUES (1, 1, 'USDT',50000);
INSERT INTO TRANSACTION (id, user_id, given_crypto_name, given_crypto_amount, taken_crypto_name, taken_crypto_amount, transaction_timestamp) VALUES (1, 1, 'ETH', 50000, 'USDT', 50000,  parsedatetime('02-07-2022', 'dd-MM-yyyy'));
