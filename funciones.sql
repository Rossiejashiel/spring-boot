--funcion que muestra  la tabla personas
create or replace function listar( )
returns
table(
id_persona integer,
nombre varchar,
ciudad varchar,
sueldo integer
)
as $$
begin
return query

select * from persona
;
end;
$$
language 'plpgsql';

select *from  listar();

--funcion que despliega los atributos de la persona con id=id_p
create or replace function listarid(id_p integer )
returns
table(
idPersona  integer,
nombre varchar,
ciudad varchar,
sueldo integer
)
as $$
begin
return query

select * from persona where id_persona=id_p
;
end;
$$
language 'plpgsql';
select idPersona as id_persona, nombre,ciudad,sueldo from listarid(3);
