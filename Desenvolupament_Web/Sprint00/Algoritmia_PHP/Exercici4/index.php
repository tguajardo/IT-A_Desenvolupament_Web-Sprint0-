<?php
function isHeterograma(string $word): bool
{
    $word = strtolower($word);
    for ($i = 0; $i < strlen($word); $i++) {
        $current = $word[$i];
        for ($j = $i + 1; $j < strlen($word); $j++) {
            if ($word[$j] === $current) {
                return false;
            }
        }
    }
    return true;
}

function isIsograma(string $word): bool
{
    $word = strtolower($word);
    if (isHeterograma($word)) {
        return true;
    }
    $expectedCount = -1;
    for ($i = 0; $i < strlen($word); $i++) {
        $current = $word[$i];
        $alreadyProcessed = false;

        for ($j = 0; $j < $i; $j++) {
            if ($word[$j] === $current) {
                $alreadyProcessed = true;
                break;
            }
        }

        if ($alreadyProcessed) {
            continue;
        }

        $count = 0;

        for ($j = 0; $j < strlen($word); $j++) {
            if ($word[$j] === $current) {
                $count++;
            }
        }

        if ($expectedCount === -1) {
            $expectedCount = $count;
        } elseif ($count !== $expectedCount) {
            return false;
        }
    }
    return true;
}

function getWord(): string
{
    echo "Introduce la palabra a revisar: ";

    return trim(fgets(STDIN));
}


$word = getWord();

echo "Es heterograma: ";
echo isHeterograma($word) ? "true" : "false";
echo PHP_EOL;

echo "Es isograma: ";
echo isIsograma($word) ? "true" : "false";
echo PHP_EOL;
