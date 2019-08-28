package bootcamp;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;

/* Our contract, governing how our state will evolve over time.
 * See src/main/java/examples/ArtContract.java for an example. */
public class TokenContract implements Contract {
    public static String ID = "bootcamp.TokenContract";

    @Override
    public void verify(LedgerTransaction tx) throws IllegalArgumentException {
        if(tx.getInputStates().size()!=0)
            throw new IllegalArgumentException("Transaction must have zero inputs.");
        if(tx.getOutputStates().size()!=1)
            throw new IllegalArgumentException("Transaction must have one inputs.");
    }


    public interface Commands extends CommandData {
        class Issue implements Commands { }
    }
}