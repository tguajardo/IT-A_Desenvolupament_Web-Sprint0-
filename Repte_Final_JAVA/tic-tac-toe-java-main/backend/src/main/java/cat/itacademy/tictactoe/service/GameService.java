package cat.itacademy.tictactoe.service;

import cat.itacademy.tictactoe.model.GameResponse;
import cat.itacademy.tictactoe.model.MoveRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    // Constants del tauler.
    // El frontend espera aquests valors numèrics:
    // 0 = casella buida, 1 = jugador, 2 = màquina.
    private static final int EMPTY = 0;
    private static final int PLAYER = 1;
    private static final int MACHINE = 2;

    // Constants d'estat de la partida.
    // Aquests textos es retornen al frontend dins de la response.
    private static final String IN_PROGRESS = "IN_PROGRESS";
    private static final String PLAYER_WON = "PLAYER_WON";
    private static final String MACHINE_WON = "MACHINE_WON";
    private static final String DRAW = "DRAW";
    private static final String INVALID_MOVE = "INVALID_MOVE";

    // DONE: descriu que representen aquests atributs i què vol dir que siguin "private"
    //Variables correspondientes al inicio de un juego.
    //Private indicar que no son accesibles desde fuera de la clase
    //Se crea tablero (matriz bidireccional de ints) y se declara vacio, usando metodo createEmptyBoard().
    private int[][] board = createEmptyBoard();
    //Se declara el atributo actualStatus, que almacena el estado actual de la partida. Inicialmente toma
    // el valor IN_PROGRESS, indicando que el juego está en curso.
    private String actualStatus = IN_PROGRESS;
    //Se declara un objeto de la clase Random que permite generar valores aleatorios. Se utilizará para que la máquina
    //seleccione movimientos de forma aleatoria. El modificador final indica que la referencia no podrá cambiar una vez
    // creada.
    private final Random random = new Random();

    // DONE: descriu que fa aquest mètode línea a línea i què vol dir que sigui "public"
    //Metodo publico, usable desde fuera de la clase, en este caso la clase GameController, devuelve un objeto
    // GameResponse. Y es usado por el metodo GameController.playMove().
    public GameResponse startGame() {
        //Mensaje de inicio de juego
        System.out.println("Service: starting a new game");
        //Se declara vacia, llamando al metodo createEmptyBoard().
        board = createEmptyBoard();
        //Estado inicial que permite jugar, ver playMove.
        actualStatus = IN_PROGRESS;
        //Crea y devuelve un nuevo objeto GameResponse que contiene el tablero actual, el estado de la partida y el
        //mensaje "Game started". Este objeto será enviado al componente que llamó al metodo para informar del inicio
        //del juego.
        return new GameResponse(board, actualStatus, "Game started");
    }

    // DONE: descriu que fa aquest mètode, perquè és públic i des d'on s'utilitza
    // Es publico ya que se usa fuera de la clase, GameController.playMove()
    // El flujo del programa ocurre en este metodo, cuando el jugador realiza una jugada.
    public GameResponse playMove(MoveRequest moveRequest) {
        //GameController.playMove() recibe el http request de movimiento, pero lo pasa aca para realizar el movimiento.
        System.out.println("Service: processing player move row=" + moveRequest.getRow() + ", col=" + moveRequest.getCol());

        // DONE: descriu que fa aquest bloc de codi línea a línea
        //Se asegura que 1)sea una partida en juego y 2)el movimiento solicitado es valido.
        if (!actualStatus.equals(IN_PROGRESS) || !isValidMove(moveRequest)) {
            System.out.println("Service: invalid move");
            return new GameResponse(board, INVALID_MOVE, "Invalid move");
        }

        // DONE: que fa aquesta línea?
        // Se cambia el valor de la posicion del tablero de EMPTY(0) a PLAYER(1)
        board[moveRequest.getRow()][moveRequest.getCol()] = PLAYER;

        // DONE: Què comprova aquest bloc de codi, com i que passa?
        // Verifica por el metodo hasWinner() si existen tres (1) en raya, para declarar ganador al PLAYER.
        // if true, cambia el estado del juego a PLAYER_WON y emite el mensaje correspondiente.
        if (hasWinner(PLAYER)) {
            actualStatus = PLAYER_WON;

            return new GameResponse(board, actualStatus, "Player won");
        }

        // DONE: Què comprova aquest bloc de codi, com i que passa?
        // Verifica si quedan movimientos posibles por isDraw(), si no quedan se declara un empate.
        if (isDraw()) {
            actualStatus = DRAW;

            return new GameResponse(board, actualStatus, "Draw");
        }

        // DONE: Què és això i que fa?
        // Ejecuta el metodo que realiza el movimiento de la maquina, se usa this ya que no se declara static.
        this.playMachineMove();

        // DONE: Què comprova aquest bloc de codi, com i que passa?
        // Verifica por el metodo hasWinner() si existen tres (2) en raya, para declarar ganador a MACHINE.
        // if true, cambia el estado del juego a MACHINE_WON y emite el mensaje correspondiente.
        if (hasWinner(MACHINE)) {
            actualStatus = MACHINE_WON;

            return new GameResponse(board, actualStatus, "Machine won");
        }

        // DONE: Què comprova aquest bloc de codi, com i que passa?
        // Verifica si quedan movimientos posibles por isDraw(), si no quedan se declara un empate.
        if (isDraw()) {
            actualStatus = DRAW;

            return new GameResponse(board, actualStatus, "Draw");
        }


        return new GameResponse(board, actualStatus, "Turn processed");
    }

    // DONE: Descriu que fa aquest mètode, quin tipus de dada retorna,
    // Revisa si el movimiento es valido. Debe hacerse dentro del tablero y no debe haberse jugado con anterioridad.
    // Acá se encontraba el bug ya que tenia solo return false, por tanto todas las jugadas eran invalidas.
    private boolean isValidMove(MoveRequest moveRequest) {
        return isInsideBoard(moveRequest.getRow(), moveRequest.getCol()) &&
                board[moveRequest.getRow()][moveRequest.getCol()] == EMPTY;
    }

    // DONE: Descriu que fa aquest mètode, quin tipus de dada retorna,
    // Revisa que la jugada declarada se ejecute dentro de los parametros del tablero de 3x3, devuelve un bool
    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row <= 2 && col >= 0 && col <= 2;
    }

    // DONE: Intenta entendre que fa aquest mètode en general i, si pots, línea a línea
    // Realiza una jugada para la maquina de forma aleatoria, dentro de los espacios disponibles.
    private void playMachineMove() {
        // Crea un ArrayList de movimientos (espacios en 0) disponibles.
        List<MoveRequest> availableMoves = new ArrayList<>();

        // Recorre filas del tablero
        for (int row = 0; row < board.length; row++) {
            // Recorre las columnas dentro de cada fila
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == EMPTY) {
                    // Si el movimiento del tablero esta EMPTY (La fila,columna es 0)
                    // Se agrega al ArrayList de movimientos disponibles.
                    availableMoves.add(new MoveRequest(row, col));
                }
            }
        }

        // Se escoge dentro del ArrayList de forma aleatoria un movimiento, se declara el movimiento.
        MoveRequest machineMove = availableMoves.get(random.nextInt(availableMoves.size()));
        System.out.println("Service: machine plays row=" + machineMove.getRow() + ", col=" + machineMove.getCol());
        // Se anota en el board
        board[machineMove.getRow()][machineMove.getCol()] = MACHINE;
    }

    // DONE: Què fa aquest mètode y que representa el seu paràmetre?
    // DONE: Es reutilitza? si es així, desde on?
    // Es un metodo que revisa a partir de uno de los ints definidos para PLAYER y MACHINE si existe un ganador, se
    // ocupa cuando juega PLAYER o MACHINE.
    // Para esto debe revisar:
    private boolean hasWinner(int mark) {
        // Si existen tres en linea para las filas, o
        if (hasWinningRow(mark)) return true;
        // Tres en linea para las columnas, o
        if (hasWinningColumn(mark)) return true;
        // Tres en linea para las dos diagonales
        if (hasWinningDiagonal(mark)) return true;
        // Devuelve true (existe un ganador) si se cumple alguna de las formas de tres en linea, se podria
        // simplificar con OR.
		return false;
    }

    // Para revisar Filas se pasa por las tres viendo que valor tiene cada elemento de la columna.
    private boolean hasWinningRow(int mark) {
        for (int[] ints : board) {
            if (ints[0] == mark && ints[1] == mark && ints[2] == mark) {
                return true;
            }
        }

        return false;
    }

    // En este caso se fija la columna y se pasa por las filas.
    private boolean hasWinningColumn(int mark) {
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == mark && board[1][col] == mark && board[2][col] == mark) {
                return true;
            }
        }

        return false;
    }

    // En este caso se ven directamente las dos diagonales.
    private boolean hasWinningDiagonal(int mark) {
        return board[0][0] == mark && board[1][1] == mark && board[2][2] == mark
                || board[0][2] == mark && board[1][1] == mark && board[2][0] == mark;
    }

    // DONE: que fa aquest mètode?
    // Revisa si existe un empate, es decir que ya no existen movimientos disponibles, la condición de ganador se
    // revisa antes de esta.
    private boolean isDraw() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    // DONE: que fa aquest mètode i desde on s'utilitza?
    // Crea un tablero vacio, es decir que todas las posiciones son EMPTY (0)
    // Se usa cuando se declaran las variables iniciales y cuando se inicia un nuevo juego. (33 y 49)
    private int[][] createEmptyBoard() {
        return new int[][]{
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        };
    }

}
