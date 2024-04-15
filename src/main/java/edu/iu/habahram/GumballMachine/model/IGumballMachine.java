package edu.iu.habahram.GumballMachine.model;

public interface IGumballMachine {
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter();
    TransitionResult turnCrank();
    TransitionResult handleAllStates();
    void changeTheStateTo(GumballMachineState name);
    Integer getCount();
    String getTheStateName();
    TransitionResult refill(int id, int count);

    void releaseBall();
}
