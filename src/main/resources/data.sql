CREATE TABLE TB_TASK(
    id bigint primary key auto_increment,
    title varchar(200),
    description varchar(200),
    points int,
    status int DEFAULT 0
);

CREATE TABLE TB_USER (
    id bigint PRIMARY KEY auto_increment,
    name varchar(200),
    email varchar(200),
    password varchar(200),
    points int DEFAULT 0,
    githubuser varchar(200)
);

CREATE TABLE TB_ROLE (
id int primary key auto_increment,
name varchar(200)
);

INSERT INTO TB_ROLE(name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

CREATE TABLE TB_USER_ROLES(
    user_id int,
    roles_id int
);

INSERT INTO TB_USER_ROLES VALUES (1,1),(2,2),(3,2);


INSERT INTO TB_USER(name, email, password, points, githubuser) VALUES
('Celso Lorensatto', 'lorensatto98@gmail.com', '$2a$12$PhDeVXbGSlyxWxiv1i41fuUU1jaf7ZhhGqWbGevTedjjlsA7zJGme',50, 'Lorensatto99'),
('Guilherme Rodriguero', 'gui@mail.com', '$2a$12$.NtDBYL0kWvB1q/zS0/Heuyl.nC3AIiiUoQNu5JSeh.irrXWCbsUm',150, 'GuiRodriguero'),
('Eduardo Costa', 'edu@mail.com', '$2a$12$VdIuZ6W.5.wGRJPM4832ruTmz9WXyYUYwrkzHho6cFPh9EfgFnw2G',100, 'eD0o');


INSERT INTO TB_TASK (title, description, points, status) VALUES (
    'Criar banco de dados',
    'Criar bd oracle na nuvem',
    300,
    10
);

INSERT INTO TB_TASK (title, description, points, status) VALUES (
    'Protótipo',
    'Criar protótipo de alta fidelidade',
    150,
    60
);

INSERT INTO TB_TASK (title, description, points, status) VALUES (
    'Modelagem de dados',
    'Criar modelo lógico dos dados',
    200,
    95
);


