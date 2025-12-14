package FreeTask.static_final;

public class ActiveSession {

    private static int activeSession = 0;
    private boolean isClose = true;

    public ActiveSession() {
        isClose = false;
        activeSession++;
    }

    public static int getActiveSession() {
        return activeSession;
    }
    public  void close() {
        if (!isClose) {
            activeSession--;
            isClose = true;
        }
    }
}
