<?php
/*Exercici 3
Donat un
array de
temperatures,digues:

Temperatura màxima.
Temperatura mínima.
Temperatura mitjana.
Dies amb
més de 20graus.
        Exemple :[17,19,18,21,20,17,19,20,21,24,26,25,20,18,19,17,22,20,25,28,25]

Temperatura màxima = 28.
Temperatura mínima = 17.
Temperatura mitjana = 21.
Dies amb
més de 20graus =9.*/

const TEMPERATURES = [
        17,
        19,
        18,
        21,
        20,
        17,
        19,
        20,
        21,
        24,
        26,
        25,
        20,
        18,
        19,
        17,
        22,
        20,
        25,
        28,
        25
];

function getMax(array $tempArray): int
{
        $maxValue = $tempArray[0];

        foreach ($tempArray as $temp) {
                if ($temp > $maxValue) {
                        $maxValue = $temp;
                }
        }

        return $maxValue;
}

function getMin(array $tempArray): int
{
        $minValue = $tempArray[0];

        foreach ($tempArray as $temp) {
                if ($temp < $minValue) {
                        $minValue = $temp;
                }
        }

        return $minValue;
}

function getAvg(array $tempArray): float
{
        $sum = 0;

        foreach ($tempArray as $temp) {
                $sum += $temp;
        }

        return $sum / count($tempArray);
}

function over20(array $tempArray): int
{
        $dayCount = 0;

        foreach ($tempArray as $temp) {
                if ($temp > 20) {
                        $dayCount++;
                }
        }

        return $dayCount;
}

function getArray(): array
{
        $tempArray = [];

        echo "Ingresa temperaturas (para terminar escribe x):\n";

        while (true) {
                $input = trim(fgets(STDIN));

                if ($input === "x") {
                        break;
                }

                if (filter_var($input, FILTER_VALIDATE_INT) !== false) {
                        $tempArray[] = (int)$input;
                } else {
                        echo "Valor inválido. Introduce un entero o x.\n";
                }
        }

        return $tempArray;
}

$tempArray = getArray();

if (empty($tempArray)) {
        echo "No se ingresaron valores, se usarán los datos por defecto.\n";

        $tempArray = TEMPERATURES;
} else {
        echo "Calculando con valores ingresados.\n";
}

$maxTemp = getMax($tempArray);
$minTemp = getMin($tempArray);
$avgTemp = getAvg($tempArray);
$over20Temp = over20($tempArray);

echo "\nResultados\n";
echo "Temperatura máxima: $maxTemp\n";
echo "Temperatura mínima: $minTemp\n";
echo "Temperatura media: " . round($avgTemp, 2) . "\n";
echo "Días con más de 20 grados: $over20Temp\n";
