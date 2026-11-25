<?php

function getArray(): array
{
    $returnArray = [];
    echo "Ingresa los elementos del array, para terminar introduce \"FIN\"\n";
    while (true) {
        $element = trim(fgets(STDIN));
        if ($element === "FIN") {
            echo "Array terminado.\n";
            break;
        }
        $returnArray[] = $element;
    }
    return $returnArray;
}

function moveArray(array $oldArray): array
{
    if (empty($oldArray)) {
        echo "El array esta vacio.\n";
        return $oldArray;
    }

    echo "Espacios a mover el array: ";
    $moveSpaces = trim(fgets(STDIN));

    $newArray = [];

    for ($i = 0; $i < count($oldArray); $i++) {
        $newArray[] = $oldArray[($i + $moveSpaces) % count($oldArray)];
    }
    return $newArray;
}

function showArray(array $array): void
{
    if (empty($array)) {
        echo "Array vacío.\n";
        return;
    }
    echo "Array: \n";
    //Alternativa print_r($array); or echo "[" echo implode(",", $array) echo"]"
    echo "[";
    for ($i = 0; $i < count($array); $i++) {
        echo $array[$i];
        if ($i < count($array) - 1) {
            echo ", ";
        }
    }
    echo "]\n";
    return;
}

function showMenu(string $title, array $options): int
{
    echo "----{$title}----\n";

    foreach ($options as $index => $option) {
        echo ($index + 1) . ". {$option}\n";
    }

    echo "0. Salir.\n";
    echo "Opcion: ";

    return (int) trim(fgets(STDIN));
}

$workArray = [];
$options = ["Crear Array", "Mover Array", "Ver Array"];
do {
    $option = showMenu("Menu Array", $options);
    switch ($option) {
        case 0:
            echo "Saliendo del programa\n";
            break;
        case 1:
            if (empty($workArray)) {
                $workArray = getArray();
            } else {
                echo "El array ya existe.\n";
            }
            break;
        case 2:
            $workArray = moveArray($workArray);
            break;
        case 3:
            showArray($workArray);
            break;

        default:
            echo "Opcion no valida\n";
    }
} while ($option !== 0);
