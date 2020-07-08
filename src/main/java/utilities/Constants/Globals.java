package utilities.Constants;

public interface Globals {

    int GLOBAL_TIMEOUT = 30;
    int PAGE_LOAD_TIMEOUT = 3;

    interface PLATFORM {
        boolean IS_ANDROID_DEVICE = ThreadVariables.platformValue.get().equalsIgnoreCase("ANDROID");
        boolean IS_IOS_DEVICE = ThreadVariables.platformValue.get().equalsIgnoreCase("IOS");
    }

}
