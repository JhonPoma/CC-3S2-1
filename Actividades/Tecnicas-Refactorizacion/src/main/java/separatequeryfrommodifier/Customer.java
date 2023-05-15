package separatequeryfrommodifier;

// Antes
/*
public class Customer {
    int totalOutstanding;
    boolean readyForSummaries;

    public int getTotalOutstandingAndSetReadyForSummaries(boolean readyForSummaries) {
        this.readyForSummaries = readyForSummaries;
        return totalOutstanding;
    }
}
*/

// Después
public class Customer {
    int totalOutstanding;
    boolean readyForSummaries;

    public int getTotalOutstanding() {
        return totalOutstanding;
    }

    public void setReadyForSummaries(boolean readyForSummaries) {
        this.readyForSummaries = readyForSummaries;
    }
}

