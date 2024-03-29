package bootcamp;

import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.serialization.CordaSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/* Our state, defining a shared fact on the ledger.
 * See src/main/java/examples/ArtState.java for an example. */
@BelongsToContract(bootcamp.TokenContract.class)
//@CordaSerializable
public class TokenState implements ContractState{
    private Party issuer;
    private Party owner;
    private int amount;
    List<AbstractParty> participants;
    public TokenState(Party issuer, Party owner, int amount ){
        this.issuer = issuer;
        this.owner = owner;
        this.amount = amount;
        this.participants = new ArrayList<>();
        participants.add(issuer);
        participants.add(owner);
    }

    public Party getIssuer() {
        return issuer;
    }

    public Party getOwner() {
        return owner;
    }

    public int getAmount() {
        return amount;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return participants;
    }
    //flow start TokenIssueFlow owner: PartyB, amount: 99
    //run vaultQuery contractStateType:bootcamp.TokenState
}