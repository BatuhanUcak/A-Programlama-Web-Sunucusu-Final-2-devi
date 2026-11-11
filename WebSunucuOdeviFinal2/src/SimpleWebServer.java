import java.io.*;
import java.net.*;

public class SimpleWebServer {
    private static final int PORT = 1989;

    public static void main(String[] args) {
        // Sunucu soketi belirtilen port üzerinden dinlemeye başlar
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Sunucu " + PORT + " portunda başarıyla başlatıldı.");
            System.out.println("Tarayıcınızdan http://localhost:" + PORT + " adresine gidebilirsiniz.");

            // Sunucu sürekli açık kalıp gelen istekleri dinler
            while (true) {
                Socket clientSocket = serverSocket.accept(); // İstemci bağlandığında kabul et

                // Gelen her isteği ayrı bir iş parçacığında (Thread) işle
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Sunucu başlatılırken bir hata oluştu: " + e.getMessage());
        }
    }
}

// İstemci İsteklerini İşleyen Sınıf (Single Responsibility Prensibi)
class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String requestLine = in.readLine();
            if (requestLine != null && requestLine.startsWith("GET")) {
                System.out.println("Gelen İstek: " + requestLine);

                // CSS ile biçimlendirilmiş HTML İçeriği
                String htmlResponse = "<html>" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<title>Batuhan Uçak - Web Sunucusu</title>" +
                        "<style>" +
                        "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f8f9fa; color: #333; margin: 50px; display: flex; flex-direction: column; align-items: center; }" +
                        ".container { background: #ffffff; padding: 30px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); max-width: 600px; width: 100%; }" +
                        "h1 { color: #2c3e50; border-bottom: 3px solid #3498db; padding-bottom: 10px; margin-bottom: 5px; text-align: center; }" +
                        "h2 { color: #16a085; font-weight: 500; font-size: 1.2em; text-align: center; margin-bottom: 20px; }" +
                        "p { line-height: 1.6; font-size: 16px; color: #555; }" +
                        ".highlight { color: #e74c3c; font-weight: bold; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Batuhan Uçak</h1>" +
                        "<h2>Öğrenci No: 5250505032</h2>" +
                        "<p>Merhaba, ben <span class=\"highlight\">Batuhan Uçak</span>. Kırklareli Üniversitesi'nde <strong>Yazılım Mühendisliği</strong> öğrencisiyim.</p>" +
                        "<p>Java ve C dilleri üzerinde projeler geliştirmeye çalışıyorum; Ders olarak veri yapıları, algoritmalar ve nesne yönelimli programlama üzerine yoğunlaşıyorum. Akademik hayatımın yanı sıra Voleybol ve Okul kulüpleriyle de ilgileniyorum.</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>";

                // HTTP başlıklarını (Header) oluşturuyoruz
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + htmlResponse.getBytes("UTF-8").length + "\r\n" +
                        "Connection: close\r\n\r\n" +
                        htmlResponse;

                // İstemciye (tarayıcıya) yanıtı gönder
                out.print(response);
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("İstemci işlenirken hata: " + e.getMessage());
        } finally {
            try {
                clientSocket.close(); // İşlem bitince bağlantıyı kapat
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}