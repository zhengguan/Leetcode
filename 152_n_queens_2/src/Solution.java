// Leetcode 51,152
// N-Queens I, II 
// https://leetcode.com/problems/n-queens-ii/

// 51
// The n-queens puzzle is the problem of placing n queens on an n¡Án chessboard 
// such that no two queens attack each other.
// Given an integer n, return all distinct solutions to the n-queens puzzle.
// Each solution contains a distinct board configuration of the n-queens'
// placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

// 152
// Follow up for N-Queens problem.
// Now, instead outputting board configurations, return the total number of 
// distinct solutions.
import tester.Tester;

import java.util.*;

public class Solution {
    List<List<String>> results;
    
    // RETURNS: all of the possible configurations of the N-Queens problem, 
    public List<List<String>> solveNQueens(int n) {
        results = new ArrayList<List<String>>();
        Position[] prevPositions = new Position[n];
        for(int i = 0; i < n; i++) {
            prevPositions[i] = new Position(i, 0);
        }
        addConfigQueensStartFromRow(0, n, prevPositions);
        return results;
    }

    // RETURNS: the number of legal N-Queens configurations
    public int totalNQueens2(int n) {
        int[] config = new int[n];
        return totalQueensStartFromRow2(0, n, config);
    }
    
    public int totalQueensStartFromRow2(int start, int n, int[] config) {
        if(start == n) {
            return 1;
        }
        else {
            int count = 0;
            for(int i = 0; i < n; i++) {
                config[start] = i;
                if(isLegalPosition2(i, start, config)) {
                    count = count + totalQueensStartFromRow2(start + 1, n, config);
                }
            }
            return count;
        }
    }
    
    public int totalQueensStartFromRowIter(int start, int n, int[] config) {
        int[] indexs = new int[n];
        int[] counts = new int[n];
        int curr = 0;
        
        return -1;
    }
    
    // GIVEN: the coordinate of a position and a int[] represents a 
    // configuration
    // WHERE: (i, config[i]) represents a position of the configuration,
    // the first row positions of the configuration are legal
    // RETURNS: true iff adding (row, col) to the configuration will not 
    // cause the configuration to be illegal
    public boolean isLegalPosition2(int col, int row, int[] config) {
        for(int i = 0; i < row; i++) {
            if(willAttack2(i, config[i], row, col)) {
                return false;
            }
        }
        return true;
    }
    
    // GIVEN: the coordinates of tow positions
    // RETURNS: true iff the queens at the given positions could attack each 
    // other
    public boolean willAttack2(int x1, int y1, int x2, int y2) {
        return
        x1 == x2
        || y1 == y2
        || Math.abs(x1 - x2) == Math.abs(y1 - y2);
    }
    
    
    
    // RETURNS: the number of legal N-Queens configurations
    public int totalNQueens(int n) {
        Position[] prevPositions = new Position[n];
        return totalQueensStartFromRow(0, n, prevPositions);
    }   
    
    
    // EFFECT: add legal N-Queens configuration with n queens on 
    // the first n rows at the first n Positions of prevPositions to results
    public void addConfigQueensStartFromRow(int n, int N, 
            Position[] prevPositions) {
        if(n == N) {
            addConfig(prevPositions);            
        }
        else {
            //@assert row < rowNum;          
            Position p = prevPositions[n];
            for(int i = 0; i < N; i++) {
                p.y = i;
                if(isLegalPosition(p, n, prevPositions)) {
                    addConfigQueensStartFromRow(n + 1, N, prevPositions);
                }
            }
        }
    }    
    
    // RETURNS: the number of possible board configurations with n queens on 
    // the first n rows at the first n Positions of prevPositions. 
    public int totalQueensStartFromRow(int n, int rowNum, 
            Position[] prevPositions) {
        if(n == rowNum) {
            return 1;            
        }
        else {
            //@assert row < rowNum;          
            prevPositions[n] = new Position(n, 0);
            Position p = prevPositions[n];
            int count = 0;
            for(int i = 0; i < rowNum; i++) {
                p.y = i;
                if(isLegalPosition(p, n, prevPositions)) {
                    count = count + totalQueensStartFromRow(n + 1, rowNum, prevPositions);
                }
            }
            return count;
        }
    }
    
    // RETURNS: true iff queen on the given Position p of the chessboard could 
    // not attack queens on the first n positions of given array.   
    public boolean isLegalPosition(Position p, int n, Position[] prevPositions) {
        for(int i = 0; i < n; i++) {
            if(willAttack(p, prevPositions[i])) {
                return false;
            }
        }
        return true;
    }
    
    // RETURNS: true iff the given two queens could attack each other
    public boolean willAttack(Position p, Position q) {
        return
        (p.x == q.x)
        || (p.y == q.y)
        || (Math.abs(p.x - q.x) == Math.abs(p.y - q.y));
    }
    
    // EFFECT: add the legal configuration represented by ps to results
    void addConfig(Position[] ps) {
        int len = ps.length;
        List<String> config = new ArrayList<String>();
        for(int i = 0; i < len; i++) {
            Position p = ps[i];
            char[] line = new char[len];
            for(int j = 0; j < len; j++) {
                if(j == p.y) {
                    line[j] = 'Q';
                }
                else {
                    line[j] = '.';
                }
            }
            config.add(new String(line));
        }
        results.add(config);
    }
}

class Position {
    int x;
    int y;
    
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


class SolutionExamples {
    Solution s = new Solution();
    Position p0 = new Position(0, 3);
    Position p1 = new Position(1, 1);
    Position p2 = new Position(2, 2);
    Position p3 = new Position(2, 1);
    Position p4 = new Position(3, 5);
    
    
    // tests for method Solution.willAttack()
    boolean testWillAttack(Tester t) {
        return
        t.checkExpect(s.willAttack(p1, p2)) &&
        t.checkExpect(s.willAttack(p1, p2)) &&
        t.checkExpect(s.willAttack(p1, p3)) &&
        t.checkExpect(!s.willAttack(p1, p4));
    }


    
    // tests for method Solution.isLegalPosition()
    boolean testIsLegalPosition(Tester t) {
        Position[] ps = new Position[5];
        ps[0] = p0;
        ps[1] = p1;
        return
        t.checkExpect(s.isLegalPosition(p4, 2, ps)) &&
        t.checkExpect(!s.isLegalPosition(p2, 2, ps));
    }
    
    // tests for method Solution.totalQueensStartFromRow()
    boolean testTotalQueensStartFromRow(Tester t) {
        return
//        t.checkExpect(s.totalQueensStartFromRow(0, 1, new Position[1]), 1) &&
        t.checkExpect(s.totalQueensStartFromRow(0, 2, new Position[2]), 0) &&
        t.checkExpect(s.totalQueensStartFromRow(0, 3, new Position[3]), 0) &&
        t.checkExpect(s.totalQueensStartFromRow(0, 4, new Position[4]), 2);
    }
    
    boolean testSolveNQueens(Tester t) {
//        System.out.println(s.solveNQueens(4));
        return true;
    }
    
    boolean testTotalNQueens(Tester t) {
        return
        t.checkExpect(s.totalNQueens2(1) == s.totalNQueens(1)) &&
        t.checkExpect(s.totalNQueens2(8) == s.totalNQueens(8));
    }
}