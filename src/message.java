public class message {
    member toWho;
    member fromWho;
    String text;
    int rate;

    message(member toWho, member fromWho, String text) {
        this.toWho = toWho;
        this.fromWho = fromWho;
        this.text = text;
        rate = 0;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getText() {
        return text;
    }
}
