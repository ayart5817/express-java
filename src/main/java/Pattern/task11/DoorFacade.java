package Pattern.task11;

public class DoorFacade {
    public final DoorOpener opener = new DoorOpener();
    public final DoorCloser closer = new DoorCloser();
    public final DoorLocker locker = new DoorLocker();
    public final DoorUnLocker unlocker = new DoorUnLocker();


    public void openDoor() {
        opener.open();
    }

    public void closeDoor() {
        closer.close();
    }

    public void lockDoor() {
        locker.lock();
    }
    public void unlockDor() {
        unlocker.unlock();
    }
}
