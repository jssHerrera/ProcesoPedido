create or replace PACKAGE PKG_PEDIDO AS 

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
        
  );
  
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
        
  );

END PKG_PEDIDO;