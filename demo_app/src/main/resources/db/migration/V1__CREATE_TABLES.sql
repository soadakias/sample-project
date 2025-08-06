create table if not exists sport (
    sport_name varchar(80) primary key
);

create table if not exists specifier (
    specifier_name "char" primary key
);

create table if not exists match (
    id bigserial primary key,
    description varchar(250),
    match_date date,
    match_time time with time zone,
    sport varchar(80) not null,
    constraint fk_sport foreign key(sport) references sport(sport_name)
);

create table if not exists match_odds(
    id bigserial primary key,
    match_id bigint not null,
    specifier "char" not null,
    odd numeric(10,2),
    constraint fk_match_id foreign key(match_id) references match(id),
    constraint fk_specifier foreign key (specifier) references specifier(specifier_name)
);