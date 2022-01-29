CREATE TABLE task(
    id bigint primary key auto_increment,
    title varchar(200),
    description varchar(200),
    points int,
    status int DEFAULT 0
);

CREATE TABLE user (
    id bigint PRIMARY KEY auto_increment,
    name varchar(200),
    email varchar(200),
    password varchar(200),
    githubuser varchar(200)
);

INSERT INTO user(name, email, password, githubuser) VALUES
('Celso Lorensatto', 'lorensatto98@gmail.com', '$2a$12$PhDeVXbGSlyxWxiv1i41fuUU1jaf7ZhhGqWbGevTedjjlsA7zJGme', 'Lorensatto99'),
('Guilherme Rodriguero', 'gui@mail.com', '$2a$12$.NtDBYL0kWvB1q/zS0/Heuyl.nC3AIiiUoQNu5JSeh.irrXWCbsUm', 'GuiRodriguero'),
('Eduardo Costa', 'edu@mail.com', '$2a$12$VdIuZ6W.5.wGRJPM4832ruTmz9WXyYUYwrkzHho6cFPh9EfgFnw2G', 'eD0o');


INSERT INTO task (title, description, points, status) VALUES (
    'Criar banco de dados',
    'Criar bd oracle na nuvem',
    300,
    10
);

INSERT INTO task (title, description, points, status) VALUES (
    'Protótipo',
    'Criar protótipo de alta fidelidade',
    150,
    60
);

INSERT INTO task (title, description, points, status) VALUES (
    'Modelagem de dados',
    'Criar modelo lógico dos dados',
    200,
    95
);


