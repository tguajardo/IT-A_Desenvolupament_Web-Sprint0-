<?php

class Board
{
    const ROWS = 3;
    const COLUMNS = 3;

    public function render(): void
    {
        echo '<table id="board">';
        for ($row = 0; $row < self::ROWS; $row++) {
            $this->renderRow($row);
        }
        echo '</table>';
    }

    public function setCell(int $row, int $column, string $value): void {
        $_SESSION['board'][$row][$column] = $value;
    }

    public function isEmptyCell(int $row, int $column): bool {
        return $_SESSION['board'][$row][$column] === "";
    }

    public function getEmptyPositions(): array {
        $empty_positions = [];
        for($i = 0; $i < self::ROWS; ++$i) {
            for($j = 0; $j < self::COLUMNS; ++$j) {
                if($_SESSION['board'][$i][$j] == "") $empty_positions[] = [$i,$j];
            }
        }
        return $empty_positions;
    }

    public function isFull(): bool {
        for($i = 0; $i < self::COLUMNS; ++$i) {
            for($j = 0; $j < self::ROWS; ++$j) {
                if($_SESSION['board'][$i][$j] === "") return false;                 
            }
        }
        return true;
    }

    private function renderRow(int $row): void {
        echo '<tr>';
        for ($column = 0; $column < self::COLUMNS; $column++) {
            echo '<td class="board-cell" data-row="'.$row.'" data-column="'.$column.'">'.$_SESSION['board'][$row][$column].'</td>';
        }
        echo '</tr>';
    }
    
    

 
}