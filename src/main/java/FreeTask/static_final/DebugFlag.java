package FreeTask.static_final;

public class DebugFlag {
     private static boolean isDebugMenu = false;

    public DebugFlag() {};

    public static void setIsDebagMenu(boolean isDebugMode) {
        isDebugMenu = isDebugMode;

    }
    public static boolean isIsDebugMenu() {
        return isDebugMenu;
    }


    static void main() {
        System.out.println(DebugFlag.isIsDebugMenu());
        DebugFlag.setIsDebagMenu(true);
        System.out.println(DebugFlag.isIsDebugMenu());
    }
}
