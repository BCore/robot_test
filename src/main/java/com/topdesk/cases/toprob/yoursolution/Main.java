package com.topdesk.cases.toprob.yoursolution;


import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.Grid;
import com.topdesk.cases.toprob.Instruction;
import com.topdesk.cases.toprob.helper.GridFactory;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        Grid grid = GridFactory.create(
                ".rBA.",
                "...o.",
                ".ook.",
                "..o..");

//        Grid grid = GridFactory.create(
//                ".rBA......",
//                "...oW.X.Y.",
//                "...U.ookN.",
//                ".Do....J..",
//                ".C......Z.",
//                ".E...I....",
//                ".F........",
//                ".V.GH...K.",
//                ".......LM.",
//                "...TSRQPO.");

        Coordinate rob = grid.getRoom();
        Set<Coordinate> holes = grid.getHoles();

        List<Instruction> solution = new YourSolution().solve(grid, 0);

        System.out.println(solution);

    }
}
