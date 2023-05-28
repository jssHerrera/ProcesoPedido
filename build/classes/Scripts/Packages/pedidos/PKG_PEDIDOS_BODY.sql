create or replace PACKAGE BODY PKG_PEDIDO AS
/**

**/
 PROCEDURE SP_INSERTAR_CABECERA(
        P_PEDIDO_CABECERA_ID    OUT PEDIDO_CABECERA.PEDIDO_CABECERA_ID%TYPE,
        P_CLIENTE_ID            IN  PEDIDO_CABECERA.CLIENTE_ID%TYPE,
        P_EMPLEADO_ID           IN  PEDIDO_CABECERA.EMPLEADO_ID%TYPE,
        P_GLOSA                 IN  PEDIDO_CABECERA.GLOSA%TYPE,
        P_SUB_TOTAL             IN  PEDIDO_CABECERA.SUB_TOTAL%TYPE,
        P_DESCUENTO             IN  PEDIDO_CABECERA.DESCUENTO%TYPE,
        P_IGV                   IN  PEDIDO_CABECERA.IGV%TYPE,
        P_TOTAL                 IN  PEDIDO_CABECERA.TOTAL%TYPE,
        
        --Auditoria
        P_AUD_IDUSUARIO         IN  PEDIDO_CABECERA.AUD_IDUSUARIO%TYPE,
        P_AUD_SESION            IN  PEDIDO_CABECERA.AUD_SESION%TYPE,
        P_AUD_IP                IN  PEDIDO_CABECERA.AUD_IP%TYPE
  )AS
  BEGIN
  
     -- Generando el ID
    SELECT 
        SEQ_PEDIDO_CABECERA.NEXTVAL
    INTO
        P_PEDIDO_CABECERA_ID
    FROM
        DUAL;
        
    -- Insercion
    INSERT INTO PEDIDO_CABECERA
        (
            PEDIDO_CABECERA_ID,
            CLIENTE_ID,
            EMPLEADO_ID,
            GLOSA,
            FECHA_REGISTRO,
            SUB_TOTAL,
            DESCUENTO,
            IGV,
            TOTAL,
            
            -- Estado
            ESTADO,
            
            --Auditoria
            AUD_TIPO,
            AUD_IDUSUARIO,
            AUD_SESION,
            AUD_IP,
            AUD_FECHA
        )
    VALUES
        (
            P_PEDIDO_CABECERA_ID,
            P_CLIENTE_ID,
            P_EMPLEADO_ID,
            P_GLOSA,
            sysdate,
            P_SUB_TOTAL,
            P_DESCUENTO,
            P_IGV,
            P_TOTAL,
            
            -- Estado
            '1',
            
            --Auditoria
            'I',
            P_AUD_IDUSUARIO,
            P_AUD_SESION,
            P_AUD_IP,
            sysdate
        );
  
  END SP_INSERTAR_CABECERA;
  
  
  PROCEDURE SP_INSERTAR_DETALLE(
        P_PEDIDO_DETALLE_ID    OUT PEDIDO_DETALLE.PEDIDO_DETALLE_ID%TYPE,
        P_PEDIDO_CABECERA_ID    OUT PEDIDO_DETALLE.PEDIDO_CABECERA_ID%TYPE,
        P_PRODUCTO_ID           IN  PEDIDO_DETALLE.PRODUCTO_ID%TYPE,
        P_CANTIDAD              IN  PEDIDO_DETALLE.CANTIDAD%TYPE,
        P_PRECIO                IN  PEDIDO_DETALLE.PRECIO%TYPE,
        P_SUB_TOTAL             IN  PEDIDO_DETALLE.SUB_TOTAL%TYPE,
        
        --Auditoria
        P_AUD_IDUSUARIO         IN  PEDIDO_DETALLE.AUD_IDUSUARIO%TYPE,
        P_AUD_SESION            IN  PEDIDO_DETALLE.AUD_SESION%TYPE,
        P_AUD_IP                IN  PEDIDO_DETALLE.AUD_IP%TYPE
        
  )AS
  BEGIN
  
       -- Generando el ID
    SELECT 
        SEQ_PEDIDO_DETALLE.NEXTVAL
    INTO
        P_PEDIDO_DETALLE_ID
    FROM
        DUAL;
        
    -- Insercion
    INSERT INTO PEDIDO_DETALLE
        (
            PEDIDO_DETALLE_ID,
            PEDIDO_CABECERA_ID,
            PRODUCTO_ID,
            CANTIDAD,
            PRECIO,
            SUB_TOTAL,
           
            -- Estado
            ESTADO,
            
            --Auditoria
            AUD_TIPO,
            AUD_IDUSUARIO,
            AUD_SESION,
            AUD_IP,
            AUD_FECHA
        )
    VALUES
        (
            P_PEDIDO_DETALLE_ID,
            P_PEDIDO_CABECERA_ID,
            P_PRODUCTO_ID,
            P_CANTIDAD,
            P_PRECIO,
            P_SUB_TOTAL,
            
            -- Estado
            '1',
            
            --Auditoria
            'I',
            P_AUD_IDUSUARIO,
            P_AUD_SESION,
            P_AUD_IP,
            sysdate
        );
  
  -- Actualizar Cantidad del producto
    UPDATE 
        PRODUCTO
    SET 
        STOCK= STOCK- P_CANTIDAD
    WHERE
        PRODUCTO_ID=P_PRODUCTO_ID;
        
   
  END SP_INSERTAR_DETALLE;
  
END PKG_PEDIDO;