CREATE OR REPLACE
PACKAGE BODY PKG_USUARIO AS

  PROCEDURE SP_VALIDAR_ACCESO(
    P_CURSOR OUT SYS_REFCURSOR,
    p_USUARIO IN USUARIO.USUARIO%TYPE,
    p_CLAVE IN USUARIO.CLAVE%TYPE
  ) AS
  BEGIN
        OPEN
            P_CURSOR
        FOR
            SELECT
                ID_USUARIO,
                USUARIO,
                CLAVE,
                NOMBRE
            FROM
                USUARIO
            WHERE
                        USUARIO     =   P_USUARIO
                AND     CLAVE       =   P_CLAVE
                AND     ESTADO      =   '1';
                
  END SP_VALIDAR_ACCESO;

    PROCEDURE SP_LISTAR(
    P_CURSOR  OUT SYS_REFCURSOR,
    P_USUARIO IN  USUARIO.USUARIO%TYPE,
    P_NOMBRE  IN  USUARIO.NOMBRE%TYPE
  )AS
  BEGIN
    OPEN
            P_CURSOR
        FOR
            SELECT
                ID_USUARIO,
                USUARIO,
                CLAVE,
                NOMBRE
            FROM
                USUARIO
            WHERE
                        UPPER(USUARIO)     LIKE   '%'||UPPER(P_USUARIO)||'%'
                AND     UPPER(NOMBRE)      LIKE   '%'||UPPER(P_NOMBRE)||'%'
                AND     ESTADO              =     '1';
  END SP_LISTAR;
  
  PROCEDURE SP_INSERTAR(
    P_ID_USUARIO    OUT USUARIO.ID_USUARIO%TYPE,
    P_USUARIO       IN  USUARIO.USUARIO%TYPE,
    P_CLAVE         IN  USUARIO.CLAVE%TYPE,
    P_NOMBRE        IN  USUARIO.NOMBRE%TYPE
  )AS
  BEGIN
  
    -- Generando el ID
    SELECT 
        SEQ_USUARIO.NEXTVAL
    INTO
        P_ID_USUARIO
    FROM
        DUAL;
    -- Insercion
    INSERT INTO USUARIO
        (
            ID_USUARIO,
            USUARIO,
            CLAVE,
            NOMBRE
        )
    VALUES
        (
            P_ID_USUARIO,
            P_USUARIO,
            P_CLAVE,
            P_NOMBRE
        );
  END SP_INSERTAR;
  
  
END PKG_USUARIO;