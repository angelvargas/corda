package bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.*;

@InitiatedBy(TwoPartyFlow.class)
@StartableByRPC
public class TwoPartyFlowResponder extends FlowLogic<Void> {
    private FlowSession counterpartySession;

    public TwoPartyFlowResponder(FlowSession counterpartySession){
        this.counterpartySession = counterpartySession;
    }
    @Suspendable
    public Void call() throws FlowException {
        int receivedInt = counterpartySession.receive(Integer.class).unwrap(it->{
           if(it>3) throw new IllegalArgumentException("Number too high");
           return it;
        });
        int receivedIntPlusOne = receivedInt +1;

        counterpartySession.send(receivedIntPlusOne);

        return null;
    }
}
