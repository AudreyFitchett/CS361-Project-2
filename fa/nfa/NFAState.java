package fa.nfa;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
public class NFAState extends fa.State{
    private Map<Character, Set<NFAState>> subDelta;
    public NFAState(String name) {
		super(name);
        subDelta = new LinkedHashMap<>();
	}

    //method to add transition states
    public Set<NFAState> addSubDelta(char c, Set<NFAState> set){
        return subDelta.put(c, set);
    }

    public Map<Character, Set<NFAState>> getSubDelta(){
        return new LinkedHashMap<>(subDelta);
    }
}
