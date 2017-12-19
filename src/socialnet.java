import java.lang.reflect.Member;
import java.sql.*;
import java.util.*;
import java.util.Comparator;

public class socialnet {
    String name;
    ArrayList<member> Members;

    socialnet(String name) {
        this.name = name;
        Members = new ArrayList<member>();
    }

    public void addNewMember(String name) {
        Members.add(new member(name, this));
    }

    public member searchMember(String name) {
        member searchingMember = new member(name, this);
        return Members.get(Members.indexOf(searchingMember));
    }

    public void makeRate(member Member) {
        ArrayList<message> Messages = Member.getMessages();

        String allMessages = "";

        for(int i = 0; i < Messages.size(); i++) {
            allMessages = Messages.get(i) + " " + allMessages;
        }

        List<String> words = Arrays.asList(allMessages.split("[,;:.!?\\s]+"));
        Map<Integer, String> popularWord = new HashMap<Integer, String>();

        for(int i = 0; i < words.size(); i++) {
            if(!popularWord.containsValue(words.get(i)))
                popularWord.put(Collections.frequency(words, words.get(i)), words.get(i));
        }

        popularWord = new TreeMap<Integer, String>(
                new Comparator<Integer>() {

                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                });

        for(int i = 0; i < 10 && i < popularWord.size(); i++) {
            for(int j = 0; j < Messages.size(); j++)
                if(Messages.get(j).getText().contains(popularWord.get(i)))
                    Messages.get(j).setRate(10 - i);
                else
                    Messages.get(j).setRate(0);
        }

    }

    public void downloadMembers() {
        String url = "jdbc:mysql://localhost:3306/members";
        String nameOfUser = "ruslan";
        String password = "1234";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, nameOfUser, password);
            Statement state = connection.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM allmembers");
            while(result.next()) {
                addNewMember(result.getString(1));
            }
            result.close();
            state.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}