/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpreter.analex.utils;

/**
 *
 * @author suarez
 */
public class Token {

    private int name;// si es CU, ACTION o ERROR
    private int attribute; // que tipo ya sea CU, ACTION o ERROR

    //constantes numericas para manejar el analex
    public static final int CU = 0;
    public static final int ACTION = 1;
    public static final int PARAMS = 2;
    public static final int END = 3;
    public static final int ERROR = 4;

    // ajustar de acuerdo a sus casos de uso con valores entre 100 a 199
    //Titulos de casos de uso en numero
    public static final int HELP = 100;
    public static final int USUARIO = 101;
    public static final int CONTENIDO = 102;
    public static final int ESTADISTICA = 103;
    public static final int NOTICIA = 104;
    public static final int PAGO = 105;
    public static final int PRESENTADOR = 106;
    public static final int PROYECTO = 107;
    public static final int SUSCRIPCION = 108;

    //ajustar de acuerdo a sus acciones con valores entre 200 a 299
    //Titulos de las acciones generales
    public static final int ADD = 200;
    public static final int DELETE = 201;
    public static final int MODIFY = 202;
    public static final int GET = 203;
    public static final int VERIFY = 204;
    public static final int CANCEL = 205;
    public static final int REPORT = 206;

    public static final int AGREGAR = 207;
    public static final int ELIMINAR = 208;
    public static final int MODIFICAR = 209;
    public static final int LISTAR = 210;
    public static final int GRAFICA = 211;
    public static final int VER = 212;

    public static final int ERROR_COMMAND = 300;
    public static final int ERROR_CHARACTER = 301;

    //constantes literales para realizar un efecto de impresión
    public static final String LEXEME_CU = "caso de uso";
    public static final String LEXEME_ACTION = "action";
    public static final String LEXEME_PARAMS = "params";
    public static final String LEXEME_END = "end";
    public static final String LEXEME_ERROR = "error";

    // ajustar de acuerdo a sus casos de uso con valores en string
    //Titulos de casos de uso con string
    public static final String LEXEME_HELP = "help";
    public static final String LEXEME_USUARIO = "usuario";
    public static final String LEXEME_CONTENIDO = "contenido";
    public static final String LEXEME_ESTADISTICA = "estadistica";
    public static final String LEXEME_NOTICIA = "noticia";
    public static final String LEXEME_PAGO = "pago";
    public static final String LEXEME_PRESENTADOR = "presentador";
    public static final String LEXEME_PROYECTO = "proyecto";
    public static final String LEXEME_SUSCRIPCION = "suscripcion";

    //ajustar de acuerdo a sus acciones con valores en string
    //Titulos de las acciones generales en string
    public static final String LEXEME_ADD = "add";
    public static final String LEXEME_DELETE = "delete";
    public static final String LEXEME_MODIFY = "modify";
    public static final String LEXEME_GET = "get";
    public static final String LEXEME_VERIFY = "verify";
    public static final String LEXEME_CANCEL = "cancel";
    public static final String LEXEME_REPORT = "report";

    public static final String LEXEME_AGREGAR = "agregar";
    public static final String LEXEME_ELIMINAR = "eliminar";
    public static final String LEXEME_MODIFICAR = "modificar";
    public static final String LEXEME_LISTAR = "listar";
    public static final String LEXEME_GRAFICA = "graficar";
    public static final String LEXEME_VER = "ver";

    public static final String LEXEME_ERROR_COMMAND = "UNKNOWN COMMAND";
    public static final String LEXEME_ERROR_CHARACTER = "UNKNOWN CHARACTER";

    /**
     * Constructor por default.
     */
    public Token() {

    }

    /**
     * Constructor parametrizado por el literal del token
     *
     * @param token
     */
    //No Tocar
    public Token(String token) {
        int id = findByLexeme(token);
        if (id != -1) {
            if (100 <= id && id < 200) {
                this.name = CU;
                this.attribute = id;
            } else if (200 <= id && id < 300) {
                this.name = ACTION;
                this.attribute = id;
            }
        } else {
            this.name = ERROR;
            this.attribute = ERROR_COMMAND;
            System.err.println("Class Token.Constructor dice: \n "
                    + " El lexema enviado al constructor no es reconocido como un token \n"
                    + "Lexema: " + token);
        }
    }

    /**
     * Constructor parametrizado 2.
     *
     * @param name
     */
    public Token(int name) {
        this.name = name;
    }

    /**
     * Constructor parametrizado 3.
     *
     * @param name
     * @param attribute
     */
    public Token(int name, int attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    // Setters y Getters
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        if (0 <= name && name <= 1) {
            return "< " + getStringToken(name) + " , " + getStringToken(attribute) + ">";
        } else if (name == 2) {
            return "< " + getStringToken(name) + " , " + attribute + ">";
        } else if (3 == name) {
            return "< " + getStringToken(name) + " , " + "_______ >";
        } else if (name == 4) {
            return "< " + getStringToken(name) + " , " + getStringToken(attribute) + ">";
        }
        return "< TOKEN , DESCONOCIDO>";
    }

    /**
     * Devuelve el valor literal del token enviado Si no lo encuentra retorna N:
     * token.
     *
     * @param token
     * @return
     */
    //ajustar de acuerdo a sus CU
    public String getStringToken(int token) {
        switch (token) {
            case CU:
                return LEXEME_CU;
            case ACTION:
                return LEXEME_ACTION;
            case PARAMS:
                return LEXEME_PARAMS;
            case END:
                return LEXEME_END;
            case ERROR:
                return LEXEME_ERROR;

            //CU
            case HELP:
                return LEXEME_HELP;
            case USUARIO:
                return LEXEME_USUARIO;
            case CONTENIDO:
                return LEXEME_CONTENIDO;
            case ESTADISTICA:
                return LEXEME_ESTADISTICA;
            case NOTICIA:
                return LEXEME_NOTICIA;
            case PAGO:
                return LEXEME_PAGO;
            case PRESENTADOR:
                return LEXEME_PRESENTADOR;
            case PROYECTO:
                return LEXEME_PROYECTO;
            case SUSCRIPCION:
                return LEXEME_SUSCRIPCION;

            //ACCION
            case ADD:
                return LEXEME_ADD;
            case DELETE:
                return LEXEME_DELETE;
            case MODIFY:
                return LEXEME_MODIFY;
            case GET:
                return LEXEME_GET;
            case VERIFY:
                return LEXEME_VERIFY;
            case CANCEL:
                return LEXEME_CANCEL;
            case REPORT:
                return LEXEME_REPORT;
            case AGREGAR:
                return LEXEME_AGREGAR;
            case ELIMINAR:
                return LEXEME_ELIMINAR;
            case MODIFICAR:
                return LEXEME_MODIFICAR;
            case LISTAR:
                return LEXEME_LISTAR;
            case GRAFICA:
                return LEXEME_GRAFICA;
            case VER:
                return LEXEME_VER;

            case ERROR_COMMAND:
                return LEXEME_ERROR_COMMAND;
            case ERROR_CHARACTER:
                return LEXEME_ERROR_CHARACTER;
            default:
                return "N: " + token;
        }
    }

    /**
     * Devuelve el valor numerico del lexema enviado Si no lo encuentra retorna
     * -1.
     *
     * @param lexeme
     * @return
     */
    //ajustar de acuerdo a sus CU
    private int findByLexeme(String lexeme) {
        switch (lexeme) {
            case LEXEME_CU:
                return CU;
            case LEXEME_ACTION:
                return ACTION;
            case LEXEME_PARAMS:
                return PARAMS;
            case LEXEME_END:
                return END;
            case LEXEME_ERROR:
                return ERROR;

            //CU 
            case LEXEME_HELP:
                return HELP;
            case LEXEME_USUARIO:
                return USUARIO;
            case LEXEME_CONTENIDO:
                return CONTENIDO;
            case LEXEME_NOTICIA:
                return NOTICIA;
            case LEXEME_ESTADISTICA:
                return ESTADISTICA;
            case LEXEME_PAGO:
                return PAGO;
            case LEXEME_PRESENTADOR:
                return PRESENTADOR;
            case LEXEME_PROYECTO:
                return PROYECTO;
            case LEXEME_SUSCRIPCION:
                return SUSCRIPCION;

            //ACTION    
            case LEXEME_ADD:
                return ADD;
            case LEXEME_DELETE:
                return DELETE;
            case LEXEME_MODIFY:
                return MODIFY;
            case LEXEME_GET:
                return GET;
            case LEXEME_VERIFY:
                return VERIFY;
            case LEXEME_CANCEL:
                return CANCEL;
            case LEXEME_REPORT:
                return REPORT;
            case LEXEME_AGREGAR:
                return AGREGAR;
            case LEXEME_ELIMINAR:
                return ELIMINAR;
            case LEXEME_MODIFICAR:
                return MODIFICAR;
            case LEXEME_LISTAR:
                return LISTAR;
            case LEXEME_GRAFICA:
                return GRAFICA;
            case LEXEME_VER:
                return VER;

            case LEXEME_ERROR_COMMAND:
                return ERROR_COMMAND;
            case LEXEME_ERROR_CHARACTER:
                return ERROR_CHARACTER;
            default:
                return -1;
        }
    }
}
