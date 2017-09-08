delete from   saap.detalle_planilla WHERE id_cabecera_planilla in 
(
select cp.id_cabecera_planilla from saap.cabecera_planilla cp inner join saap.llave ll on ( cp.id_llave=ll.id_llave ) inner join saap.usuario us on ( ll.id_usuario=us.id_usuario )
where 
cp.id_llave=243
);
DELETE FROM saap.lectura WHERE id_llave = 243;
DELETE FROM saap.cabecera_planilla WHERE id_llave =243;
DELETE FROM saap.llave WHERE id_llave = 243;
DELETE FROM saap.usuario WHERE id_usuario = 290;