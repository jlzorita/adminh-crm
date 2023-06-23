/*    FORMAPAGO:    EFECTIVO(0), TRANSFERENCIA(1), BANCO(2);
      TIPO:         PROPIETARIO(0), PROVEEDOR(1)
*/

INSERT INTO cliente(nombre, nif, tipo, direccion, cp, municipio, provincia, forma_pago, iban, email, telefono, usuario)
VALUES ('José Luis Zorita Gutiérrez', '74115531L', 0, 'Carrer Bosquet, 23, 1-2', '08100', 'Mollet del Vallès',
        'Barcelona',0, 'ES3008100205014559877234', 'jzorita@uoc.edu','555263111', 'jzorita')
,('Liu Zhang', 'Y6123123L', 0, 'Av. Tres, 25', '08150', 'Parets del Vallès', 'Barcelona', 2, 'ES3011507312553451231239', 'lzhang@sucorreo.es', '573123744', 'lzhang23')
,('Rafael Bravo García', '', 0, 'Carrer Bosquet, 23, 1-1', '08100', 'Mollet del Vallès', 'Barcelona', 2, 'ES2545656564564581121002', '', '', 'rbravo')
,('Yolanda Diz Roca', '51105565N', 0, 'Carrer Bosquet, 23, 2-1', '08100', 'Mollet del Vallès', 'Barcelona', 1, '', '', '578123911', 'ydiz')
,('Jonathan Ramírez Pérez', '23100641L', 0, 'Carrer Bosquet, 23, 2-1', '08100', 'Mollet del Vallès', 'Barcelona', null, '', '', '569122209', 'jramirez')
,('Joana Martorell Baños', '63454552H', 0, 'Av. Plunx, 23, 2-3', '08007', 'Barcelona', 'Barcelona', 0, 'ES4012365590460546456122', 'jmartorel23@sucorreo.es', '565450234', 'jmartorell')
,('Josep Flaix Tres', '', 0, 'Av. Plunx, 23, 2-3', '08007', 'Barcelona', 'Barcelona', null, '', 'jflaix@sucorreo.es', '523455345', 'jflaix')
,('Magda Pérez Rios', '85123231S', 0, 'Carrer Bosquet, 23, 3-1', '08100', 'Mollet del Vallès', 'Barcelona', 0, 'ES0091231239065991232350', '', '845435123', 'mperez')
,('Yang Yang', '44260091Y', 0, 'Carrer Bosquet, 23, 3-1', '08100', 'Mollet del Vallès', 'Barcelona', null, '', '', '555123665', 'yyang')
,('María Montserat Liáñez Llop', '75353453N', 0, 'Carrer Bosquet, 23, 3-2', '08100', 'Mollet del Vallès', 'Barcelona', 0, 'ES7905679029304234789123', 'm.montse59@sucorreo.es', '412333323', 'mlianez')
,('Pau López Rius', '', 0, 'Carrer Bosquet, 23, Ático', '08100', 'Mollet del Vallès', 'Barcelona', 0, 'ES7001293905690900123903', '', '550343023', 'plopez')
,('Luz Energy SA', 'A53355477', 1, 'Av. Cuatro Cantos, 56 Edif. 1', '28080', 'Madrid', 'Madrid', null, '', 'info@luzenergy.com', '911236534', null)
,('Extintores Gava SL', 'B73455453', 1, 'c/ Cinc Agost, 11', '08850', 'Gavá', 'Barcelona', null, '', 'comercial@extintoresgava.cat', '577234345', null)
,('Seguros Tant', 'B67756456', 1, 'c/ Valencia, 8', '08020', 'Barcelona', 'Barcelona', null, '', 'asistencia@segurostant.es', '521363455', null)
,('Ascensores Vilanova SL', 'B61236733', 1, 'c/ Madrid, 11', '08800', 'Vilanova', 'Barcelona', null, '', 'info@ascensoresvilanova.es', '517456674', null)
,('Aguas BCN SL', 'B77434571', 1, 'c/ Valencia, 13', '08020', 'Barcelona', 'Barcelona', null, '', 'info@aguasbcn.es', '', null)
,('Ferreters Mollet SL', 'B53423411', 1, 'c/ Nou Mollet, 103, Local 2', '08100', 'Mollet del Vallès', 'Barcelona', null, '', 'contacte@ferretersmollet.cat', '588012305', null)
,('Servei Express SL', 'B12366743', 1, 'Carretera Badalona, 11', '08015', 'Barcelona', 'Barcelona', null, '', 'info@serveiexpress.es', '', null);

INSERT INTO mensaje(titulo, mensaje, fecham, leidom, respuesta, fechar, leidor, cliente_id, administrador, comunidad_id)
VALUES ('Estado contable', 'Hola, ¿Puedes enviarme por correo el estado contable que se present en la reunión? gracias', '2022/11/12 14:27',true, 'Enviado','2022/12/12 19:52',true,1,'ilopez',1);

INSERT INTO mensaje(administrador,fecham,leidom, titulo, mensaje, cliente_id, leidor, comunidad_id)
VALUES ('ilopez','2023/03/15 10:15',false,'Reparación terraza', 'Buenos días, ruego me indique qué día se realizará la reparación de la terraza',1, false, 1)
,('ilopez','2023/03/15 10:15',false,'orden SEPA', 'Hola. Necesito orden sepa para poder cambiar la domiciliación. gracias',2, false, 1);

INSERT INTO notificacion(mensaje, leido, cliente_id)
VALUES ('Se ha publicado un nuevo mensaje en el tablón',false,1);