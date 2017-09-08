select *,ll.*,us.* from saap.cabecera_planilla cp inner join saap.llave ll on ( cp.id_llave=ll.id_llave ) inner join saap.usuario us on ( ll.id_usuario=us.id_usuario )
where 
--cp.observacion in ('0000000007139','0000000007140')
--and 
--us.id_usuario=316

cp.total>100

order  by cp.total