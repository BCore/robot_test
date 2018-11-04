package com.topdesk.cases.toprob.yoursolution;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.Grid;
import com.topdesk.cases.toprob.Instruction;
import com.topdesk.cases.toprob.Solution;

public class YourSolution implements Solution {
	private List<Instruction> solution;
	private int time;
	private Set<Coordinate> invalid_positions;
	private List<Instruction> movement_order;

	@Override
	public List<Instruction> solve(Grid grid, int _time) {
		time = _time;
		solution = new ArrayList<Instruction>();
		invalid_positions = grid.getHoles();

		List<Coordinate> visited = new ArrayList<Coordinate>();
		List<Coordinate> valid_steps = new ArrayList<Coordinate>();

		Coordinate current = grid.getRoom();
		Coordinate goal = grid.getKitchen();

		movement_order = setMovementOrder(current, goal);
		System.out.println(movement_order);

		if(grid == null){
			throw new NullPointerException("Invalid grid size");
		}

		if(time < 0){
			throw new IllegalArgumentException("Invalid time");
		}

		while(true) {
			if (find_path(grid, current, goal, valid_steps, visited)) {
				break;
			}
		}
//		wait_in_kitchen(solution);
//
//		visited = new ArrayList<Coordinate>();
//		current = grid.getKitchen();
//		goal = grid.getRoom();
//		movement_order = setMovementOrder(current, goal);
//
//		while(true) {
//			if (find_path(grid, current, goal, valid_steps, visited)) {
//				break;
//			}
//		}

		System.out.println(time);

		//throw new UnsupportedOperationException("Nothing implemented yet");
		return solution;
	}

	private boolean find_path(Grid grid, Coordinate position, Coordinate goal, List<Coordinate> solution_steps, List<Coordinate> visited_positions){
		if (position.equals(goal)) { return true; }

		isInvalid(position, invalid_positions, solution_steps);
		solution_steps.add(position);
		for(Instruction movement: movement_order){
			Coordinate next_move = movement.execute(position);
			if(next_move.equals(goal)){
				position = next_move;
				solution_steps.add(position);
				visited_positions.add(position);
				solution.add(movement);
				time++;
				return true;
			}
		}

		for(Instruction movement: movement_order) {
			Coordinate next_move = movement.execute(position);
			if (!invalid_positions.contains(next_move) && onGrid(grid, next_move) && !visited_positions.contains(next_move)) {
				while (next_move.equals(grid.getBug(time + 1))) {
					position = Instruction.PAUSE.execute(position);
					solution.add(Instruction.PAUSE);
					time++;
					System.out.println(next_move);
				}
				visited_positions.add(position);
				position = next_move;
				solution.add(movement);
				time++;
				System.out.println(next_move);
				if (find_path(grid, position, goal, solution_steps, visited_positions)) {
					return true;
				}
			}
		}

//		if(Instruction.NORTH.execute(position).equals(goal)){
//			position = Instruction.NORTH.execute(position);
//			solution_steps.add(position);
//			visited_positions.add(position);
//			solution.add(Instruction.NORTH);
//			time++;
//			return true;
//		}
//		if(Instruction.EAST.execute(position).equals(goal)){
//			position = Instruction.EAST.execute(position);
//			solution_steps.add(position);
//			visited_positions.add(position);
//			solution.add(Instruction.EAST);
//			time++;
//			return true;
//		}
//		if(Instruction.SOUTH.execute(position).equals(goal)){
//			position = Instruction.SOUTH.execute(position);
//			solution_steps.add(position);
//			visited_positions.add(position);
//			solution.add(Instruction.SOUTH);
//			time++;
//			return true;
//		}
//		if(Instruction.WEST.execute(position).equals(goal)){
//			position = Instruction.WEST.execute(position);
//			solution_steps.add(position);
//			visited_positions.add(position);
//			solution.add(Instruction.WEST);
//			time++;
//			return true;
//		}


//		if (!invalid_positions.contains(Instruction.NORTH.execute(position)) && position.getY() > 0 && !visited_positions.contains(Instruction.NORTH.execute(position))) {
//			while(Instruction.NORTH.execute(position).equals(grid.getBug(time+1))){
//				position = Instruction.PAUSE.execute(position);
//				solution.add(Instruction.PAUSE);
//				time++;
//			}
//			visited_positions.add(position);
//			position = Instruction.NORTH.execute(position);
//			solution.add(Instruction.NORTH);
//			time++;
//			if (find_path(grid, position, goal, solution_steps, visited_positions)) {
//				return true;
//			}
//		}
//		if (!invalid_positions.contains(Instruction.EAST.execute(position)) && position.getX() < grid.getWidth()-1 && !visited_positions.contains(Instruction.EAST.execute(position))) {
//			while(Instruction.EAST.execute(position).equals(grid.getBug(time+1))){
//				position = Instruction.PAUSE.execute(position);
//				solution.add(Instruction.PAUSE);
//				time++;
//			}
//			visited_positions.add(position);
//			position = Instruction.EAST.execute(position);
//			solution.add(Instruction.EAST);
//			time++;
//			if (find_path(grid, position, goal, solution_steps, visited_positions)) {
//				return true;
//			}
//		}
//		if (!invalid_positions.contains(Instruction.SOUTH.execute(position)) && position.getY() < grid.getHeight()-1 && !visited_positions.contains(Instruction.SOUTH.execute(position))) {
//			while(Instruction.SOUTH.execute(position).equals(grid.getBug(time+1))){
//				position = Instruction.PAUSE.execute(position);
//				solution.add(Instruction.PAUSE);
//				time++;
//			}
//			visited_positions.add(position);
//			position = Instruction.SOUTH.execute(position);
//			solution.add(Instruction.SOUTH);
//			time++;
//			if (find_path(grid, position, goal, solution_steps, visited_positions)) {
//				return true;
//			}
//		}
//		if (!invalid_positions.contains(Instruction.WEST.execute(position)) && position.getX() > 0 && !visited_positions.contains(Instruction.WEST.execute(position))) {
//			while (Instruction.WEST.execute(position).equals(grid.getBug(time + 1))) {
//				position = Instruction.PAUSE.execute(position);
//				solution.add(Instruction.PAUSE);
//				time++;
//			}
//			visited_positions.add(position);
//			position = Instruction.WEST.execute(position);
//			solution.add(Instruction.WEST);
//			time++;
//			if (find_path(grid, position, goal, solution_steps, visited_positions)) {
//				return true;
//			}
//		}

		if(!position.equals(goal)) {
			invalid_positions.add(position);
		}
		return false;
	}

	private boolean onGrid(Grid grid, Coordinate position) {
		if (position.getX() > 0 && position.getY() < grid.getWidth()-1 && position.getY() > 0 && position.getY() < grid.getHeight()-1){
			return true;
		}
		return false;
	}

	private void isInvalid(Coordinate position, Set<Coordinate> invalid_positions, List<Coordinate> solution_steps) {
		if(invalid_positions.contains(position)){
			solution_steps.remove(position);
		}
	}

	private List<Instruction> wait_in_kitchen(List<Instruction> steps){
		for(int i=0; i<5; i++) {
			steps.add(Instruction.PAUSE);
			time++;
		}
		return steps;
	}

	private List<Instruction> backtrack(List<Instruction> instructions){
		List<Instruction> backtrack = new ArrayList<Instruction>();
		for(Instruction instruction: instructions){
			if(instruction == Instruction.NORTH){
				time++;
				backtrack.add(Instruction.SOUTH);
			} else if(instruction == Instruction.SOUTH){
				time++;
				backtrack.add(Instruction.NORTH);
			} else if(instruction == Instruction.EAST){
				time++;
				backtrack.add(Instruction.WEST);
			} else if(instruction == Instruction.WEST){
				time++;
				backtrack.add(Instruction.EAST);
			}
		}
		Collections.reverse(backtrack);
		return backtrack;
	}

	private List<Instruction> setMovementOrder(Coordinate start, Coordinate end){
		movement_order = new ArrayList<Instruction>();
		int dx = end.getX() - start.getX();
		int dy = end.getY() - start.getY();

		if (dx >= 0 && dy >= 0){
			movement_order.add(Instruction.EAST);
			movement_order.add(Instruction.SOUTH);
			movement_order.add(Instruction.WEST);
			movement_order.add(Instruction.NORTH);
		}
		if (dx >= 0 && dy < 0){
			movement_order.add(Instruction.NORTH);
			movement_order.add(Instruction.EAST);
			movement_order.add(Instruction.SOUTH);
			movement_order.add(Instruction.WEST);
		}
		if (dx < 0 && dy >= 0){
			movement_order.add(Instruction.SOUTH);
			movement_order.add(Instruction.WEST);
			movement_order.add(Instruction.NORTH);
			movement_order.add(Instruction.EAST);
		}
		if (dx < 0 && dy < 0){
			movement_order.add(Instruction.WEST);
			movement_order.add(Instruction.NORTH);
			movement_order.add(Instruction.EAST);
			movement_order.add(Instruction.SOUTH);
		}
		return movement_order;
	}

}
