INSERT INTO Role (name)
VALUES ('ROLE_ADMIN');
INSERT INTO Role (name)
VALUES ('ROLE_USER');


INSERT INTO user_account(firstname, lastname, username, email, password, enabled, avatar_url)
VALUES ('diman', 'volina','volina', 'dimanvolina@mail.ru', '123', 'true','https://pp.vk.me/c629114/v629114505/1370f/7mMtaMqFbck.jpg');
INSERT INTO users_roles(user_id, role_id)
VALUES ('1', '2');

INSERT INTO user_account(firstname, lastname, username, email, password, enabled, avatar_url)
VALUES ('admin', 'admin','admin', 'dimanvolina@gmail.com', '321', 'true','https://pp.vk.me/c623719/v623719151/4a61b/hVzcAMWD8SU.jpg');
INSERT INTO users_roles(user_id, role_id)
VALUES ('2', '1');



INSERT INTO site(username, site_name, description , site_logo_url)
VALUES ('volina', 'vadik', 'vadik pizdati','https://pp.vk.me/c623719/v623719151/4a61b/hVzcAMWD8SU.jpg');