<?php
/*L'usuari introdueix un número, si el número és positiu, el programa escriu tots els nombres de 0 a aquest nombre positiu, d'un en un. Si el número és negatiu, el programa escriu tots els nombres des de d'aquest nombre negatiu fins a 0. També d'un en un. */
function getNumber(): int
{
    while (TRUE) {
        echo "Input a number: ";
        // fgets(STDIN) es un string, revisar con isnumeric(number)
        // si es int se puede revisar con == ya que permite compara un string con un numeric (int)
        $number = (int) trim(fgets(STDIN));
        if ($number === 0) {
            echo "El numero debe ser distinto a 0\n";
            continue;
        }
        return $number;
    }
}
function printNumbers(int $number): void
{
    if ($number < 0) {
        for (; $number < 0; $number++) {
            echo "{$number} ";
        }
    } else {
        for ($i = 1; $i <= $number; $i++) {
            echo "{$i} ";
        }
    }
}

printNumbers(getNumber());
