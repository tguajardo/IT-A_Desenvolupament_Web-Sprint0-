<?php
require_once __DIR__.'/../src/Game.php';

session_start();
if(isset($_GET['reset']) && $_GET['reset'] == 1) {
    unset($_SESSION['board']);
} 
if(!isset($_SESSION['board'])) {
    $_SESSION['board'] = [
        ['', '', ''],
        ['', '', ''],
        ['', '', '']
    ];
}

$game = new Game();

if($_SERVER['REQUEST_METHOD'] === 'POST' 
   && isset($_POST['row']) 
   && isset($_POST['column'])
   && !$game->isFinished()) {
    $row = $_POST['row'];
    $column = $_POST['column'];
    $game->playerMove($row,$column);
}

?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tic Tac Toe!</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<?php $game->print(); ?>
<div id='game-result'>
    <?php if($game->isFinished()) { ?>
    <h1><?= $game->showResult()?></h1>
    <?php } ?>
    <a href="?reset=1">
        <button type="button" style="margin-top:15%;">Reiniciar partida</button>
    </a>
</div>
<script src="script.js"></script>
</body>
</html>