package edu.iu.habahram.GumballMachine.model;

public class SoldOutState implements IState{
    GumballMachine2 gumballMachine;
    public SoldOutState(GumballMachine2 gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        String message = "You inserted a quarter";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult ejectQuarter() {
        String message = "You ejected the quarter";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult turnCrank() {
        String message = "You turned the crank, but nothing is happening";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult dispense() {
        String message = "Oh no, it's sold out of gumballs";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult refill(int count) {
        //change the state to no quarter
        gumballMachine.state = new NoQuarterState(gumballMachine);
        String message = "You refilled the gumball machine";
        boolean succeeded = true;
        gumballMachine.setCount(count);
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public String getTheName() {
        return GumballMachineState.OUT_OF_GUMBALLS.name();
    }
}
