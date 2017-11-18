public class message {
    member toWho;
    member fromWho;
    String text;

    message(member toWho, member fromWho, String text) {
        this.toWho = toWho;
        this.fromWho = fromWho;
        this.text = text;
    }
}
