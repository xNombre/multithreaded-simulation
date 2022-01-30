package simulation.simulation;

import java.util.ArrayList;

public class Helpline {
    private static final int OPERATORS_COUNT = 10;
    public static final Helpline INSTANCE = new Helpline();

    private ArrayList<Operator> operatorsList;
    private int currentOperator = 0;

    private Helpline() {
        operatorsList = new ArrayList<Operator>();
        for(int i = 0; i < OPERATORS_COUNT; i++) {
            operatorsList.add(new Operator(i));
        }
    }

    public static Helpline getInstance() {
        return INSTANCE;
    }

    public synchronized Operator getOperator() {
        if (currentOperator == OPERATORS_COUNT)
            currentOperator = 0;
        return operatorsList.get(currentOperator++);
    }
}
