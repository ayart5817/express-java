package FreeTask.static_final;

public class ImmutableAccessToken {
   final private String accessToken;

    public ImmutableAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getAccessToken() {
        return this.accessToken;
    }
}
