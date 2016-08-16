INSERT INTO Role (name)
VALUES ('ROLE_ADMIN');
INSERT INTO Role (name)
VALUES ('ROLE_USER');


INSERT INTO user_account(firstname, lastname, email, password, enabled)
VALUES ('diman', 'volina', 'dimanvolina@mail.ru', '123', 'true');
INSERT INTO users_roles(user_id, role_id)
VALUES ('1', '2');

INSERT INTO user_account(firstname, lastname, email, password, enabled)
VALUES ('admin', 'admin', 'dimanvolina@gmail.com', '321', 'true');
INSERT INTO users_roles(user_id, role_id)
VALUES ('2', '1');