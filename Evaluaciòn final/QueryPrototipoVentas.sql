
Create database DBPrototiposGuatemala;

use DBPrototiposGuatemala;

Create Table Cliente(
	codigoCliente int auto_increment not null,
    nombreCliente varchar(100) not null,
    apellidoCliente varchar(100) not null,
    domicilioCliente varchar(100) not null,
	fechaNacimiento date not null,
    telefonoCliente int not null,
    correoCliente varchar(40),
    primary key PK_codigoCliente (codigoCliente)    
);

Create Table FormaPago(
	codigoFormaPago int auto_increment not null,
    nombrePago varchar(40) not null,
    otrosDetalles varchar(45),
    primary key PK_codigoFormaPago (codigoFormaPago)
);

Create Table Categoria(
	codigoCategoria int not null auto_increment,
    nombreCategoria varchar(45) not null,
    descripcionCategoria varchar(150) not null,
    primary key PK_codigoCategoria (codigoCategoria)
);

Create table Producto(
	codigoProducto int auto_increment not null,
    nombreProducto varchar(60) not null,
    precioProducto decimal(10,2) not null,
    stock int not null,
    codigoCategoria int not null,
    primary key PK_codigoProducto (codigoProducto),
    Constraint FK_Producto_Categoria foreign key (codigoCategoria) references Categoria(codigoCategoria)
);

Create Table Factura(
	numeroFactura int not null,
    fechaFactura date not null,
    codigoCliente int not null,
    codigoFormaPago int not null,
    primary key PK_numeroFactura (numeroFactura),
    Constraint FK_Factura_Cliente foreign key (codigoCliente)
		references Cliente(codigoCliente),
    Constraint FK_Factura_FormaPago foreign key (codigoFormaPago)
		references FormaPago(codigoFormaPago)
);


Create table DetalleFactura(
	codigoDetalleFactura int not null auto_increment,
    numeroFactura int not null,
    cantidad int not null,
    precio decimal(10,2) not null,
    codigoProducto int not null,
    primary key PK_codigoDetalleFactura_numeroFactura (codigoDetalleFactura, numeroFactura),
    Constraint FK_DetalleFactura_Producto foreign key (codigoProducto)
		references Producto(codigoProducto),
	Constraint FK_DetalleFactura_Factura foreign key (numeroFactura) 
		references Factura(numeroFactura)
);





--        Procedimientos Almacenados -------------------

/*-- -----------------CLIENTE------------------------------
-----------------------------------------------------------*/

-- -----------------LISTAR-----------------------
Delimiter $$
	Create procedure sp_ListarClientes()
		Begin
			Select 
				C.codigoCliente, 
                C.nombreCliente, 
                C.apellidoCliente, 
                C.domicilioCliente, 
                C.fechaNacimiento, 
                C.telefonoCliente, 
                C.correoCliente
                from Cliente C;
        End$$
Delimiter ;

-- -----------------AGREGAR-----------------------

Delimiter $$
	Create procedure sp_AgregarCliente(in nombreCliente varchar(100), in apellidoCliente varchar(100), 
		in domicilioCliente varchar(100), in fechaNacimiento date, in telefonoCliente int, 
        in correoCliente varchar(40))
        Begin
			Insert into Cliente (nombreCliente, apellidoCliente, domicilioCliente, fechaNacimiento, telefonoCliente, correoCliente) 
				values (nombreCliente, apellidoCliente, domicilioCliente, fechaNacimiento, telefonoCliente, correoCliente);
        End$$
Delimiter ;

-- -----------------ELIMINAR-----------------------

Delimiter $$
	Create procedure sp_EliminarCliente(in codCliente int)
		Begin
			Delete from Cliente
				where codigoCliente = codCliente;
        End$$
Delimiter ;

-- -----------------BUSCAR-----------------------

Delimiter $$
	Create procedure sp_BuscarCliente(in codCliente int)
		Begin
			Select 
				C.codigoCliente, 
                C.nombreCliente, 
                C.apellidoCliente, 
                C.domicilioCliente, 
                C.fechaNacimiento, 
                C.telefonoCliente, 
                C.correoCliente
                from Cliente C where codigoCliente = codCliente;
        End$$
Delimiter ;

-- -----------------EDITAR-----------------------

Delimiter $$
	Create procedure sp_EditarCliente(in codCliente int,in nomCliente varchar(100), 
		in apeCliente varchar(100), in domCliente varchar(100), 
        in fecNacimiento date, in telCliente int, in coCliente varchar(40))
        Begin
			Update Cliente
				set
					nombreCliente = nomCliente, 
                    apellidoCliente = apeCliente, 
                    domicilioCliente = domCliente, 
                    fechaNacimiento = fecNacimiento, 
                    telefonoCliente = telCliente, 
                    correoCliente = coCliente
                    where codigoCliente = codCliente;
        End$$
Delimiter ;

/*-- -----------------FORMA PAGO---------------------------
-----------------------------------------------------------*/
-- -----------------LISTAR-----------------------
Delimiter $$
	Create procedure sp_ListarFormasPagos()
		Begin
			Select 
				FP.codigoFormaPago, 
                FP.nombrePago, 
                FP.otrosDetalles              
                from FormaPago FP;
        End$$
Delimiter ;

-- -----------------AGREGAR-----------------------

Delimiter $$
	Create procedure sp_AgregarFormaPago(in nombrePago varchar(40), in otrosDetalles varchar(45))
		Begin
			Insert into FormaPago (nombrePago, otrosDetalles)
				values (nombrePago, otrosDetalles);
        End$$
Delimiter ;

-- -----------------ELIMINAR-----------------------

Delimiter $$
	Create procedure sp_EliminarFormaPago(in codFPago int)
		Begin
			Delete from FormaPago
				where codigoFormaPago = codFPago;
        End$$
Delimiter ;

-- -----------------BUSCAR-----------------------

Delimiter $$
	Create procedure sp_BuscarFormaPago(in codFPago int)
		Begin
			Select 
				FP.codigoFormaPago, 
                FP.nombrePago, 
                FP.otrosDetalles                
                from FormaPago FP
				where codigoFormaPago = codFPago;
        End$$
Delimiter ;



-- -----------------EDITAR-----------------------

Delimiter $$
	Create procedure sp_EditarFormaPago(in codFPago int, in nomPago varchar(40), in oDetalles varchar(45))
		Begin
			Update FormaPago
				set
					nombrePago = nomPago,
                    otrosDetalles = oDetalles
                    where codigoFormaPago = codFPago;
        End$$
Delimiter ;

/*-- -----------------FACTURA---------------------------
-----------------------------------------------------------*/
-- -----------------LISTAR-----------------------

Delimiter $$
	Create procedure sp_ListarFacturas()
		Begin
			Select 
				F.numeroFactura, 
                F.fechaFactura, 
                F.codigoCliente,
                F.codigoFormaPago
                from Factura F;
        End$$
Delimiter ;

-- -----------------AGREGAR-----------------------

Delimiter $$
	Create procedure sp_AgregarFactura(in numeroFactura int, in fechaFactura date, in codigoCliente int, in codigoFormaPago int)
		Begin
			Insert into Factura (numeroFactura, fechaFactura, codigoCliente, codigoFormaPago)
				values (numeroFactura, fechaFactura, codigoCliente, codigoFormaPago);
        End$$
Delimiter ;

-- -----------------ELIMINAR-----------------------

Delimiter $$
	Create procedure sp_EliminarFactura(in numFact int)
		Begin
			Delete from Factura
				where numeroFactura = numFact;
        End$$
Delimiter ;

-- -----------------BUSCAR-----------------------

Delimiter $$
	Create procedure sp_BuscarFactura(in numFact int)
		Begin
			Select 
				F.numeroFactura, 
                F.fechaFactura, 
                F.codigoCliente,
                F.codigoFormaPago
                from Factura F
				where numeroFactura = numFact;
        End$$
Delimiter ;

call sp_BuscarFactura(1001);
-- -----------------EDITAR-----------------------

Delimiter $$
	Create procedure sp_EditarFactura(in numFactura int, in fFactura date)
		Begin
			Update Factura
				set
					fechaFactura = fFactura                    
                    where numeroFactura = numFactura;
        End$$
Delimiter ;


/*-- -----------------CATEGORIA------------------------------
-----------------------------------------------------------*/

-- -----------------LISTAR-----------------------
Delimiter $$
	Create procedure sp_ListarCategorias()
		Begin
			Select 
				C.codigoCategoria, 
                C.nombreCategoria, 
                C.descripcionCategoria                
                from Categoria C;
        End$$
Delimiter ;

-- -----------------AGREGAR-----------------------

Delimiter $$
	Create procedure sp_AgregarCategoria(in nombreCategoria varchar(45), in descripcionCategoria varchar(150))
        Begin
			Insert into Categoria (nombreCategoria, descripcionCategoria) 
				values (nombreCategoria, descripcionCategoria);
        End$$
Delimiter ;

-- -----------------ELIMINAR-----------------------

Delimiter $$
	Create procedure sp_EliminarCategoria(in codCategoria int)
		Begin
			Delete from Categoria
				where codigoCategoria = codCategoria;
        End$$
Delimiter ;

-- -----------------BUSCAR-----------------------

Delimiter $$
	Create procedure sp_BuscarCategoria(in codCategoria int)
		Begin
			Select 
				C.codigoCategoria, 
                C.nombreCategoria, 
                C.descripcionCategoria
                from Categoria C where codigoCategoria = codCategoria;
        End$$
Delimiter ;

-- -----------------EDITAR-----------------------

Delimiter $$
	Create procedure sp_EditarCategoria(in codCategoria int,in nomCategoria varchar(45), 
		in descCategoria varchar(150))
        Begin
			Update Categorias
				set
					nombreCategoria = nomCategoria, 
                    descripcionCategoria = descCategoria
                    where codigoCategoria = codCategoria;
        End$$
Delimiter ;


/*-- -----------------PRODUCTO------------------------------
-----------------------------------------------------------*/

-- -----------------LISTAR-----------------------
Delimiter $$
	Create procedure sp_ListarProductos()
		Begin
			Select 
				P.codigoProducto, 
                P.nombreProducto, 
                P.precioProducto,
                P.stock,
                P.codigoCategoria
                from Producto P;
        End$$
Delimiter ;

-- -----------------AGREGAR-----------------------

Delimiter $$
	Create procedure sp_AgregarProducto(in nombreProducto varchar(60), in precioProducto decimal(10,2), in stock int,
		in codigoCategoria int)
        Begin
			Insert into Producto (nombreProducto, precioProducto, stock, codigoCategoria) 
				values (nombreProducto, precioProducto, stock, codigoCategoria);
        End$$
Delimiter ;

-- -----------------ELIMINAR-----------------------

Delimiter $$
	Create procedure sp_EliminarProducto(in codProducto int)
		Begin
			Delete from Producto
				where codigoProducto = codProducto;
        End$$
Delimiter ;

-- -----------------BUSCAR-----------------------

Delimiter $$
	Create procedure sp_BuscarProducto(in codProducto int)
		Begin
			Select 
				P.codigoProducto, 
                P.nombreProducto, 
                P.precioProducto,
                P.stock,
                P.codigoCategoria                
                from Producto P where codigoProducto = codProducto;
        End$$
Delimiter ;

-- -----------------EDITAR-----------------------

Delimiter $$
	Create procedure sp_EditarProducto(in codProducto int,in nomProducto varchar(60), 
		in pProducto decimal(10,2), in sto int)
        Begin
			Update Producto
				set
					nombreProducto = nomProducto,
                    precioProducto = pProducto,
                    stock = sto
                    where codigoProducto = codProducto;
        End$$
Delimiter ;






/*-- -----------------DETALLE FACTURA------------------------
-----------------------------------------------------------*/

-- -----------------LISTAR-----------------------
Delimiter $$
	Create procedure sp_ListarDetallesFacturas()
		Begin
			Select 
				DF.codigoDetalleFactura,
                DF.numeroFactura,
                DF.cantidad,
                DF.precio,
                DF.codigoProducto
                from DetalleFactura DF;
        End$$
Delimiter ;

-- -----------------AGREGAR-----------------------

Delimiter $$
	Create procedure sp_AgregarDetalleFactura(in numeroFactura int, in cantidad int, in precio decimal(10,2), in codigoProducto int)
        Begin
			Insert into DetalleFactura (numeroFactura, cantidad, precio, codigoProducto) 
				values (numeroFactura, cantidad, precio, codigoProducto);
        End$$
Delimiter ;

-- -----------------ELIMINAR-----------------------

Delimiter $$
	Create procedure sp_EliminarDetalleFactura(in codDetalle int)
		Begin
			Delete from DetalleFactura
				where codigoDetalleFactura = codDetalle;
        End$$
Delimiter ;

-- -----------------BUSCAR-----------------------

Delimiter $$
	Create procedure sp_BuscarDetalleFactura(in codDetalle int)
		Begin
			Select 
				DF.codigoDetalleFactura,
                DF.numeroFactura,
                DF.cantidad,
                DF.precio,
                DF.codigoProducto
                from DetalleFactura DF where codigoDetalleFactura = codDetalle;
        End$$
Delimiter ;

-- -----------------EDITAR-----------------------

Delimiter $$
	Create procedure sp_EditarDetalleFactura(in codDeFac int,in can int, in pre decimal(10,2))
        Begin
			Update DetalleFactura
				set
					cantidad = can,
                    precio = pre
                    where codigoDetalleFactura = codDeFac;
        End$$
Delimiter ;

call sp_AgregarCliente('Pedro Manuel','Armas','Mixco','1982-08-17',58747859,'pedroarmas@kinal.org.gt');
call sp_AgregarCliente('Jorge','López','Ciudad, Guatemala','1950-10-01',11111111,'jlopez@gmail.com');
call sp_ListarClientes();

call sp_AgregarFormaPago('Efectivo','');
call sp_AgregarFormaPago('Tarjeta de Crédito','Se aplica el 7% sobre la compra');
call sp_ListarFormasPagos();




call sp_AgregarFactura(1001,'2020-09-21',1,1);

call sp_ListarFacturas();
call sp_ListarDetallesFacturas();

call sp_AgregarCategoria('Alacena', 'Granos, pastas legumbres y más');
call sp_AgregarCategoria('Carnes, Pescados y mariscos', 'Productos cárnicos');
call sp_AgregarCategoria('Frutas y Verduras', 'Productos de temporada');
call sp_AgregarCategoria('Alimentos Congelados y Preparados', 'Productos en congelación frescos y sin conservantes');


call sp_AgregarProducto('Atol 13 Cereales Haba', 12.75, 25, 1);
call sp_AgregarProducto('Albay frijo negro 400 gr', 8.50, 13, 1);
call sp_AgregarProducto('Fajutas de pollo Granel Libra', 16.90, 53, 2);
call sp_AgregarProducto('Banano Criollo Libra, 4 unidades', 2.75, 10, 3);
call sp_AgregarProducto('Alitas Piolindo Barbaco de Pollo 390 gr', 26.50, 114, 4);


call sp_ListarProductos();

select * from Factura F inner join Cliente C on F.codigoCliente = C.codigoCliente
	inner join FormaPago FP on F.codigoFormaPago = FP.codigoFormaPago where numeroFactura = 1001;
    
    


-- Producto, DetalleFactura, Categoria 

select * from Producto P inner Join DetalleFactura DF on P.codigoProducto = DF.codigoProducto
	inner join Categoria CA on CA.codigoCategoria = P.codigoCategoria where numeroFactura = 1002;


call sp_ListarProductos();


call sp_ListarClientes(); 

call sp_AgregarDetalleFactura(1001,4,12.75,1);

select * from Producto P inner Join DetalleFactura DF on P.codigoProducto = DF.codigoProducto
	inner join  Categoria CA on CA.codigoCategoria = P.codigoCategoria where numeroFactura = 1001;


Create table Usuario(
	codigoUsuario int not null auto_increment,
    nombreUsuario varchar(100) not null,
    apellidoUsuario varchar(100) not null,
    usuarioLogin varchar(50) not null,
    contrasena varchar(50) not null,    
    primary key PK_codigoUsuario (codigoUsuario)
);

-- ---------------------------Procedimiento Almacenado Agregar Usuario ----------------------------
Delimiter $$
	create procedure sp_AgregarUsuario (in codigoUsuario int, in nombreUsuario varchar(100), apellidoUsuario varchar(100), 
    usuarioLogin varchar(50), contrasena varchar(50))
		begin insert into Usuario (codigoUsuario, nombreUsuario, apellidoUsuario, usuarioLogin, contrasena)
			values (codigoUsuario, nombreUsuario, apellidoUsuario, usuarioLogin, contrasena);
end $$
Delimiter ;

Delimiter $$
Create procedure sp_ListarUsuarios()
	Begin
		Select
			U.codigoUsuario, 
            U.nombreUsuario, 
            U.apellidoUsuario, 
            U.usuarioLogin, 
            U.contrasena
            from Usuario U;
    End$$
Delimiter ;

call sp_AgregarUsuario('Pedro','Armas','parmas','12345');

Create table Login(
	usuarioMaster varchar(50) not null,
    passwordLogin varchar(50) not null,
    primary key PK_usuarioMaster (usuarioMaster)
);

call sp_ListarUsuarios();

call sp_AgregarUsuario('Manuel','Chang','mchang','1111');


select * from cliente;