import exceptions.AlreadyPlayedCellException;
import exceptions.GameAlreadyFinishedException;
import exceptions.UnknownCellException;

import java.util.HashMap;
import java.util.Map;

class TicTacToe {

    private static final String PLAYER_1_SYMBOL = "X";
    private static final String PLAYER_2_SYMBOL = "O";
    private static final String[] ROWS = new String[]{"A", "B", "C"};
    private static final String[] COLUMNS = new String[]{"1", "2", "3"};
    private final Map<String, String> cells;
    private boolean isPlayer1Turn = true;

    public TicTacToe() {
        this.cells = new HashMap<>();
    }

    public void play(String cell) throws AlreadyPlayedCellException, UnknownCellException, GameAlreadyFinishedException {
        checkGamePlayability();
        checkCellPlayability(cell);

        cells.put(cell, isPlayer1Turn ? PLAYER_1_SYMBOL : PLAYER_2_SYMBOL);
        isPlayer1Turn = !isPlayer1Turn;
    }

    public String getCellPlayer(String cell) throws UnknownCellException {
        checkCellExistence(cell);
        return cells.get(cell);
    }

    public boolean isTheGameIsFinished() {
        return isPlayerWinner(PLAYER_1_SYMBOL) || isPlayerWinner(PLAYER_2_SYMBOL);
    }

    public boolean isPlayerWinner(String player) {
        try {
            for (String row : ROWS)
                if (hasPlayerAlignedTheRow(row, player)) return true;
            for (String column : COLUMNS)
                if (hasPlayerAlignedTheColumn(column, player)) return true;
            return false;
        } catch (UnknownCellException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getCurrentTurnPlayer() throws GameAlreadyFinishedException {
        if (isTheGameIsFinished()) throw new GameAlreadyFinishedException();
        return isPlayer1Turn ? PLAYER_1_SYMBOL : PLAYER_2_SYMBOL;
    }

    private void checkGamePlayability() throws GameAlreadyFinishedException {
        if (isTheGameIsFinished()) throw new GameAlreadyFinishedException();
    }

    private void checkCellPlayability(String cell) throws AlreadyPlayedCellException, UnknownCellException {
        checkCellExistence(cell);
        if (cells.containsKey(cell)) throw new AlreadyPlayedCellException();
    }

    private void checkCellExistence(String cell) throws UnknownCellException {
        if (cell == null) throw new UnknownCellException();
        if (cell.length() != 2) throw new UnknownCellException();
        final String row = cell.substring(0, 1);
        final String column = cell.substring(1, 2);
        if (!isRowExists(row)) throw new UnknownCellException();
        if (!isColumnExists(column)) throw new UnknownCellException();
    }

    private boolean isColumnExists(String column) {
        for (String c : COLUMNS)
            if (c.equals(column)) return true;
        return false;
    }

    private boolean isRowExists(String row) {
        for (String r : ROWS)
            if (r.equals(row)) return true;
        return false;
    }

    private boolean hasPlayerAlignedTheColumn(String column, String player) throws UnknownCellException {
        for (String row : ROWS)
            if (!player.equals(getCellPlayer(row + column))) return false;
        return true;
    }

    private boolean hasPlayerAlignedTheRow(String row, String player) throws UnknownCellException {
        for (String column : COLUMNS)
            if (!player.equals(getCellPlayer(row + column))) return false;
        return true;
    }
}