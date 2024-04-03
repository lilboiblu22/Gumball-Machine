package edu.iu.habahram.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    final String SOLD_OUT = GumballMachineState.OUT_OF_GUMBALLS.name();
    final String NO_QUARTER = GumballMachineState.NO_QUARTER.name();
    final String HAS_QUARTER = GumballMachineState.HAS_QUARTER.name();
    final String SOLD = GumballMachineState.GUMBALL_SOLD.name();
    private String id;
    String state = SOLD_OUT;
    int count = 0;

    public GumballMachine(String id, String state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }

    @Override
    public TransitionResult insertQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "You can't insert another quarter";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            state = HAS_QUARTER;
            message = "You inserted a quarter";
            succeeded = true;
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You can't insert a quarter, the machine is sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "Please wait, we're already giving you a gumball";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            state = NO_QUARTER;
            message = "Quarter returned";
        }
        else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "You haven't inserted a quarter";
        }
        else if (state.equalsIgnoreCase(SOLD)) {
            message = "Sorry, you already turned the crank";
        }
        else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You can't eject, you haven't inserted a quarter yet";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult turnCrank() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(SOLD)) {
            message = "Turning twice doesn't get you another gumball!";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "You turned but there's no quarter";
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You turned, but there are no gumballs";
        } else if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "You turned...";
            state = SOLD;
            succeeded = true;
        }
        return new TransitionResult(succeeded, message, state, count);
    }
    public TransitionResult dispense() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(SOLD)) {
            message = "A gumball comes rolling out the slot";
            count = count - 1;
            if (count == 0) {
                message = "Oops, out of gumballs!";
                state = SOLD_OUT;
            } else {
                state = NO_QUARTER;
            }
            succeeded = true;
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "You need to pay first";
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "No gumball dispensed";
        } else if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "No gumball dispensed";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public void changeTheStateTo(GumballMachineState name) {
        this.state = name.name();
    }

    public TransitionResult handleAllStates(){
        if(state.equalsIgnoreCase(HAS_QUARTER)){
            return ejectQuarter();
        }
        else if(state.equalsIgnoreCase(NO_QUARTER)){
            return insertQuarter();
        }
        else if(state.equalsIgnoreCase(SOLD)){
            return dispense();
        }
        else if(state.equalsIgnoreCase(SOLD_OUT)){
            return new TransitionResult(false, "No gumball dispensed", state, count);
        }
        return new TransitionResult(false, "No gumball dispensed", state, count);

    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public String getTheStateName() {
        return null;
    }

    @Override
    public void releaseBall() {

    }



}
