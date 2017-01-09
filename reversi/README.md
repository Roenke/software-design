Тут описание некоторых классов в диаграмме.

Понятные перечисления - игрок и направление на игровом поле
```
enum Player {
  WHITE, BLACK
}

enum Direction {
  UP, DOWN, LEFT, RIGHT,
  UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
}
```


Клетки игрового поля
```
interface Cell(x, y) - позиция на игровом поле
FreeCell: Cell - представляет свободную игровую позицию
FilledCell(Player player): Cell - представляет занятую игровую позицию с указанием игрока
```

GameBoard - stateless class представляющий состояние игрового поля, который просто хранит матрицу игры и осуществует 
    несколько примитивных операций доступа и позволяет получить следующее состояние игрового поля. Почему stateless - можно 
    узнать историю ходов
```
  GameBoard makeTurn(int i, int j, Player player); // Сделать ход вернуть новое состояние игрового поля
  int whitesCount(); количество белых фише
  int blacksCount(); количество черных фишек
  getCell(int i, int j); получить клетку поля
  SmartGameBoard smart(); получить умное игровое поле для текущего состояния
```
SmartGameBoard(GameBoard) - класс-обертка для GameBoard, предоставляет удобные методы для разработки стратегий
```
  SmartGameBoard virtualTurn(int i, int j, Player player); - позволяет получить новое игровое поле после совершения хода 
    (но без самого хода). Это может быть удобно при разработке стратегий 
  List<FreeCell> getFreeCells(); - все свободные ячейки
  List<FreeCell> getAllAvailableTurnCells(Player player); - все ячейки куда можно сделать ход
  List<FilledCell> getPlayerPositions(Player player); - получить список фишек по игроку
  Map<Direction, List<FilledCell>> getTurnScore(FreeCell, Player); - результат хода (направление -> список захваченных фишек соперника)
```
GameLog // readonly история ходов. Стратегии могут опираться на предыдущие ходы
```
  GameBoard getLastTurn()
  List<GameBoard> getAllTurns() - получить все элементы истории
  List<GameBoard> getLastTurns(int count) - получить несколько последних ходов
  
MutableGameLog: GameLog
  append(GameBoard board); - добавить состояние в лог
```

GamingStrategy(Player) - Базовый интерфейс для всех игровых стратегий
```
  Cell turn(SmartGameBoard board, GameLog log) // Сделать ход

RandomStrategy(Player, long seed = 0) implements GamingStrategy - стратегия со случайными ходами
TreeSearchStrategy(Player player, int maxDeep) implements GamingStrategy - стратерия, которая ободит дерево, чтобы выбрать лучший ход
CornerHeuristics(Player player) implements GamingStrategy - стратегия, которая считает угловые клетки предпочтительными для хода
BorderHeuristicsStrategy(Player player) implements GamingStrategy - стратегия, для которой боковые клетки предпочтительны для хода
```
GameRound(GamingStrategy whites, GamingStrategy blacks) - игровой раунд
```
  boolean isCompeleted() - раунд окончен
  Player getWinner() - получить сторону, которая победила
  GameLog getLog() - получить текущие лог игры
  GameBoard getBoard() - получить текущее состояние игры
  int getWhitesScore() - получить количество белых фишек
  int getBlacksScore() - получить количество черных фишек
  void reset() - начать сначала
  ```
