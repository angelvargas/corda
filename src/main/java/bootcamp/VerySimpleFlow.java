package bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;

@InitiatingFlow
@StartableByRPC
public class VerySimpleFlow extends FlowLogic<Integer> {

    @Suspendable
    public Integer call() throws FlowException{
        int a = returnOne();
        int b = 2;
        return a+b;
    }
    public Integer returnOne(){
        return 1;
    }
}
