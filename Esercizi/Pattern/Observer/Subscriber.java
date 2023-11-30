public class Subscriber implements Listener<Video>{

    @Override
    public void update(Video t) {
        System.out.println("New Video has just been published: " + t.title);
    }
    
}
