package edu.iu.habahram.GumballMachine.model;

public class SoldState implements IState{
    GumballMachine2 gumballMachine;

    public SoldState(GumballMachine2 gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        gumballMachine.changeTheStateTo(GumballMachineState.HAS_QUARTER);
        String message = "You inserted a quarter";
        boolean succeeded = true;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        String message = "You ejected a quarter";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult turnCrank() {
        String message = "You turned the crank..";
        dispense();
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult dispense() {
        String message = "Yay you got a gumball";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult refill(int count) {
        String message = "You refilled the gumball machine";
        boolean succeeded = true;
        gumballMachine.setCount(count);
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public String getTheName() {
        return GumballMachineState.GUMBALL_SOLD.name();
    }
}
