import java.util.ArrayList;

public class YoutubeChannel {
    
    ArrayList<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void newVideo() {
        Video v = new Video();
        v.title = "Observer Pattern Explained";
        notifyAll(v);
    }

    public void notifyAll(Video v) {
        subscribers.forEach(s -> s.update(v));
    }

    public static void main(String[] args) {
        YoutubeChannel javaProgramming = new YoutubeChannel();

        Subscriber s1 = new Subscriber();
        Subscriber s2 = new Subscriber();

        javaProgramming.subscribe(s1);
        javaProgramming.subscribe(s2);

        javaProgramming.newVideo();

    }

}
