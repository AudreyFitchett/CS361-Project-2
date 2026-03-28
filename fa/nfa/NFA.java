package fa.nfa;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import fa.State;

public class NFA implements NFAInterface {

    private Set<NFAState> states;
	private Set<Character> sigma;
	private Map<NFAState, Map<Character, Set<NFAState>>> delta;
	private NFAState startState;
	private Set<NFAState> finalStates;

    public NFA(){
        states = new LinkedHashSet<>();
		sigma = new LinkedHashSet<>();
		delta = new LinkedHashMap<>();
		startState = null;
		finalStates = new LinkedHashSet<>();
    }

    @Override
    public boolean addState(String name) {
        if (name == null) return false;
		for (NFAState s :states){
			if(name.equals(s.getName())) return false;

		}
		NFAState ns = new NFAState(name);
		boolean isAdded = states.add(ns);
		if (isAdded) delta.put(ns,ns.getSubDelta());
		return isAdded;
    }

    @Override
    public boolean setFinal(String name) {
        State s = getState(name);
		if (s == null) return false;
		return finalStates.add((NFAState) s);
    }

    @Override
    public boolean setStart(String name) {
        State s = getState(name);
        if(s == null){
            return false;
        }
        startState = (NFAState) s;
        return true;
    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
        return;
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        return new LinkedHashSet<>(sigma);
    }

    @Override
    public State getState(String name) {
        if (name == null) return null; //if name provided empty, return null
		for (NFAState s: states){
			if (name.equals(s.getName())) return s; // return found state

		}
		return null; // if no matching state found
    }

    @Override
    public boolean isFinal(String name) {
        for(NFAState s: finalStates){
            if(name.equals(s.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        if(name.equals(startState.getName())){
            return true;
        }
        return false;
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToState'");
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eClosure'");
    }

    @Override
    public int maxCopies(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxCopies'");
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        //Get the states
        State from = getState(fromState);

        Set<NFAState> to = new LinkedHashSet<>();

        for(String s: toStates){
            State state = getState(s);
            to.add((NFAState) state);
        }

        //Check if states exist
        if(to == null || from == null){
            return false;
        }

        //check if symbol is a part of sigma
        if(!sigma.contains(onSymb)){
            return false;
        }

        
        //I don't think this is needed? 
		if (delta.get(from) == null) return false; 

        //add to nfastate map
        NFAState fromNFA = (NFAState) from;
        fromNFA.addSubDelta(onSymb, to);

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }

    @Override
    public boolean isDFA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDFA'");
    }
    
}
