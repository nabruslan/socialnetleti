import java.util.ArrayList;

public class socialnet {
    String name;
    ArrayList<member> Members;

    socialnet(String name) {
        this.name = name;
        Members = new ArrayList<member>();
    }

    public void addNewMember(String name) {
        Members.add(new member(name));
    }

    public member searchMember(String name) {
        member searchingMember = new member(name);
        return Members.get(Members.indexOf(searchingMember));
    }
}
