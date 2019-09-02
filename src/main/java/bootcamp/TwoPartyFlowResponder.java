package bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.flows.*;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.node.NodeInfo;
import net.corda.core.node.ServiceHub;

import java.util.List;

@InitiatedBy(TwoPartyFlow.class)
@StartableByRPC
public class TwoPartyFlowResponder extends FlowLogic<Void> {
    private FlowSession counterpartySession;

    public TwoPartyFlowResponder(FlowSession counterpartySession){
        this.counterpartySession = counterpartySession;
    }
    @Suspendable
    public Void call() throws FlowException {
        ServiceHub servicehub = getServiceHub();
        List<StateAndRef<HouseState>> statesFromVault = servicehub.getVaultService().queryBy(HouseState.class).getStates();

        CordaX500Name alicesName = new CordaX500Name("Alice", "Manchester", "UK");
        NodeInfo alice = servicehub.getNetworkMapCache().getNodeByLegalName(alicesName);

        int platformVersion = servicehub.getMyInfo().getPlatformVersion();

        int receivedInt = counterpartySession.receive(Integer.class).unwrap(it->{
           if(it>3) throw new IllegalArgumentException("Number too high");
           return it;
        });
        int receivedIntPlusOne = receivedInt +1;

        counterpartySession.send(receivedIntPlusOne);

        return null;
    }
}
