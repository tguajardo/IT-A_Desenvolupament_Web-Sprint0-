<?php

require_once "Contact.php";

$contacts = [];

do {
    echo "\n----MENU----\n";
    echo "1. Crear contacto nuevo.\n";
    echo "2. Buscar contacto.\n";
    echo "3. Ver todos los contactos.\n";
    echo "0. Salir del programa.\n";

    $option = (int) trim(readline("Opción: "));

    switch ($option) {
        case 1:
            addContact($contacts);
            break;

        case 2:
            findContact($contacts);
            break;

        case 3:
            printContacts($contacts);
            break;

        case 0:
            echo "Saliendo del programa.\n";
            break;

        default:
            echo "Opción inválida.\n";
    }
} while ($option !== 0);

function addContact(array &$contacts): void
{
    $contacts[] = Contact::createContact();
}

function findContact(array $contacts): void
{
    echo "Datos contacto a buscar\n";

    $name = trim(readline("Nombre: "));
    $lastNames = trim(readline("Apellidos: "));

    $found = false;

    foreach ($contacts as $contact) {

        if (
            str_contains(
                strtolower($contact->getName()),
                strtolower($name)
            )
            &&
            str_contains(
                strtolower($contact->getLastNames()),
                strtolower($lastNames)
            )
        ) {
            echo "\nPersona encontrada.\n";
            echo $contact . "\n";

            $found = true;
        }
    }

    if (!$found) {
        echo "\nPersona NO encontrada.\n";
    }
}

function printContacts(array $contacts): void
{
    echo "\nLista de contactos:\n";

    foreach ($contacts as $index => $contact) {
        echo "\nContacto " . ($index + 1) . "\n";
        echo $contact . "\n";
    }
}
