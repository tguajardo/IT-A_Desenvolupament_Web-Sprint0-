<?php
/* Determinar si una contrasenya:

» Té almenys 8 caràcters.
» Conté una majúscula.
» Conté un número.
Al finalitzar mostrar si la contrasenya és correcte o si no ho és, mostrar quines condicions no compleix. */
const MIN_PASSWORD_LENGTH = 8;
function hasValidLength(string $pwd): bool
{
    return strlen($pwd) >= MIN_PASSWORD_LENGTH;
}

function hasUppercase(string $pwd): bool
{
    for ($i = 0; $i < strlen($pwd); $i++) {
        if (ctype_upper($pwd[$i])) {
            return true;
        }
    }
    return false;
}

function hasDigit(string $pwd): bool
{
    for ($i = 0; $i < strlen($pwd); $i++) {
        if (ctype_digit($pwd[$i])) {
            return true;
        }
    }
    return false;
}

function getPwd(): string
{
    while (true) {
        echo "Input a password: ";
        $pwd = trim(fgets(STDIN));
        $badPwd = false;
        if (!hasValidLength($pwd)) {
            $badPwd = true;
            echo "El password no tiene el largo indicado.\nMinimo 8 caracteres.\n";
        }

        if (!hasUppercase($pwd)) {
            $badPwd = true;
            echo "El password no tiene mayuscula.\nAl menos una.\n";
        }

        if (!hasDigit($pwd)) {
            $badPwd = true;
            echo "El password no tiene digito.\nAl menos uno.\n";
        }
        if (!$badPwd) {
            echo "El password ingresado es correcto.\n";
            return $pwd;
        }
    }
}

$pwd = getPwd();
echo "Password = {$pwd}";
