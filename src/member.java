import java.util.ArrayList;

public class member {
    String name;

    ArrayList<message> newMessages; //список непрочитанных сообщений
    ArrayList<message> sendMessages; //отправленные сообщения
    ArrayList<message> Messages; //сообщения

    ArrayList<member> newFriends; //заявки в друзья
    ArrayList<member> Friends; //друзья

    member(String name) {
        this.name = name;

        newMessages = new ArrayList<message>();
        sendMessages = new ArrayList<message>();
        Messages = new ArrayList<message>();

        newFriends = new ArrayList<member>();
        Friends = new ArrayList<member>();
    }

    public void sentMessage(member toWho, String text) { //отправить сообщение
        message newMessage = new message(toWho, this, text);
        toWho.newMessage(newMessage);
        sendMessages.add(newMessage);
    }

    public void newMessage(message mess) {
        newMessages.add(mess);
    }

    public void readMessage(message mess) { //прочитать сообщение из списка новых сообщений
        if (newMessages.contains(mess)) {
            newMessages.remove(mess);
            Messages.add(mess);
        }
    }

    public void newFriend(member newFriend) {
        newFriends.add(newFriend);
    }

    public void addFriend(member newFriend) { //добавить в друзья из списка заявок либо кинуть заявку
        if(newFriends.contains(newFriend)) {
            newFriends.remove(newFriend);
            Friends.add(newFriend);
        }
        else {
            this.newFriend(newFriend);
            newFriend.newFriend(this);
        }
    }

    public void deleteFriend(member Friend) { //удалить друга
        if(Friends.contains(Friend)) {
            Friends.remove(Friend);
            Friend.deleteFriend(Friend);
        }
    }

    public void deleteNewFriend(member newFriend) { //удалить человека из списка заявок
        if(newFriends.contains(newFriend)) {
            newFriends.remove(newFriend);
            newFriend.deleteNewFriend(this);
        }
    }
}
