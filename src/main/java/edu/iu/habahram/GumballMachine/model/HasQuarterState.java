package edu.iu.habahram.GumballMachine.model;

public class HasQuarterState implements IState{
    GumballMachine2 gumballMachine2;

    public HasQuarterState (GumballMachine2 gumballMachine){
        this.gumballMachine2 = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        gumballMachine2.changeTheStateTo(GumballMachineState.HAS_QUARTER);
        String message = "You inserted a quarter";
        boolean succeeded = true;
        int count = gumballMachine2.getCount();
        return new TransitionResult(succeeded, message, gumballMachine2.getTheStateName(), count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        String message = "You ejected a quarter";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine2.getTheStateName(), gumballMachine2.getCount());
    }

    @Override
    public TransitionResult turnCrank() {
        String message = "You turned...something is happening...";
        boolean succeeded = true;
        dispense();
        return new TransitionResult(succeeded, message, gumballMachine2.getTheStateName(), gumballMachine2.getCount());
    }

    @Override
    public TransitionResult dispense() {
        String message = "You got a gumball! Yay!";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine2.getTheStateName(), gumballMachine2.getCount());
    }

    @Override
    public TransitionResult refill(int count) {
        String message = "You refilled the gumball machine";
        boolean succeeded = true;
        gumballMachine2.setCount(count);
        return new TransitionResult(succeeded, message, gumballMachine2.getTheStateName(), gumballMachine2.getCount());
    }

    @Override
    public String getTheName() {
        return GumballMachineState.HAS_QUARTER.name();
    }
}
