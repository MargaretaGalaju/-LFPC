import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

public class Dfa {
        private Integer startState;
        private Set<Integer> acceptStates;
        private Map<Integer, Map<String,Integer>> trans;

        public Dfa(Integer startState,
                   Set<Integer> acceptStates,
                   Map<Integer,Map<String,Integer>> trans) {
            this.startState = startState;
            this.acceptStates = acceptStates;
            this.trans = trans;
        }

        public Integer getStart() { return startState; }

        public Set<Integer> getAccept() { return acceptStates; }

        public Map<Integer,Map<String,Integer>> getTrans() { return trans; }

        public String toString() {
            return "DFA start=" + startState + "\naccept=" + acceptStates
                    + "\n" + trans;
        }

        public void writeDot(String filename) throws IOException {
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            // Accept states are double circles
            for (Integer state : trans.keySet())
                if (acceptStates.contains(state))
                    out.println("n" + state + " [peripheries=2];");

            // The transitions
            for (Map.Entry<Integer,Map<String,Integer>> entry : trans.entrySet()) {
                Integer s1 = entry.getKey();
                for (Map.Entry<String,Integer> s1Trans : entry.getValue().entrySet()) {
                    String lab = s1Trans.getKey();
                    Integer s2 = s1Trans.getValue();
                    out.println("n" + s1 + " -> n" + s2 + " [label=" + lab + "];");
                }
            }
            out.println("}");
            out.close();
        }
}

