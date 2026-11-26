<?php
include_once('Board.php');

class Game
{

    const WINNER_PLAYS =
    [
        [[0, 0], [0, 1], [0, 2]],
        [[1, 0], [1, 1], [1, 2]],
        [[2, 0], [2, 1], [2, 2]],

        [[0, 0], [1, 0], [2, 0]],
        [[0, 1], [1, 1], [2, 1]],
        [[0, 2], [1, 2], [2, 2]],

        [[0, 0], [1, 1], [2, 2]],
        [[0, 2], [1, 1], [2, 0]]
    ];

    // Para cambiar las Fichas, cambiar los TOKEN
    const PLAYER_TOKEN = "O";
    const CPU_TOKEN = "X";

    private Board $board;
    private string $winner;

    public function __construct()
    {
        $this->board = new Board();
        $this->winner = "";
    }

    public function print(): void
    {
        $this->board->render();
    }

    public function playerMove(int $row, int $column): void
    {
        if ($this->board->isEmptyCell($row, $column)) {
            $this->board->setCell($row, $column, self::PLAYER_TOKEN);
            //BUG: Cpu always move. Even when human has winner play done. How can we fix that?
            //Despues de cada jugada de player y CPU se debe revisar si existe una jugada ganadora o un empate
            if ($this->hasWinner()) {
                return; // el jugador ganó, la CPU no juega
            }

            if ($this->hasDraw()) {
                return; // empate, la CPU tampoco juega
            }
            $this->cpuMove();

            // comprobar si la CPU ganó después de mover
            $this->hasWinner();
        }
    }

    public function isFinished(): bool
    {
        //TODO: How can we make game finish and show result message?
        //return false, el juego nunca termina, ahora acaba si 1) hay un ganador o 2) hay un empate

        return $this->hasWinner() || $this->hasDraw();
    }

    public function showResult(): string
    {
        $output = match ($this->winner) {
            self::PLAYER_TOKEN => "Player won!",
            self::CPU_TOKEN => "CPU won!",
            '' => "Draw!"
        };
        return $output;
    }

    private function hasWinner(): bool
    {
        $gameOver = false;
        for ($i = 0; $i < count(self::WINNER_PLAYS) && !$gameOver; ++$i) {
            $gameOver = $this->checkWinnerPlayResult(self::WINNER_PLAYS[$i]);
        }
        return $gameOver;
    }

    private function hasDraw(): bool
    {
        return $this->board->isFull();
    }

    private function cpuMove()
    {
        $emptyPositions = $this->board->getEmptyPositions();
        if (count($emptyPositions) > 0) {
            $emptyPosition = $emptyPositions[array_rand($emptyPositions)];
            $this->board->setCell($emptyPosition[0], $emptyPosition[1], self::CPU_TOKEN);
        }
    }

    private function checkWinnerPlayResult(array $winnerPlay): bool
    {
        [$a, $b, $c] = $winnerPlay;
        if (
            $_SESSION['board'][$a[0]][$a[1]] !== '' &&
            $_SESSION['board'][$a[0]][$a[1]] === $_SESSION['board'][$b[0]][$b[1]] &&
            $_SESSION['board'][$b[0]][$b[1]] === $_SESSION['board'][$c[0]][$c[1]]
        ) {
            $this->winner = $_SESSION['board'][$a[0]][$a[1]];
            return true;
        }
        return false;
    }
}
