package ComplexTask.task1;

public class Main {
    public static void main(String[] args) {

        System.out.println("====== Кодируем с BASE62 ======");
        UrlShortenerService shortenerService = new UrlShortenerService(new Base62Strategy());
        String url1 = "https://example.com/very/long/url";
        String shortUrl1 = shortenerService.shortenUrl(url1);
        System.out.println("Short URL: " + shortUrl1);

        String longUrl = shortenerService.expandUrl(shortUrl1);
        System.out.println("Original URL: " + longUrl);

        System.out.println("====== Кодируем с UUID ======");
        shortenerService = new UrlShortenerService(new UUIDStrategy());
        String url2 = "https://example.com/very/long/url";
        String shortUrl2 = shortenerService.shortenUrl(url2);
        System.out.println("Short URL: " + shortUrl2);

        String longUrl2 = shortenerService.expandUrl(shortUrl2);
        System.out.println("Original URL: " + longUrl2);

        System.out.println("====== Кодируем хеш ======");
        shortenerService = new UrlShortenerService(new HashStrategy());
        String url3 = "https://example.com/very/long/url";
        String shortUrl3 = shortenerService.shortenUrl(url3);
        System.out.println("Short URL: " + shortUrl3);

        System.out.println("====== Востанавливаем ======");
        String longUrl3 = shortenerService.expandUrl(shortUrl3);
        System.out.println("Original URL: " + longUrl3);

        System.out.println("====== Востанавливаем несуществующий ======");
        System.out.println(shortenerService.expandUrl("ELFUYH"));

    }
}
