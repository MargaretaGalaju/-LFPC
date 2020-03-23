import java.io.IOException;

public class NfaToDfa {
    public static void main(String[] args)
            throws IOException {
        buildAndShow("dfa.dot");
    }

    public static void buildAndShow(String filename)
            throws IOException {

        if(filename == "dfa.dot"){
            Nfa nfa = new Nfa(0,2);
            nfa.addTrans(0,"A",1);
            nfa.addTrans(0,"A",0);
            nfa.addTrans(1,"B",2);
            nfa.addTrans(0,"B",0);
            nfa.addTrans(1,"B",1);
            nfa.addTrans(2,"B",2);
            System.out.println(nfa);
            Dfa dfa = nfa.toDfa();
            System.out.println(dfa);
            System.out.println("Writing DFA graph to file " + filename);
            dfa.writeDot(filename);
            System.out.println();}

    }
}
