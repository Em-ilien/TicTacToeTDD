import exceptions.AlreadyPlayedCellException;
import exceptions.GameAlreadyFinishedException;
import exceptions.UnknownCellException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {
    TicTacToe ticTacToe;

    @BeforeEach
    void setUp() {
        ticTacToe = new TicTacToe();
    }

    @Test
    void playACellShouldWork() throws Exception {
        ticTacToe.play("A1");
    }

    @Test
    void playACellShouldAssignItToX() throws Exception {
        ticTacToe.play("A1");
        assertEquals("X", ticTacToe.getCellPlayer("A1"));
    }

    @Test
    void playASecondCellShouldAssignItToO() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("A2");
        assertEquals("O", ticTacToe.getCellPlayer("A2"));
    }

    @Test
    void playTwoCellsShouldAssignThemCorrectly() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("A2");
        assertEquals("X", ticTacToe.getCellPlayer("A1"));
        assertEquals("O", ticTacToe.getCellPlayer("A2"));
    }

    @Test
    void replayingACellShouldThrowException() throws Exception {
        ticTacToe.play("A1");
        assertThrows(AlreadyPlayedCellException.class, () -> ticTacToe.play("A1"));
    }

    @Test
    void getNotPlayedCellShouldReturnNull() throws Exception {
        assertNull(ticTacToe.getCellPlayer("A1"));
    }

    @Test
    void getUnknownCellShouldThrowException() {
        assertThrows(UnknownCellException.class, () -> ticTacToe.getCellPlayer("A9"));
    }

    @Test
    void getCellWithOneCharShouldThrowException() {
        assertThrows(UnknownCellException.class, () -> ticTacToe.getCellPlayer("A"));
    }

    @Test
    void getCellWithThreeCharsShouldThrowException() {
        assertThrows(UnknownCellException.class, () -> ticTacToe.getCellPlayer("A12"));
    }

    @Test
    void getCellWithNullShouldThrowException() {
        assertThrows(UnknownCellException.class, () -> ticTacToe.getCellPlayer(null));
    }

    @Test
    void playACellWithNullShouldThrowException() {
        assertThrows(UnknownCellException.class, () -> ticTacToe.play(null));
    }

    @Test
    void playACellWithOneCharShouldThrowException() {
        assertThrows(UnknownCellException.class, () -> ticTacToe.play("A"));
    }

    @Test
    void playACellWhichDoesNotExistShouldThrowException() {
        assertThrows(UnknownCellException.class, () -> ticTacToe.play("A9"));
    }

    @Test
    void alignTheFirstRowShouldReturnTrue() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("B1");
        ticTacToe.play("A2");
        ticTacToe.play("B2");
        ticTacToe.play("A3");
        assertTrue(ticTacToe.isPlayerWinner("X"));
    }

    @Test
    void checkIfPlayer1IsWinnerWhileHeIsNotShouldReturnFalse() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("B1");
        ticTacToe.play("A2");
        ticTacToe.play("B2");
        assertFalse(ticTacToe.isPlayerWinner("X"));
    }

    @Test
    void alignTheSecondRowShouldReturnTrue() throws Exception {
        ticTacToe.play("B1");
        ticTacToe.play("A1");
        ticTacToe.play("B2");
        ticTacToe.play("A2");
        ticTacToe.play("B3");
        assertTrue(ticTacToe.isPlayerWinner("X"));
    }

    @Test
    void alignTheFirstColumnShouldReturnTrue() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("A2");
        ticTacToe.play("B1");
        ticTacToe.play("A3");
        ticTacToe.play("C1");
        assertTrue(ticTacToe.isPlayerWinner("X"));
    }

    @Test
    void playWhilePlayer1IsWinnerShouldThrowException() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("B1");
        ticTacToe.play("A2");
        ticTacToe.play("B2");
        ticTacToe.play("A3");
        assertThrows(GameAlreadyFinishedException.class, () -> ticTacToe.play("B3"));
    }

    @Test
    void playWhilePlayer2IsWinnerShouldThrowException() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("B1");
        ticTacToe.play("A2");
        ticTacToe.play("B2");
        ticTacToe.play("C1");
        ticTacToe.play("B3");
        assertThrows(GameAlreadyFinishedException.class, () -> ticTacToe.play("C2"));
    }

    @Test
    void theFirstPlayerShouldBeX() throws Exception {
        assertEquals("X", ticTacToe.getCurrentTurnPlayer());
    }

    @Test
    void theSecondPlayerShouldBeO() throws Exception {
        ticTacToe.play("A1");
        assertEquals("O", ticTacToe.getCurrentTurnPlayer());
    }

    @Test
    void theFirstPlayerShouldBeXAfterTwoTurns() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("A2");
        assertEquals("X", ticTacToe.getCurrentTurnPlayer());
    }

    @Test
    void theGameShouldBeFinishedWhenTheFirstPlayerWins() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("B1");
        ticTacToe.play("A2");
        ticTacToe.play("B2");
        ticTacToe.play("A3");
        assertTrue(ticTacToe.isTheGameIsFinished());
    }

    @Test
    void theCurrentPlayerTurnMethodShouldThrowExceptionAfterTheGameIsFinished() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("B1");
        ticTacToe.play("A2");
        ticTacToe.play("B2");
        ticTacToe.play("A3");
        assertThrows(GameAlreadyFinishedException.class, () -> ticTacToe.getCurrentTurnPlayer());
    }

    @Test
    void alignTheLeftToRightDiagonalShouldEndTheGame() throws Exception {
        ticTacToe.play("A1");
        ticTacToe.play("B1");
        ticTacToe.play("B2");
        ticTacToe.play("A2");
        ticTacToe.play("C3");
        assertTrue(ticTacToe.isTheGameIsFinished());
        assertTrue(ticTacToe.isPlayerWinner("X"));
    }

    @Test
    void alignTheRightToLeftDiagonalShouldEndTheGame() throws Exception {
        ticTacToe.play("C3");
        ticTacToe.play("A3");
        ticTacToe.play("B1");
        ticTacToe.play("B2");
        ticTacToe.play("A2");
        ticTacToe.play("C1");
        assertTrue(ticTacToe.isTheGameIsFinished());
        assertTrue(ticTacToe.isPlayerWinner("O"));
    }
}